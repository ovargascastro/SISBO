/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbCatContable;
import logic.SboTbFamilia;


/**
 *
 * @author boris
 */

@Path("contables")
public class catContable {
  
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbCatContable> search(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbCatContable> lista = Model.instance().listaCatContables(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbCatContable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
      @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbCatContable get(@PathParam("filtro") int filtro) {
        try {
            SboTbCatContable ob = Model.instance().getSboTbCatContable(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbCatContable cont) {
        try {
            Model.instance().actualizarCatContable(cont);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
   
      @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarCont(SboTbCatContable cont) {
        try {
            Model.instance().crearCatContable(cont);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    
}


