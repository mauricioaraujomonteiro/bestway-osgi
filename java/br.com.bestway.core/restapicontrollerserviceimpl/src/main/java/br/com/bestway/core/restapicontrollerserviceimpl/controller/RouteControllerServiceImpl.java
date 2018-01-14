package br.com.bestway.core.restapicontrollerserviceimpl.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import br.com.bestway.core.restapicontrollerservice.RouteControllerService;

/**
 * @author mmont
 *
 */

@Path("/route")
public class RouteControllerServiceImpl extends Application implements RouteControllerService
{
    /* (non-Javadoc)
     * @see br.com.bestway.core.restapicontrollerservice.RouteControllerService#postRoute(java.lang.String)
     */
    @Override
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String postRoute(String pBody)
    {
        System.out.println("merda");
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see br.com.bestway.core.restapicontrollerservice.RouteControllerService#getRoute()
     */
    @Override
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRoute()
    {
        // TODO Auto-generated method stub
        System.out.println("CHEGOU AQUI");
        return "GET ROUTE";
    }
}
