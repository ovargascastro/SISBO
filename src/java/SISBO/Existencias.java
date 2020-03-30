package SISBO;


import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Encoded;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import logic.AbaaTbDepartamento;
import logic.Model;
import logic.SboSicop;
import logic.SboTbArticulo;
import logic.SboTbBodega;
import logic.SboTbCatArticulo;
import logic.SboTbExistencia;

@Path("Existencias")
public class Existencias {

    @Context
    private UriInfo context;
    private static String bode;
    private static String arti;
    private static String dpt;
    
    private SboTbExistencia actualizaDatosExistencia(SboTbExistencia e){
        SboTbBodega b= new SboTbBodega();
        b.setBodeIdPk(Integer.parseInt(bode));
        
        AbaaTbDepartamento d = new AbaaTbDepartamento();
        d.setDeptoIdPk(dpt);
        
        SboSicop s = new SboSicop();
        s.setSicopId(Integer.parseInt(arti));
        
        e.setAbaaTbDepartamento(d);
        e.setSboTbBodega(b);
        e.setSboTbSicop(s);
        return e;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(SboTbExistencia existencia) {
        try {
            Model.instance().aumentarExistenciasArticulo(existencia);
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SboTbExistencia> updateCant(SboTbExistencia existencia) {
        try {
            existencia = actualizaDatosExistencia(existencia);
            Model.instance().actualizaCantExist(existencia);
            List<SboTbExistencia> lista = Model.instance().listaExistencias(bode, dpt, arti);
            return lista;
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("{bodeg}/{depto}/{arti}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public List<SboTbExistencia> getExistencias(@PathParam("bodeg") String x, @PathParam("depto") String y, @PathParam("arti") String z)
            throws ClassNotFoundException, SQLException {

        String bodega = x;
        String departamento = y;
        String articulo = z;
        bode = bodega;
        arti = articulo;
        dpt = departamento;
        List<SboTbExistencia> lista = Model.instance().listaExistencias(bodega, departamento, articulo);
        return lista;
    }

}
