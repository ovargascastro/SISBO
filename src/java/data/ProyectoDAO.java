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
import logic.SboTbExistencia;

/**
 *
 * @author Osvaldo Vargas
 */
public class ProyectoDAO {
    RelDatabase db;
    
    public ProyectoDAO() {
        db = new RelDatabase();
    }
    // se crea un objeto proyecto
    private AbaaProyectos proyecto(ResultSet rs) {
        try {
            AbaaProyectos ob = new AbaaProyectos();
            ob.setProyIdPk(Integer.parseInt(rs.getString("Proy_id_PK")));
            ob.setProyDesc(rs.getString("Proy_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    // se listaan los proyectos
    public List<AbaaProyectos> listaProyecto() {
        List<AbaaProyectos> resultado = new ArrayList<AbaaProyectos>();
        try {
            String sql = "select * from ABAA_TB_Proyecto";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(proyecto(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    
        public void insertarOCProyecto(int orden, int proy, int arti) throws SQLException {
        String query = "insert into ABAA_TB_OCProyecto(OCProy_OC_ID, OCProy_Proy_ID, OCProy_Arti_ID)"
                + "values(?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, orden);
        preparedStmt.setInt(2, proy);
        preparedStmt.setInt(3, arti);

        preparedStmt.executeUpdate();
        db.getConnection().close();

    }
}
