package cloud.model.provider;

import cloud.configuration.Config;
import cloud.configuration.Constants;
import cloud.model.design.DesignManager;
import cloud.model.pricing.*;
import cloud.model.services.*;
import javafx.collections.ObservableList;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * protected Amazon provider class
 */
class Amazon extends Provider implements Pricing {

    /**
     * Protected constructor
     */
    Amazon() {
        setServiceName("Amazon Web Services");
        setServiceFile(Config.getInstance().getConfigValue("aws-services"));
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

        /* check category and calculate static costs for all added services */
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
                        else if (ServiceChecker.getInstance().isDatabaseItem(service.getCategory()) && service instanceof  DatabaseService)
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

    /**
     * Calculate compute service costs
     * @param service compute service
     * @param element element of service file
     * @return costs object
     */
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
                    serviceCosts.setFormula(service.getNumOne()+" * "+instancePrice+" USD"+ " + " + service.getData()+" * "+dataPrice+" USD");
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
            double dataPrice = 0;
            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equalsIgnoreCase("hour")) {
                            hourPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("data")) {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double hourCosts = service.getNumOne() * hourPrice * Constants.MONTH_HOURS;
                    double dataCosts = service.getNumOne() * service.getData() * dataPrice;
                    serviceCosts.setPrice(hourCosts + dataCosts);
                    serviceCosts.setFormula(service.getNumOne()+" * "+hourPrice+" USD * "+Constants.MONTH_HOURS+" hours"+" + "
                            + service.getNumOne()+" * "+service.getData()+" * "+dataPrice+" USD");
                }
            }
        }
        return serviceCosts;
    }

    /**
     * Calculate storage service costs
     * @param service storage service
     * @param element element of service file
     * @return costs object
     */
    private Costs calcStorageServiceCosts(StorageService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("storage-type");
        String[] modes = Config.getInstance().getConfigValuesAsArray("storage-object-mode");
        Costs serviceCosts = new Costs();

        if (service.getStorageType().equalsIgnoreCase(types[0]) && service.getStorageMode().equalsIgnoreCase(modes[0])) {
            // storage type "Object-Storage (Standard)"
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
                    double capacityCosts = service.getCapacity() * capacityPrice;
                    double requestsCosts = Math.ceil(service.getRequests().getKey() / requestsFactor) * requestsReadPrice +
                            Math.ceil(service.getRequests().getValue() / requestsFactor) * requestsWritePrice;
                    double dataCosts = service.getData() * dataOutPrice;
                    serviceCosts.setPrice(capacityCosts + requestsCosts + dataCosts);
                    serviceCosts.setFormula(service.getCapacity()+" * "+capacityPrice+" USD"+" + "
                            + Math.ceil(service.getRequests().getKey()/requestsFactor)+" * "+requestsReadPrice+" USD"+" + "
                            + Math.ceil(service.getRequests().getValue()/requestsFactor)+" * "+requestsWritePrice+" USD"+" + "
                            + service.getData()+" * "+dataOutPrice+" USD");
                }
            }
        }
        return serviceCosts;
    }

    /**
     * Calculate database service costs
     * @param service database service
     * @param element element of service file
     * @return costs object
     */
    private Costs calcDatabaseServiceCosts(DatabaseService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("database-system-type");
        String[] modes = Config.getInstance().getConfigValuesAsArray("database-sql-scheme");
        Costs serviceCosts = new Costs();

        if (service.getDatabaseType().equalsIgnoreCase(types[0]) && service.getDatabaseScheme().equalsIgnoreCase(modes[1])) {
            // storage type "SQL (PostgreSQL)"
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

    /**
     * Calculate analytic service costs
     * @param service analytic service
     * @param element element of service file
     * @return costs object
     */
    private Costs calcAnalyticServiceCosts(AnalyticService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");
        Costs serviceCosts = new Costs();

        if (service.getAnalyticType().equalsIgnoreCase(types[3])) {
            /* analytic type "Data Stream" */
            double dataPrice = 0;
            double dataHourSize = 0;
            double unitPrice = 0;
            double unitSize = 0;

            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        if (subNode.getNodeName().equalsIgnoreCase("data")) {
                            dataHourSize = Double.parseDouble(subNode.getAttributes().getNamedItem("size").getTextContent());
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("unit")) {
                            unitSize = Double.parseDouble(subNode.getAttributes().getNamedItem("size").getTextContent());
                            unitPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double dataSize = (double) service.getUnits() * service.getData() / Constants.DATA_FACTOR;
                    int numOfDataHours = (int) Math.ceil(dataHourSize * dataSize);
                    double dataCosts = numOfDataHours * Constants.MONTH_HOURS * dataPrice;

                    int numOfUnits = (int) Math.ceil((service.getData() / unitSize));
                    double unitCosts = (numOfUnits * service.getUnits() * Constants.MONTH_SECONDS) / Constants.M_FACTOR  * unitPrice;
                    serviceCosts.setPrice(dataCosts + unitCosts);
                    serviceCosts.setFormula(numOfDataHours+" * "+Constants.MONTH_HOURS+" hours"+" * "+dataPrice+" USD"+" + "
                            + numOfUnits+" * "+service.getUnits()+" * "+Constants.MONTH_SECONDS+" seconds"+" / "+Constants.M_FACTOR+" * "+unitPrice+" USD");
                }
            }
        } else if (service.getAnalyticType().equalsIgnoreCase(types[6])) {
            /* analytic type "Search Engine" */
            double instancePrice = 0;
            double dataPrice = 0;
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
                                && subNodeAttributes.getNamedItem("cpu").getTextContent().equalsIgnoreCase(service.getNum().getKey().toString())
                                && subNodeAttributes.getNamedItem("ram").getTextContent().equalsIgnoreCase(service.getNum().getValue().toString()))
                        {
                            instancePrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("data")) {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent()))
                        {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                    double unitCosts = service.getUnits() * instancePrice;
                    double dataCosts = service.getData() * dataPrice;
                    double dataOutCosts = service.getDataOut() * dataOutPrice;
                    serviceCosts.setPrice(unitCosts + dataCosts + dataOutCosts);
                    serviceCosts.setFormula(service.getUnits()+" * "+instancePrice+" USD"+" + "
                            + service.getData()+" * "+dataPrice+" USD"+" + "
                            + service.getDataOut()+" * "+dataOutPrice+" USD");
                }
            }
        }
        return serviceCosts;
    }

    /**
     * Calculate network service costs
     * @param service network service
     * @param element element of service file
     * @return costs object
     */
    private Costs calcNetworkServiceCosts(NetworkService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("network-type");
        Costs serviceCosts = new Costs();

        if (service.getNetworkType().equalsIgnoreCase(types[0])) {
            /* network type "VPC" */
            double connectionPrice = 0;
            double endpointPrice = 0;
            double dataPrice = 0;
            double dataOutPrice = 0;

            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("connection")) {
                            connectionPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("endpoint")) {
                            endpointPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("data")) {
                            dataPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                }
            }
            double connectionCosts = service.getRequests() * connectionPrice * service.getNumTwo();
            double endpointCosts = service.getNumOne() * endpointPrice * service.getNumTwo();
            double dataCosts = service.getData() * dataPrice;
            double dataOutCosts = service.getDataOut() * dataOutPrice;
            serviceCosts.setPrice(connectionCosts + endpointCosts + dataCosts + dataOutCosts);
            serviceCosts.setFormula(service.getRequests()+" * "+connectionPrice+" USD"+" * "+service.getNumTwo()+" hours"+" + "
                    + service.getNumTwo()+" * "+endpointPrice+" USD"+" * "+service.getNumTwo()+" hours"+" + "
                    + service.getData()+" * "+dataPrice+" USD"+" + "
                    + service.getDataOut()+" * "+dataOutPrice+" USD");
        } else if (service.getNetworkType().equalsIgnoreCase(types[1])) {
            /* network type "VPN" */
            double connectionPrice = 0;
            double dataOutPrice = 0;

            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
                        NamedNodeMap subNodeAttributes = subNode.getAttributes();
                        if (subNode.getNodeName().equalsIgnoreCase("connection")) {
                            connectionPrice = Double.parseDouble(subNode.getTextContent());
                        } else if (subNode.getNodeName().equalsIgnoreCase("dataOut")
                                && service.getData() >= Double.parseDouble(subNodeAttributes.getNamedItem("min").getTextContent())
                                && service.getData() <= Double.parseDouble(subNodeAttributes.getNamedItem("max").getTextContent())) {
                            dataOutPrice = Double.parseDouble(subNode.getTextContent());
                        }
                    }
                }
            }
            double connectionCosts = service.getRequests() * connectionPrice * Constants.MONTH_HOURS;
            double dataOutCosts = service.getDataOut() * dataOutPrice;
            serviceCosts.setPrice(connectionCosts + dataOutCosts);
            serviceCosts.setFormula(service.getRequests()+" * "+connectionPrice+" USD"+" * "+Constants.MONTH_HOURS+" hours"+" + "
                    + service.getDataOut()+" * "+dataOutPrice+" USD");
        } else if (service.getNetworkType().equalsIgnoreCase(types[3])) {
            /* network type "CDN" */
            double dataPrice = 0;
            double dataOutPrice = 0;
            double httpPrice = 0;
            double httpFactor = 0;

            NodeList locationElements = element.getElementsByTagName("location");
            for (int i = 0; i < locationElements.getLength(); i++) {
                Node locationNode = locationElements.item(i);
                if (service.getLocation().equalsIgnoreCase(locationNode.getAttributes().getNamedItem("name").getNodeValue())) {
                    for (int j = 0; j < locationNode.getChildNodes().getLength(); j++) {
                        Node subNode = locationNode.getChildNodes().item(j);
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
            double dataOutCosts = service.getDataOut() * Constants.K_FACTOR * dataOutPrice;
            double httpCosts = (service.getRequests() / httpFactor) * httpPrice;
            serviceCosts.setPrice(dataCosts + dataOutCosts + httpCosts);
            serviceCosts.setFormula(service.getData()+" * "+dataPrice+" USD"+" + "
                    + service.getDataOut()+" * "+Constants.K_FACTOR+" * "+dataOutPrice+" USD"+" + "
                    + service.getRequests()+"/"+httpFactor+" * "+httpPrice+" USD");
        }
        return serviceCosts;
    }

    /**
     * Calculate administration service costs
     * @param service administration service
     * @param element element of service file
     * @return costs object
     */
    private Costs calcAdministrationServiceCosts(AdministrationService service, Element element) {
        String[] types = Config.getInstance().getConfigValuesAsArray("administration-type");
        Costs serviceCosts = new Costs();

        if (service.getAdministrationType().equalsIgnoreCase(types[0]) && !(service.getLoggingState())) {
            /* administration type "Monitoring (System Monitor)" */
            double requestsPrice = Double.parseDouble(element.getElementsByTagName("requests").item(0).getTextContent());
            double requestsFactor = Double.parseDouble(element.getElementsByTagName("requests").item(0).getAttributes().getNamedItem("factor").getTextContent());
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
            double requestsCosts = service.getRequests() / requestsFactor * requestsPrice;
            double eventsCosts = service.getEvents() * eventsPrice;
            double dataCosts = service.getData().getKey() * dataCollectPrice + service.getData().getValue() * dataSavePrice;
            serviceCosts.setPrice(metricsCosts + requestsCosts + eventsCosts + dataCosts);
            serviceCosts.setFormula(service.getMetrics()+" * "+metricsPrice+" USD"+" + "
                    + service.getRequests()+" * "+requestsPrice+" USD"+" + "
                    + service.getEvents()+" * "+eventsPrice+" USD"+" + "
                    + service.getData().getKey()+" * "+dataCollectPrice+" USD"+" + "
                    + service.getData().getValue()+" * "+dataSavePrice+" USD");
        }
        return serviceCosts;
    }

    @Override
    public void optimizeCosts() {
        /*
         * TODO: implement function to optimize costs of services for amazon web services
         */
    }
}
