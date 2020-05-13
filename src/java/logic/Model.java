package logic;

import data.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final usuariosDAO usuariosDao;
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
        usuariosDao = new usuariosDAO();

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

        SboTbArticulo aux = articulodao.getArticulo(articulo.getArtIdPk());
        SboSicop sicop = new SboSicop();
        sicop.setSicopId(articulo.getSboSicop().getSicopId());
        aux.setSboSicop(sicop);
        int cantRestante = aux.getArtCantRest() - articulo.getArtCant();
        articulo.setArtIdPk(aux.getArtIdPk());

        if (cantRestante == 0) {

            aux.setArtCantRest(cantRestante);
            artidao.actualizaSicop(aux);
            artidao.actualizaRestante(aux);
            articulodao.actualizarLote(articulo);

            int cantidad = articulo.getArtCant();
            for (int i = 0; i < cantidad; i++) {
                SboTbExistencia existencia = new SboTbExistencia();
                SboTbBodega bodega = new SboTbBodega();
                bodega.setBodeIdPk(Integer.parseInt(articulo.getArtCodContGast()));
                existencia.setArticulo(aux);
                existencia.setSboTbBodega(bodega);
                existencia.setExistFingr(articulo.getArtFingr());
                existencia.setSboTbEsta(1);
                articulodao.insertarExist(existencia);
            }
            artidao.verificarEstadoOCs(aux);

        } else if (cantRestante > 0) {
            aux.setArtCantRest(cantRestante);
            artidao.actualizaSicop(aux);
            artidao.actualizaRestante(aux);
            articulodao.actualizarLote(articulo);
            int cantidad = articulo.getArtCant();
            for (int i = 0; i < cantidad; i++) {
                SboTbExistencia existencia = new SboTbExistencia();
                SboTbBodega bodega = new SboTbBodega();
                bodega.setBodeIdPk(Integer.parseInt(articulo.getArtCodContGast()));
                existencia.setArticulo(aux);
                existencia.setSboTbBodega(bodega);
                existencia.setExistFingr(articulo.getArtFingr());
                existencia.setSboTbEsta(1);
                articulodao.insertarExist(existencia);
            }
            artidao.verificarEstadoOCs(aux);

        } else if (cantRestante < 0) {

            return;

        }

        //artidao.disminuirCantPendienteArticulo(articulo);
    }

//    public void aumentarExistenciasArticulo(SboTbExistencia existencia) throws Exception {
//        artidao.aumentarExistencias(existencia);
//    }
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

//    public int sumaExistencias(String idDepto, int idCatArt) throws Exception {
//        int sum = 0;
//        List<SboTbExistencia> existencias = solArtdao.existenciasXarticuloxdepto(idDepto, idCatArt);
//        for (SboTbExistencia exist : existencias) {
//
//            sum += exist.getExisCant();
//        }
//        return sum;
//    }
//    public int sumaExistencias2(int id) throws Exception {
//        SboTbArticulo art = articulodao.getArticulo2(id);
//        String idDep = art.getAbaaTbDepartamento().getDeptoIdPk();
//        int idCatArt = art.getSboTbCatArticulo().getCatIdPk();
//        int sum = 0;
//        List<SboTbExistencia> existencias = solArtdao.existenciasXarticuloxdepto(idDep, idCatArt);
//
//        for (SboTbExistencia exist : existencias) {
//
//            sum += exist.getExisCant();
//        }
//        return sum;
//    }
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
        if (articulo.equals("all")) {
            return existdao.listaExistenciasTodosArticulos(bodega, departamento, articulo);
        } else {
            return existdao.listaExistenciasArticulos(bodega, departamento, articulo);
        }
    }

    public List<SboTbExistencia> listaExistenciasfiltro(String depa) {
        return existdao.listaExistenciasfiltro(depa);
    }
//
//    public SboTbExistencia getSboTbExistencia(String depa, String Arti) throws Exception {
//        return existdao.getSboTbExistencia(depa, Arti);
//    }

    public void agregarArticuloSinOrden(SboTbArticulo art) throws Exception {
        articulodao.agregarArticuloSinOrden(art);

    }

    public void agregarExistencias(SboTbExistencia exist) throws Exception {

        int seq = articulodao.getLastInsertArticulo();
        SboTbArticulo lote = articulodao.getArticuloSimple(seq);
        articulodao.insertarExistencias(lote, exist);

    }

    public SboTbArticulo getArticuloSimple(String id) throws Exception {
        int val = Integer.parseInt(id);
        SboTbArticulo articulo = articulodao.getArticuloCompleto(val);
        return articulo;
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

    public List<SboTbLimiteDpto> disminuirExistencias(SboTbSoliArti soliArti) throws Exception {
        //Obtiene listado de existencias segun numero de solicitud
        ArrayList<SboTbExistencia> listadoExistencias = (ArrayList) existdao.obtenerExistenciasSegunNumSolicitud(soliArti.getSolArtiIdPk());

        //Se obtiene el id del dpto que realizó la solicitud de articulo
        int idDpto = solArtdao.obtenerIdDptoPorSoli(soliArti.getSolArtiIdPk());

        //Verifica que todas existencias esten disponibles
        if (verificaEstadoExisSoliArti(listadoExistencias)) {
            for (int i = 0; i < listadoExistencias.size(); i++) {

                //Actualiza el estado de la existencia a entregado, es decir, no disponible
                listadoExistencias.get(i).setSboTbEsta(0);
                existdao.actualizarExistencia(listadoExistencias.get(i));
            }
            //actualiza el estado de la solicitud a aprobada
            solArtdao.actualizarEstadoAprobado(soliArti.getSolArtiIdPk());

            return verificaLimiteExis(soliArti.getSolArtiIdPk(), idDpto);
        } else {
            throw new Exception("Error general");
        }
    }

    private boolean verificaEstadoExisSoliArti(ArrayList<SboTbExistencia> existencias) {
        boolean bandera = true;
        int cont = 0;
        while (cont < existencias.size() && bandera == true) {
            if (existencias.get(cont).getSboTbEsta() == 0) {
                bandera = false;
            } else {
                cont++;
            }
        }
        return bandera;
    }

    private ArrayList<SboTbLimiteDpto> verificaLimiteExis(int idSoliArti, int idDpto) throws Exception {
        //Obtiene el listado de codigos de SICOP involucrados en la solicitud de articulos
        ArrayList<SboSicop> listadoSicop = (ArrayList) sicopDao.listadoCodigosSicopSegunSoliArti(idSoliArti);

        //Se crea lista vacia de posibles alertas de minimos
        ArrayList<SboTbLimiteDpto> alertas = new ArrayList<>();

        for (int i = 0; i < listadoSicop.size(); i++) {
            //Se obtiene la cantidad restante en existencias segun sicop y dpto
            int cantidad = existdao.obtenerCantidadExisPorDptoSicop(listadoSicop.get(i).getSicopId(), idDpto);

            //se obtiene el limite por dpto asignado segun sicop y dpto
            SboTbLimiteDpto limite = limiDAO.getLimiteDepaPorExis(listadoSicop.get(i).getSicopId(), idDpto);

            //¿La cantidad restante es menor al limite establecido?
            if (limite.getLimite() > cantidad) {
                limite.setLimite(cantidad);
                alertas.add(limite);
            }
        }
        return alertas;
    }

    public List<SboTbSoliArti> solicitudesPendientesxFunc(int func) {
        return solArtdao.listadoSolicitudPorFuncionarioPendientes(func);
    }

    public List<SboTbSoliArti> solicitudesTotalxFunc(int func) {
        return solArtdao.listadoSolicitudPorFuncionarioTotal(func);
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

    public void eliminaExistencia(SboTbExistencia e) throws Exception {
        existdao.actualizarExistencia(e);
    }

    public void actualizarExistenciaSoliPendiente(SboTbExistencia e) throws Exception {
        existdao.actualizarExistenciaSoliPendiente(e);
    }

    public void agregarLimite(SboTbLimiteDpto e) throws Exception {
        limiDAO.insertLimites(e);
    }

    public void deleteLimite(SboTbLimiteDpto e) throws Exception {
        limiDAO.deleteLimites(e);
    }

    public void updateLimite(SboTbLimiteDpto e) throws Exception {
        limiDAO.updateLimites(e);
    }

    public List<SboTbLimiteDpto> listaLimites(String x, String y) throws Exception {
        return limiDAO.listaLimites(x, y);
    }

    public List<SboTbLimiteDpto> listaLimitesxDepartamento(String x) throws Exception {
        return limiDAO.listaLimitesxDepartamento(x);
    }

    public List<SboTbLimiteDpto> listaLimitesxArti(String x) throws Exception {
        return limiDAO.listaLimitesxArti(x);
    }

    public SboTbLimiteDpto getLimite(String x, String y) throws Exception {
        return limiDAO.getLimite(x, y);
    }

    public ArrayList<SboTbSolixArti> listaReporte(String arti, String depa, String inicio, String fin) throws Exception {
        ArrayList<SboTbSolixArti> aux = new ArrayList<>();
        if (arti.equals("all")) {
            aux = solixartdao.reporteConsumoTodos(depa, inicio, fin);
        } else {
            aux = solixartdao.reporteConsumo(depa, inicio, fin, arti);
        }
        return aux;
    }

    public List<AbaaTbPersona> personasLista() {
        return usuariosDao.personasLista();
    }

    public AbaaTbPersona getUsuario(String id) throws Exception {
        return usuariosDao.getUsuario(id);
    }

    public void updatUsuarioSinContrasenna(AbaaTbPersona p) throws SQLException {
        usuariosDao.updatUsuarioSinContrasenna(p);
    }

    public void updateUsuarioConContrasenna(AbaaTbPersona p) throws SQLException {
        usuariosDao.updateUsuarioConContrasenna(p);
    }

    public void insertarUsuario(AbaaTbPersona per) {
        try {
            String clave = per.getPasswAux();
            usuariosDao.InsertarPersona(per);
            String id = per.getPersCedu();
            AbaaTbPersona aux = usuariosDao.getPersona(id);

            AbaaTbUsuario user = new AbaaTbUsuario();
            aux.setPersCedu(per.getPersCedu());
            user.setPersona(aux);
            user.setUsuaCont(clave);

            usuariosDao.InsertarUsuario(user);

        } catch (Exception ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<SboTbArticulo> listadoArticulosFaltaContConta() {
        return articulodao.listadoArticulosFaltaContConta();
    }
    public List<SboTbExistencia> listaExistenciasStocks(String bodega, String departamento) {
        return existdao.listaExistenciasStocks(bodega, departamento);
    }
    
    public void generarReporteConsumo(String arti, String depaId, String depaNomb, String inicio, String fin) throws Exception {
        String path="";
        RelDatabase db;
        db = new RelDatabase();
        Map<String, Object> parametros;
        parametros = new HashMap<>();
        parametros.clear();
        parametros.put("depaId", depaId);
        parametros.put("depaNomb", depaNomb);
        parametros.put("inicio", inicio);
        parametros.put("fin", fin);
        if (arti.equals("all")) {
            path = "./src/java/reportes/ReporteConsumoTotal.jasper";
        } else {
            path = "./src/java/reportes/ReporteConsumoPorArticulo.jasper";
            parametros.put("articulo", arti);
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(path, parametros, db.getConnection());
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteConsumoPorDepartamento.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();
    }

}
