package logic;

import data.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private final catalogosDAO catdao;
    private final OrdenCompraDAO ocdao;
    private final ArticuloOCDAO artidao;
    private final BodegaDAO bodegadao;
    private static Model uniqueInstance;
    private final DepartamentoDAO dptodao;
    private final ProyectoDAO proyecdao;
    private final ProveedoresDAO provedao;
    private final OrdenCompraDAO ordendao;
    private final ArticulosDAO articulodao;
    private final OCxProyectoDAO ordenXdao;
    public int numOrden;
    public int numArticulo;
    Map<Integer, SboTbArticulo> listaTemp = new HashMap<>();
    public int contador = 1;

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
        dptodao = new DepartamentoDAO();
        proyecdao = new ProyectoDAO();
        provedao = new ProveedoresDAO();
        ordendao = new OrdenCompraDAO();
        articulodao = new ArticulosDAO();
        ordenXdao = new OCxProyectoDAO();
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

    public List<AbaaTbDepartamento> listaDepartamentos() throws ClassNotFoundException, SQLException {
        List result = dptodao.listaDepartamento();
        return result;
    }

    public List<AbaaProyectos> listaProyectos() throws ClassNotFoundException, SQLException {
        List result = proyecdao.listaProyecto();
        return result;
    }

    public List<AbaaTbProveedor> listaProveedores() throws ClassNotFoundException, SQLException {
        List result = provedao.listaProveedor();
        return result;
    }

    public AbaaTbProveedor getProveedor(int id) throws Exception {
        AbaaTbProveedor ob = provedao.getProveedor(id);
        return ob;
    }

    public void agregarArtTemp(SboTbArticulo art) throws Exception {
        String idDepto = art.getAbaaTbDepartamento().getDeptoIdPk();
        AbaaTbDepartamento depto = dptodao.getDepartamento(idDepto);
        art.setAbaaTbDepartamento(depto);
        art.setArtIdPk(contador);
        contador++;
        listaTemp.put(art.getArtIdPk(), art);
    }

    public SboTbArticulo getArticuloTemporal(int id) {
        return listaTemp.get(id);
    }

    public void eliminarArtTemp(SboTbArticulo art) throws Exception {
        listaTemp.remove(art.getArtIdPk(), art);
    }

    public void actualizarArticuloTemporal(SboTbArticulo art) throws Exception {
        listaTemp.put(art.getArtIdPk(), art);
    }

    public Map<Integer, SboTbArticulo> getListaTemp() {
        return listaTemp;
    }

    public void setListaTemp(Map<Integer, SboTbArticulo> listaTemp) {
        this.listaTemp = listaTemp;
    }

    public void agregarOrden(SboTbOrdenCompra orden) throws Exception {
        orden.setOcPrecTota(calculaTotal());
        ordendao.agregarOrdenCompra(orden);
        numOrden = ordendao.getLastInsertOrdenesCompra();
    }

    public Double calculaTotal() {
        Double total = 0.0;
        for (SboTbArticulo art : listaTemp.values()) {
            total += art.getArtPrecio() * art.getArtCant();
        }
        return total;
    }

    public void reiniciaLista() {
        listaTemp = new HashMap<>();
        contador = 1;
    }

    public void agregarArticulos() throws Exception {
        SboTbOrdenCompra orden = new SboTbOrdenCompra(numOrden);
        for (SboTbArticulo art : listaTemp.values()) {
            art.setSboTbOrdenCompra(orden);
        }
        for (SboTbArticulo art : listaTemp.values()) {
            if (art.getAbaaProyectos().getProyIdPk() == 0) {
                articulodao.agregarArticulo(art);
            } else {
                articulodao.agregarArticuloConProyecto(art);
            }
        }
    }

    public List<SboTbOrdenCompra> listaOrdenesCompraC(String filtro) {
        return ordendao.listadoOrdenesC(filtro);
    }

    public List<SboTbOrdenCompra> listaOrdenesCompraConta(String filtro) {
        return ordendao.listadoOrdenesCompraConta(filtro);
    }

    public List<SboTbArticulo> listaArticulosOrdenCompra(String filtro) {
        int valor = Integer.parseInt(filtro);
        return articulodao.listadoArticulosPorOrden(valor);
    }

    public List<SboTbArticulo> listaArticulosOrdenCompraConta(String filtro) {
        int valor = Integer.parseInt(filtro);
        return articulodao.listadoArticulosPorOrdenConta(valor);
    }

    public void actualizarCodigoCont(SboTbArticulo art) throws Exception {
        articulodao.actualizarCodigCont(art);
    }

    public void actualizaEstadoOrdenCom(SboTbOrdenCompra objeto) throws SQLException {
        ordendao.actualizaEstadoOC(objeto);
    }
    
//    public List<SboTbCatContable> listaCatContable() throws ClassNotFoundException, SQLException {
//        List result = catdao.listaCodContables();
//        return result;
//    }
    
    public List<SboTbCatContable> listaCatContables(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaCatContable(filtro);
        return result;
        
    }
    
    public SboTbCatContable getSboTbCatContable(int filtro) throws Exception {
        SboTbCatContable ob = catdao.getSboTbCatContable(filtro);
        return ob;
    }
    
    public void actualizarCatContable(SboTbCatContable cont) throws Exception {
        catdao.actualizarCatContable(cont);
        
    }
      public void crearCatContable(SboTbCatContable cont) throws Exception {
        catdao.crearCatContable(cont);
    }
    
}

