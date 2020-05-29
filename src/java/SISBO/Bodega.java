package SISBO;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbBodega;

@Path("ListaBodega")
public class Bodega {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Update Bodega"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    // se lista las bodegas ********
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbBodega> getBodegas() throws ClassNotFoundException, SQLException {
        //   List<SboTbBodega> lista = Model.instance().ListaBodega();
        return null;
    }

    //se bloquean las bodegas para que no se pueda utilizar "se elimina"
    @PUT
    @Consumes({"application/json; charset=UTF-8"})
    public void update(@Encoded SboTbBodega b) {
        try {
            b.setBodeEsta(0);
            Model.instance().deleteBodega(b);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0],b.getBodeDesc());
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

}
