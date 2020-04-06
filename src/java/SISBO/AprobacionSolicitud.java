package SISBO;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbExistencia;
import logic.SboTbSolixArti;

@Path("aprobacionSolicitudBodeguero")
public class AprobacionSolicitud {
    
    @Context
    private UriInfo context;
//restful para actualizar el estado de las solicitudes por aprobar del bodeguero 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SboTbExistencia> update(SboTbSolixArti soliXArti) {
        try {
            return Model.instance().disminuirExistencias(soliXArti);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    
}
