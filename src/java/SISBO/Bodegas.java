/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboSicop;
import logic.SboTbBodega;

/**
 *
 * @author oscar
 */
@Path("Bodegas")
public class Bodegas {

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarBodega(@Encoded SboTbBodega b) {
        try {
            Model.instance().agregarBodega(b);

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded SboTbBodega b) {
        try {

            Model.instance().updateBodega(b);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbBodega get(@PathParam("filtro") String filtro) {
        try {
            SboTbBodega ob = Model.instance().getBodega(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
