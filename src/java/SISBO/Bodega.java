
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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboSicop;
import logic.SboTbBodega;

/**
 *
 * @author Marco
 */
@Path("ListaBodega")
public class Bodega {

    @Context
    private UriInfo conext;
// se lista las bodegas ********
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbBodega> getBodegas() throws ClassNotFoundException, SQLException {
        //   List<SboTbBodega> lista = Model.instance().ListaBodega();
        return null;

    }
//se bloquean las bodegas para que no se pueda utilizar "se elimina"
    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded SboTbBodega b) {
        try {
           b.setBodeEsta(0);
           Model.instance().deleteBodega(b);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
