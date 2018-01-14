package br.com.bestway.core.restapicontrollerservice;

/**
 * @author mmont
 *
 */
public interface RouteControllerService
{
    /**
     * 
     * @param pBody
     * @return
     */
    public String postRoute(String pBody);

    /**
     * 
     * @return
     */
    public String getRoute();
}
