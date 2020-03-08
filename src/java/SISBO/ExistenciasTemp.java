/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;


import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbArticulo;
import logic.SboTbExistencia;

/**
 *
 * @author boris
 */

@Path("ExistenciasTemp")
public class ExistenciasTemp {

     @GET
   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbExistencia> search(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbExistencia> lista = Model.instance().listaExistenciasfiltro(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbExistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
     @GET

    @Path("{depto}/{arti}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public SboTbExistencia getSboTbExistencia(@PathParam("depto") String y, @PathParam("arti") String z)
            throws ClassNotFoundException, SQLException, Exception {
        String departamento = y;
        String articulo = z;
        SboTbExistencia lista = Model.instance().getSboTbExistencia(departamento, articulo);

        return lista;

    }
    
}

