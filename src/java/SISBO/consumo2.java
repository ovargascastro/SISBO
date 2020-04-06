/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbCatArticulo;
import logic.SboTbSolixArti;

/**
 *
 * @author oscar
 */
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

            return Model.instance().listaReporte(articulo,departamento,fInicio,fFinal);

        } catch (Exception ex) {
            Logger.getLogger(Consumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
