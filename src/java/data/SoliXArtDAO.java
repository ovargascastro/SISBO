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
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.SboSicop;
import logic.SboTbSoliArti;
import logic.SboTbSolixArti;

/**
 *
 * @author Marco
 */
public class SoliXArtDAO {

    RelDatabase db;

    public SoliXArtDAO() {
        db = new RelDatabase();
    }

    public void insertarSolxArt(SboTbSolixArti objeto) throws Exception {
        String sql = "Execute agregarSoliXarti ?,?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
        preparedStmt.setInt(1, objeto.getSboTbSoliArti().getSolArtiIdPk());
        preparedStmt.setInt(2, objeto.getSboSicop().getSicopId());
        preparedStmt.setInt(3, objeto.getSolArtiCant());
        preparedStmt.setString(4, objeto.getSolArtiDeta());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public List<SboTbSolixArti> filtraSolixArti(String filtro) {
        List<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql = "select sxa.Soli_Arti_Id_X_Soli_Arti_PK,sxa.Soli_Arti_Id_X_Sico_PK,sxa.Soli_Arti_X_Cant,sxa.Soli_Arti_Deta,sxa.Soli_Arti_Fech_Sali,\n"
                    + "soli.Soli_Arti_Id_Depa_FK\n"
                    + "from SIBO_TB_Soli_X_Arti sxa, SIBO_TB_Soli_Arti soli\n"
                    + "where sxa.Soli_Arti_Id_X_Soli_Arti_PK=soli.Soli_Arti_Id_PK\n"
                    + "and Soli_Arti_Id_X_Soli_Arti_PK='%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(solixArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private SboTbSolixArti solixArti(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setSboTbSoliArti(soliArti(rs));
            solxArt.setSboSicop(articulo(rs));
            solxArt.setSolArtiCant(rs.getInt("Soli_Arti_X_Cant"));
            solxArt.setSolArtiDeta(rs.getString("Soli_Arti_Deta"));
            solxArt.setSolArtiSali(rs.getDate("Soli_Arti_Fech_Sali"));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbSoliArti soliArti(ResultSet rs) {
        try {
            SboTbSoliArti solArt = new SboTbSoliArti();
            solArt.setSolArtiIdPk(rs.getInt("Soli_Arti_Id_X_Soli_Arti_PK"));
            solArt.setAbaaTbDepartamento(departamento(rs));
            return solArt;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboSicop articulo(ResultSet rs) {
        try {
            SboSicop sicop = new SboSicop();
            sicop.setSicopId(rs.getInt("Soli_Arti_Id_X_Sico_PK"));
            return sicop;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Soli_Arti_Id_Depa_FK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

}