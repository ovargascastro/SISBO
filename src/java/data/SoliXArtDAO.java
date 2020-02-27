/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import logic.SboTbSolixArti;

/**
 *
 * @author Marco
 */
public class SoliXArtDAO {
    
    RelDatabase db;
    
    
    public SoliXArtDAO(){
        db = new RelDatabase();
    }
    
     public void insertarSolxArt(SboTbSolixArti objeto) throws Exception{
       String sql = "Execute agregarSoliXarti ?,?,?,?;";
       PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
       preparedStmt.setInt(1,objeto.getSboTbSoliArti().getSolArtiIdPk());
       preparedStmt.setInt(2, objeto.getSboSicop().getSicopId());
       preparedStmt.setInt(3, objeto.getSolArtiCant());
       preparedStmt.setString(4, objeto.getSolArtiDeta());
       preparedStmt.executeUpdate();
       db.getConnection().close(); 
       }
}
