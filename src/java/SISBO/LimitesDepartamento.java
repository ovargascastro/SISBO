package SISBO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbLimiteDpto;

@Path("limiDepa")
public class LimitesDepartamento {

    @Context
    private UriInfo context;
    @Context
    HttpServletRequest request;

    private static String arti;
    private static String dpt;
    private static final String[] accionBitacora = {"Insert Limite Minimo", "Update Limite Minimo", "Delete Limite Minimo"};

    private String obtenerNombre() {
        AbaaTbPersona logged = (AbaaTbPersona) request.getSession(true).getAttribute("logged");
        return String.format("%s %s %s", logged.getPersNomb(), logged.getPersApe1(), logged.getPersApe2());
    }

    private String obtenerDescripcion(SboTbLimiteDpto limite) {
        return String.format("Id Dpto:%s / Id Sicop:%s", limite.getAbaaTbDepartamento().getDeptoIdPk(), limite.getSboSicop().getSicopId());
    }

    @GET
    @Path("{depto}/{arti}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbLimiteDpto> listLimites(@PathParam("depto") String x, @PathParam("arti") String y) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<SboTbLimiteDpto> lista = new ArrayList<>();

        String depart = x;
        String artic = y;
        dpt = depart;
        arti = artic;
        if (arti.equals("0")) {
            lista = (ArrayList<SboTbLimiteDpto>) Model.instance().listaLimitesxDepartamento(depart);
        } else if (dpt.equals("0")) {
            lista = (ArrayList<SboTbLimiteDpto>) Model.instance().listaLimitesxArti(arti);
        } else {
            lista = (ArrayList<SboTbLimiteDpto>) Model.instance().listaLimites(depart, artic);
        }
        return lista;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void agregarlimites(SboTbLimiteDpto limi) {
        try {
            Model.instance().agregarLimite(limi);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[0], obtenerDescripcion(limi));
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateLimites(SboTbLimiteDpto limi) {
        try {
            Model.instance().updateLimite(limi);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[1], obtenerDescripcion(limi));
        } catch (Exception e) {
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteLimites(SboTbLimiteDpto limi) {
        try {
            Model.instance().deleteLimite(limi);
            Model.instance().insertarEnBitacora(obtenerNombre(), accionBitacora[2], obtenerDescripcion(limi));
        } catch (Exception e) {
        }
    }
}
