package cloud.model.provider;

import cloud.configuration.Config;
import cloud.model.pricing.IPricing;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public abstract class Provider implements IPricing {

    private String serviceName;
    private String serviceShortName;
    private String priceFile;
    private String freeFile;
    private Document doc;

    public Provider() {
        setServicesDocument();
    }

    public String getServiceName() {
        return this.serviceName;
    }
    void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }
    public void setServiceShortName(String serviceShortName) {
        this.serviceShortName = serviceShortName;
    }

    public String getPriceFile() {
        return priceFile;
    }
    public void setPriceFile(String priceFile) {
        this.priceFile = priceFile;
    }

    public String getFreeFile() {
        return freeFile;
    }
    public void setFreeFile(String freeFile) {
        this.freeFile = freeFile;
    }

    public String getMatchingServiceForName(String nameValue) {
        NodeList servicesNodeList = doc.getElementsByTagName("service");

        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);
            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;
                if (nameValue.equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                    return serviceElement.getElementsByTagName(serviceShortName).item(0).getTextContent();
                }
            }
        }
        return "";
    }

    private void setServicesDocument() {
        String servicesFile = Config.getInstance().getConfigValue("services-file");
        try {
            File file = new File(getClass().getClassLoader().getResource(servicesFile).getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
