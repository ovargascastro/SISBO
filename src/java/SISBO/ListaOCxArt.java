/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbArticulo;

/**
 *
 * @author Marco
 */

@Path("ListaOCxArt")

public class ListaOCxArt {
    @Context
    private UriInfo context;
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbArticulo> getArticulos(@QueryParam("numeroOCArt") String filtro) throws ClassNotFoundException, SQLException {
        List<SboTbArticulo> lista = Model.instance().ListaArtxOrden(filtro);
        return lista;
    }
}
