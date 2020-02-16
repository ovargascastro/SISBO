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
import logic.AbaaTbPersona;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.AbaaTbRolxPermiso;

/**
 *
 * @author oscar
 */
public class loginDAO {

    RelDatabase db;

    public loginDAO() {
        db = new RelDatabase();
    }

    public AbaaTbPersona logged(String user, String password) throws SQLException, Exception {

//            String sql = "execute sisboLogin ?,?;";
//            sql = String.format(sql,user,password);
//            ResultSet rs = db.executeQuery(sql);
//            if (rs.next()) {
//                return persona(rs);
//            } else {
//                throw new Exception("Persona no Existe");
//            }
        String query = "execute sisboLogin ?,?;";
        CallableStatement callableStatement = db.getConnection().prepareCall(query);
        callableStatement.setString(1, user);
        callableStatement.setString(2, password);//use OracleTypes.CURSOR
        callableStatement.execute();
        //executeUpdate();//execute USER_OUT store procedure

        ResultSet rs = (ResultSet) callableStatement.executeQuery();
        while (rs.next()) {
            return persona(rs);
        }
        return null;
    }

    private AbaaTbPersona persona(ResultSet rs) {
        try {
            AbaaTbPersona ob = new AbaaTbPersona();
            ob.setPersIdPK(rs.getInt("Pers_id_PK"));
            ob.setDepartamento(departamento(rs));
            ob.setPersApe1(rs.getString("Pers_ape1"));
            ob.setPersApe2(rs.getString("Pers_ape2"));
            ob.setPersCedu(rs.getString("Pers_cedu"));
            ob.setPersNomb(rs.getString("Pers_nomb"));
            ob.setPers_es_jefe(rs.getInt("Pers_es_jefe"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Cata_Depa_id_FK"));
            //ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbRolxPermiso RolxPermiso(ResultSet rs) {
        try {
            AbaaTbRolxPermiso ob = new AbaaTbRolxPermiso();
            ob.setRol_x_Perm_id_PK(rs.getInt("Rol_x_Perm_id_FK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

//        public List<AbaaTbDepartamento> listaDepartamento() {
//        List<AbaaTbDepartamento> resultado = new ArrayList<AbaaTbDepartamento>();
//        try {
//            String sql = "select * from ABAA_TB_Catalogo_Departamento";
//            sql = String.format(sql);
//            ResultSet rs = db.executeQuery(sql);
//            while (rs.next()) {
//                resultado.add(departamento(rs));
//            }
//        } catch (SQLException ex) {
//        }
//        return resultado;
//    }
}
