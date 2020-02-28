package cloud.model.services;

/**
 * Service factory
 */
public class ServiceFactory {
    /**
     * Get service by service creator
     * @param serviceCreator Service creator of service category
     * @return service object
     */
    public static Service getService(ServiceCreator serviceCreator){
        return serviceCreator.createService();
    }
}
