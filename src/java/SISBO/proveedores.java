/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.AbaaTbProveedor;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("proveedores")
public class proveedores {
    @Context
    private UriInfo context;
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaTbProveedor> getProveedores() throws ClassNotFoundException, SQLException {
        List<AbaaTbProveedor> lista = Model.instance().listaProveedores();
        List<AbaaTbProveedor> lista2 = lista;
        return lista2;

    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AbaaTbProveedor getProveedor(@PathParam("id") int id) {
        try {
            AbaaTbProveedor ob = Model.instance().getProveedor(id);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
