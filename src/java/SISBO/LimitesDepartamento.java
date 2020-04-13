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
    public List<SboTbLimiteDpto> listLimites(@PathParam("depto") String depa, @PathParam("arti") String arti) throws ClassNotFoundException, SQLException {
        
       String depart = depa;
       String artic = arti;
       List<SboTbLimiteDpto> lista = Model.instance().listaLimites(depart, artic);
       
       return lista;
    }
    
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteLimites(SboTbLimiteDpto limi){
        try {
            Model.instance().deleteLimite(limi);
        } catch (Exception e) {
        }
    }
    
    /*@GET
    @Consumes(MediaType.APPLICATION_JSON)
    public void ReadLimites(String limi, String limi2){
        try {
            Model.instance().getLimites(limi, limi2);
        } catch (Exception e) {
        }
    }
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateLimites(SboTbLimiteDpto limi){
        try {
            Model.instance().updateLimite(limi);
        } catch (Exception e) {
        }
    }
}
