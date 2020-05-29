package SISBO;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbArticulo;
import logic.SboTbOrdenCompra;

@Path("infoArticulo")
public class infoArticulo {

    @GET
    @Path("{filtro}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbArticulo get(@PathParam("filtro") String filtro) {
        try {
            SboTbArticulo ob = Model.instance().getArticuloSimple(filtro);
            if (ob.getSboTbOrdenCompra() == null) {
                SboTbOrdenCompra orden = new SboTbOrdenCompra();
                orden.setOcIdPk(0);
                ob.setSboTbOrdenCompra(orden);
            }
            return ob;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
