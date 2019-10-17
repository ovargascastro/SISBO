/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import logic.Model;
import logic.SboTbArticulo;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("artSolTemp")
public class ArituculoXSolicitudTemporal {
    @Context
//    private UriInfo context;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbArticulo> search(@QueryParam("temporales") String cedula) {
        try {
            List<SboTbArticulo> lista = new ArrayList();
            Map<Integer,SboTbArticulo> list = Model.instance().getListaArtxSolTemp();
            list.values().forEach((art) -> {
                lista.add(art);
            });
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbArticulo get(@PathParam("filtro") int filtro) {
        try {
            SboTbArticulo art = Model.instance().getArtxSolTemp(filtro);
            return art;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public void agregarArticulo(SboTbArticulo articulo) {
        try {
            Model.instance().agregarArtxSolTemp(articulo);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void del(@PathParam("id") Integer id) {
        try {
            SboTbArticulo art = Model.instance().getListaArtxSolTemp().get(id);
            Model.instance().eliminarArtxSolTemp(art);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
