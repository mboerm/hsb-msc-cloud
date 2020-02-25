package cloud.model.services;

public class ServiceFactory {
    public static Service getService(ServiceCreator serviceCreator){
        return serviceCreator.createService();
    }
}
