/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaProyectos;
import logic.AbaaTbDepartamento;
import logic.AbaaTbPersona;
import logic.AbaaTbProveedor;
import logic.SboTbArticulo;
import logic.SboTbBodega;
import logic.SboTbCatArticulo;
import logic.SboTbExistencia;
import logic.SboTbFamilia;
import logic.SboTbOrdenCompra;
import logic.SboTbSoliArti;
import logic.SboTbSolixArti;
import logic.SboTbSolixArtiId;
import logic.SboSicop;
import logic.SboTbSubFamilia;

/**
 *
 * @author Boris Méndez
 */
public class solicitudArtDAO {

    RelDatabase db;

    public solicitudArtDAO() {
        db = new RelDatabase();
    }
// se crea un articulo de sicop

    private SboSicop articulo(ResultSet rs) {
        try {
            SboSicop ar = new SboSicop();
            ar.setSicopId(rs.getInt("Sico_Id_PK"));
            ar.setSicopCodiInden(rs.getString("Sico_Codi_Iden"));
            ar.setSicopCodiClas(rs.getString("Sico_Codi_Clas"));
            ar.setSicopDesc(rs.getString("Sico_Desc"));
            return ar;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se obtiene todos los articulos en existencias de un departamento
    public List<SboSicop> getArticuloExistencia(String filtro) {
        List<SboSicop> resultado = new ArrayList<SboSicop>();
        try {
            String sql = "select *"
                    + "from SIBO_TB_Articulo art, SIBO_TB_Cata_Arti catArt, ABAA_TB_Catalogo_Departamento dpto, "
                    + "SIBO_TB_Exis exist, SIBO_TB_Bode bod "
                    + "where art.Arti_Codi_Cata_Arti_FK = catArt.Cata_Id_PK and art.Arti_Cata_Depa_FK = dpto.Cata_Depa_id_PK "
                    + "and exist.Exis_Id_Arti_PK = art.Arti_Id_PK and exist.Exis_Id_Bode_PK = bod.Bode_Id_PK  "
                    + "and exist.Exis_Cant > 0 and dpto.Cata_Depa_id_PK='%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(articulo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se seleccionan los articulos en existencias por departamento
    public List<SboTbExistencia> existenciasXarticuloxdepto(String idDepto, int idCatArt) throws Exception {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select *"
                    + "from SIBO_TB_Exis exist, SIBO_TB_Articulo art, "
                    + "ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Cata_Arti cat "
                    + "where art.Arti_Depa_FK = dpto.Cata_Depa_id_PK and exist.Exis_Id_Arti = art.Arti_Id_PK "
                    + "and cat.Cata_Id_PK=art.Arti_Codi_Cata_Arti_FK "
                    + "and Cata_Depa_id_PK=" + idDepto + " and art.Arti_Codi_Cata_Arti_FK=" + idCatArt + ";";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private SboTbArticulo Articulo2(ResultSet rs) {
        try {
            SboTbArticulo arti = new SboTbArticulo();
            AbaaTbDepartamento dpto = new AbaaTbDepartamento();
            SboTbCatArticulo cat = new SboTbCatArticulo();
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            arti.setArtIdPk(rs.getInt("Arti_Id_PK"));
            arti.setArtDesc(rs.getString("Arti_Desc"));
            arti.setArtMode(rs.getString("Arti_Mode"));
            arti.setArtMarc(rs.getString("Arti_Marc"));
            arti.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            cat.setCatIdPk(rs.getInt("Cata_Id_PK"));
            cat.setCatDesc(rs.getString("Cata_Desc"));
            dpto.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            dpto.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            arti.setSboTbCatArticulo(cat);
            arti.setAbaaTbDepartamento(dpto);
            oc.setOcIdPk(rs.getInt("OC_Id_PK"));
            arti.setSboTbOrdenCompra(oc);

            return arti;
        } catch (SQLException ex) {
            return null;
        }
    }

// se crea un objeto de tipo existencia
    private SboTbExistencia existencia(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setSboTbBodega(Bodega(rs));
            ob.setArticulo(Articulo2(rs));
            ob.setSboTbEsta(rs.getInt("Exis_Esta"));
            //ob.setExisCant(rs.getDouble("Exis_Cant"));
            //ob.setAbaaTbDepartamento(departamento(rs));
            // ob.setSboTbSicop(sicop(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

// se crea un objeto de tipo bodega
    private SboTbBodega Bodega(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeIdPk(rs.getInt("Bode_Id_PK"));
            bodega.setBodeUbic(rs.getString("Bode_Ubic"));
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }
// se crea un objeto de tipo catalogo de articulo ***

    private SboTbCatArticulo catArticulo(ResultSet rs) {
        try {
            SboTbCatArticulo ob = new SboTbCatArticulo();
            ob.setCatIdPk(rs.getInt("Cata_Id_PK"));
            ob.setCatDesc(rs.getString("Cata_Desc"));
            ob.setArtCat_Estado(rs.getString("Cata_Esta"));
            ob.setSboTbSubFamilia(Subfamilia(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se crea un objeto de tipo subfamilia
    private SboTbSubFamilia Subfamilia(ResultSet rs) {
        try {
            SboTbSubFamilia ob = new SboTbSubFamilia();
            ob.setSubFamiIdPk(rs.getString("Sub_Fami_Id_PK"));
            ob.setSboTbFamilia(familia(rs));
            ob.setSubFamiDesc(rs.getString("Sub_Fami_Desc"));
            ob.setSubFamiEstado(rs.getString("Sub_Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    // se crea un objeto de tipo familia

    private SboTbFamilia familia(ResultSet rs) {
        try {
            SboTbFamilia ob = new SboTbFamilia();
            ob.setFamiDesc(rs.getString("Fami_Desc"));
            ob.setFamiIdPk(rs.getString("Fami_Id_PK"));
            ob.setFamiEstado(rs.getString("Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    // se crea un objeto de tipo departamento

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }
    // se crea un objeto de tipo proyecto******

    private AbaaProyectos proyecto(ResultSet rs) {
        try {
            AbaaProyectos ob = new AbaaProyectos();
            ob.setProyIdPk(Integer.parseInt(rs.getString("Proy_Id_PK")));
            ob.setProyDesc(rs.getString("Proy_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    // se crea un objeto de tipo Orden de compra *****

    private SboTbOrdenCompra OrdenCompra(ResultSet rs) {
        try {
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            oc.setOcIdPk(rs.getInt("OC_Id_PK"));
            oc.setOcFecha(rs.getDate("OC_Fecha"));
            oc.setOcPrecTota(rs.getDouble("OC_Prec_Tota"));
            oc.setOcEsta(rs.getString("OC_Esta"));
            oc.setAbaaTbProveedor(Proveedor(rs));
            oc.setOcPlazoEntrega(rs.getString("OC_Plaz_Entr"));
            oc.setOcEntregarA(rs.getString("OC_Entr_A"));
            return oc;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se crea un objeto de tipo proveedor
    private AbaaTbProveedor Proveedor(ResultSet rs) {
        try {
            AbaaTbProveedor pro = new AbaaTbProveedor();
            pro.setProveIdProvePk(rs.getInt("Prove_Id_Prove_PK"));
            pro.setProveCodigo(rs.getString("Prove_Codi"));
            pro.setProveCedula(rs.getString("Prove_Cedu"));
            pro.setProveTelefono(rs.getInt("Prove_Tele"));
            pro.setProveCorreo(rs.getString("Prove_Corre"));
            pro.setProveFax(rs.getString("Prove_Fax"));
            pro.setProveNomb(rs.getString("Prove_Nomb"));
            return pro;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se obtiene el ultimo id de la tabla solicitud de articulos de tipo int
    private int lastInsertSolicitudArticulo(ResultSet rs) {
        try {
            int x;
            x = rs.getInt("seq");
            return x;
        } catch (SQLException ex) {
            return 0;
        }
    }

    // se agregra los datos a la tabla de solixarti
//    public void agregarSolicitudXArticulo(SboTbSolixArti objeto) throws Exception {
//        String query = "insert into SIBO_TB_Soli_X_Arti(Soli_Arti_Id_X_Soli_Arti_PK,Soli_Arti_Id_X_Arti_PK,"
//                + "Soli_Arti_X_Cant)"
//                + "values(?,?,?)";
//
//        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
//        preparedStmt.setInt(1, objeto.getSboTbSoliArti().getSolArtiIdPk());
//        preparedStmt.setInt(2, objeto.getSboSicop().getSicopId());
//        preparedStmt.setInt(3, objeto.getSolArtiCant());
//        preparedStmt.executeUpdate();
//        db.getConnection().close();
//    }
    // se muestran la solicitud que tiene el correspondiente id  
    public List<SboTbSoliArti> listadoSolicitudesArticulos(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep, ABAA_TB_Persona per "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
                    + "and sa.Soli_Arti_Id_Func_FK=per.Pers_id_PK "
                    + "and sa.Soli_Arti_Id_Depa_FK=dep.Cata_Depa_id_PK";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se crea un objeto solicitud
    private SboTbSoliArti soliArti(ResultSet rs) {
        try {
            SboTbSoliArti solArt = new SboTbSoliArti();
            solArt.setSolArtiIdPk(rs.getInt("Soli_Arti_Id_PK"));
            solArt.setSolArtiVistJefe(rs.getBoolean("Soli_Arti_Vist_Jefe"));
            solArt.setSolArtiVistTi(rs.getBoolean("Soli_Arti_Vist_Ti"));
            solArt.setSolArtiFechSoli(rs.getDate("Soli_Arti_Fech_Soli"));
            solArt.setAbaaTbDepartamento(departamento(rs));
            solArt.setAbaaTbPersona(persona(rs));
            solArt.setSolArtiEsta(rs.getString("Soli_Arti_Esta"));
            return solArt;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se crea un objeto de tipo persona
    private AbaaTbPersona persona(ResultSet rs) {
        try {
            AbaaTbPersona ob = new AbaaTbPersona();
            ob.setPersIdPK(rs.getInt("Pers_id_PK"));
            ob.setDepartamento(departamento(rs));
            ob.setPersApe1(rs.getString("Pers_ape1"));
            ob.setPersApe2(rs.getString("Pers_ape2"));
            ob.setPersNomb(rs.getString("Pers_nomb"));
            ob.setPersSfun(rs.getByte("Pers_es_func"));
            ob.setPers_es_jefe(rs.getByte("Pers_es_jefe"));
            ob.setPersCedu(rs.getString("Pers_cedu"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se selecciona los articulos por solicitud
    public List<SboTbSolixArti> listadoArticulosPorSolicitud(int filtro) {
        List<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_X_Arti s\n"
                    + "inner join SIBO_TB_Exis e on s.Soli_Arti_Id_X_Exis_FK=e.Exis_Id_PK\n"
                    + "inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "where s.Soli_Arti_Id_X_Soli_Arti_PK='%s';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(solixArtiCompleto(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se crea un objeto de tipo solixArti
    private SboTbSolixArti solixArti(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setExistencia(existencia(rs));
            solxArt.setSboTbSoliArti(soliArti(rs));
            solxArt.setSolArtiDeta(rs.getString("Soli_Arti_Deta"));
            solxArt.setSolArtiSali(rs.getDate("Soli_Arti_Fech_Sali"));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se crea un objeto de tipo solixArti completo con inner join a existncia
    private SboTbSolixArti solixArtiCompleto(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setExistencia(existencia(rs));
            solxArt.setSboTbSoliArti(soliArti(rs));
            solxArt.setSolArtiDeta(rs.getString("Soli_Arti_Deta"));
            solxArt.setSolArtiSali(rs.getDate("Soli_Arti_Fech_Sali"));
            solxArt.setExistencia(existenciaCompleto(rs));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbExistencia existenciaCompleto(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setArticulo(ArticuloCompleto(rs));
            ob.setSboTbEsta(rs.getInt("Exis_Esta"));
            //ob.setExisCant(rs.getDouble("Exis_Cant"));
            //ob.setAbaaTbDepartamento(departamento(rs));
            // ob.setSboTbSicop(sicop(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbArticulo ArticuloCompleto(ResultSet rs) {
        try {
            SboTbArticulo arti = new SboTbArticulo();
            arti.setArtIdPk(rs.getInt("Arti_Id_PK"));
            arti.setArtDesc(rs.getString("Arti_Desc"));
            arti.setArtMode(rs.getString("Arti_Mode"));
            arti.setArtMarc(rs.getString("Arti_Marc"));
            arti.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            return arti;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se seleccionan las solicitudes con el estado pendiente
    public List<SboTbSoliArti> listadoSolicitudxAprobar(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {

            String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep, ABAA_TB_Persona per  "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
                    + "and (sa.Soli_Arti_Esta = 'pendiente' or sa.Soli_Arti_Esta = 'VBJefeAprobado' or sa.Soli_Arti_Esta = 'VBTIAprobado' or sa.Soli_Arti_Esta = 'PendienteVBJefe' or sa.Soli_Arti_Esta = 'PendienteVBTI')"
                    + "and sa.Soli_Arti_Id_Depa_FK=dep.Cata_Depa_id_PK and sa.Soli_Arti_Id_Func_FK=per.Pers_id_PK";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    //se actualiza el estado de la solicitud
    public void actualizarEstSolicitud(SboTbSoliArti objeto) throws SQLException {
        String query = "update SIBO_TB_Soli_Arti set Soli_Arti_Esta = ? where Soli_Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSolArtiEsta());
        preparedStmt.setInt(2, objeto.getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }

    // se selecciona la solicitud por id
    public SboTbSoliArti getSboTbSoliArti(int filtro) throws Exception {
        String sql = "select * from SIBO_TB_Soli_Arti f where f.Soli_Arti_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return soliArti(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    // se selecciona el regisro de la tabla solixarti por id de la solicitud
    public SboTbSolixArti getSboTbSolixArti(int filtro) throws Exception {
        String sql = "select * from SIBO_TB_Soli_X_Arti s inner join SIBO_TB_Articulo a on s.Soli_Arti_Id_X_Arti_PK = a.Arti_Id_PK inner join SIBO_TB_Soli_Arti sa on s.Soli_Arti_Id_X_Soli_Arti_PK = sa.Soli_Arti_Id_PK"
                + " where s.Soli_Arti_Id_X_Soli_Arti_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return solixArti(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

// se muestran los datos que estan pendientes del visto bueno por parte del jefe
    public List<SboTbSoliArti> listadoSolicitudVistobuenoJf(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep "
                    + "where sa.Soli_Arti_Id_Depa_FK like '%%%s%%'"
                    + "and sa.Soli_Arti_Esta = 'PendienteVBJefe'"
                    + "and sa.Soli_Arti_Id_Depa_FK=dep.Cata_Depa_id_PK";
//            String sql = "select * from Sbo_TB_Soli_Arti o where o.Sol_Arti_Esta='pendiente' and o.Sol_Arti_Id_PK like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se muestran los datos que estan pendientes del visto bueno por parte del departamento de TI
    public List<SboTbSoliArti> listadoSolicitudVistobuenoTI(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
                    + "and sa.Soli_Arti_Esta = 'PendienteVBTI'"
                    + "and sa.Soli_Arti_Id_Depa_FK=dep.Cata_Depa_id_PK";
//            String sql = "select * from Sbo_TB_Soli_Arti o where o.Sol_Arti_Esta='pendiente' and o.Sol_Arti_Id_PK like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se actualizan los datos que con la opcion  seleccionado por parte del jefe
    public void actualizarEstSolicitudJefe(SboTbSoliArti objeto) throws SQLException {
        String query = "update SIBO_TB_Soli_Arti set Soli_Arti_Esta = ?, Soli_Arti_Vist_Jefe = ? where Soli_Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSolArtiEsta());
        preparedStmt.setBoolean(2, objeto.getSolArtiVistJefe());
        preparedStmt.setInt(3, objeto.getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }
// se actualizan los datos que con la opcion  seleccionado por parte del departamento de TI

    public void actualizarEstSolicitudTI(SboTbSoliArti objeto) throws SQLException {
        String query = "update SIBO_TB_Soli_Arti set Soli_Arti_Esta = ?, Soli_Arti_Vist_Ti = ? where Soli_Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSolArtiEsta());
        preparedStmt.setBoolean(2, objeto.getSolArtiVistTi());
        preparedStmt.setInt(3, objeto.getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }

    // se inserta la solicitid mediante el procedimiento
    public void InsertarSoli(SboTbSoliArti objeto) throws Exception {
        String query = "execute Soli_Insertar ?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setInt(2, objeto.getAbaaTbPersona().getPersIdPK());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

// se ejecuta el procedimiento para disminuir las existencias
//    public void disminuyeExistencias(SboTbSolixArti objeto) throws Exception {
//        String query = "execute DisminuyeExistencias ?,?,?;";
//        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
//        preparedStmt.setInt(1, objeto.getSboSicop().getSicopId());
//        preparedStmt.setInt(2, objeto.getSolArtiCant());
//        preparedStmt.setInt(3, objeto.getSboTbSoliArti().getSolArtiIdPk());
//        preparedStmt.executeUpdate();
//        db.getConnection().close();
//    }
    // se selecciona el ultimo id de la tabla soli_arti
    public int getLastInsertSolicitudArticulo() throws Exception {
        String sql = " select IDENT_CURRENT( 'SIBO_TB_Soli_Arti' ) as seq ";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return lastInsertSolicitudArticulo(rs);
        } else {
            throw new Exception("error");
        }
    }

// se muestran las solicitudes que tiene un funcionario pendiente
    public List<SboTbSoliArti> listadoSolicitudPorFuncionarioPendientes(int filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_Arti s where s.Soli_Arti_Id_Func_FK =" + filtro + "and s.Soli_Arti_Esta = 'pendiente';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se muestran todas las solicitudes realizadas por un funcionario
    public List<SboTbSoliArti> listadoSolicitudPorFuncionarioTotal(int filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_Arti s where s.Soli_Arti_Id_Func_FK =" + filtro + " and s.Soli_Arti_Esta <> 'pendiente';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

}

// se agrega en la tabla solixarti por medio de un procedimiento
//    public void insertarSolxArt(SboTbSolixArti objeto) throws Exception {
//        String sql = "Execute agregarSoliXarti ?,?,?,?;";
//        PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
//        preparedStmt.setInt(1, objeto.getId().getSolixArtiIdSoliArtiPk());
//        preparedStmt.setInt(2, objeto.getSboSicop().getSicopId());
//        preparedStmt.setInt(3, objeto.getSolArtiCant());
//        preparedStmt.setString(4, objeto.getSolArtiDeta());
//        preparedStmt.executeUpdate();
//        db.getConnection().close();
//    }

