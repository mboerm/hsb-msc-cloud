package cloud.model.services;

public class ServiceFactory {
    public static Service getService(IServiceCreator serviceCreator){
        return serviceCreator.createService();
    }
}
