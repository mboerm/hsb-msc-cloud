package cloud.model;

import cloud.model.components.Component;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Design {

    private int selectedComponent;
    private final ObservableList<Component> componentsList =
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

    public ObservableList<Component> getComponentsList() {
        return this.componentsList;
    }

    public void addComponent(Component comp) {
        this.componentsList.add(comp);
    }

    public Component getComponent(String name, String category) {
        for (int i = 0; i < this.componentsList.size(); i++) {
            if (this.componentsList.get(i).getName() == name && this.componentsList.get(i).getCategory() == category) {
                return this.componentsList.get(i);
            }
        }
        return null;
    }

    public void removeComponent(Component comp) {
        this.componentsList.remove(comp);
    }

    public void clearComponents() {
        this.componentsList.clear();
    }

    public int getSelectedComponent() {
        return this.selectedComponent;
    }
    public void setSelectedComponent(int id) {
        this.selectedComponent = id;
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
