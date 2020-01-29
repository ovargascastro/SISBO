/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
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
import logic.AbaaTbProveedor;
import logic.SboTbSubFamilia;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("proveedores")
public class proveedores {

    @Context
    private UriInfo context;

    @GET
    @Path("orden")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaTbProveedor> getProveedores() throws ClassNotFoundException, SQLException {
        List<AbaaTbProveedor> lista = Model.instance().listaProveedores();
        List<AbaaTbProveedor> lista2 = lista;
        return lista2;
    }

    @GET
    @Path("filtro")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<AbaaTbProveedor> getProveedoresFiltro(@QueryParam("fil") String y) throws ClassNotFoundException, SQLException {
        String filtro = y;
        List<AbaaTbProveedor> listaa = Model.instance().listaProvFiltro(filtro);
        List<AbaaTbProveedor> lista22 = listaa;
        return lista22;

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

    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded AbaaTbProveedor p) {
        try {
            Model.instance().actualizaProveedor(p);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarProveedor(@Encoded AbaaTbProveedor p) {
        try {
            Model.instance().agregarProveedor(p);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    //-
}
