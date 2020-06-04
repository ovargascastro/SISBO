package SISBO;

import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("cantExist")
public class canExistPorArti {

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getCantExistencias(@QueryParam("id") int id) throws ClassNotFoundException, SQLException, Exception {
        String cant = Integer.toString(0);
        return cant;
    }
}
