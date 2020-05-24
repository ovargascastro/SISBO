package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import logic.Model;
import logic.SboTbOrdenCompra;

@Path("listadoOCArtNuevos")
public class listadoOCArtNuevos {
    
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbOrdenCompra> getFamilias(@QueryParam("numeroOC") String filtro) throws ClassNotFoundException, SQLException {
        List<SboTbOrdenCompra> lista = Model.instance().listaOrdenesCompra(filtro);
        return lista;
    }
}
