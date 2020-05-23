/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

/**
 *
 * @author boris
 */


import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbBodega;

@Path("BodegaListaOC")
public class BodegaListaOC {

    @Context
    private UriInfo context;
//se listan las bodgas existentes en la base
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbBodega> listaBodegas(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {
        List<SboTbBodega> lista = Model.instance().listaBodegas(filtro);
        return lista;
    }

}

