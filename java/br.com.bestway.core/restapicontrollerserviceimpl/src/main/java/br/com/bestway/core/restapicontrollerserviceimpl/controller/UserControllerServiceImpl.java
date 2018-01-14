/**
 *
 */
package br.com.bestway.core.restapicontrollerserviceimpl.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.bestway.core.restapicontrollerservice.UserControllerService;

/**
 * @author mmont
 *
 */
@Path("/user")
public class UserControllerServiceImpl implements UserControllerService
{

    /* (non-Javadoc)
     * @see br.com.bestway.core.restapicontrollerservice.UserControllerService#getUser()
     */
    @Override
    @GET
    public String getUser()
    {
        // TODO Auto-generated method stub
        return "User info";
    }

}
