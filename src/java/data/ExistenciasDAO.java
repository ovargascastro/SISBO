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

public class ExistenciasDAO {

    RelDatabase db;

    public ExistenciasDAO() {
        db = new RelDatabase();
    }

    private SboTbBodega Bodega(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            //bodega.setBodeIdPk(rs.getInt("Bode_Id_PK"));
            // bodega.setBodeUbic(rs.getString("Bode_Ubic"));
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
            //  ob.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
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
            ob.setSicopId(rs.getInt("Sico_Id_PK"));
//            ob.setSicopCodiClas(rs.getString("Sico_Codi_Clas"));
//            ob.setSicopCodiInden(rs.getString("Sico_Codi_Iden"));
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
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbExistencia existencia(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setSboTbBodega(Bodega(rs));
            ob.setExisCant(rs.getDouble("Exis_Cant"));
            ob.setAbaaTbDepartamento(departamento(rs));
            ob.setSboTbSicop(sicop(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbExistencia existencia2(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setSboTbBodega(Bodega2(rs));
            ob.setSboTbSicop(sicop2(rs));
            ob.setAbaaTbDepartamento(departamento2(rs));
            ob.setExisCant(rs.getDouble("Exis_Cant"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

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

    public List<SboTbExistencia> listaExistenciasfiltro(String depa) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select bode.Bode_Desc, dpto.Cata_Depa_nomb,sicop.Sico_Id_PK, sicop.Sico_Desc, exis.Exis_Cant\n"
                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"
                    + "SIBO_TB_Exis exis\n"
                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"
                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"
                    + "and exis.Exist_Depa_PK=" + depa;
            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public SboTbExistencia getSboTbExistencia(String departamento, String articulo) throws SQLException, Exception {
        String sql = "select Exis_Cant from SIBO_TB_Exis exis\n"
                + "where exis.Exist_Depa_PK=" + departamento + "\n"
                + "and exis.Exis_Id_Sico_PK=" + articulo + ";";
        sql = String.format(sql, departamento, articulo);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return existencia(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    public SboTbExistencia registroExistenciasPorSolicitud(String depa, String sicop) throws SQLException, Exception {
        String sql = "select SIBO_TB_Exis.Exis_Id_Bode_PK,SIBO_TB_Exis.Exis_Id_Sico_PK,SIBO_TB_Exis.Exist_Depa_PK,SIBO_TB_Exis.Exis_Cant \n"
                + "from SIBO_TB_Exis,SIBO_TB_Bode,SIBO_TB_Sicop,ABAA_TB_Catalogo_Departamento\n"
                + "where SIBO_TB_Exis.Exis_Id_Bode_PK=SIBO_TB_Bode.Bode_Id_PK\n"
                + "and SIBO_TB_Exis.Exis_Id_Sico_PK=SIBO_TB_Sicop.Sico_Id_PK\n"
                + "and SIBO_TB_Exis.Exist_Depa_PK=ABAA_TB_Catalogo_Departamento.Cata_Depa_id_PK\n"
                + "and SIBO_TB_Exis.Exis_Id_Sico_PK='%s' and SIBO_TB_Exis.Exist_Depa_PK='%s';";
        sql = String.format(sql, sicop, depa);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return existencia2(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    public void actualizarExistencia(SboTbExistencia objeto) throws Exception {
        String query = "update SIBO_TB_Exis set Exis_Cant=? where Exis_Id_Bode_PK=? and Exis_Id_Sico_PK=? and Exist_Depa_PK=?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setDouble(1, objeto.getExisCant());
        preparedStmt.setInt(2, objeto.getSboTbBodega().getBodeIdPk());
        preparedStmt.setInt(3, objeto.getSboTbSicop().getSicopId());
        preparedStmt.setString(4, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
