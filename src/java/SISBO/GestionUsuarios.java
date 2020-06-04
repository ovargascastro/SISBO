package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbPersona;
import logic.Model;

@Path("gestionUsuarios")
public class GestionUsuarios {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert Usuario", "Update Usuario"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaTbPersona> getUsuarios() throws ClassNotFoundException, SQLException {
        List<AbaaTbPersona> lista = Model.instance().personasLista();
        return lista;
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AbaaTbPersona getUuario(@PathParam("filtro") String filtro) {
        try {
            AbaaTbPersona ob = Model.instance().getUsuario(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarUsuario(@Encoded AbaaTbPersona p) {
        try {
            Model.instance().insertarUsuario(p);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0],p.getPersCedu());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded AbaaTbPersona p) {
        System.out.println("");
        try {
            if (p.getPasswAux().equals("")) {
                Model.instance().updatUsuarioSinContrasenna(p);
                //Se mantiene la misma contrasenna
            } else {
                Model.instance().updateUsuarioConContrasenna(p);
                //Se actualiza la contrasenna
            }
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1],p.getPersCedu());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
