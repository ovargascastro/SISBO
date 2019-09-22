/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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

import logic.SboTbFamilia;

/**
 *
 * @author oscar
 */
@Path("familias")
public class familias {

    @Context
    private UriInfo context;

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbFamilia> getFamilias(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {

        List<SboTbFamilia> lista = Model.instance().listaFamilias(filtro);
        List<SboTbFamilia> lista2 = lista;
        return lista2;

    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbFamilia get(@PathParam("filtro") String filtro) {
        try {
            SboTbFamilia ob = Model.instance().getSboTbFamilia(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbFamilia familia) {
        try {
            Model.instance().actualizarFamilia(familia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarFunc(SboTbFamilia familia) {
        try {
            Model.instance().crearFamilia(familia);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     *
     */
}
