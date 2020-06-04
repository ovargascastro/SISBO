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

@Path("solicitudArticuloPDF")
public class solicitudesArticulosParaPDF {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbSoliArti> searchParaModal(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbSoliArti> lista = Model.instance().listaSolicitudesArticulosParaPDF(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
