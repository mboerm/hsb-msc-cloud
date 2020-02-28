package cloud.model.services;

import cloud.configuration.Config;
import javafx.util.Pair;

/**
 * Administration service creator
 */
public class AdministrationServiceCreator implements ServiceCreator {

    private String name;
    private String administrationType;
    private int metrics;
    private int apiRequests;
    private Pair<Integer, Integer> data;
    private int events;
    private boolean loggerState;

    /**
     * Constructor
     * @param name service name
     * @param administrationType administration type
     * @param metrics number of metric
     * @param apiRequests number of requests
     * @param data number of data
     * @param events number of events
     * @param loggerState boolean of logger state
     */
    public AdministrationServiceCreator(String name, String administrationType, int metrics, int apiRequests, Pair<Integer, Integer> data, int events, boolean loggerState) {
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
        AdministrationService adminService;
        String mode;
        if (administrationType.equals(types[0])) {
            adminService = new AdministrationService(name, administrationType, metrics, apiRequests, data, events, loggerState);
            String[] modes = Config.getInstance().getConfigValuesAsArray("administration-monitoring-mode");
            if (loggerState) {
                mode = modes[0];
            } else {
                mode = modes[1];
            }
        } else {
            return null;
        }
        /* set category of service */
        adminService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[6]);
        /* set identifier of service */
        adminService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(adminService.getCategory(), administrationType, mode));
        return adminService;
    }
}
