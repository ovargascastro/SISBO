package logic;

import data.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    
    private final catalogosDAO catdao;
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
    
    public SboTbArticulo getArticuloTemporal(int id){
        return listaTemp.get(id);
    }
    
    public void eliminarArtTemp(SboTbArticulo art) throws Exception{
        listaTemp.remove(art.getArtIdPk(), art);
    }
    
    public void actualizarArticuloTemporal(SboTbArticulo art) throws Exception{
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
    
    public void reiniciaLista(){
        listaTemp = new HashMap<>();
        contador = 1;
    }
    
    public void agregarArticulos()throws Exception{
        SboTbOrdenCompra orden = new SboTbOrdenCompra(numOrden);
        //AbaaTbOcproyecto ordenxProyecto = new AbaaTbOcproyecto();
//        SboTbArticulo arti = new SboTbArticulo();
        for(SboTbArticulo art : listaTemp.values()){
            art.setSboTbOrdenCompra(orden);
        }
        for(SboTbArticulo art : listaTemp.values()){
//            articulodao.agregarArticulo(art);
            
            if(art.getAbaaProyectos().getProyIdPk()==0){
                articulodao.agregarArticulo(art);
                
//                numArticulo=articulodao.getLastInsertArticulo();
//                arti.setArtIdPk(numArticulo);
//                ordenxProyecto.setAbaaProyectos(art.getAbaaProyectos());
//                ordenxProyecto.setSboTbArticulo(arti);
//                ordenxProyecto.setSboTbOrdenCompra(orden);
//                ordenXdao.agregarDatos(ordenxProyecto);
            }
            else{
                articulodao.agregarArticuloConProyecto(art);
                
//                numArticulo=articulodao.getLastInsertArticulo();
//                arti.setArtIdPk(numArticulo);
//                ordenxProyecto.setAbaaProyectos(art.getAbaaProyectos());
//                ordenxProyecto.setSboTbArticulo(arti);
//                ordenxProyecto.setSboTbOrdenCompra(orden);
//                ordenXdao.agregarDatos(ordenxProyecto);
            }
        }
    }
    
    public List<SboTbOrdenCompra> listaOrdenesCompra(String filtro) {
        
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
    
}
