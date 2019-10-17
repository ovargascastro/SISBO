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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbOrdenCompra;
import logic.SboTbSoliArti;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("solicitudArticulo")
public class solicitudesArticulos {
    @Context
    private UriInfo context;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarSolicitud(SboTbSoliArti solicitud) {
        try {
            Model.instance().agregarSolicitudArticulo(solicitud);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbSoliArti> search(@QueryParam("filtro") String filtro) {
        try {
          List<SboTbSoliArti> lista = Model.instance().listaSolicitudesArticulos(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
        @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbSoliArti get(@PathParam("filtro") int filtro) {
        try {
            SboTbSoliArti ob = Model.instance().getSboTbSoliArti(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
      
   
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbSoliArti cont) {
        try {
            Model.instance().actualizarEstSolicitud(cont);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
