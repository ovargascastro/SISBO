package SISBO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbOrdenCompra;

@Path("ordenes")
public class ordenesCompra {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static final String[] accionBitacora = {"Insert Orden de Compra"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<SboTbOrdenCompra> search(@QueryParam("filtro") String filtro) {
        try {
            List<SboTbOrdenCompra> lista = Model.instance().listaOrdenesCompraC(filtro);
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SboTbOrdenCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @POST
    @Consumes({"application/json; charset=UTF-8"})
    public void agregarOrden(SboTbOrdenCompra orden) {
        try {
            Model.instance().agregarOrden(orden);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0], orden.getOcEsta());

        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    public java.util.Date parseFecha(String fecha) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        date = dateFormat.parse(fecha);
        return date;
    }
}
