/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

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
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbFamilia;
import logic.SboTbSubFamilia;

/**
 *
 * @author oscar
 */
@Path("subfamilias")
public class subFamilias {

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbSubFamilia> getSubFamilias(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {

        List<SboTbSubFamilia> lista = Model.instance().listaSubFamilias(filtro);
        List<SboTbSubFamilia> lista2 = lista;
        return lista2;

    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbSubFamilia get(@PathParam("filtro") String filtro) {
        try {
              SboTbSubFamilia ob = Model.instance().getSboTbSubfamilia(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbSubFamilia subfamilia) {
        try {
            Model.instance().actualizarSubFamilia(subfamilia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
}
