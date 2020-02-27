package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Objects;

public class ServiceChecker {
    private static volatile ServiceChecker INSTANCE = null;
    private static String[] serviceTypes = Config.getInstance().getConfigValuesAsArray("service-categories");

    public static ServiceChecker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceChecker();
        }
        return INSTANCE;
    }

    public boolean isComputeItem(String item) {
        return item.equals(serviceTypes[0]);
    }
    public boolean isDatabaseItem(String item) {
        return item.equals(serviceTypes[1]);
    }
    public boolean isStorageItem(String item) {
        return item.equals(serviceTypes[2]);
    }
    public boolean isAnalyticItem(String item) {
        return item.equals(serviceTypes[3]);
    }
    public boolean isNetworkItem(String item) {
        return item.equals(serviceTypes[4]);
    }
    public boolean isIntegrationItem(String item) {
        return item.equals(serviceTypes[5]);
    }
    public boolean isAdministrationItem(String item) {
        return item.equals(serviceTypes[6]);
    }

    public String getServiceIdentifier(String category, String type, String... mode) {
        try {
            File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(Config.getInstance().getConfigValue("generic-services"))).getFile());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList servicesNodeList = doc.getElementsByTagName("service");
            for(int i=0; i<servicesNodeList.getLength(); i++) {
                Node serviceNode = servicesNodeList.item(i);
                if(serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element serviceElement = (Element) serviceNode;

                    if (category.equalsIgnoreCase(serviceElement.getAttribute("category")) &&
                    type.equalsIgnoreCase(serviceElement.getAttribute("type"))) {

                        if (serviceElement.hasAttribute("mode")) {
                            if (mode[0].equalsIgnoreCase(serviceElement.getAttribute("mode")))
                                return serviceElement.getElementsByTagName("id").item(0).getTextContent();
                        } else {
                            return serviceElement.getElementsByTagName("id").item(0).getTextContent();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Pair<String[],String[]> getSpecificProperties(Service service) {
        if (ServiceChecker.getInstance().isComputeItem(service.getCategory()) && service instanceof ComputeService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("compute-type");
            ComputeService compService = (ComputeService) service;
            if (compService.getComputeType().equals(types[0])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("compute-vm-labels"),
                        new String[] {
                                compService.getInstanceType(),
                                String.valueOf(compService.getNumOne()),
                                String.valueOf(compService.getCPU()),
                                String.valueOf(compService.getStorage()),
                                compService.getSystem(),
                                String.valueOf(compService.getData()),
                        }
                );
            } else if (compService.getComputeType().equals(types[1])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("compute-container-labels"),
                        new String[] {
                                compService.getSystem(),
                                String.valueOf(compService.getCPU()),
                                String.valueOf(compService.getStorage()),
                                String.valueOf(compService.getNumOne()),
                                String.valueOf(compService.getNumTwo()),
                                String.valueOf(compService.getData())
                        }
                );
            } else if (compService.getComputeType().equals(types[2])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("compute-app-labels"),
                        new String[] {
                                compService.getSystem(),
                                compService.getComputeInstance(),
                                compService.getStorageInstance()
                        }
                );
            } else if (compService.getComputeType().equals(types[3])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("compute-batch-labels"),
                        new String[] {
                                compService.getComputeInstance()
                        }
                );
            } else if (compService.getComputeType().equals(types[4])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("compute-code-labels"),
                        new String[] {
                                String.valueOf(compService.getNumOne()),
                                String.valueOf(compService.getNumTwo()),
                                String.valueOf(compService.getStorage())
                        }
                );
            } else if (compService.getComputeType().equals(types[5])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("compute-balancer-labels"),
                        new String[] {
                                String.valueOf(compService.getNumOne()),
                                String.valueOf(compService.getData())
                        }
                );
            }
        } else if (ServiceChecker.getInstance().isDatabaseItem(service.getCategory()) && service instanceof  DatabaseService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("database-system-type");
            DatabaseService dbService = (DatabaseService) service;
            if (dbService.getDatabaseType().equals(types[0])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("database-sql-labels"),
                        new String[] {
                                dbService.getDatabaseScheme(),
                                dbService.getInstanceType(),
                                dbService.getNum().getKey().toString(),
                                dbService.getNum().getValue().toString(),
                                String.valueOf(dbService.getDuration()),
                                String.valueOf(dbService.getStorage()),
                                String.valueOf(dbService.getBackup()),
                                String.valueOf(dbService.getData())
                        }
                );
            } else if (dbService.getDatabaseType().equals(types[1])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("database-nosql-labels"),
                        new String[] {
                                dbService.getNum().getKey().toString(),
                                dbService.getNum().getValue().toString(),
                                String.valueOf(dbService.getStorage()),
                                String.valueOf(dbService.getData())
                        }
                );
            } else if (dbService.getDatabaseType().equals(types[2])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("database-document-labels"),
                        new String[] {
                                dbService.getNum().getKey().toString(),
                                dbService.getNum().getValue().toString(),
                                String.valueOf(dbService.getStorage()),
                                String.valueOf(dbService.getData())
                        }
                );
            } else if (dbService.getDatabaseType().equals(types[3])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("database-cache-labels"),
                        new String[] {
                                dbService.getNum().getKey().toString(),
                                dbService.getNum().getValue().toString(),
                                String.valueOf(dbService.getDuration()),
                                String.valueOf(dbService.getData())
                        }
                );
            }
        } else if (ServiceChecker.getInstance().isStorageItem(service.getCategory()) && service instanceof StorageService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("storage-type");
            StorageService storageService = (StorageService) service;
            if (storageService.getStorageType().equals(types[0])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("storage-object-labels"),
                        new String[] {
                                storageService.getStorageMode(),
                                String.valueOf(storageService.getCapacity()),
                                storageService.getRequests().getKey().toString(),
                                storageService.getRequests().getKey().toString(),
                                String.valueOf(storageService.getData())
                        }
                );
            } else if (storageService.getStorageType().equals(types[1])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("storage-block-labels"),
                        new String[] {
                                storageService.getStorageMode(),
                                String.valueOf(storageService.getCapacity()),
                                String.valueOf(storageService.getData())
                        }
                );
            } else if (storageService.getStorageType().equals(types[2])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("storage-file-labels"),
                        new String[] {
                                storageService.getStorageMode(),
                                String.valueOf(storageService.getCapacity()),
                                String.valueOf(storageService.getRate()),
                                String.valueOf(storageService.getData())
                        }
                );
            }
        } else if (ServiceChecker.getInstance().isAnalyticItem(service.getCategory()) && service instanceof AnalyticService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("analytic-type");
            AnalyticService analyticService = (AnalyticService) service;
            if (analyticService.getAnalyticType().equals(types[0]) || analyticService.getAnalyticType().equals(types[1])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("analytic-query-labels"),
                        new String[] {
                                String.valueOf(analyticService.getData())
                        }
                );
            } else if (analyticService.getAnalyticType().equals(types[2])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("analytic-transfer-labels"),
                        new String[] {
                                analyticService.getNum().getKey().toString(),
                                analyticService.getNum().getValue().toString()
                        }
                );
            } else if (analyticService.getAnalyticType().equals(types[3])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("analytic-stream-labels"),
                        new String[] {
                                String.valueOf(analyticService.getUnits()),
                                String.valueOf(analyticService.getData())
                        }
                );
            } else if (analyticService.getAnalyticType().equals(types[4])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("analytic-catalog-labels"),
                        new String[] {
                                String.valueOf(analyticService.getData()),
                                String.valueOf(analyticService.getUnits())
                        }
                );
            } else if (analyticService.getAnalyticType().equals(types[5])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("analytic-lake-labels"),
                        new String[] {
                                String.valueOf(analyticService.getUnits()),
                                String.valueOf(analyticService.getData())
                        }
                );
            } else if (analyticService.getAnalyticType().equals(types[6])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("analytic-search-labels"),
                        new String[] {
                                analyticService.getNum().getKey().toString(),
                                analyticService.getNum().getValue().toString(),
                                String.valueOf(analyticService.getUnits()),
                                String.valueOf(analyticService.getData()),
                                String.valueOf(analyticService.getDataOut())
                        }
                );
            }
        } else if (ServiceChecker.getInstance().isNetworkItem(service.getCategory()) && service instanceof NetworkService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("network-type");
            NetworkService netService = (NetworkService) service;
            if (netService.getNetworkType().equals(types[0])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("network-vpc-labels"),
                        new String[]{
                                String.valueOf(netService.getRequests()),
                                String.valueOf(netService.getZones()),
                                String.valueOf(netService.getData()),
                                String.valueOf(netService.getDataOut())
                        }
                );
            } else if (netService.getNetworkType().equals(types[1])) {
                    return new Pair<>(
                            Config.getInstance().getConfigValuesAsArray("network-vpn-labels"),
                            new String[] {
                                    String.valueOf(netService.getRequests()),
                                    String.valueOf(netService.getData()),
                                    String.valueOf(netService.getDataOut())
                            }
                    );
            } else if (netService.getNetworkType().equals(types[2])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("network-api-labels"),
                        new String[] {
                                String.valueOf(netService.getRequests()),
                                String.valueOf(netService.getData())
                        }
                );
            } else if (netService.getNetworkType().equals(types[3])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("network-cdn-labels"),
                        new String[] {
                                String.valueOf(netService.getData()),
                                String.valueOf(netService.getDataOut()),
                                String.valueOf(netService.getRequests())
                        }
                );
            } else if (netService.getNetworkType().equals(types[4])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("network-dns-labels"),
                        new String[] {
                                String.valueOf(netService.getZones()),
                                String.valueOf(netService.getData())
                        }
                );
            }
        } else if (ServiceChecker.getInstance().isIntegrationItem(service.getCategory()) && service instanceof IntegrationService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("integration-type");
            IntegrationService intService = (IntegrationService) service;
            if (intService.getIntegrationType().equals(types[0])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("integration-messaging-labels"),
                        new String[]{
                                intService.getIntegrationMode(),
                                String.valueOf(intService.getData()),
                                String.valueOf(intService.getRequests()),
                                String.valueOf(intService.getMessages()[0]),
                                String.valueOf(intService.getMessages()[1]),
                                String.valueOf(intService.getMessages()[2]),
                                String.valueOf(intService.getMessages()[3])
                        }
                );
            }
        } else if (ServiceChecker.getInstance().isAdministrationItem(service.getCategory()) && service instanceof AdministrationService) {
            String[] types = Config.getInstance().getConfigValuesAsArray("administration-type");
            AdministrationService adminService = (AdministrationService) service;
            if (adminService.getAdministrationType().equals(types[0])) {
                return new Pair<>(
                        Config.getInstance().getConfigValuesAsArray("administration-monitoring-labels"),
                        new String[] {
                                String.valueOf(adminService.getMetrics()),
                                String.valueOf(adminService.getRequests()),
                                adminService.getData().getKey().toString(),
                                adminService.getData().getValue().toString(),
                                String.valueOf(adminService.getEvents()),
                                String.valueOf(adminService.getLoggingState())
                        }
                );
            }
        }
        return null;
    }
}
