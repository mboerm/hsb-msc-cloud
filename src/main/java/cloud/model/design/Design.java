package cloud.model.design;

import cloud.model.provider.Provider;
import cloud.model.provider.ProviderServices;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Design {

    private int selectedService;
    private final ObservableList<Service> servicesList = FXCollections.observableArrayList();

    /**
     * private final Map<Service, String> matchedServicesMap = new HashMap<Service, String>();
     * matchedServicesMap.put(Service,"AWS-EC2");
     */

    /**
    private final List<Pair> matchedServicesList = new ArrayList<Pair>();
    Pair pair = new Pair(Service,"AWS-EC2");
     matchedServicesList.add(pair);
     */

    private Provider provider;
    private String usagePeriod;
    private String primaryRegion;
    private String numOfInstances;
    private String numOfRequests;
    private String periodOfRequests;
    private String numOfCapacity;
    private String periodOfCapacity;

    public Service getService(String name) {
        for (Service service : this.servicesList) {
            if (service.getName().equals(name)) {
                return service;
            }
        }
        return null;
    }

    public void addService(Service service) {
        this.servicesList.add(service);
    }
    public void removeService(Service service) {
        this.servicesList.remove(service);
    }
    public ObservableList<Service> getServicesList() {
        return this.servicesList;
    }
    public void clearServicesList() {
        this.servicesList.clear();
    }
    public int getSelectedService() {
        return this.selectedService;
    }
    public void setSelectedService(int id) {
        this.selectedService = id;
    }

    public void setMatchedServicesForDesign() {
        for (Service service : servicesList) {
            service.setProviderService(ProviderServices.getInstance().getProviderServiceForName(
                            service.getDisplayName(), provider.getShortName()));

            System.out.println(service.getDisplayName() + " in " + provider.getShortName() + " => " + service.getProviderService());
        }
    }

    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getUsagePeriod() {return this.usagePeriod;}
    public void setUsagePeriod(String period) {this.usagePeriod = period;}

    public String getPrimaryRegion() {return this.primaryRegion;}
    public void setPrimaryRegion(String region) {this.primaryRegion = region;}

    public String getNumOfInstances() {return this.numOfInstances;}
    public void setNumOfInstances(String instances) {this.numOfInstances = instances;}

    public String getNumOfRequests() {return this.numOfRequests;}
    public void setNumOfRequests(String requests) {this.numOfRequests = requests;}

    public String getPeriodOfRequests() {return this.periodOfRequests;}
    public void setPeriodOfRequests(String requestPeriod) {this.periodOfRequests = requestPeriod;}

    public String getNumOfCapacity() {return this.numOfCapacity;}
    public void setNumOfCapacity(String capacity) {this.numOfCapacity = capacity;}

    public String getPeriodOfCapacity() {return this.periodOfCapacity;}
    public void setPeriodOfCapacity(String capacityPeriod) {this.periodOfCapacity = capacityPeriod;}
}