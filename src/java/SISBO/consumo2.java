package SISBO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import logic.AbaaTbDepartamento;
import logic.Model;
import logic.SboTbSolixArti;

@Path("consumo2")
public class consumo2 {

    @GET
    @Path("{arti}/{inicio}/{fin}/{dep}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbSolixArti> getConsumoDpto(@PathParam("arti") String x, @PathParam("inicio") String y, @PathParam("fin") String z, @PathParam("dep") String d) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1;
        java.util.Date date2;
        String articulo = x;
        String fInicio = y;
        String fFinal = z;
        String departamento = d;
        try {
            date1 = dateFormat.parse(fInicio);
            date2 = dateFormat.parse(fFinal);
            return Model.instance().listaReporte(articulo, d, fInicio, fFinal);
        } catch (Exception ex) {
            Logger.getLogger(Consumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @GET
    @Path("{dep}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public AbaaTbDepartamento getDpto( @PathParam("dep") String d) {
        try {
            AbaaTbDepartamento depa = Model.instance().getDepartamentoPorId(d);
            return depa; 
        } catch (Exception ex) {
            Logger.getLogger(Consumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
