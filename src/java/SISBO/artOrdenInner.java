/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbArticulo;
import logic.SboTbOrdenCompra;
/**
 *
 * @author oscar
 */
@Path("artInner")
public class artOrdenInner {
    
        @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbArticulo> search(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbArticulo> lista = Model.instance().listaArticulosOrdenCompraConta(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
