/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbArticulo;

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
}
