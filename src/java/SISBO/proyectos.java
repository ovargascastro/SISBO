package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import logic.Model;
import logic.AbaaProyectos;

@Path("proyectos")
public class proyectos {

    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<AbaaProyectos> getProyectos() throws ClassNotFoundException, SQLException {
        List<AbaaProyectos> lista = Model.instance().listaProyectos();
        List<AbaaProyectos> lista2 = lista;
        return lista2;
    }
}
