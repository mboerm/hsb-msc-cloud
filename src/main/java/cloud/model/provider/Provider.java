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
    private String servicesFile;
    private String freeFile;
    private Document doc;

    public String getServiceName() {
        return this.serviceName;
    }
    void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicesFile() {return servicesFile;}
    public void setServicesFile(String servicesFile) {
        this.servicesFile = servicesFile;
    }

    public String getFreeFile() {
        return freeFile;
    }
    public void setFreeFile(String freeFile) {
        this.freeFile = freeFile;
    }

    public String getMatchingServiceForID(String id) {
        setDocument(servicesFile);

        NodeList servicesNodeList = doc.getElementsByTagName("service");
        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);
            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;
                if (id.equalsIgnoreCase(serviceElement.getAttribute("id"))) {
                    return serviceElement.getAttribute("service");
                }
            }
        }
        return "";
    }

    protected Document getDocument() {return doc;}
    protected void setDocument(String fileName) {
        try {
            File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
