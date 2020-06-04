package SISBO;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import logic.Model;
import logic.SboTbArticulo;

@Path("descCatsArticulo")
public class descCatsArticulo {

    @GET
    @Path("{artiID}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public SboTbArticulo get(@PathParam("artiID") String filtro) {
        try {
            return Model.instance().buscaDescCatsArticulo(filtro);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }
}
