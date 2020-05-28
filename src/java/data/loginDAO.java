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

public class loginDAO {

    RelDatabase db;

    public loginDAO() {
        db = new RelDatabase();
    }

    
    //se ejecuta sisbologin en la base de datos para el inicio de sesion
    public AbaaTbPersona logged(String user, String password) throws SQLException, Exception {
        String query = "execute sisboLogin ?,?;";
        CallableStatement callableStatement = db.getConnection().prepareCall(query);
        callableStatement.setString(1, user);
        callableStatement.setString(2, password);//use OracleTypes.CURSOR
        callableStatement.execute();
        ResultSet rs = (ResultSet) callableStatement.executeQuery();
        while (rs.next()) {
            return persona(rs);
        }
        return null;
    }
// se crea el objeto persona
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
// se crea el objeto departamento
    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Depa_id_FK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }
    
// se crea un objeto de tipo RolxPermiso ******
    private AbaaTbRolxPermiso RolxPermiso(ResultSet rs) {
        try {
            AbaaTbRolxPermiso ob = new AbaaTbRolxPermiso();
            ob.setRol_x_Perm_id_PK(rs.getInt("Rol_x_Perm_id_FK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }
}
