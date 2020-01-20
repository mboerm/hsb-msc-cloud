package cloud.model.provider;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import cloud.configuration.Config;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ProviderServices {
    private Document doc;
    private String servicesFileName;

    private static ProviderServices instance;

    private ProviderServices() {
        servicesFileName = Config.getInstance().getConfigValue("services-file");
        setServicesDocument();
    }

    public static ProviderServices getInstance() {
        if (instance == null) {
            instance = new ProviderServices();
        }
        return instance;
    }

    private void setServicesDocument() {
        try {
            File servicesFile = new File(getClass().getClassLoader().getResource(servicesFileName).getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(servicesFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getServices() {
        try {
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList servicesNodeList = doc.getElementsByTagName("service");
            System.out.println("----------------------------");


            for (int i = 0; i < servicesNodeList.getLength(); i++) {
                Node serviceNode = servicesNodeList.item(i);
                System.out.println("\nCurrent Element :" + serviceNode.getNodeName());

                if (serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element serviceElement = (Element) serviceNode;

                    String serviceName = serviceElement.getAttribute("name");
                    String serviceAWS = serviceElement.getElementsByTagName("aws").item(0).getTextContent();
                    String serviceAzure = serviceElement.getElementsByTagName("azure").item(0).getTextContent();
                    String serviceGCP = serviceElement.getElementsByTagName("gcp").item(0).getTextContent();

                    System.out.println("Name : " + serviceName);
                    System.out.println("AWS : " + serviceAWS);
                    System.out.println("Azure : " + serviceAzure);
                    System.out.println("GCP : " + serviceGCP);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getServiceByName(String nameValue) {
        NodeList servicesNodeList = doc.getElementsByTagName("service");

        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);

            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;

                if(nameValue.equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                    String serviceAWS = serviceElement.getElementsByTagName("aws").item(0).getTextContent();
                    String serviceAzure = serviceElement.getElementsByTagName("azure").item(0).getTextContent();
                    String serviceGCP = serviceElement.getElementsByTagName("gcp").item(0).getTextContent();

                    return new String[] {nameValue, serviceAWS, serviceAzure, serviceGCP};
                }
            }
        }
        return null;
    }

    public String getProviderServiceForName(String nameValue, String provider) {
        NodeList servicesNodeList = doc.getElementsByTagName("service");

        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);
            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;
                if (nameValue.equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                    return serviceElement.getElementsByTagName(provider).item(0).getTextContent();
                }
            }
        }
        return "";
    }
}


