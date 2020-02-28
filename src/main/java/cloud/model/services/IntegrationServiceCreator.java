package cloud.model.services;

import cloud.configuration.Config;

/**
 * Integration service creator
 */
public class IntegrationServiceCreator implements ServiceCreator {

    private String name;
    private String integrationType;
    private String integrationMode;
    private int data;
    private int requests;
    private int[] messages;

    /**
     * Constructor
     * @param name name of service
     * @param integrationType integration type
     * @param mode integration type mode
     * @param data number of data
     * @param requests number of requests
     * @param messages number of messages as array
     */
    public IntegrationServiceCreator(String name, String integrationType, String mode, int data, int requests, int[] messages) {
        this.name = name;
        this.integrationType = integrationType;
        this.integrationMode = mode;
        this.data = data;
        this.requests = requests;
        this.messages = messages;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("integration-type");
        IntegrationService integrationService;

        if (integrationType.equals(types[0])) {
            // Messaging
            integrationService = new IntegrationService(name, integrationType, integrationMode, data, requests, messages);
        } else {
            return null;
        }
        /* set category of service */
        integrationService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[5]);
        /* set identifier of service */
        integrationService.setIdentifier(ServiceChecker.getInstance().getServiceIdentifier(integrationService.getCategory(), integrationType, integrationMode));
        return integrationService;
    }
}
