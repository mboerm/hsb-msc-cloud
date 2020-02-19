package cloud.model.services;

import cloud.configuration.Config;

public class IntegrationServiceCreator implements IServiceCreator {

    private String name;
    private String integrationType;
    private String integrationMode;
    private int data;
    private int requests;
    private int[] messages;

    public IntegrationServiceCreator(String name, String type, String mode, int data, int requests, int[] messages) {
        this.name = name;
        this.integrationType = type;
        this.integrationMode = mode;
        this.data = data;
        this.requests = requests;
        this.messages = messages;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("integration-type");
        String[] modes = Config.getInstance().getConfigValuesAsArray("integration-communication-mode");
        IntegrationService integrationService;

        if (integrationType.equals(types[0])) {
            integrationService = new IntegrationService(name, integrationType, integrationMode, data, requests, messages);
            integrationService.setIdentifier(integrationType + " (" + integrationMode + ")");
            if (integrationMode.equals(modes[1])) {
                integrationService.setMessages(null);
            }
        } else {
            return null;
        }
        integrationService.setCategory(Config.getInstance().getConfigValuesAsArray("service-categories")[5]);
        return integrationService;
    }
}
