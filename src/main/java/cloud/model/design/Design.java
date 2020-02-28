package cloud.model.design;

import cloud.configuration.Constants;
import cloud.model.pricing.Costs;
import cloud.model.provider.Provider;
import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * Design class
 */
public class Design {
    /* list of added services */
    private final ObservableList<Service> servicesList = FXCollections.observableArrayList();
    /* List of costs per service */
    private final ObservableList<Pair<Service, Costs>> servicesCosts = FXCollections.observableArrayList();

    private Provider provider;
    private String usagePeriod;
    private String primaryLocation;
    private Integer numOfInstances;
    private Integer numOfRequests;
    private String periodOfRequests;
    private Integer numOfCapacity;
    private String periodOfCapacity;

    /**
     * Constructor
     */
    public Design() {
        setUsagePeriod("");
        setPrimaryLocation("");
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

    /**
     * Get matching service for provider
     */
    public void matchServices() {
        if (getProvider() != null) {
            for (Service service : servicesList) {
                service.setProviderService(getProvider().getMatchingServiceForID(service.getIdentifier()));
            }
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

    public String getPrimaryLocation() {return this.primaryLocation;}
    public void setPrimaryLocation(String location) {this.primaryLocation = location;}

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

    /**
     * Calculate total costs
     * @return total costs per month
     */
    public double getTotalCosts() {
        double total = 0;
        for (Pair<Service, Costs> cost : servicesCosts) {
            total += cost.getValue().getPrice();
        }
        return total;
    }

    /**
     * Calculate total costs per day
     * @return total costs per day
     */
    public double getTotalCostsPerDay() {
        return getTotalCosts() / Constants.MONTH_DAYS;
    }

    /**
     * Calculate total costs per hour
     * @return total costs per hour
     */
    public double getTotalCostsPerHour() {
        return getTotalCosts() / Constants.MONTH_HOURS;
    }

    /**
     * Scale costs
     */
    public void scaleCosts() {
        /*
         * TODO: implement function to scale costs of services
         */
    }

    /**
     * Compare costs
     */
    public void compareCosts() {
        /*
         * TODO: implement function to compare costs
         */
    }

    /**
     * Get design properties
     * @return String array
     */
    public String[] getDesignProperties() {
        return new String[] {
                this.getProvider().getServiceName(),
                this.getPrimaryLocation(),
                this.getUsagePeriod(),
                this.getNumOfInstances().toString(),
                this.getNumOfRequests().toString() + " " + this.getPeriodOfRequests(),
                this.getNumOfCapacity().toString() + " " + this.getPeriodOfCapacity()
        };
    }
}
