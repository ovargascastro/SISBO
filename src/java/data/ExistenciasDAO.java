package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.SboSicop;
import logic.SboTbBodega;
import logic.SboTbCatContable;
import logic.SboTbExistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.SboSicop;
import logic.SboTbArticulo;
import logic.SboTbBodega;
import logic.SboTbCatArticulo;
import logic.SboTbExistencia;
import logic.SboTbOrdenCompra;

public class ExistenciasDAO {

    RelDatabase db;

    public ExistenciasDAO() {
        db = new RelDatabase();
    }

    private SboTbBodega Bodega(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbBodega Bodega2(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeIdPk(rs.getInt("Exis_Id_Bode_PK"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbDepartamento departamento2(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Exist_Depa_PK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboSicop sicop(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboSicop sicop2(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopId(rs.getInt("Exis_Id_Sico_PK"));
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

//    private SboTbExistencia existencia(ResultSet rs) {
//        try {
//            SboTbExistencia ob = new SboTbExistencia();
//            ob.setSboTbBodega(Bodega(rs));
//            ob.setIdE(rs.getInt("Exis_Id_Bode_PK"));
//            ob.setExisCant(rs.getDouble("Exis_Cant"));
//            ob.setAbaaTbDepartamento(departamento(rs));
//            ob.setSboTbSicop(sicop(rs));
//            return ob;
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
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

    private SboTbArticulo ArticuloExist(ResultSet rs) {
        try {
            SboTbArticulo arti = new SboTbArticulo();
            AbaaTbDepartamento dpto = new AbaaTbDepartamento();
            SboTbCatArticulo cat = new SboTbCatArticulo();
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
             SboSicop ob = new SboSicop();
            arti.setArtIdPk(rs.getInt("Arti_Id_PK"));
            arti.setArtDesc(rs.getString("Arti_Desc"));
            arti.setArtMode(rs.getString("Arti_Mode"));
            arti.setArtMarc(rs.getString("Arti_Marc"));
            arti.setArtPrecio(rs.getDouble("Arti_Prec"));
            arti.setSboSicop(sicop(rs));
            arti.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            dpto.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            dpto.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            arti.setAbaaTbDepartamento(dpto);
            return arti;
        } catch (SQLException ex) {
            return null;
        }
    }
    

    
    
        private SboTbExistencia existenciaF (ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setSboTbBodega(Bodega(rs));
            ob.setArticulo(ArticuloExist(rs));
            ob.setSboTbEsta(rs.getInt("Exis_Esta"));
            ob.setId(rs.getInt("Exis_Id_PK"));
            ob.setExistFingr(rs.getDate("Exis_Fech_Ingr"));
            //ob.setExisCant(rs.getDouble("Exis_Cant"));
            //ob.setAbaaTbDepartamento(departamento(rs));
            // ob.setSboTbSicop(sicop(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

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

//    private SboTbExistencia existencia2(ResultSet rs) {
//        try {
//            SboTbExistencia ob = new SboTbExistencia();
//            ob.setSboTbBodega(Bodega2(rs));
//            ob.setSboTbSicop(sicop2(rs));
//            ob.setAbaaTbDepartamento(departamento2(rs));
//            ob.setExisCant(rs.getDouble("Exis_Cant"));
//            return ob;
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
//    private SboTbExistencia existencia3(ResultSet rs) {
//        try {
//            SboTbExistencia ob = new SboTbExistencia();
//            ob.setSboTbBodega(Bodega(rs));
//            ob.setAbaaTbDepartamento(departamento(rs));
//            ob.setSboTbSicop(sicop2(rs));
//            ob.setExisCant(rs.getDouble("Exis_Cant"));
//            return ob;
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
//
//    private SboTbExistencia existencia4(ResultSet rs) {
//        try {
//            SboTbExistencia ob = new SboTbExistencia();
//            ob.setExisCant(rs.getDouble("Exis_Cant"));
//            return ob;
//        } catch (SQLException ex) {
//            return null;
//        }
//    }
//    public void updateExist(SboTbExistencia e) throws SQLException {
//        String query = "update SIBO_TB_Exis set Exis_Cant=? where Exis_Id_Bode_PK=? and Exis_Id_Sico_PK=? and Exist_Depa_PK=?";
//        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
//        preparedStmt.setDouble(1, e.getExisCant());
//        preparedStmt.setInt(2, e.getSboTbBodega().getBodeIdPk());
//        preparedStmt.setInt(3, e.getSboTbSicop().getSicopId());
//        preparedStmt.setString(4, e.getAbaaTbDepartamento().getDeptoIdPk());
//        preparedStmt.executeUpdate();
//        db.getConnection().close();
//    }
    public List<SboTbExistencia> listaExistencias(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select bode.Bode_Desc, dpto.Cata_Depa_nomb, sicop.Sico_Id_PK, sicop.Sico_Desc, exis.Exis_Cant\n"
                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"
                    + "SIBO_TB_Exis exis\n"
                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"
                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"
                    + "and exis.Exis_Id_Bode_PK=" + bodega + "\n"
                    + "and exis.Exist_Depa_PK=" + departamento + "\n"
                    + "and exis.Exis_Id_Sico_PK=" + articulo + ";";
            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> listaExistencias2(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select exis.Exis_Id_Bode_PK, bode.Bode_Desc, dpto.Cata_Depa_nomb, sicop.Sico_Desc, exis.Exis_Cant\n"
                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"
                    + "SIBO_TB_Exis exis\n"
                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"
                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"
                    + "and exis.Exis_Id_Bode_PK=" + bodega + "\n"
                    + "and exis.Exist_Depa_PK=" + departamento + "\n"
                    + "and exis.Exis_Id_Sico_PK=" + articulo + ";";

            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> listaExistenciasArticulos(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {

            String sql = "select * from SIBO_TB_Exis e inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Bode b on e.Exis_Id_Bode_PK= Bode_Id_Pk "
                    + "inner join ABAA_TB_Catalogo_Departamento d on a.Arti_Cata_Depa_FK=d.Cata_Depa_id_PK "
                    + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                    + "where e.Exis_Id_Bode_Pk=" + bodega + "and\n"
                    + "a.Arti_Cata_Depa_FK=" + departamento + "and a.Arti_Cod_Sico_FK=" + articulo + " and e.Exis_Esta='1';";

            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existenciaF(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    
    public List<SboTbExistencia> listaExistenciasfiltro(String depa) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select * from SIBO_TB_Exis e inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Bode b on e.Exis_Id_Bode_PK= Bode_Id_Pk "
                    + "inner join ABAA_TB_Catalogo_Departamento d on a.Arti_Cata_Depa_FK=d.Cata_Depa_id_PK "
                    + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                    + "where a.Arti_Cata_Depa_FK=" + depa + "and\n"
                    + "e.Exis_Esta='1';";
            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existenciaF(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
//    public SboTbExistencia getSboTbExistencia(String departamento, String articulo) throws SQLException, Exception {
//        String sql = "select Exis_Cant from SIBO_TB_Exis exis\n"
//                + "where exis.Exist_Depa_PK=" + departamento + "\n"
//                + "and exis.Exis_Id_Sico_PK=" + articulo + ";";
//        sql = String.format(sql, departamento, articulo);
//        ResultSet rs = db.executeQuery(sql);
//        if (rs.next()) {
//            return existencia4(rs);
//        } else {
//            throw new Exception("Bien no Existe");
//        }
//    }
//    public SboTbExistencia registroExistenciasPorSolicitud(String depa, String sicop) throws SQLException, Exception {
//        String sql = "select SIBO_TB_Exis.Exis_Id_Bode_PK,SIBO_TB_Exis.Exis_Id_Sico_PK,SIBO_TB_Exis.Exist_Depa_PK,SIBO_TB_Exis.Exis_Cant, SIBO_TB_Sicop.Sico_Desc\n"
//                + "from SIBO_TB_Exis,SIBO_TB_Bode,SIBO_TB_Sicop,ABAA_TB_Catalogo_Departamento\n"
//                + "where SIBO_TB_Exis.Exis_Id_Bode_PK=SIBO_TB_Bode.Bode_Id_PK\n"
//                + "and SIBO_TB_Exis.Exis_Id_Sico_PK=SIBO_TB_Sicop.Sico_Id_PK\n"
//                + "and SIBO_TB_Exis.Exist_Depa_PK=ABAA_TB_Catalogo_Departamento.Cata_Depa_id_PK\n"
//                + "and SIBO_TB_Exis.Exis_Id_Sico_PK='%s' and SIBO_TB_Exis.Exist_Depa_PK='%s';";
//        sql = String.format(sql, sicop, depa);
//        ResultSet rs = db.executeQuery(sql);
//        if (rs.next()) {
//            return existencia2(rs);
//        } else {
//            throw new Exception("Bien no Existe");
//        }
//    }
    public void actualizarExistencia(SboTbExistencia objeto) throws Exception {
        String query = "update SIBO_TB_Exis set Exis_Esta=? where Exis_Id_PK=?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, 0);
        preparedStmt.setInt(2, objeto.getId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
//        public List<SboTbExistencia> listaConsumo(String depa) {
//        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
//        try {
//            String sql = "select bode.Bode_Desc, dpto.Cata_Depa_nomb,exis.Exis_Id_Sico_PK, sicop.Sico_Desc, exis.Exis_Cant\n"
//                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"
//                    + "SIBO_TB_Exis exis\n"
//                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
//                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"
//                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"
//                    + "and exis.Exist_Depa_PK=" + depa;
//            sql = String.format(sql, depa);
//            ResultSet rs = db.executeQuery(sql);
//            while (rs.next()) {
//                resultado.add(existencia3(rs));
//            }
//        } catch (SQLException ex) {
//        }
//        return resultado;
//    }
}