package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

public class AdministrationServiceCreator implements IServiceCreator {

    private String name;
    private String administrationType;
    private Integer metrics;
    private Integer apiRequests;
    private Pair<Integer, Integer> data;
    private Integer events;
    private boolean loggerState;

    public AdministrationServiceCreator(String name, String administrationType, Integer metrics, Integer apiRequests, Pair<Integer, Integer> data, Integer events, boolean loggerState) {
        this.name = name;
        this.administrationType = administrationType;
        this.metrics = metrics;
        this.apiRequests = apiRequests;
        this.data = data;
        this.events = events;
        this.loggerState = loggerState;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("administration-type");
        if (administrationType.equals(types[0])) {
            AdministrationService adminService = new AdministrationService(name, administrationType, metrics, apiRequests, data, events, loggerState);
            String[] modes = Config.getInstance().getConfigValuesAsArray("administration-monitoring-mode");
            if (loggerState) {
                adminService.setIdentifier(administrationType + " (" + modes[0] + ")");
            } else {
                adminService.setIdentifier(administrationType + " (" + modes[1] + ")");
            }
            return adminService;
        } else {
            return null;
        }
    }
}
