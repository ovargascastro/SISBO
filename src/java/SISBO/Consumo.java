/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import data.RelDatabase;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import logic.AbaaTbPersona;
import logic.Model;
import logic.SboTbSolixArti;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;

/**
 *
 * @author oscar
 */
@Path("Consumo")
public class Consumo {

    @Context
    HttpServletRequest request;

    @GET
    @Path("{arti}/{inicio}/{fin}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbSolixArti> getExistencias(@PathParam("arti") String x, @PathParam("inicio") String y, @PathParam("fin") String z) {

        AbaaTbPersona logged = (AbaaTbPersona) request.getSession().getAttribute("logged");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1;
        java.util.Date date2;
        String articulo = x;
        String fInicio = y;
        String fFinal = z;

        try {
            date1 = dateFormat.parse(fInicio);
            date2 = dateFormat.parse(fFinal);
            String depa = logged.getDepartamento().getDeptoIdPk();
            return Model.instance().listaReporte(articulo, depa, fInicio, fFinal);
        } catch (Exception ex) {
            Logger.getLogger(Consumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
