package logic;

import data.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    private static Model uniqueInstance;
    private final catalogosDAO catdao;
    private final OrdenCompraDAO ocdao;
    private final ArticuloOCDAO artidao;
    private final BodegaDAO bodegadao;
    private final DepartamentoDAO dptodao;
    private final ProyectoDAO proyecdao;
    private final ProveedoresDAO provedao;
    private final OrdenCompraDAO ordendao;
    private final ArticulosDAO articulodao;
    private final OCxProyectoDAO ordenXdao;
    private final solicitudArtDAO solArtdao;
    private final sicopDAO sicopDao;
    private final loginDAO logindao;
    private final ExistenciasDAO existdao;
    private final SoliXArtDAO solixartdao;
    private final LimitesDepartamentoDAO limiDAO;

    public int numOrden;
    public int numSoliArti;
    public int numArticulo;
    public int contador = 1;
    public int contadorArtXSol = 1;
    Map<Integer, SboTbArticulo> listaTemp = new HashMap<>();
    Map<Integer, SboTbSolixArti> artXsolTemp = new HashMap<>();
    Map<Integer, Integer> cantArtTemp = new HashMap<>();

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
        solArtdao = new solicitudArtDAO();
        logindao = new loginDAO();
        sicopDao = new sicopDAO();
        existdao = new ExistenciasDAO();
        solixartdao = new SoliXArtDAO();
        limiDAO = new LimitesDepartamentoDAO();

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

    public List<AbaaTbProveedor> listaProvFiltro(String filtro) throws ClassNotFoundException, SQLException {
        List result = provedao.listaProveedorFiltro(filtro);
        return result;
    }

    public void actualizaProveedor(AbaaTbProveedor prov) throws Exception {
        provedao.actualizarProveedor(prov);
    }

    public void agregarProveedor(AbaaTbProveedor prov) throws Exception {
        provedao.crearProveedor(prov);
    }

    public List<AbaaTbProveedor> listaProvesFilt(String filtro) throws ClassNotFoundException, SQLException {
        List result = provedao.listaProveedorFiltro(filtro);
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
        int idCatArt = art.getSboTbCatArticulo().getCatIdPk();
        SboTbCatArticulo catArt = catdao.getCatArticulo(idCatArt);
        art.setSboTbCatArticulo(catArt);
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

    public List<SboTbCatContable> listaCatContables(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaCatContable(filtro);
        return result;
    }

    public SboTbCatContable getSboTbCatContable(int filtro) throws Exception {
        SboTbCatContable ob = catdao.getSboTbCatContable(filtro);
        return ob;
    }

    public SboTbSoliArti getSboTbSoliArti(int filtro) throws Exception {
        SboTbSoliArti ob = solArtdao.getSboTbSoliArti(filtro);
        return ob;
    }

    public SboTbSolixArti getSboTbSolixArti(int filtro) throws Exception {
        SboTbSolixArti ob = solArtdao.getSboTbSolixArti(filtro);
        return ob;
    }

    public void actualizarCatContable(SboTbCatContable cont) throws Exception {
        catdao.actualizarCatContable(cont);
    }

    public void crearCatContable(SboTbCatContable cont) throws Exception {
        catdao.crearCatContable(cont);
    }

    public List<SboTbArticulo> listaArticulosExistencia(String filtro) throws ClassNotFoundException, SQLException {
        List result = solArtdao.getArticuloExistencia(filtro);
        return result;
    }

    public List<SboTbArticulo> listaExistenciasDepartamento(String filtro) throws ClassNotFoundException, SQLException {

        // List result = articulodao.listaExistenciasDepartamento(filtro);
        //     return result;
        return null;

    }

    public SboTbSolixArti getArtxSolTemp(int id) {
        return artXsolTemp.get(id);
    }

    public void eliminarArtxSolTemp(SboTbArticulo artSol) throws Exception {
        artXsolTemp.remove(artSol.getArtIdPk(), artSol);
    }

    public Map<Integer, SboTbSolixArti> getListaArtxSolTemp() {
        return artXsolTemp;
    }

    public void setListaArtxSolTemp(Map<Integer, SboTbSolixArti> listaTemp) {
        this.artXsolTemp = listaTemp;
    }

    public void agregarSolicitudArticulo(SboTbSoliArti solArti) throws Exception {

    }

    public void reiniciaListaSolart() {
        artXsolTemp = new HashMap<>();
    }

    public int sumaExistencias(String idDepto, int idCatArt) throws Exception {
        int sum = 0;
        List<SboTbExistencia> existencias = solArtdao.existenciasXarticuloxdepto(idDepto, idCatArt);
        for (SboTbExistencia exist : existencias) {

            sum += exist.getExisCant();
        }
        return sum;
    }

    public int sumaExistencias2(int id) throws Exception {
        SboTbArticulo art = articulodao.getArticulo2(id);
        String idDep = art.getAbaaTbDepartamento().getDeptoIdPk();
        int idCatArt = art.getSboTbCatArticulo().getCatIdPk();
        int sum = 0;
        List<SboTbExistencia> existencias = solArtdao.existenciasXarticuloxdepto(idDep, idCatArt);

        for (SboTbExistencia exist : existencias) {

            sum += exist.getExisCant();
        }
        return sum;
    }

    public List<SboTbSoliArti> listaSolicitudesArticulos(String filtro) {
        return solArtdao.listadoSolicitudesArticulos(filtro);
    }

    public List<SboTbSolixArti> listaArticulosXSolicitud(String filtro) {
        int valor = Integer.parseInt(filtro);
        return solArtdao.listadoArticulosPorSolicitud(valor);
    }

    public List<SboTbSoliArti> listadoSolicitudxAprobar(String soli) {
        return solArtdao.listadoSolicitudxAprobar(soli);
    }

    public void actualizarEstSolicitud(SboTbSoliArti cont) throws Exception {
        solArtdao.actualizarEstSolicitud(cont);
    }

    public List<SboTbSoliArti> listadoSolicitudVistobuenoJf(String filtro) {

        return solArtdao.listadoSolicitudVistobuenoJf(filtro);

    }

    public List<SboTbSoliArti> listadoSolicitudVistobuenoTI(String filtro) {
        return solArtdao.listadoSolicitudVistobuenoTI(filtro);
    }

    public void actualizarEstSolicitudJefe(SboTbSoliArti cont) throws Exception {
        solArtdao.actualizarEstSolicitudJefe(cont);
    }

    public void actualizarEstSolicitudTI(SboTbSoliArti cont) throws Exception {

        solArtdao.actualizarEstSolicitudTI(cont);
    }

//    public void disminuyeExistencias(SboTbSolixArti objeto) throws Exception {
//
//        solArtdao.disminuyeExistencias(objeto);
//
//    }
    public AbaaTbPersona login(String user, String password) throws Exception {
        return logindao.logged(user, password);
    }

    public List<SboSicop> listadoSicop(String filtro) {
        return sicopDao.getListaSicop(filtro);
    }

    public List<SboSicop> listadoSicopFiltro(String filtro) {
        return sicopDao.getListaSicopFiltro(filtro);
    }

    public SboSicop obtenerSicop(String filtro) throws Exception {
        return sicopDao.getSboSicop(filtro);
    }

    public void actualizarSicop(SboSicop s) throws SQLException {
        sicopDao.actualizarSicop(s);
    }

    public void agregarSicop(SboSicop s) throws SQLException {
        sicopDao.agregarSicop(s);
    }

    public List<SboTbExistencia> listaExistencias(String bodega, String departamento, String articulo) {
        return existdao.listaExistencias2(bodega, departamento, articulo);
    }

    public List<SboTbExistencia> listaExistenciasfiltro(String depa) {
        return existdao.listaExistenciasfiltro(depa);
    }

    public SboTbExistencia getSboTbExistencia(String depa, String Arti) throws Exception {
        return existdao.getSboTbExistencia(depa, Arti);
    }

    public void agregarArticuloSinOrden(SboTbArticulo art) throws Exception {
        articulodao.agregarArticuloSinOrden(art);
    }

    //agrego la solicitud y en la variable numSoliArti le recupera el ultimo id de la solicitud
    public void InsertarSoli(SboTbSoliArti soli) throws Exception {
        solArtdao.InsertarSoli(soli);
        // return solArtdao.getLastInsertSolicitudArticulo();
    }

    public void agregarSolxArt(SboTbSolixArti objeto) throws Exception {
        solixartdao.insertarSolxArt(objeto);
    }

    public SboTbSoliArti obtenerid() throws Exception {
        int id = solArtdao.getLastInsertSolicitudArticulo();
        return solArtdao.getSboTbSoliArti(id);
    }

    public List<SboTbExistencia> disminuirExistencias(SboTbSolixArti solixArti) throws Exception {
        ArrayList<SboTbSolixArti> listaSolicitudes = (ArrayList) solixartdao.filtraSolixArti(Integer.toString(solixArti.getSboTbSoliArti().getSolArtiIdPk()));
        ArrayList<SboTbExistencia> existencias = listaExistenciasPorSoli(listaSolicitudes);
        if (verificaDatosExisSolixArti(listaSolicitudes, existencias)) {
            for (int i = 0; i < existencias.size(); i++) {
                existencias.get(i).setExisCant(existencias.get(i).getExisCant() - listaSolicitudes.get(i).getSolArtiCant());
                existdao.actualizarExistencia(existencias.get(i));

                listaSolicitudes.get(i).getSboTbSoliArti().setSolArtiEsta("Aprobada");
                solArtdao.actualizarEstSolicitud(listaSolicitudes.get(i).getSboTbSoliArti());
            }
            return verificaLimiteExis(existencias);
        } else {
            throw new Exception("Departamento no Existe");
        }
    }

    private ArrayList<SboTbExistencia> listaExistenciasPorSoli(ArrayList<SboTbSolixArti> solicitudes) throws Exception {
        ArrayList<SboTbExistencia> existencias = new ArrayList<>();
        for (SboTbSolixArti s : solicitudes) {
            existencias.add(buscaRegistroExistencia(s));
        }
        return existencias;
    }

    private SboTbExistencia buscaRegistroExistencia(SboTbSolixArti solixArti) throws Exception {
        return existdao.registroExistenciasPorSolicitud(solixArti.getSboTbSoliArti().getAbaaTbDepartamento().getDeptoIdPk(), Integer.toString(solixArti.getSboSicop().getSicopId()));
    }

    private boolean verificaDatosExisSolixArti(ArrayList<SboTbSolixArti> solicitudes, ArrayList<SboTbExistencia> existencias) {
        boolean bandera = true;
        int cont = 0;
        while (cont < solicitudes.size() && bandera == true) {
            if (solicitudes.get(cont).getSolArtiCant() > existencias.get(cont).getExisCant()) {
                bandera = false;
            } else {
                cont++;
            }
        }
        return bandera;
    }

    private ArrayList<SboTbExistencia> verificaLimiteExis(ArrayList<SboTbExistencia> existencias) throws Exception {
        ArrayList<SboTbLimiteDpto> limites = verificaLimExisConExistencias(existencias);
        ArrayList< SboTbExistencia> alertas = new ArrayList<SboTbExistencia>();
        for (int i = 0; i < existencias.size(); i++) {
            if (limites.get(i).getLimiteDptoLimite() > existencias.get(i).getExisCant()) {
                alertas.add(existencias.get(i));
            }
        }
        return alertas;
    }

    private ArrayList<SboTbLimiteDpto> verificaLimExisConExistencias(ArrayList<SboTbExistencia> e) throws Exception {
        ArrayList<SboTbLimiteDpto> l = new ArrayList<>();
        for (int i = 0; i < e.size(); i++) {
            l.add(limiDAO.getLimiteDepaPorExis(e.get(i)));
        }
        return l;
    }

    public void actualizaCantExist(SboTbExistencia e) throws SQLException {
        existdao.updateExist(e);
    }

    public List<SboTbSoliArti> solicitudesPendientesxFunc(int func) {
        return solArtdao.listadoSolicitudPorFuncionarioPendientes(func);
    }

    public List<SboTbSoliArti> solicitudesTotalxFunc(int func) {
        return solArtdao.listadoSolicitudPorFuncionarioTotal(func);
    }
    
    public void agregarLimite(SboTbLimiteDpto limi) throws Exception{
        limiDAO.insertLimites(limi);
    }
    

    public void agregarBodega(SboTbBodega b) throws SQLException {
        bodegadao.agregarBodega(b);
    }

    public void deleteBodega(SboTbBodega s) throws SQLException {
        bodegadao.deleteBodega(s);
    }

    public void updateBodega(SboTbBodega s) throws SQLException {
        bodegadao.updateBodega(s);
    }

    public SboTbBodega getBodega(String id) throws Exception {
        return bodegadao.getBodega(id);

    }

    public ArrayList<SboTbSolixArti> listaReporte(String arti, String depa, String inicio, String fin) throws Exception {

       
        Map<String, SboTbSolixArti> aux = new HashMap<>();

        if (arti.equals("all")) {
             ArrayList<SboTbSolixArti> lista = solixartdao.reporteConsumo(depa, inicio, fin);
            for (SboTbSolixArti x : lista) {

                String key = Integer.toString(x.getSboSicop().getSicopId());

                if (aux.containsKey(key)) {
                    SboTbSolixArti obj = aux.get(key);
                    int cant = obj.getSolArtiCant();
                    int cantAux = cant + x.getSolArtiCant();
                    obj.setSolArtiCant(cantAux);

                } else {
                    aux.put(key, x);

                }

            }

            ArrayList<SboTbSolixArti> beans = new ArrayList<>(aux.values());

            return beans;
        } else {
             ArrayList<SboTbSolixArti> lista = solixartdao.reporteConsumoFilter(arti,depa, inicio, fin);
            for (SboTbSolixArti x : lista) {

                String key = Integer.toString(x.getSboSicop().getSicopId());

                if (aux.containsKey(key)) {
                    SboTbSolixArti obj = aux.get(key);
                    int cant = obj.getSolArtiCant();
                    int cantAux = cant + x.getSolArtiCant();
                    obj.setSolArtiCant(cantAux);

                } else {
                    aux.put(key, x);

                }

            }

        }
        ArrayList<SboTbSolixArti> beans = new ArrayList<>(aux.values());
        
        return beans;
    }

}
