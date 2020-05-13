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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import logic.AbaaTbRolxPermiso;
import logic.Model;
import logic.SboTbFamilia;

/**
 *
 * @author oscar
 */
@Path("roles")
public class roles {

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaTbRolxPermiso> getRoles() throws ClassNotFoundException, SQLException {

        try {
            List<AbaaTbRolxPermiso> lista = Model.instance().rolesLista();

            return lista;
        } catch (Exception ex) {
            Logger.getLogger(roles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
