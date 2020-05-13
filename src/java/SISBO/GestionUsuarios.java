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
import javax.ws.rs.core.MediaType;
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbBodega;

/**
 *
 * @author oscar
 */
@Path("gestionUsuarios")
public class GestionUsuarios {

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaTbPersona> getUsuarios() throws ClassNotFoundException, SQLException {
        List<AbaaTbPersona> lista = Model.instance().personasLista();
        return lista;
    }

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarUsuario(@Encoded AbaaTbPersona p) {
        try {
            Model.instance().insertarUsuario(p);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public AbaaTbPersona getUuario(@PathParam("filtro") String filtro) {
        try {
            AbaaTbPersona ob = Model.instance().getUsuario(filtro);
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
    
    
    
    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded AbaaTbPersona p) {
        System.out.println("");
        try {

            if(p.getPasswAux().equals("")){
                Model.instance().updatUsuarioSinContrasenna(p);
            //Se mantiene la misma contrasenna
            }else{
            
                Model.instance().updateUsuarioConContrasenna(p);
                //Se actualiza la contrasenna
            }
           
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
