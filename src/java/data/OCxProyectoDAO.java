/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.PreparedStatement;
import logic.AbaaTbOcproyecto;
import logic.SboTbArticulo;

/**
 *
 * @author Osvaldo Vargas
 */
public class OCxProyectoDAO {
 
    RelDatabase db;
    
    public OCxProyectoDAO() {
        db = new RelDatabase();
    }
    
    public void agregarDatos(AbaaTbOcproyecto objeto) throws Exception{
        String query = "insert into ABAA_TB_OCProyecto(OCProy_OC_ID,OCProy_Proy_ID,OCProy_Arti_ID)"
                + "values(?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, objeto.getSboTbOrdenCompra().getOcIdPk());
        preparedStmt.setInt(2, objeto.getAbaaProyectos().getProyIdPk());
        preparedStmt.setInt(3, objeto.getSboTbArticulo().getArtIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
}
