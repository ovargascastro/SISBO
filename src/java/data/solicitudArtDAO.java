/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaProyectos;
import logic.AbaaTbDepartamento;
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

    private SboSicop articulo(ResultSet rs) {
        try {
            SboSicop ar = new SboSicop();
            ar.setSicopId(rs.getInt("sicopId"));
            ar.setSicopDesc(rs.getString("sicopDesc"));
            ar.setSicopCodiInden(rs.getString("sicopCodiInden"));
            ar.setSicopCodiClas(rs.getString("sicopCodiClas"));
            return ar;
        } catch (SQLException ex) {
            return null;
        }
    }

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
    
    private SboTbExistencia existencia(ResultSet rs) {
        try {
            SboTbExistencia existencia = new SboTbExistencia();
            existencia.setSboTbBodega(Bodega(rs));
            //existencia.setSboTbArticulo(articulo(rs));
            existencia.setExisCant(rs.getDouble("Exis_Cant"));
            return existencia;
        } catch (SQLException ex) {
            return null;
        }
    }
    
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
    public void agregarSolicitudArticulo(SboTbSoliArti objeto) throws Exception {
        String query = "insert into SIBO_TB_Soli_Arti(Soli_Arti_Fech_Soli,Soli_Arti_Id_Depa_FK,"
                + "Soli_Arti_Id_Func_FK,Soli_Arti_Esta,Soli_Arti_Desc)"
                + "values(?,?,?,?,?)";

        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        java.util.Date utilStartDate = objeto.getSolArtiFechSoli();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(1, sqlStartDate);

        preparedStmt.setString(2, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setInt(3, objeto.getAbaaTbFuncionario().getFuncIdPk());
        preparedStmt.setString(4, objeto.getSolArtiEsta());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

  

    private int lastInsertSolicitudArticulo(ResultSet rs) {
        try {
            int x;
            x = rs.getInt("seq");
            return x;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public void agregarSolicitudXArticulo(SboTbSolixArti objeto) throws Exception {
        String query = "insert into SIBO_TB_Soli_X_Arti(Soli_Arti_Id_X_Soli_Arti_PK,Soli_Arti_Id_X_Arti_PK,"
                + "Soli_Arti_X_Cant)"
                + "values(?,?,?)";

        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, objeto.getSboTbSoliArti().getSolArtiIdPk());
        preparedStmt.setInt(2, objeto.getSboSicop().getSicopId());
        preparedStmt.setInt(3, objeto.getSolArtiCant());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
    
    public List<SboTbSoliArti> listadoSolicitudesArticulos(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
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
    
    private SboTbSoliArti soliArti(ResultSet rs) {
        try {
            SboTbSoliArti solArt = new SboTbSoliArti();
            solArt.setSolArtiIdPk(rs.getInt("Soli_Arti_Id_PK"));
            solArt.setSolArtiVistJefe(rs.getBoolean("Soli_Arti_Vist_Jefe"));
            solArt.setSolArtiVistTi(rs.getBoolean("Soli_Arti_Vist_Ti"));
            solArt.setSolArtiFechSoli(rs.getDate("Soli_Arti_Fech_Soli"));
            solArt.setAbaaTbDepartamento(departamento(rs));
            solArt.setSolArtiEsta(rs.getString("Soli_Arti_Esta"));
            return solArt;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<SboTbSolixArti> listadoArticulosPorSolicitud(int filtro) {
        List<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql ="select * "
                    + "from SIBO_TB_Articulo art, SIBO_TB_Soli_Arti solArt, SIBO_TB_Soli_X_Arti sxa "
                    + "where art.Arti_Id_PK = sxa.Soli_Arti_Id_X_Arti_PK "
                    + "and solart.Soli_Arti_Id_PK = sxa.Soli_Arti_Id_X_Soli_Arti_PK "
                    + "and solArt.Soli_Arti_Id_PK = '%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(solixArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    private SboTbSolixArti solixArti(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setSboSicop(articulo(rs));
            solxArt.setSboTbSoliArti(soliArti(rs));
            solxArt.setSolArtiCant(rs.getInt("Soli_Arti_X_Cant"));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }
    
 
    
    
    public List<SboTbSoliArti> listadoSolicitudxAprobar(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
                String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
                    + "and (sa.Soli_Arti_Esta = 'pendiente' or sa.Soli_Arti_Esta = 'VBJefeAprobado' or sa.Soli_Arti_Esta = 'VBTIAprobado' or sa.Soli_Arti_Esta = 'PendienteVBJefe' or sa.Soli_Arti_Esta = 'PendienteVBTI')"
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

    public void actualizarEstSolicitud(SboTbSoliArti objeto) throws SQLException {
        String query = "update SIBO_TB_Soli_Arti set Soli_Arti_Esta = ? where Soli_Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSolArtiEsta());
        preparedStmt.setInt(2, objeto.getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }
    
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

          public List<SboTbSoliArti> listadoSolicitudVistobuenoJf(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
                String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
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

          public List<SboTbSoliArti> listadoSolicitudVistobuenoTI(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
                String sql = "select * from SIBO_TB_Soli_Arti sa, ABAA_TB_Catalogo_Departamento dep "
                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'"
                    + "and sa.Soli_Arti_Esta = 'PendienteVBTI'"
                    + "and sa.Soli_Arti_Id_Depa_FK=dep.Depto_Id_PK";
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
     
           public void actualizarEstSolicitudJefe(SboTbSoliArti objeto) throws SQLException {
        String query = "update SIBO_TB_Soli_Arti set Soli_Arti_Esta = ?, Soli_Arti_Vist_Jefe = ? where Soli_Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSolArtiEsta());
        preparedStmt.setBoolean(2, objeto.getSolArtiVistJefe());
        preparedStmt.setInt(3, objeto.getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }
          
        public void actualizarEstSolicitudTI(SboTbSoliArti objeto) throws SQLException {
        String query = "update SIBO_TB_Soli_Arti set Soli_Arti_Esta = ?, Soli_Arti_Vist_Ti = ? where Soli_Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSolArtiEsta());
        preparedStmt.setBoolean(2, objeto.getSolArtiVistTi());
        preparedStmt.setInt(3, objeto.getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }
        
           public void disminuyeExistencias(SboTbSolixArti objeto) throws Exception {
        String query = "execute DisminuyeExistencias ?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, objeto.getSboSicop().getSicopId());
        preparedStmt.setInt(2, objeto.getSolArtiCant());
        preparedStmt.setInt(3, objeto.getSboTbSoliArti().getSolArtiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
        

       public void InsertarSoli(SboTbSoliArti objeto) throws Exception {
        String query = "execute Soli_Insertar ?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setInt(2, objeto.getAbaaTbFuncionario().getFuncIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
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
//         public SboTbSoliArti listadoSolicitudFiltro(int filtro) {
//        SboTbSoliArti resultado = new SboTbSoliArti();
//        try {
//                String sql = "select * from SIBO_TB_Soli_Arti sa"
//                    + "where sa.Soli_Arti_Id_PK like '%%%s%%'";
//            sql = String.format(sql, filtro);
//            ResultSet rs = db.executeQuery(sql);
//           if (rs.next()) {
//               return soliArti(rs);
//            }
//        } catch (SQLException ex) {
//        }
//        return resultado;
//    }
}
