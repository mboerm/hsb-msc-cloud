package cloud.model.services;

import cloud.configuration.Config;

public class IntegrationServiceCreator implements ServiceAbstractCreator {

    private String name;
    private String type;
    private String data;
    private String requests;
    private String[] messages;

    public IntegrationServiceCreator(String name, String type, String data, String requests, String[] messages) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.requests = requests;
        this.messages = messages;
    }

    @Override
    public Service createService() {
        String[] types = Config.getInstance().getConfigValuesAsArray("integration-type");

        if (type.equals(types[0])) {
            return new IntegrationService(name, type, data, requests, messages);
        } else if (type.equals(types[1])) {
            return new IntegrationService(name, type, data, requests, null);
        } else {
            return null;
        }
    }
}
