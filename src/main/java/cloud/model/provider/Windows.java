package cloud.model.provider;

import cloud.configuration.Config;
import cloud.configuration.Constants;
import cloud.model.design.DesignManager;
import cloud.model.pricing.Costs;
import cloud.model.pricing.Pricing;
import cloud.model.services.*;
import javafx.collections.ObservableList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class Windows extends Provider implements Pricing {

    Windows() {
        setServiceName("Windows Azure");
        setServiceFile(Config.getInstance().getConfigValue("azure-services"));
    }

    @Override
    public void calculateCosts() {
        /*
         * TODO: implement api call for cost calculation
         */
    }

    @Override
    public void calculateStaticCosts() {
        setDocument(getServiceFile());

        ObservableList<Service> services = DesignManager.getInstance().getDesign().getServicesList();
        NodeList servicesNodeList = getDocument().getElementsByTagName("service");
        Costs costs = new Costs();

        for (Service service : services) {
            for(int i=0; i<servicesNodeList.getLength(); i++) {
                Node serviceNode = servicesNodeList.item(i);
                if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element serviceElement = (Element) serviceNode;
                    if (service.getProviderService().equalsIgnoreCase(serviceElement.getAttribute("name"))) {
                        if (ServiceChecker.getInstance().isComputeItem(service.getCategory()) && service instanceof ComputeService)
                            costs = calcComputeServiceCosts((ComputeService) service, serviceElement);
                        else if (ServiceChecker.getInstance().isStorageItem(service.getCategory()) && service instanceof StorageService)
                            costs = calcStorageServiceCosts((StorageService) service, serviceElement);
                        else if (ServiceChecker.getInstance().isDatabaseItem(service.getCategory()) && service instanceof DatabaseService)
                            costs = calcDatabaseServiceCosts((DatabaseService) service, serviceElement);
                        else if (ServiceChecker.getInstance().isAnalyticItem(service.getCategory()) && service instanceof AnalyticService)
                            costs = calcAnalyticServiceCosts((AnalyticService) service, serviceElement);
                        else if (ServiceChecker.getInstance().isNetworkItem(service.getCategory()) && service instanceof NetworkService)
                            costs = calcNetworkServiceCosts((NetworkService) service, serviceElement);
                        else if (ServiceChecker.getInstance().isAdministrationItem(service.getCategory()) && service instanceof AdministrationService)
                            costs = calcAdministrationServiceCosts((AdministrationService) service, serviceElement);
                        else {
                            costs = new Costs();
                        }
                    }
                }
            }
            DesignManager.getInstance().getDesign().addServiceCost(service,costs);
        }
    }

    @Override
    public void optimizeCosts() {
        /*
         * TODO: implement function to optimize costs of services for windows azure
         */
    }

    private Costs calcComputeServiceCosts(ComputeService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("compute-type");
        String[] containerModes = Config.getInstance().getConfigValuesAsArray("compute-container-type");
        Costs serviceCosts = new Costs();
        if (service.getComputeType().equalsIgnoreCase(types[0])) {
            /* compute type "VM" */
            double instancePrice = 0;
            double dataPrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("instance")
                                && subNodeAttributes.getNamedItem("type").getTextContent().equalsIgnoreCase(service.getInstanceType())
                                && subNodeAttributes.getNamedItem("cpu").getTextContent().equalsIgnoreCase(String.valueOf(service.getCPU()))
                                && subNodeAttributes.getNamedItem("ram").getTextContent().equalsIgnoreCase(String.valueOf(service.getStorage()))
                                && subNodeAttributes.getNamedItem("os").getTextContent().equalsIgnoreCase(service.getSystem()))
                        {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent()))
                        {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double instanceCosts = service.getNumOne() * instancePrice;
                    double dataCosts = service.getData() * dataPrice;
                    serviceCosts.setPrice(instanceCosts + dataCosts);
                    serviceCosts.setFormula(service.getNumOne() + " * " + instancePrice + " USD" + " + " + service.getData() + " * " + dataPrice + " USD");
                }
            }
        } else if (service.getComputeType().equalsIgnoreCase(types[1]) && service.getSystem().equalsIgnoreCase(containerModes[0])) {
            /* compute type "Container (Standard)" */
            double cpuPrice = 0;
            double ramPrice = 0;
            double dataOutPrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("cpu")) {
                            cpuPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("ram")) {
                            ramPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double cpuCosts = service.getNumOne() * service.getCPU() * (cpuPrice / Constants.HOUR_SECONDS) * service.getNumTwo() * Constants.MONTH_DAYS;
                    double ramCosts = service.getNumOne() * service.getStorage() * (ramPrice / Constants.HOUR_SECONDS) * service.getNumTwo() * Constants.MONTH_DAYS;
                    double dataOutCosts = service.getData() * dataOutPrice;
                    serviceCosts.setPrice(cpuCosts + ramCosts + dataOutCosts);
                    serviceCosts.setFormula(service.getNumOne()*service.getCPU()*service.getNumTwo()+" * "+cpuPrice+" USD" + " + "
                            + service.getNumOne()*service.getStorage()*service.getNumTwo()+" * "+ramPrice+" USD" + " + "
                            + service.getData() + " * " + dataOutPrice + " USD");
                }
            }
        } else if (service.getComputeType().equalsIgnoreCase(types[2])) {
            /* compute type "App" */
            double instancePrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("instance")
                                && subNodeAttributes.getNamedItem("os").getTextContent().equalsIgnoreCase(service.getSystem())
                                && subNodeAttributes.getNamedItem("cpu").getTextContent().equalsIgnoreCase(String.valueOf(service.getCPU()))
                                && subNodeAttributes.getNamedItem("ram").getTextContent().equalsIgnoreCase(String.valueOf(service.getNumOne()))
                                && subNodeAttributes.getNamedItem("storage").getTextContent().equalsIgnoreCase(String.valueOf(service.getStorage()))) {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double instanceCosts = instancePrice * Constants.MONTH_HOURS;
                    serviceCosts.setPrice(instanceCosts);
                    serviceCosts.setFormula(instancePrice+" USD * "+Constants.MONTH_HOURS+" hours");
                }
            }
        } else if (service.getComputeType().equalsIgnoreCase(types[4])) {
            /* compute type "Code" */
            double requestsPrice = Double.parseDouble(element.getElementsByTagName("requests").item(0).getTextContent());
            double durationPrice = Double.parseDouble(element.getElementsByTagName("duration").item(0).getTextContent());
            double durationFactor = Double.parseDouble(element.getElementsByTagName("duration").item(0).getAttributes().getNamedItem("factor").getTextContent());
            double requestsCosts = service.getNumOne() * requestsPrice;
            double durationCosts = (service.getNumOne() * durationFactor) * service.getNumTwo() * ((double) (service.getStorage()) / Constants.DATA_FACTOR) * durationPrice;
            serviceCosts.setPrice(requestsCosts + durationCosts);
            serviceCosts.setFormula(service.getNumOne()+" * "+requestsPrice+" USD"+" + "
                    + (service.getNumOne()*durationFactor)*service.getNumTwo()*((double)(service.getStorage())/Constants.DATA_FACTOR)+" * "+durationPrice+" USD");
        } else if (service.getComputeType().equalsIgnoreCase(types[5])) {
            /* compute type "Load Balancing" */
            double hourPrice = 0;
            double dataPrice = Double.parseDouble(element.getElementsByTagName("data").item(0).getTextContent());

            NodeList hourElements = element.getElementsByTagName("hour");
            for (int i = 0; i < hourElements.getLength(); i++) {
                Node hourNode = hourElements.item(i);
                NamedNodeMap subNodeAttributes = hourNode.getAttributes();
                if (service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                        && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                    hourPrice = Double.parseDouble(hourNode.getTextContent());
                }
            }
            double hourCosts = service.getNumOne() * hourPrice * Constants.MONTH_HOURS;
            double dataCosts = service.getNumOne() * service.getData() * dataPrice;
            serviceCosts.setPrice(hourCosts + dataCosts);
            serviceCosts.setFormula(service.getNumOne()+" * "+hourPrice+" USD * "+Constants.MONTH_HOURS+" hours"+" + "
                    + service.getNumOne()+" * "+service.getData()+" * "+dataPrice+" USD");
        }
        return serviceCosts;
    }

    private Costs calcStorageServiceCosts(StorageService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("storage-type");
        String[] modes = Config.getInstance().getConfigValuesAsArray("storage-object-mode");
        Costs serviceCosts = new Costs();

        if (service.getStorageType().equalsIgnoreCase(types[0]) && service.getStorageMode().equalsIgnoreCase(modes[0])) {
            // storage type "Object-Storage"
            double capacityPrice = 0;
            double requestsReadPrice = 0;
            double requestsWritePrice = 0;
            double requestsFactor = 0;
            double dataOutPrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("capacity")
                                && service.getCapacity() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getCapacity() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            capacityPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("requests")) {
                            requestsFactor = Integer.parseInt(subNode.getAttributes().getNamedItem("factor").getTextContent());
                            switch (subNode.getAttributes().getNamedItem("type").getTextContent()) {
                                case "read":
                                    requestsReadPrice = Double.parseDouble(subNode.getTextContent());
                                    break;
                                case "write":
                                    requestsWritePrice = Double.parseDouble(subNode.getTextContent());
                                    break;
                            }
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double capacityCosts = service.getCapacity() * capacityPrice * Constants.MONTH_DAYS;
                    double requestsCosts = Math.ceil(service.getRequests().getKey() / requestsFactor * requestsReadPrice) * Constants.MONTH_DAYS +
                            Math.ceil(service.getRequests().getValue() / requestsFactor * requestsWritePrice)  * Constants.MONTH_DAYS;
                    double dataCosts = service.getData() * dataOutPrice * Constants.MONTH_DAYS;
                    serviceCosts.setPrice(capacityCosts + requestsCosts + dataCosts);
                    serviceCosts.setFormula(service.getCapacity()+" * "+capacityPrice+" USD * "+Constants.MONTH_DAYS+" days"+" + "
                            + Math.ceil(service.getRequests().getKey()/requestsFactor)+" * "+requestsReadPrice+" USD * "+Constants.MONTH_DAYS+" days" +" + "
                            + Math.ceil(service.getRequests().getValue()/requestsFactor)+" * "+requestsWritePrice+" USD * "+Constants.MONTH_DAYS+" days"+" + "
                            + service.getData()+" * "+dataOutPrice+" USD * "+Constants.MONTH_DAYS+" days");
                }
            }
        }
        return serviceCosts;
    }

    private Costs calcDatabaseServiceCosts(DatabaseService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("database-system-type");
        String[] modes = Config.getInstance().getConfigValuesAsArray("database-sql-scheme");
        Costs serviceCosts = new Costs();

        if (service.getDatabaseType().equalsIgnoreCase(types[0]) && service.getDatabaseScheme().equalsIgnoreCase(modes[1])) {
            // storage type "SQL - PostgreSQL"
            double instancePrice = 0;
            double storagePrice = 0;
            double backupPrice = 0;
            double dataOutPrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("instance")
                                && subNodeAttributes.getNamedItem("type").getTextContent().equalsIgnoreCase(service.getInstanceType())
                                && subNodeAttributes.getNamedItem("cpu").getTextContent().equalsIgnoreCase(String.valueOf(service.getNum().getKey()))
                                && subNodeAttributes.getNamedItem("ram").getTextContent().equalsIgnoreCase(String.valueOf(service.getNum().getValue()))) {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("storage")) {
                            storagePrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("backup") && service.getBackup() > service.getStorage()) {
                            backupPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double instanceCosts = service.getDuration() * instancePrice;
                    double storageCosts = service.getStorage() * storagePrice;
                    double backupCosts = (service.getBackup() - service.getStorage()) * backupPrice;
                    double dataCosts = service.getData() * dataOutPrice;
                    serviceCosts.setPrice(instanceCosts + storageCosts + backupCosts + dataCosts);
                    serviceCosts.setFormula(service.getDuration()+" * "+instancePrice+" USD"+" + "
                            + service.getStorage()+" * "+storagePrice+" USD"+" + "
                            + (service.getBackup()-service.getStorage())+" * "+backupPrice+" USD"+" + "
                            + service.getData()+" * "+dataOutPrice+" USD");
                }
            }
        }
        return serviceCosts;
    }

    private Costs calcAnalyticServiceCosts(AnalyticService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");
        Costs serviceCosts = new Costs();

        if (service.getAnalyticType().equalsIgnoreCase(types[3])) {
            /* analytic type "Data Stream" */
            double dataPrice = 0;
            double dataHourSize = 0;

            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equalsIgnoreCase("data")) {
                            dataHourSize = Double.parseDouble(subNode.getAttributes().getNamedItem("size").getTextContent());
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double dataSize = (double) service.getUnits() * service.getData() / Constants.DATA_FACTOR;
                    int numOfDataHours = (int) Math.ceil(dataHourSize * dataSize);
                    double dataCosts = numOfDataHours * Constants.MONTH_HOURS * dataPrice;
                    serviceCosts.setPrice(dataCosts);
                    serviceCosts.setFormula(numOfDataHours+" * "+Constants.MONTH_HOURS+" hours"+" * "+dataPrice+" USD");
                }
            }
        } else if (service.getAnalyticType().equalsIgnoreCase(types[6])) {
            /* analytic type "Search Engine" */
            double instancePrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("data")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double instanceCosts = service.getUnits() * instancePrice;
                    serviceCosts.setPrice(instanceCosts);
                    serviceCosts.setFormula(service.getUnits()+" * "+instancePrice+" USD");
                }
            }
        }

        return serviceCosts;
    }

    private Costs calcNetworkServiceCosts(NetworkService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("network-type");
        Costs serviceCosts = new Costs();

        if (service.getNetworkType().equalsIgnoreCase(types[0])) {
            /* network type "VPC" */
            double dataInPrice = Double.parseDouble(element.getElementsByTagName("dataIn").item(0).getTextContent());
            double dataOutPrice = Double.parseDouble(element.getElementsByTagName("dataOut").item(0).getTextContent());
            double dataInCosts = service.getData() * dataInPrice;
            double dataOutCosts = service.getDataOut() * dataOutPrice;
            serviceCosts.setPrice(dataInCosts + dataOutCosts);
            serviceCosts.setFormula(service.getData()+" * "+dataInPrice+" USD"+" + "
                    + service.getDataOut()+" * "+dataOutPrice+" USD");
        } else if (service.getNetworkType().equalsIgnoreCase(types[3])) {
            /* network type "CDN" */
            double dataOutPrice = 0;

            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);

                        if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getDataOut() >= Double.parseDouble(subNode.getAttributes().getNamedItem("min").getTextContent())
                                && service.getDataOut() <= Double.parseDouble(subNode.getAttributes().getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                }
            }
            double dataOutCosts = service.getDataOut() * Constants.K_FACTOR * dataOutPrice;
            serviceCosts.setPrice(dataOutCosts);
            serviceCosts.setFormula(service.getDataOut()+" * "+Constants.K_FACTOR+" * "+dataOutPrice+" USD");
        }
        return serviceCosts;
    }

    private Costs calcAdministrationServiceCosts(AdministrationService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("administration-type");
        Costs serviceCosts = new Costs();

        if (service.getAdministrationType().equalsIgnoreCase(types[0]) && !(service.getLoggingState())) {
            /* administration type "Monitoring (System)" */
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
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equalsIgnoreCase("data")) {
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
            double dataCosts = service.getData().getKey() * Constants.MONTH_DAYS * dataCollectPrice + service.getData().getValue() * dataSavePrice * Constants.MONTH_DAYS;
            serviceCosts.setPrice(metricsCosts + requestsCosts + eventsCosts + dataCosts);
            serviceCosts.setFormula(service.getMetrics()+" * "+metricsPrice+" USD"+" + "
                    + service.getRequests()+" * "+requestsPrice+" USD"+" + "
                    + service.getEvents()+" * "+eventsPrice+" USD"+" + "
                    + service.getData().getKey()+" * "+Constants.MONTH_DAYS+" * "+dataCollectPrice+" USD"+" + "
                    + service.getData().getValue()+" * "+Constants.MONTH_DAYS+" * "+dataSavePrice+" USD");
        }
        return serviceCosts;
    }
}
