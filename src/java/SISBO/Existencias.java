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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.Model;
import logic.SboTbExistencia;

@Path("Existencias")
public class Existencias {

    @Context
    private UriInfo context;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbExistencia existencia) {
        try {
            Model.instance().aumentarExistenciasArticulo(existencia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
