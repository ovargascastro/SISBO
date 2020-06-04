/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;


import javax.ws.rs.Consumes;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbExistencia;

/**
 *
 * @author boris
 */
@Path("ExistenciasSoli")
public class ExistenciaSoliPendiente {
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbExistencia existencia) {
        try {
           Model.instance().actualizarExistenciaSoliPendiente(existencia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    
}
