package SISBO;

import java.sql.SQLException;

import java.util.List;

import javax.ws.rs.Consumes;
<<<<<<< HEAD

=======
import javax.ws.rs.Encoded;
>>>>>>> f3176b462ff20101acc0708c97964fed1b7d7c42
import javax.ws.rs.GET;

import javax.ws.rs.NotFoundException;
<<<<<<< HEAD

=======
import javax.ws.rs.POST;
>>>>>>> f3176b462ff20101acc0708c97964fed1b7d7c42
import javax.ws.rs.PUT;

import javax.ws.rs.Path;

import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;

import javax.ws.rs.QueryParam;

import javax.ws.rs.core.Context;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.UriInfo;

import logic.Model;
<<<<<<< HEAD

=======
import logic.SboSicop;
import logic.SboTbArticulo;
import logic.SboTbCatArticulo;
>>>>>>> f3176b462ff20101acc0708c97964fed1b7d7c42
import logic.SboTbExistencia;

@Path("Existencias")

public class Existencias {

    @Context

    private UriInfo context;
    private static String bode;
    private static String arti;
    private static String dpt;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)

    public void update(SboTbExistencia existencia) {

        try {

            Model.instance().aumentarExistenciasArticulo(existencia);

        } catch (Exception ex) {

            throw new NotFoundException();

        }

<<<<<<< HEAD
    }
    
     @GET

=======
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SboTbExistencia> updateCant(SboTbExistencia existencia) {
        try {

            Model.instance().actualizaCantExist(existencia);
            List<SboTbExistencia> lista = Model.instance().listaExistencias(bode, dpt, arti);
            return lista;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }


    
    @GET
>>>>>>> f3176b462ff20101acc0708c97964fed1b7d7c42
    @Path("{bodeg}/{depto}/{arti}")

    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)

    public List<SboTbExistencia> getExistencias(@PathParam("bodeg") String x, @PathParam("depto") String y, @PathParam("arti") String z)

            throws ClassNotFoundException, SQLException {

        String bodega = x;

        String departamento = y;

        String articulo = z;
<<<<<<< HEAD



=======
        bode = bodega;
        arti = articulo;
        dpt = departamento;
>>>>>>> f3176b462ff20101acc0708c97964fed1b7d7c42
        List<SboTbExistencia> lista = Model.instance().listaExistencias(bodega, departamento, articulo);

        return lista;

    }



//    @GET

//    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)

//    public List<SboTbArticulo> getArticulos(@QueryParam("filtro") String filtro) throws ClassNotFoundException, SQLException {

//        List<SboTbArticulo> lista = Model.instance().listaArticulosExistencia(filtro);

//        return lista;

//    }



   
}