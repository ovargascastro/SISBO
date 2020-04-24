/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

/**
 *
 * @author Marco
 */


import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbLimiteDpto;


@Path("limiDepa")
public class LimitesDepartamento {
    
    @Context
    private UriInfo context;
    private static String arti;
    private static String dpt;
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarlimites(SboTbLimiteDpto limi) {
        try {
            Model.instance().agregarLimite(limi);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @GET
    @Path("{depto}/{arti}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbLimiteDpto> listLimites(@PathParam("depto") String x, @PathParam("arti") String y) throws ClassNotFoundException, SQLException, Exception {
     //  List<SboTbLimiteDpto> lista = (List<SboTbLimiteDpto>) new SboTbLimiteDpto(); 
       String depart = x;
       String artic = y;
       dpt = depart;
       arti = artic;
    //   if(arti== null){
     // lista = Model.instance().listaLimitesxDepartamento(depart);
    //   }
   //    else{
     List<SboTbLimiteDpto>  lista = Model.instance().listaLimites(depart, artic);
   //    }
       return lista;
    }
    
    /*@GET
    @Path("{depto}/{arti}")
    @Consumes(MediaType.APPLICATION_JSON)
    public SboTbLimiteDpto ReadLimite(@PathParam("depto") String x, @PathParam("arti") String y) throws ClassNotFoundException, SQLException, Exception {
        SboTbLimiteDpto limite = new SboTbLimiteDpto();
        try {
            String depart = x;
            String artic = y;
            dpt = depart;
            arti = artic;
            
           limite = Model.instance().getLimite(depart, artic);
           
        } catch (Exception e) {
        }
        return limite;
    }
    */
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteLimites(SboTbLimiteDpto limi){
        try {
            Model.instance().deleteLimite(limi);
        } catch (Exception e) {
        }
    }
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateLimites(SboTbLimiteDpto limi){
        try {
            Model.instance().updateLimite(limi);
        } catch (Exception e) {
        }
    }
}
