package logic;

import data.*;
import java.sql.SQLException;
import java.util.List;

public class Model {

    private final catalogosDAO catdao;
    private final OrdenCompraDAO ocdao;
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

    public SboTbOrdenCompra getSboTbArtxOrden(String filtro) throws Exception {
        return null;
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
        List result = ocdao.listaFamilias(filtro);
        return result;
    }
}
