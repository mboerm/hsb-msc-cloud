package cloud.model.services;

public class ServiceCreator {
    public static Service getService(ServiceAbstractCreator factory){
        return factory.createService();
    }
}
