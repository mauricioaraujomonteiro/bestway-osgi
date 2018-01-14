/**
 *
 */
package br.com.bestway.core.restapicontrollerserviceimpl.activator;

import java.net.URI;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import br.com.bestway.core.restapicontrollerserviceimpl.controller.RouteControllerServiceImpl;
import br.com.bestway.core.restapicontrollerserviceimpl.controller.UserControllerServiceImpl;

/**
 * @author mmont
 *
 */
public class RestAPIBundleActivator implements BundleActivator
{

    private Logger LOG = Logger.getGlobal();
    private HttpServer server = null;

    /*
     * (non-Javadoc)
     *
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
     * BundleContext)
     */
    @Override
    public void start(BundleContext pContext) throws Exception
    {
        LOG.info("Starting Rest API Bundle.");
        try
        {
            ResourceConfig config = new ResourceConfig().packages();
            config.register(RouteControllerServiceImpl.class);
            config.register(UserControllerServiceImpl.class);
            URI uri = URI.create("http://localhost:9090");
            server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        }
        catch (Exception e)
        {
            LOG.severe("Error while starting the rest api bundle: " + e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext pContext) throws Exception
    {
        LOG.info("Stoping Rest API Bundle.");
        if (null != server)
        {
            server.shutdown();
        }
    }

}
