/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbProveedor;
import logic.AbaaTbUsuario;
import logic.Model;
import logic.SboTbArticulo;
import logic.SboTbFamilia;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("articulostemporales")
public class ArticulosTemporales {
    
    @Context
//    private UriInfo context;
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbArticulo> search(@QueryParam("temporales") String cedula) {
        try {
            List<SboTbArticulo> lista = new ArrayList();
            Map<Integer,SboTbArticulo> list = Model.instance().getListaTemp();
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
            SboTbArticulo art = Model.instance().getArticuloTemporal(filtro);
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
            Model.instance().agregarArtTemp(articulo);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public void del(@PathParam("id") Integer id) {
        try {
            SboTbArticulo art = Model.instance().getListaTemp().get(id);
            Model.instance().eliminarArtTemp(art);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbArticulo articulo) {
        try {
            Model.instance().actualizarArticuloTemporal(articulo);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
