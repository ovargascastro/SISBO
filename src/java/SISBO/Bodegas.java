package SISBO;

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
import logic.SboTbBodega;

@Path("Bodegas")
public class Bodegas {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert Bodega", "Update Bodega"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbBodega get(@PathParam("filtro") String filtro) {
        try {
            SboTbBodega ob = Model.instance().getBodega(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarBodega(@Encoded SboTbBodega b) {
        try {
            Model.instance().agregarBodega(b);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0], b.getBodeDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded SboTbBodega b) {
        try {
            Model.instance().updateBodega(b);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1], b.getBodeDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
