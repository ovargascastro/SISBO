/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.CallableStatement;
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
       String sql = "Execute agregarSoliXarti(?,?,?,?);";
       CallableStatement CallStmt = db.getConnection().prepareCall(sql);
       CallStmt.setInt(1,objeto.getId().getSolixArtiIdSoliArtiPk());
       CallStmt.setInt(2, objeto.getSboSicop().getSicopId());
       CallStmt.setInt(3, objeto.getSolArtiCant());
       CallStmt.setString(4, objeto.getSolArtiDeta());
       CallStmt.execute();
       db.getConnection().close(); 
       }
}
