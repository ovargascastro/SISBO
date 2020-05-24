package SISBO;

import java.sql.SQLException;
import java.util.List;
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
import logic.SboTbCatArticulo;
import logic.Model;
import logic.SboTbFamilia;
import logic.SboTbSubFamilia;

@Path("catArticulos")
public class catArticulos {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert Catalogo Articulo", "Update Catalogo Articulo"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbCatArticulo> getCatArticulos(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {
        List<SboTbCatArticulo> lista = Model.instance().listaCatArticulos(filtro);
        return lista;
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbCatArticulo get(@PathParam("filtro") String filtro) {
        try {
            SboTbCatArticulo ob = Model.instance().getCatArticulo(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarCatArticulo(SboTbCatArticulo articulo) {
        try {
            Model.instance().crearCatArticulo(articulo);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0], articulo.getCatDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbCatArticulo articulo) {
        try {
            Model.instance().actualizarCatArticulo(articulo);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1], articulo.getCatDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
