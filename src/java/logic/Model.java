package logic;

import data.*;
import java.sql.SQLException;
import java.util.List;

public class Model {

    private final catalogosDAO catdao;
    private final OrdenCompraDAO ocdao;
    private final ArticuloOCDAO artidao;
    private final BodegaDAO bodegadao;
    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    private Model() {
        catdao = new catalogosDAO();
        ocdao = new OrdenCompraDAO();
        artidao = new ArticuloOCDAO();
        bodegadao = new BodegaDAO();
    }

    public List<SboTbFamilia> listaFamilias(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaFamilias(filtro);
        return result;

    }

    public List<SboTbSubFamilia> listaSubFamilias(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaSubFamilias(filtro);
        return result;

    }

    public List<SboTbCatArticulo> listaCatArticulos(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaCatArticulos(filtro);
        return result;

    }

    public SboTbFamilia getSboTbFamilia(String filtro) throws Exception {
        SboTbFamilia ob = catdao.getSboTbFamilia(filtro);
        return ob;
    }

    public SboTbCatArticulo getCatArticulo(String filtro) throws Exception {
        int filtro2 = Integer.parseInt(filtro);
        SboTbCatArticulo ob = catdao.getCatArticulo(filtro2);
        return ob;

    }

    public SboTbSubFamilia getSboTbSubfamilia(String filtro) throws Exception {
        SboTbSubFamilia ob = catdao.getSboTbSubFamilia(filtro);
        return ob;

    }

    public List<SboTbArticulo> ListaArtxOrden(String filtro) throws ClassNotFoundException, SQLException {
        List oc = ocdao.listaOCxArt(filtro);
        return oc;
    }

    public void actualizarFamilia(SboTbFamilia familia) throws Exception {
        catdao.actualizarFamilia(familia);
    }

    public void actualizarSubFamilia(SboTbSubFamilia subfamilia) throws Exception {
        catdao.actualizarSubFamilia(subfamilia);
    }

    public void actualizarCatArticulo(SboTbCatArticulo articulo) throws Exception {
        catdao.actualizarCatArticulo(articulo);
    }

    public void crearFamilia(SboTbFamilia familia) throws Exception {
        catdao.crearFamilia(familia);
    }

    public void crearSubFamilia(SboTbSubFamilia subfamilia) throws Exception {
        catdao.crearSubFamilia(subfamilia);
    }

    public void crearCatArticulo(SboTbCatArticulo articulo) throws Exception {
        catdao.crearCatArticulo(articulo);
    }

    public List<SboTbOrdenCompra> listaOrdenesCompra(String filtro) throws ClassNotFoundException, SQLException {
        List result = ocdao.listaOrdenesCompra(filtro);
        return result;
    }

    public SboTbArticulo buscaDatosArticulo(String filtro) throws Exception {
        return artidao.datosArticulo(filtro);
    }

    public SboTbArticulo buscaDescCatsArticulo(String filtro) throws Exception {
        return artidao.DescripcionCatsPorArticulo(filtro);
    }

    public List<SboTbBodega> listaBodegas(String filtro) throws ClassNotFoundException, SQLException {
        List result = bodegadao.listaBodegas(filtro);
        return result;
    }

    public void disminuirCantPendienteArticulo(SboTbArticulo articulo) throws Exception {
        artidao.disminuirCantPendienteArticulo(articulo);
    }

    public void aumentarExistenciasArticulo(SboTbExistencia existencia) throws Exception {
        artidao.aumentarExistencias(existencia);
    }
}
