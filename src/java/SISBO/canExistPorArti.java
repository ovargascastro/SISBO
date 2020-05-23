/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import logic.Model;

/**
 *
 * @author Osvaldo Vargas
 */
@Path("cantExist")
public class canExistPorArti {
    @Context
    private UriInfo context;
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getCantExistencias(@QueryParam("id") int id) throws ClassNotFoundException, SQLException, Exception {
       // int cantidad = Model.instance().sumaExistencias2(id);
        String cant = Integer.toString(0);
        return cant;
    }
}
