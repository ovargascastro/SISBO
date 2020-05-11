package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbDepartamento;
import logic.Model;
import logic.SboSicop;
import logic.SboTbBodega;
import logic.SboTbExistencia;
import javax.ws.rs.Path;

@Path("tomaFisica")
public class tomaFisica {

    @Context
    private UriInfo context;

    @GET
    @Path("{bodeg}/{depto}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbExistencia> listaExistenciasStocks(@PathParam("bodeg") String bodega, @PathParam("depto") String departamento)
            throws ClassNotFoundException, SQLException {
        return Model.instance().listaExistenciasStocks(bodega, departamento);
    }

}
