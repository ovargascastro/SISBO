/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbOrdenCompra;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("ordenes")
public class ordenesCompra {

    @Context
    private UriInfo context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarOrden(SboTbOrdenCompra orden) {
        try {
            Model.instance().agregarOrden(orden);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbOrdenCompra> search(@QueryParam("filtro") String filtro) {
        try {
          List<SboTbOrdenCompra> lista = Model.instance().listaOrdenesCompraC(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public java.util.Date parseFecha(String fecha) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        date = dateFormat.parse(fecha);
        return date;
    }
    
//    @DELETE
//    @Path("ordenes")
//    @Produces({MediaType.APPLICATION_JSON})
//    public void del(@PathParam("ordenes") int id) {
//        try {
//            Model.instance().EliminarOrden(id); 
//        } catch (Exception ex) {
//            throw new NotFoundException();
//        }
//}
//    
}
