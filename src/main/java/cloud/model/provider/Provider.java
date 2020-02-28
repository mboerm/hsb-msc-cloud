package cloud.model.provider;

import cloud.model.pricing.Pricing;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Objects;

/**
 * Abstract provider class
 */
public abstract class Provider implements Pricing {
    private String serviceName;
    private String serviceFile;
    private Document doc;

    public String getServiceName() {
        return this.serviceName;
    }
    void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceFile() {return serviceFile;}
    public void setServiceFile(String serviceFile) {
        this.serviceFile = serviceFile;
    }

    /**
     * Get matching service of provider for generic service id
     * @param id generic service identifier
     * @return name of matching service
     */
    public String getMatchingServiceForID(String id) {
        setDocument(serviceFile);
        NodeList servicesNodeList = doc.getElementsByTagName("service");
        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);
            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;
                if (id.equalsIgnoreCase(serviceElement.getAttribute("id"))) {
                    return serviceElement.getAttribute("name");
                }
            }
        }
        return "";
    }

    protected Document getDocument() {return doc;}
    protected void setDocument(String fileName) {
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
