package SISBO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbArticulo;
import logic.SboTbOrdenCompra;

@Path("artxordenc")
public class articulosXorden {

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbArticulo> search(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbArticulo> lista = Model.instance().listaArticulosOrdenCompra(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbArticulo p) {
        try {
            Model.instance().actualizarCodigoCont(p);
            SboTbOrdenCompra obj = p.getSboTbOrdenCompra();
            obj.setOcEsta("No Procesada");
            Model.instance().actualizaEstadoOrdenCom(obj);
            Model.instance().actualizaEstadoOrdenConProyectos(Integer.toString(obj.getOcIdPk()));
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
