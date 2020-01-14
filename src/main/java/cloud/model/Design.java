package cloud.model;

import cloud.model.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Design {

    private int selectedService;
    private final ObservableList<Service> servicesList =
            FXCollections.observableArrayList();

    private String usagePeriod;
    private String primaryRegion;
    private String numOfInstances;
    private String numOfRequests;
    private String periodOfRequests;
    private String numOfCapacity;
    private String periodOfCapacity;

    public Design() {

    }

    public ObservableList<Service> getServicesList() {
        return this.servicesList;
    }

    public void addService(Service service) {
        this.servicesList.add(service);
    }

    public Service getService(String name, String category) {
        for (int i = 0; i < this.servicesList.size(); i++) {
            if (this.servicesList.get(i).getName() == name && this.servicesList.get(i).getCategory() == category) {
                return this.servicesList.get(i);
            }
        }
        return null;
    }

    public void removeService(Service service) {
        this.servicesList.remove(service);
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
