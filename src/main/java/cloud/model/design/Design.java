package cloud.model.design;

import cloud.configuration.Constants;
import cloud.model.pricing.Costs;
import cloud.model.provider.Provider;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

public class Design {

    private final ObservableList<Service> servicesList = FXCollections.observableArrayList();
    private final ObservableList<Pair<Service, Costs>> servicesCosts = FXCollections.observableArrayList();

    private Provider provider;
    private String usagePeriod;
    private String primaryRegion;
    private Integer numOfInstances;
    private Integer numOfRequests;
    private String periodOfRequests;
    private Integer numOfCapacity;
    private String periodOfCapacity;

    public Design() {
        setUsagePeriod("");
        setPrimaryRegion("");
        setNumOfInstances(0);
        setNumOfRequests(0);
        setPeriodOfRequests("");
        setNumOfCapacity(0);
        setPeriodOfCapacity("");
    }

    public void addService(Service service) {
        this.servicesList.add(service);
    }
    public void removeService(Service service) {
        this.servicesList.remove(service);
    }
    public void replaceService(Service oldService, Service newService) {
        int index = this.servicesList.indexOf(oldService);
        this.servicesList.set(index, newService);
    }
    public ObservableList<Service> getServicesList() {return this.servicesList;}
    public void clearServicesList() {
        this.servicesList.clear();
    }

    public void addServiceCost(Service service, Costs charge) {
        this.servicesCosts.add(new Pair<>(service, charge));
    }
    public ObservableList<Pair<Service, Costs>> getServicesCosts() {return this.servicesCosts;}
    public void clearServicesCosts() {this.servicesCosts.clear();}

    public void matchServices() {
        for (Service service : servicesList) {
            service.setProviderService(getProvider().getMatchingServiceForName(service.getDisplayName()));
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

    public Integer getNumOfInstances() {return this.numOfInstances;}
    public void setNumOfInstances(Integer instances) {this.numOfInstances = instances;}

    public Integer getNumOfRequests() {return this.numOfRequests;}
    public void setNumOfRequests(Integer requests) {this.numOfRequests = requests;}

    public String getPeriodOfRequests() {return this.periodOfRequests;}
    public void setPeriodOfRequests(String requestPeriod) {this.periodOfRequests = requestPeriod;}

    public Integer getNumOfCapacity() {return this.numOfCapacity;}
    public void setNumOfCapacity(Integer capacity) {this.numOfCapacity = capacity;}

    public String getPeriodOfCapacity() {return this.periodOfCapacity;}
    public void setPeriodOfCapacity(String capacityPeriod) {this.periodOfCapacity = capacityPeriod;}

    public double getTotalCosts() {
        double total = 0;
        for (Pair<Service, Costs> cost : servicesCosts) {
            total += cost.getValue().getPrice();
        }
        return total;
    }

    public double getTotalCostsPerDay() {
        return getTotalCosts() / Constants.MONTH_DAYS;
    }

    public double getTotalCostsPerHour() {
        return getTotalCosts() / Constants.MONTH_HOURS;
    }
}
