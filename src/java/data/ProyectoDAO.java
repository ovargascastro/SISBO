/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaProyectos;

/**
 *
 * @author Osvaldo Vargas
 */
public class ProyectoDAO {
    RelDatabase db;
    
    public ProyectoDAO() {
        db = new RelDatabase();
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
    public List<AbaaProyectos> listaProyecto() {
        List<AbaaProyectos> resultado = new ArrayList<AbaaProyectos>();
        try {
            String sql = "select * from ABAA_Proyectos";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(proyecto(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
}
