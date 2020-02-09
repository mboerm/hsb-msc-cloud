package cloud.model.provider;

import cloud.configuration.Config;
import cloud.model.design.DesignManager;
import cloud.model.pricing.*;
import cloud.model.services.*;
import javafx.collections.ObservableList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class Amazon extends Provider implements IPricing {

    Amazon() {
        setServiceName("Amazon Web Services");
        setServiceShortName("aws");
        setPriceFile(Config.getInstance().getConfigValue("aws-prices"));
        setFreeFile(Config.getInstance().getConfigValue("aws-free-tier"));
    }

    @Override
    public void calculateCosts() {
        System.out.println(getServiceName() + " -> " + "Static Cost Calculation!");
        setDocument(getPriceFile());

        ObservableList<Service> services = DesignManager.getInstance().getDesign().getServicesList();
        NodeList servicesNodeList = getDocument().getElementsByTagName("service");
        Costs costs = new Costs();

        for (Service service : services) {
            for(int i=0; i<servicesNodeList.getLength(); i++) {
                Node serviceNode = servicesNodeList.item(i);
                if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element serviceElement = (Element) serviceNode;
                    if (service.getProviderService().equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                        if (ServiceChecker.isComputeItem(service.getCategory()) && service instanceof ComputeService)
                            costs = calcComputeServiceCosts((ComputeService) service, serviceElement);
                        else if (ServiceChecker.isStorageItem(service.getCategory()) && service instanceof StorageService)
                            costs = calcStorageServiceCosts((StorageService) service, serviceElement);
                        else if (ServiceChecker.isAnalyticItem(service.getCategory()) && service instanceof AnalyticService)
                            costs = calcAnalyticServiceCosts((AnalyticService) service, serviceElement);
                        else if (ServiceChecker.isNetworkItem(service.getCategory()) && service instanceof NetworkService)
                            costs = calcNetworkServiceCosts((NetworkService) service, serviceElement);
                        else if (ServiceChecker.isMonitoringItem(service.getCategory()) && service instanceof MonitoringService)
                            costs = calcMonitoringServiceCosts((MonitoringService) service, serviceElement);
                    }
                }
            }
            DesignManager.getInstance().getDesign().addServiceCost(service,costs);
        }
    }

    @Override
    public void optimizeCosts() {
        /*
         * TODO: implement function to optimize costs of services for amazon web services
         */
    }

    private Costs calcComputeServiceCosts(ComputeService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("compute-type");
        Costs serviceCosts = new Costs();
        if (service.getComputeType().equals(types[0])) {
            /* compute type "VM" */
            double instancePrice = 0;
            double dataPrice = 0;
            NodeList regions = element.getElementsByTagName("region");
            for (int i = 0; i < regions.getLength(); i++) {
                Node regionNode = regions.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equals("instance")
                                && subNodeAttributes.getNamedItem("type").getTextContent().equals(service.getInstanceType())
                                && subNodeAttributes.getNamedItem("size").getTextContent().equals(service.getInstanceSize())
                                && subNodeAttributes.getNamedItem("cpu").getTextContent().equals(service.getCPU().toString())
                                && subNodeAttributes.getNamedItem("ram").getTextContent().equals(service.getStorage().toString())
                                && subNodeAttributes.getNamedItem("os").getTextContent().equals(service.getSystem()))
                        {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equals("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent()))
                        {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    serviceCosts.setPrice(service.getNumOne() * instancePrice + service.getData() * dataPrice);
                }
            }
        } else if (service.getComputeType().equals(types[4])) {
            /* compute type "Code" */
            double requestsPrice = Double.parseDouble(element.getElementsByTagName("requests").item(0).getTextContent());
            double durationPrice = Double.parseDouble(element.getElementsByTagName("duration").item(0).getTextContent());
            double durationFactor = Double.parseDouble(element.getElementsByTagName("duration").item(0).getAttributes().getNamedItem("factor").getTextContent());
            double requestsCosts = service.getNumOne() * requestsPrice;
            double durationCosts = (service.getNumOne() * durationFactor) * service.getNumTwo() * ((double) (service.getStorage()) / DATA_FACTOR) * durationPrice;
            serviceCosts.setPrice(requestsCosts + durationCosts);
        } else if (service.getComputeType().equals(types[5])) {
            /* compute type "Load Balancing" */
            double hourPrice = 0;
            double dataPrice = 0;
            NodeList balancerRegions = element.getElementsByTagName("region");
            for (int i = 0; i < balancerRegions.getLength(); i++) {
                Node regionNode = balancerRegions.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equals("hour")) {
                            hourPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equals("data")) {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double hourCosts = service.getNumOne() * hourPrice * MONTH_HOURS;
                    double dataCosts = service.getNumOne() * service.getData() * dataPrice;
                    serviceCosts.setPrice(hourCosts + dataCosts);
                }
            }
        }
        return serviceCosts;
    }

    private Costs calcStorageServiceCosts(StorageService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("storage-type");
        String[] modes = Config.getInstance().getConfigValuesAsArray("storage-object-mode");
        Costs serviceCosts = new Costs();

        if (service.getStorageType().equals(types[0]) && service.getStorageMode().equals(modes[0])) {
            // storage type "Object-Storage"
            double capacityPrice = 0;
            double requestsReadPrice = 0;
            double requestsWritePrice = 0;
            double requestsFactor = 0;
            double dataOutPrice = 0;
            NodeList regions = element.getElementsByTagName("region");
            for (int i = 0; i < regions.getLength(); i++) {
                Node regionNode = regions.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equals("capacity")
                                && service.getCapacity() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getCapacity() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            capacityPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equals("requests")) {
                            requestsFactor = Integer.parseInt(subNode.getAttributes().getNamedItem("factor").getTextContent());
                            switch (subNode.getAttributes().getNamedItem("type").getTextContent()) {
                                case "read":
                                    requestsReadPrice = Double.parseDouble(subNode.getTextContent());
                                    break;
                                case "write":
                                    requestsWritePrice = Double.parseDouble(subNode.getTextContent());
                                    break;
                            }
                        } else if (subNode.getNodeName().equals("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double capacityCosts = service.getCapacity() * capacityPrice;
                    double requestsCosts = Math.ceil(service.getRequests().getKey() / requestsFactor * requestsReadPrice) +
                            Math.ceil(service.getRequests().getValue() / requestsFactor * requestsWritePrice);
                    double dataCosts = service.getData() * dataOutPrice;
                    serviceCosts.setPrice(capacityCosts + requestsCosts + dataCosts);
                }
            }
        }
        return serviceCosts;
    }

    private Costs calcAnalyticServiceCosts(AnalyticService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");
        Costs serviceCosts = new Costs();

        if (service.getAnalyticType().equals(types[3])) {
            /* analytic type "Data Stream" */
            double dataPrice = 0;
            int dataHourSize = 0;
            double unitPrice = 0;
            int unitSize = 0;

            NodeList balancerRegions = element.getElementsByTagName("region");
            for (int i = 0; i < balancerRegions.getLength(); i++) {
                Node regionNode = balancerRegions.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equals("data")) {
                            dataHourSize = Integer.parseInt(subNode.getAttributes().getNamedItem("size").getTextContent());
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equals("unit")) {
                            unitSize = Integer.parseInt(subNode.getAttributes().getNamedItem("size").getTextContent());
                            unitPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }

                    double dataSize = (double) service.getNum().getKey() * service.getNum().getValue() / DATA_FACTOR;
                    int numOfDataHours = (int) Math.ceil((dataHourSize * dataSize) / 100.0);
                    double dataCosts = numOfDataHours * MONTH_HOURS * dataPrice;

                    int numOfUnits = (int) Math.ceil((double) (service.getNum().getValue() / unitSize) / 100.0);
                    double unitCosts = numOfUnits * MONTH_SECONDS * unitPrice;
                    serviceCosts.setPrice(dataCosts + unitCosts);
                }
            }
        } else if (service.getAnalyticType().equals(types[6])) {
            double instancePrice = 0;
            double dataPrice = 0;
            double dataOutPrice = 0;
            NodeList regions = element.getElementsByTagName("region");
            for (int i = 0; i < regions.getLength(); i++) {
                Node regionNode = regions.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equals("instance")
                                && subNodeAttributes.getNamedItem("type").getTextContent().equals(service.getInstanceType())
                                && subNodeAttributes.getNamedItem("size").getTextContent().equals(service.getInstanceSize())
                                && subNodeAttributes.getNamedItem("cpu").getTextContent().equals(service.getNum().getKey().toString())
                                && subNodeAttributes.getNamedItem("ram").getTextContent().equals(service.getNum().getValue().toString()))
                        {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equals("data")) {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equals("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent()))
                        {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    serviceCosts.setPrice(service.getUnits() * instancePrice +
                            service.getData() * dataPrice +
                            service.getDataOut() * dataOutPrice);
                }
            }
        }

        return serviceCosts;
    }

    private Costs calcNetworkServiceCosts(NetworkService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("network-type");
        Costs serviceCosts = new Costs();

        if (service.getNetworkType().equals(types[3])) {
            /* network type "CDN" */
            double dataPrice = 0;
            double dataOutPrice = 0;
            double httpPrice = 0;
            double httpFactor = 0;

            NodeList regionsElements = element.getElementsByTagName("region");
            for (int i = 0; i < regionsElements.getLength(); i++) {
                Node regionNode = regionsElements.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        switch (subNode.getNodeName()) {
                            case "data":
                                dataPrice = Double.parseDouble(subNode.getTextContent());
                                break;
                            case "http":
                                httpFactor = Integer.parseInt(subNode.getAttributes().getNamedItem("factor").getTextContent());
                                httpPrice = Double.parseDouble(subNode.getTextContent());
                                break;
                            case "dataOut":
                                if (service.getDataOut() >= Double.parseDouble(subNode.getAttributes().getNamedItem("min").getTextContent())
                                        && service.getDataOut() <= Double.parseDouble(subNode.getAttributes().getNamedItem("max").getTextContent())) {
                                    dataOutPrice = Double.parseDouble(subNode.getTextContent());
                                }
                                break;
                        }
                    }
                }
            }
            double dataCosts = service.getData() * dataPrice;
            double dataOutCosts = service.getDataOut() * K_FACTOR * dataOutPrice;
            double httpCosts = (service.getRequests() / httpFactor) * httpPrice;
            serviceCosts.setPrice(dataCosts + dataOutCosts + httpCosts);
        }
        return serviceCosts;
    }

    private Costs calcMonitoringServiceCosts(MonitoringService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("monitoring-type");
        Costs serviceCosts = new Costs();

        if (!(service.getLoggingState()) && service.getDisplayName().equals(types[1])) {
            /* monitoring type "System Monitor" */
            double requestsPrice = Double.parseDouble(element.getElementsByTagName("requests").item(0).getTextContent());
            double eventsPrice = Double.parseDouble(element.getElementsByTagName("events").item(0).getTextContent());
            double metricsPrice = 0;
            double dataCollectPrice = 0;
            double dataSavePrice = 0;
            NodeList metricsElements = element.getElementsByTagName("metrics");
            for (int i = 0; i < metricsElements.getLength(); i++) {
                Node metricsNode = metricsElements.item(i);
                if (service.getMetrics() >= Double.parseDouble(metricsNode.getAttributes().getNamedItem("min").getTextContent())
                        && service.getMetrics() <= Double.parseDouble(metricsNode.getAttributes().getNamedItem("max").getTextContent())) {
                    metricsPrice = Double.parseDouble(metricsNode.getTextContent());
                }
            }
            NodeList regionsElements = element.getElementsByTagName("region");
            for (int i = 0; i < regionsElements.getLength(); i++) {
                Node regionNode = regionsElements.item(i);
                if (service.getRegion().equals(regionNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < regionNode.getChildNodes().getLength(); j++) {
                        Node subNode = regionNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equals("data")) {
                            switch (subNode.getAttributes().getNamedItem("type").getTextContent()) {
                                case "collect":
                                    dataCollectPrice = Double.parseDouble(subNode.getTextContent());
                                    break;
                                case "save":
                                    dataSavePrice = Double.parseDouble(subNode.getTextContent());
                                    break;
                            }
                        }
                    }
                }
            }
            double metricsCosts = service.getMetrics() * metricsPrice;
            double requestsCosts = service.getRequests() * requestsPrice;
            double eventsCosts = service.getEvents() * eventsPrice;
            double dataCosts = service.getData().getKey() * dataCollectPrice + service.getData().getValue() * dataSavePrice;
            serviceCosts.setPrice(metricsCosts + requestsCosts + eventsCosts + dataCosts);
        }
        return serviceCosts;
    }
}
