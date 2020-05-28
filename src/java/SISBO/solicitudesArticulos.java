package SISBO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbOrdenCompra;
import logic.SboTbSoliArti;

@Path("solicitudArticulo")
public class solicitudesArticulos {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert Solicitud Articulo", "Update Solicitud Articulo", "Pendiente"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbSoliArti> search(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbSoliArti> lista = Model.instance().listaSolicitudesArticulos(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbSoliArti get(@PathParam("filtro") int filtro) {
        try {
            SboTbSoliArti ob = Model.instance().getSboTbSoliArti(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarSolicitud(SboTbSoliArti solicitud) {
        try {
            Model.instance().InsertarSoli(solicitud);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0], accionBitacora[2]);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbSoliArti cont) {
        try {
            Model.instance().actualizarEstSolicitud(cont);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1], cont.getSolArtiEsta());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
