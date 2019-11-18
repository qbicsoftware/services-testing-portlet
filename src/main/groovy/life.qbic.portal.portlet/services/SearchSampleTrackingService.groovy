package life.qbic.portal.portlet.services

import life.qbic.portal.utils.ConfigurationManagerFactory
import life.qbic.services.ConsulServiceFactory
import life.qbic.services.Service
import life.qbic.services.ServiceConnector
import life.qbic.services.ServiceType
import life.qbic.services.connectors.ConsulConnector

class SearchSampleTrackingService {

    def serviceRegistryUrl

    SearchSampleTrackingService() {
        // Read properties
        def config = ConfigurationManagerFactory.getInstance()
        // Setup service registry
        serviceRegistryUrl = new URL(config.servicesRegistryUrl)
    }

    List<Service> execute(){
        def serviceList = []
        ServiceConnector connector = new ConsulConnector(serviceRegistryUrl)
        connector.withCloseable {
            ConsulServiceFactory factory = new ConsulServiceFactory(it)
            serviceList.addAll(factory.getServicesOfType(ServiceType.SAMPLE_TRACKING))
        }
        return serviceList
    }

}
