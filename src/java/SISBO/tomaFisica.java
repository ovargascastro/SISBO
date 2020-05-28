package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import logic.Model;
import logic.SboTbExistencia;
import javax.ws.rs.Path;

@Path("tomaFisica")
public class tomaFisica {
    
    @GET
    @Path("{bodeg}/{depto}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbExistencia> listaExistenciasStocks(@PathParam("bodeg") String bodega, @PathParam("depto") String departamento)
            throws ClassNotFoundException, SQLException {
        return Model.instance().listaExistenciasStocks(bodega, departamento);
    }
}
