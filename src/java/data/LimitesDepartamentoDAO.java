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
import logic.SboSicop;
import logic.SboTbExistencia;
import logic.SboTbLimiteDpto;

/**
 *
 * @author Marco
 */
public class LimitesDepartamentoDAO {

    RelDatabase db;

    public LimitesDepartamentoDAO() {
        db = new RelDatabase();
    }

    private SboTbLimiteDpto limites(ResultSet rs) {
        try {
            SboTbLimiteDpto li = new SboTbLimiteDpto();
            li.getAbaaTbDepartamento().setDeptoIdPk(rs.getString("Limi_Depa_limi_Id_Dpto_PK"));
            li.getSboSicop().setSicopId(rs.getInt("Sico_Id_PK"));
            li.setLimiteDptoLimite(rs.getInt("Limi_Depa_limi"));
            
            return li;
        } catch (SQLException ex) {
            return null;
        }
    }

    public SboTbLimiteDpto getLimiteDepaPorExis(SboTbExistencia exis) throws SQLException, Exception {
        String sql = "select limi.Limi_Depa_limi  \n"
                + "from SIBO_TB_Limi_Depa limi, SIBO_TB_Sicop sicop, ABAA_TB_Catalogo_Departamento depa\n"
                + "where sicop.Sico_Id_PK = limi.Limi_Depa_Id_Sico_PK "
                + "and limi.Limi_Depa_Id_Dpto_PK = depa.Cata_Depa_id_PK and "
                + "limi.Limi_Depa_Id_Dpto_PK=" + exis.getAbaaTbDepartamento().getDeptoIdPk()
                + " and limi.Limi_Depa_Id_Sico_PK=" + exis.getSboTbSicop().getSicopId();
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return limites(rs);
        } else {
            throw new Exception("LimiteDepto no Existe");
        }

    }
    
    public void insertLimites(SboTbLimiteDpto ilimites) throws SQLException, Exception{
        String sql = "insert into SIBO_TB_Limi_Depa(Limi_Depa_Id_Dpto_PK,Limi_Depa_Id_Sico_PK,Limi_Depa_limi)"
                + " values(?,?,?);";
        
        PreparedStatement pstmt = db.getConnection().prepareStatement(sql);
        pstmt.setString(1,ilimites.getAbaaTbDepartamento().getDeptoIdPk());
        pstmt.setInt(2,ilimites.getSboSicop().getSicopId());
        pstmt.setInt(3,ilimites.getLimiteDptoLimite());
        pstmt.executeUpdate();
        db.getConnection().close();
    }
    
     public List<SboTbLimiteDpto> listaLimites(String departamento, String sicop) {
        List<SboTbLimiteDpto> resultado = new ArrayList<SboTbLimiteDpto>();
        try {
            String sql = "select depa.Cata_Depa_nomb, sicop.Sico_Desc, limi.Limi_Depa_limi \n" +
                        "from SIBO_Tb_Sicop sicop, ABAA_TB_Catalogo_Departamento depa, SIBO_TB_Limi_Depa limi\n" +
                        "where sicop.Sico_Id_PK = limi.Limi_Depa_Id_Sico_Pk \n" +
                        "and limi.Limi_Depa_Id_Sico_Pk = depa.Cata_Depa_id_PK\n" +
                        "and sicop.Sico_Id_PK ="+ departamento +"and depa.Cata_Depa_id_PK = "+sicop+";";
           sql = String.format(sql, departamento, sicop);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(limites(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
     
     public void updateLimites(SboTbLimiteDpto depa) throws SQLException {

        String query = "update SIBO_TB_Limi_Depa set Limi_Depa_limi=? where Limi_Depa_Id_Dpto_PK = ? and Limi_Depa_Id_Sico_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, depa.getLimiteDptoLimite());
        preparedStmt.setString(2, depa.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setInt(3,depa.getSboSicop().getSicopId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
     
     public void deleteLimites(SboTbLimiteDpto s) throws SQLException {

        String query = "delete SIBO_TB_Limi_Depa=? where Limi_Depa_Id_Dpto_PK = ? and Limi_Depa_Id_Sico_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, s.getLimiteDptoLimite());
        preparedStmt.setString(2, s.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setInt(3,s.getSboSicop().getSicopId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
     
     public SboTbLimiteDpto getLimites(String id, String id2) throws Exception {
        String sql = "select * from SIBO_TB_Limi_Depa where Limi_Depa_Id_Dpto_PK = '%s'"
                + "and Limi_Depa_Id_Sico_PK = '%s'";
        sql = String.format(sql, id,id2);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return limites(rs);
        } else {
            throw new Exception("Bodega no Existe");
        }
    }
     
}
