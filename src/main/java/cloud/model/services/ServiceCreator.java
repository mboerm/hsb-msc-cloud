package cloud.model.services;

public class ServiceCreator {
    public static Service getService(IServiceCreator factory){
        return factory.createService();
    }
}
