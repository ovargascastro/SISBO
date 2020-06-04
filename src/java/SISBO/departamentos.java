package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import logic.Model;
import logic.AbaaTbDepartamento;

@Path("departamentos")
public class departamentos {

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaTbDepartamento> getDepartamentos() throws ClassNotFoundException, SQLException {
        List<AbaaTbDepartamento> lista = Model.instance().listaDepartamentos();
        List<AbaaTbDepartamento> lista2 = lista;
        return lista2;
    }
}
