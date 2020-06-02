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
import logic.Model;
import logic.SboTbSubFamilia;

@Path("subfamilias")
public class subFamilias {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert SubFamilia", "Update SubFamilia"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbSubFamilia> getSubFamilias(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {
        List<SboTbSubFamilia> lista = Model.instance().listaSubFamilias(filtro);
        List<SboTbSubFamilia> lista2 = lista;
        return lista2;
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbSubFamilia get(@PathParam("filtro") String filtro) {
        try {
            SboTbSubFamilia ob = Model.instance().getSboTbSubfamilia(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarSubFamilia(SboTbSubFamilia subfamilia) {
        try {
            Model.instance().crearSubFamilia(subfamilia);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0],subfamilia.getSubFamiDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbSubFamilia subfamilia) {
        try {
            Model.instance().actualizarSubFamilia(subfamilia);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1],subfamilia.getSubFamiDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}