package cloud.report;

import cloud.configuration.*;
import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.services.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import javafx.util.Pair;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFont;

public class PDFReport implements IReport {
    private String dest;
    private Document document;
    private Date date;
    private PdfFont bf12Bold;

    public PDFReport() {
        try {
            bf12Bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        } catch (IOException e) {
            System.out.println("Creating fonts failed" + e);
        }

        date = Calendar.getInstance().getTime();
        dest = "report_" + Constants.DATE_FORMAT_FILE.format(date) + ".pdf";
    }

    @Override
    public void createReport() {
        createDocument();
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
        document.add(new Paragraph(Constants.DATE_FORMAT_TITLE.format(date)).setFont(bf12Bold));
        document.add(addEmptyLine(new Paragraph(), 1));
        document.add(new Paragraph("Cost Calculation Report").setFont(bf12Bold).setFontSize(16));
        document.add(addEmptyLine(new Paragraph(), 1));
    }

    private void writeChapterSummary() {
        document.add(new Paragraph("Summary").setFont(bf12Bold).setFontSize(14));

        String[] summaryLabels = Config.getInstance().getConfigValuesAsArray("design-property-labels");
        float[] columnWidths = {150F, 150F};
        Table summaryTable = new Table(columnWidths);
        summaryTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

        insertCell(summaryTable, summaryLabels[0], TextAlignment.LEFT);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getProvider().getServiceName(), TextAlignment.CENTER);
        insertCell(summaryTable, summaryLabels[1], TextAlignment.LEFT);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getPrimaryRegion(), TextAlignment.CENTER);
        insertCell(summaryTable, summaryLabels[2], TextAlignment.LEFT);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getUsagePeriod(), TextAlignment.CENTER);
        insertCell(summaryTable, summaryLabels[3], TextAlignment.LEFT);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getNumOfInstances().toString(), TextAlignment.CENTER);
        insertCell(summaryTable, summaryLabels[4], TextAlignment.LEFT);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getNumOfRequests().toString(), TextAlignment.CENTER);
        insertCell(summaryTable, summaryLabels[5], TextAlignment.LEFT);
        insertCell(summaryTable, DesignManager.getInstance().getDesign().getNumOfCapacity().toString(), TextAlignment.CENTER);

        document.add(summaryTable);
    }

    private void writeChapterServices() {
        document.add(addEmptyLine(new Paragraph(), 1));
        document.add(new Paragraph("Services").setFont(bf12Bold).setFontSize(14));

        List servicesList = new List().setSymbolIndent(12).setListSymbol("\u2022");
        for (Service service : DesignManager.getInstance().getDesign().getServicesList()) {
            servicesList.add(new ListItem(service.getName() + " - " + service.getProviderService()));
        }
        document.add(servicesList);

        float[] columnWidths = {150F, 150F};
        Table propertiesTable;
        Table usageTable;
        String[] serviceGeneralLabels = Config.getInstance().getConfigValuesAsArray("service-general-labels");
        String[] serviceUsageLabels = Config.getInstance().getConfigValuesAsArray("service-usage-labels");
        document.add(addEmptyLine(new Paragraph(), 1));
        for (Service service : DesignManager.getInstance().getDesign().getServicesList()) {
            document.add(new Paragraph(service.getName()).setFont(bf12Bold));

            document.add(new Paragraph("Properties"));
            propertiesTable = new Table(columnWidths);
            propertiesTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
            for (int i = 0; i < serviceGeneralLabels.length; i++) {
                insertCell(propertiesTable, serviceGeneralLabels[i], TextAlignment.LEFT);
                insertCell(propertiesTable, service.getGeneralProperties()[i], TextAlignment.CENTER);
            }
            document.add(propertiesTable);

            document.add(new Paragraph("Usage"));
            usageTable = new Table(columnWidths);
            usageTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
            for (int i = 0; i < serviceUsageLabels.length; i++) {
                insertCell(usageTable, serviceUsageLabels[i], TextAlignment.LEFT);
                insertCell(usageTable, service.getUsageProperties()[i], TextAlignment.CENTER);
            }
            document.add(usageTable);
        }
    }

    private void writeChapterCostCalc() {
        document.add(new AreaBreak());
        document.add(new Paragraph("Cost Calculation").setFont(bf12Bold).setFontSize(14));

        document.add(new Paragraph("Service costs"));
        float[] columnWidths = {100F, 100F};
        Table serviceCostsTable = new Table(columnWidths);
        serviceCostsTable.setHorizontalAlignment(HorizontalAlignment.CENTER);

        for (Pair<Service, Costs> designCost : DesignManager.getInstance().getDesign().getServicesCosts()) {
            insertCell(serviceCostsTable, designCost.getKey().getName(), TextAlignment.LEFT);
            insertCell(serviceCostsTable, Constants.DOUBLE_FORMAT_2.format(designCost.getValue().getPrice()) + " USD", TextAlignment.RIGHT);
        }
        document.add(serviceCostsTable);

        document.add(new Paragraph("Total costs"));
        Table totalCostsTable = new Table(columnWidths);
        totalCostsTable.setHorizontalAlignment(HorizontalAlignment.CENTER);
        insertCell(totalCostsTable, "Per Hour:", TextAlignment.LEFT);
        insertCell(totalCostsTable, Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCostsPerHour()) + " USD", TextAlignment.RIGHT);
        insertCell(totalCostsTable, "Per Day:", TextAlignment.LEFT);
        insertCell(totalCostsTable, Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCostsPerDay()) + " USD", TextAlignment.RIGHT);
        insertCell(totalCostsTable, "Per Month:", TextAlignment.LEFT);
        insertCell(totalCostsTable, Constants.DOUBLE_FORMAT_2.format(DesignManager.getInstance().getDesign().getTotalCosts()) + " USD", TextAlignment.RIGHT);
        document.add(totalCostsTable);
    }

    private void writeChapterCostScale() {
        document.add(addEmptyLine(new Paragraph(), 1));
        document.add(new Paragraph("Cost Scaling").setFont(bf12Bold).setFontSize(14));
    }

    private void writeChapterCostOptimize() {
        document.add(addEmptyLine(new Paragraph(), 1));
        document.add(new Paragraph("Cost Optimization").setFont(bf12Bold).setFontSize(14));
    }

    private void writeChapterCostCompare() {
        document.add(addEmptyLine(new Paragraph(), 1));
        document.add(new Paragraph("Cost Comparison").setFont(bf12Bold).setFontSize(14));
    }

    private void createDocument() {
        try {
            // Creating a PdfWriter for destination
            PdfWriter writer = new PdfWriter(dest);

            // Creating a PdfDocument
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Adding a new page
            pdfDoc.addNewPage();

            // Creating a Document
            document = new Document(pdfDoc);
        } catch (FileNotFoundException e) {
            System.err.println("Creating report failed! " + e);
        }
    }

    private void closeReport() {
        document.close();
        System.out.println("Report completed!");
    }

    private static void insertCell(Table table, String text, TextAlignment align, boolean... border){
        //create a new cell with the specified Text
        Cell cell = new Cell().add(new Paragraph(text.trim()));
        //set border
        if (border.length == 0) {
            cell.setBorder(Border.NO_BORDER);
        }
        //set the cell alignment
        cell.setTextAlignment(align);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
            cell.setMinHeight(10f);
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
