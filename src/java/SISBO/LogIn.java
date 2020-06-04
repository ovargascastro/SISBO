package SISBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbPersona;
import logic.Model;

@Path("login")
public class LogIn {

    @Context
    private UriInfo context;

    @Context
    HttpServletRequest request;

    @GET
    @Path("{user}/{password}")
    @Produces({MediaType.APPLICATION_JSON})
    public AbaaTbPersona login(@PathParam("user") String user, @PathParam("password") String password) {
        try {
            AbaaTbPersona logged = Model.instance().login(user, password);
            HttpSession session = request.getSession(true);
            session.setAttribute("logged", logged);
            return logged;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
