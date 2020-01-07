package cloud.model;

import cloud.model.components.Component;

import java.util.ArrayList;

public class Design {

    private ArrayList<Component> components;
    private Component selectedComponent;

    private String usagePeriod;
    private String primaryRegion;
    private String numOfInstances;
    private String numOfRequests;
    private String periodOfRequests;
    private String numOfCapacity;
    private String periodOfCapacity;

    public Design() {
        components = new ArrayList<>();
    }

    public void addComponent(Component comp) {
        this.components.add(comp);
    }

    public void removeComponent(Component comp) {
        this.components.remove(comp);
    }

    public void clearComponents() {
        this.components.clear();
    }

    public Component getSelectedComponent() {
        return this.selectedComponent;
    }
    public void setSelectedComponent(Component comp) {
        this.selectedComponent = comp;
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
