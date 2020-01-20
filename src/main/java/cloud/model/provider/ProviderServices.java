package cloud.model.provider;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import cloud.configuration.Config;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class Services {
    private Document doc;
    private String servicesFileName;

    public Services() {
        servicesFileName = Config.getInstance().getConfigValue("services-file");
        setServicesDocument();
    }

    private void setServicesDocument() {
        try {
            File servicesFile = new File(servicesFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(servicesFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getServices() {
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
                    System.out.println("Name : " + serviceName);

                    if (serviceElement.getAttributes().getLength() > 1) {
                        String serviceMode = serviceElement.getAttribute("mode");
                        System.out.println("Mode : " + serviceMode);
                    }

                    String serviceAWS = serviceElement.getElementsByTagName("aws").item(0).getTextContent();
                    String serviceAzure = serviceElement.getElementsByTagName("azure").item(0).getTextContent();
                    String serviceGCP = serviceElement.getElementsByTagName("gcp").item(0).getTextContent();

                    System.out.println("AWS : " + serviceAWS);
                    System.out.println("Azure : " + serviceAzure);
                    System.out.println("GCP : " + serviceGCP);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] getServiceByName(String nameValue) {
        NodeList servicesNodeList = doc.getElementsByTagName("service");

        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);

            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;

                if(nameValue.equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                    String serviceMode = "";
                    if (serviceElement.getAttributes().getLength() > 1) {
                        serviceMode = serviceElement.getAttribute("mode");
                        System.out.println("Mode : " + serviceMode);
                    }

                    String serviceAWS = serviceElement.getElementsByTagName("aws").item(0).getTextContent();
                    String serviceAzure = serviceElement.getElementsByTagName("azure").item(0).getTextContent();
                    String serviceGCP = serviceElement.getElementsByTagName("gcp").item(0).getTextContent();
                    System.out.println("AWS : " + serviceAWS);
                    System.out.println("Azure : " + serviceAzure);
                    System.out.println("GCP : " + serviceGCP);

                    return new String[] {nameValue, serviceMode, serviceAWS, serviceAzure, serviceGCP};
                }
            }
        }
        return null;
    }

    private String[] getProviderServiceForName(String nameValue, String modeValue, String provider) {
        NodeList servicesNodeList = doc.getElementsByTagName("service");
        String service = "No provider service";

        for(int i=0; i<servicesNodeList.getLength(); i++) {
            Node serviceNode = servicesNodeList.item(i);
            if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;

                if (serviceElement.getAttributes().getLength() == 1 &&
                        nameValue.equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                    service = serviceElement.getElementsByTagName(provider).item(0).getTextContent();

                } else if (nameValue.equalsIgnoreCase(serviceElement.getAttribute("name")) &&
                        modeValue.equalsIgnoreCase(serviceElement.getAttribute("mode"))) {
                    service = serviceElement.getElementsByTagName(provider).item(0).getTextContent();
                }

                System.out.println("Provider service : " + service);
                return new String[] {nameValue, modeValue, provider, service};
            }
        }
        return null;
    }
}


