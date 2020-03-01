package SISBO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import logic.Model;
import logic.SboTbArticulo;
import logic.SboTbOrdenCompra;
import logic.SboTbSoliArti;
import logic.SboTbSolixArti;

@Path("aprobacionSolicitudBodeguero")
public class AprobacionSolicitud {
    
    @Context
    private UriInfo context;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbSolixArti soliXArti) {
        try {
            //Model.instance(). filtraSolixArti(soliXArti);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
