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
// se ejecuta el procedimiento que agregra los datos en la tabla solixarti
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

    // se selecciona los datos de la tabla solixarti por medio del id
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

    // se crea un objeto solixArti
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

    //se crea un objeto de tipo solicitud
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
// se crea un un objeto de tipo articulo de sicop
    private SboSicop articulo(ResultSet rs) {
        try {
            SboSicop sicop = new SboSicop();
            sicop.setSicopId(rs.getInt("Soli_Arti_Id_X_Sico_PK"));
            return sicop;
        } catch (SQLException ex) {
            return null;
        }
    }
// se crea un objeto de tipo departamento
    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Soli_Arti_Id_Depa_FK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    // se crea otro objeto de tipo sicop con todos sus atributos
        private SboSicop sicop(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopId(rs.getInt("Sico_Id_PK"));
            ob.setSicopCodiClas(rs.getString("Sico_Codi_Clas"));
            ob.setSicopCodiInden(rs.getString("Sico_Codi_Iden"));
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    // se crea un objeto de tipo solixarti para el reporte
        private SboTbSolixArti objetoReporte(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setSboSicop(articulo(rs));
            solxArt.setSolArtiCant(rs.getInt("Soli_Arti_X_Cant"));
            solxArt.setSboSicop(sicop(rs));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    // se lista todos los articulos que se solicitaron por departamento desde una fecha de inicio hasta una fecha final
    public  ArrayList<SboTbSolixArti>  reporteConsumo(String depa, String inicio, String fin) {
         ArrayList<SboTbSolixArti>  resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql = "select si.Sico_Desc, si.Sico_Codi_Iden,si.Sico_Codi_Clas,si.Sico_Id_PK, Soli_Arti_X_Cant from SIBO_TB_Soli_X_Arti s inner join\n"
                    + "SIBO_TB_Sicop si on s.Soli_Arti_Id_X_Sico_PK=si.Sico_Id_PK inner join\n"
                    + "SIBO_TB_Soli_Arti so on s.Soli_Arti_Id_X_Soli_Arti_PK=so.Soli_Arti_Id_PK\n"
                    + "where so.Soli_Arti_Id_Depa_FK='%s' and s.Soli_Arti_Fech_Sali between '"+inicio+"'and '"+fin+"';";
            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(objetoReporte(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
       // se lista un articulos especifico que se solicito por departamento desde una fecha de inicio hasta una fecha final
        public  ArrayList<SboTbSolixArti>  reporteConsumoFilter(String arti, String depa, String inicio, String fin) {
         ArrayList<SboTbSolixArti>  resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql = "select si.Sico_Desc, si.Sico_Codi_Iden,si.Sico_Codi_Clas,si.Sico_Id_PK, Soli_Arti_X_Cant from SIBO_TB_Soli_X_Arti s inner join\n"
                    + "SIBO_TB_Sicop si on s.Soli_Arti_Id_X_Sico_PK=si.Sico_Id_PK inner join\n"
                    + "SIBO_TB_Soli_Arti so on s.Soli_Arti_Id_X_Soli_Arti_PK=so.Soli_Arti_Id_PK\n"
                    + "where so.Soli_Arti_Id_Depa_FK='%s' and s.Soli_Arti_Id_X_Sico_PK="+arti+" and s.Soli_Arti_Fech_Sali between '"+inicio+"'and '"+fin+"';";
            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(objetoReporte(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }


}
