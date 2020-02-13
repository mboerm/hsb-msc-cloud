package cloud.report;

import cloud.configuration.*;
import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.services.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import javafx.util.Pair;

/* iText */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Report {
    private Document document;
    private PdfWriter writer;
    private Date date = Calendar.getInstance().getTime();

    Font bf12 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.NORMAL);
    Font bf12Bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
    Font bf14Bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
    Font bf16Bold = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, Font.BOLD);

    public Report() {
        createReport();
        writeHeader();

        writeChapterSummary();
        writeChapterServices();
        writeChapterCostCalc();
        writeChapterCostScale();
        writeChapterCostOptimize();
        writeChapterCostCompare();

        closeReport();
    }

    private void writeHeader() {
        try {
            document.add(new Paragraph(Constants.DATE_FORMAT_TITLE.format(date), bf12Bold));
            document.add(addEmptyLine(new Paragraph(), 1));
            document.add(new Paragraph("Cost Calculation Report", bf16Bold));
            document.add(addEmptyLine(new Paragraph(), 1));
        } catch (DocumentException e) {
            System.err.println("Adding header to document failed! " + e);
        }
    }

    private void writeChapterSummary() {
        Paragraph summaryTitle = new Paragraph("Summary", bf14Bold);

        float[] columnWidths = {1f, 1f};
        PdfPTable summaryTable = new PdfPTable(columnWidths);
        summaryTable.setWidthPercentage(75f);
        summaryTable.setSpacingBefore(10f);
        summaryTable.setSpacingAfter(10f);

        String[] summaryLabels = Config.getInstance().getConfigValuesAsArray("design-property-labels");

        insertCell(summaryTable, summaryLabels[0], Element.ALIGN_LEFT, bf12);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getProvider().getServiceName(), Element.ALIGN_CENTER, bf12);
        insertCell(summaryTable, summaryLabels[1], Element.ALIGN_LEFT, bf12);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getPrimaryRegion(), Element.ALIGN_CENTER, bf12);
        insertCell(summaryTable, summaryLabels[2], Element.ALIGN_LEFT, bf12);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getUsagePeriod(), Element.ALIGN_CENTER, bf12);
        insertCell(summaryTable, summaryLabels[3], Element.ALIGN_LEFT, bf12);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getNumOfInstances().toString(), Element.ALIGN_CENTER, bf12);
        insertCell(summaryTable, summaryLabels[4], Element.ALIGN_LEFT, bf12);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getNumOfRequests().toString(), Element.ALIGN_CENTER, bf12);
        insertCell(summaryTable, summaryLabels[5], Element.ALIGN_LEFT, bf12);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getNumOfCapacity().toString(), Element.ALIGN_CENTER, bf12);

        try {
            document.add(summaryTitle);
            document.add(summaryTable);
        } catch (DocumentException e) {
            System.err.println("Adding summary chapter to document failed! " + e);
        }
    }

    private void writeChapterServices() {
        Chapter servicesChapter = new Chapter(new Paragraph("Services"), 1);

        List servicesList = new List();
        for (Service service : DesignManager.getInstance().getDesign().getServicesList()) {
            servicesList.add(service.getName() + " - " + service.getProviderService());
        }
        servicesChapter.add(servicesList);

        float[] columnWidths = {1f, 1f};
        PdfPTable propertiesTable;
        PdfPTable usageTable;
        String[] serviceGeneralLabels = Config.getInstance().getConfigValuesAsArray("service-general-labels");
        String[] serviceUsageLabels = Config.getInstance().getConfigValuesAsArray("service-usage-labels");

        for (Service service : DesignManager.getInstance().getDesign().getServicesList()) {
            Section serviceSection = servicesChapter.addSection(new Paragraph(service.getName()));

            Section servicePropertiesSection = serviceSection.addSection(new Paragraph("Properties"));
            propertiesTable = new PdfPTable(columnWidths);
            propertiesTable.setWidthPercentage(75f);
            propertiesTable.setSpacingBefore(10f);
            propertiesTable.setSpacingAfter(10f);
            for (int i = 0; i < serviceGeneralLabels.length; i++) {
                insertCell(propertiesTable, serviceGeneralLabels[i], Element.ALIGN_LEFT, bf12);
                insertCell(propertiesTable, service.getGeneralProperties()[i], Element.ALIGN_CENTER, bf12);
            }
            servicePropertiesSection.add(propertiesTable);

            Section serviceUsageSection = serviceSection.addSection(new Paragraph("Usage"));
            usageTable = new PdfPTable(columnWidths);
            usageTable.setWidthPercentage(75f);
            usageTable.setSpacingBefore(10f);
            usageTable.setSpacingAfter(10f);
            for (int i = 0; i < serviceUsageLabels.length; i++) {
                insertCell(usageTable, serviceUsageLabels[i], Element.ALIGN_LEFT, bf12);
                insertCell(usageTable, service.getUsageProperties()[i], Element.ALIGN_CENTER, bf12);
            }
            serviceUsageSection.add(usageTable);
        }

        try {
            document.add(servicesChapter);
        } catch (DocumentException e) {
            System.err.println("Adding services chapter to document failed! " + e);
        }
    }

    private void writeChapterCostCalc() {
        Chapter costCalcChapter = new Chapter(new Paragraph("Cost Calculation"), 2);

        Section serviceCostsSection = costCalcChapter.addSection(new Paragraph("Service costs"));
        float[] columnWidths = {1f, 1f};
        PdfPTable serviceCostsTable = new PdfPTable(columnWidths);
        serviceCostsTable.setWidthPercentage(50f);
        serviceCostsTable.setSpacingBefore(10f);
        serviceCostsTable.setSpacingAfter(10f);

        for (Pair<Service, Costs> designCost : DesignManager.getInstance().getDesign().getServicesCosts()) {
            insertCell(serviceCostsTable, designCost.getKey().getName(), Element.ALIGN_LEFT, bf12);
            insertCell(serviceCostsTable, Constants.DOUBLE_FORMAT_2.format(designCost.getValue().getPrice()) + " USD", Element.ALIGN_RIGHT, bf12);
        }
        serviceCostsSection.add(serviceCostsTable);

        Section totalCostsSection = costCalcChapter.addSection(new Paragraph("Total costs"));
        PdfPTable totalCostsTable = new PdfPTable(columnWidths);
        totalCostsTable.setWidthPercentage(50f);
        totalCostsTable.setSpacingBefore(10f);
        totalCostsTable.setSpacingAfter(10f);
        insertCell(totalCostsTable, "Per Hour:", Element.ALIGN_LEFT, bf12);
        insertCell(totalCostsTable, Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCostsPerHour()) + " USD", Element.ALIGN_RIGHT, bf12);
        insertCell(totalCostsTable, "Per Day:", Element.ALIGN_LEFT, bf12);
        insertCell(totalCostsTable, Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCostsPerDay()) + " USD", Element.ALIGN_RIGHT, bf12);
        insertCell(totalCostsTable, "Per Month:", Element.ALIGN_LEFT, bf12);
        insertCell(totalCostsTable, Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCosts()) + " USD", Element.ALIGN_RIGHT, bf12);
        totalCostsSection.add(totalCostsTable);

        try {
            document.add(costCalcChapter);
        } catch (DocumentException e) {
            System.err.println("Adding cost calculation chapter to document failed! " + e);
        }
    }

    private void writeChapterCostScale() {
        Chapter costScaleChapter = new Chapter(new Paragraph("Cost Scaling"), 3);
        try {
            document.add(costScaleChapter);
        } catch (DocumentException e) {
            System.err.println("Adding cost scaling chapter to document failed! " + e);
        }
    }

    private void writeChapterCostOptimize() {
        Chapter costOptimizeChapter = new Chapter(new Paragraph("Cost Optimization"), 4);
        try {
            document.add(costOptimizeChapter);
        } catch (DocumentException e) {
            System.err.println("Adding cost optimization chapter to document failed! " + e);
        }
    }

    private void writeChapterCostCompare() {
        Chapter costCompareChapter = new Chapter(new Paragraph("Cost Comparison"), 5);
        try {
            document.add(costCompareChapter);
        } catch (DocumentException e) {
            System.err.println("Adding cost comparison chapter to document failed! " + e);
        }
    }

    private void createReport() {
        try {
            document = new Document();
            writer = PdfWriter.getInstance(document,
                    new FileOutputStream("report_" + Constants.DATE_FORMAT_FILE.format(date) + ".pdf"));
            document.open();
        } catch (DocumentException | FileNotFoundException e) {
            System.err.println("Creating report failed! " + e);
        }
    }
    private void closeReport() {
        document.close();
        writer.close();
        System.out.println("Report completed!");
    }

    private static void insertCell(PdfPTable table, String text, int align, Font font, boolean... border){
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set border
        if (border.length == 0) {
            cell.setBorder(Rectangle.NO_BORDER);
        }
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(1);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
    }

    private static Paragraph addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
        return paragraph;
    }
}
