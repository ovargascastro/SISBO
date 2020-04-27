/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbArticulo;
import java.util.List;
import javax.ws.rs.QueryParam;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("articulos")
public class articulos {

    @Context
    private UriInfo context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarArticulos() {
        try {
            Model.instance().agregarArticulos();
            Model.instance().reiniciaLista();

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @POST
    @Path("articulo")
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarArticulo(SboTbArticulo articulo) {
        try {
            Model.instance().agregarArticuloSinOrden(articulo);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    @GET
    @Path("codConta")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbArticulo> search(@QueryParam("filtro") String filtro) {
        try {
          List<SboTbArticulo> lista = Model.instance().listadoArticulosFaltaContConta();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
