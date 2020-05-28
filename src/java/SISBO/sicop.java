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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbPersona;
import logic.AbaaTbProveedor;
import logic.Model;
import logic.SboSicop;

@Path("Sicop")
public class sicop {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert SICOP", "Update SICOP"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboSicop> listaSicop(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {
        List<SboSicop> lista = Model.instance().listadoSicop(filtro);
        return lista;
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboSicop get(@PathParam("filtro") String filtro) {
        try {
            SboSicop ob = Model.instance().obtenerSicop(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("filtro")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboSicop> getSicopFiltro(@QueryParam("fil") String y) throws ClassNotFoundException, SQLException {
        String filtro = y;
        List<SboSicop> ob = Model.instance().listadoSicopFiltro(filtro);
        return ob;
    }

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarSicop(@Encoded SboSicop p) {
        try {
            Model.instance().agregarSicop(p);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0], p.getSicopDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded SboSicop p) {
        try {
            Model.instance().actualizarSicop(p);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1], p.getSicopDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
