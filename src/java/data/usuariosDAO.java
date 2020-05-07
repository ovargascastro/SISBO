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
import logic.AbaaTbPersona;
import logic.AbaaTbUsuario;
import logic.SboTbSoliArti;
import logic.SboTbSolixArti;

/**
 *
 * @author oscar
 */
public class usuariosDAO {
    
    RelDatabase db;
    
    public usuariosDAO() {
        db = new RelDatabase();
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
    
    private AbaaTbPersona persona2(ResultSet rs) {
        try {
            AbaaTbPersona ob = new AbaaTbPersona();
            ob.setPersIdPK(rs.getInt("Pers_id_PK"));
            
            return ob;
        } catch (SQLException ex) {
            return null;
        }
        
    }
    
    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public AbaaTbPersona getUsuario(String id) throws Exception {
        
        String sql = "select *\n"
                + "	from ABAA_TB_Persona p inner join ABAA_TB_Catalogo_Departamento d\n"
                + "	on p.Depa_id_FK=d.Cata_Depa_id_PK where p.Pers_cedu='%s';";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return persona(rs);
        } else {
            throw new Exception("Usuario no Existe");
        }
        
    }
    
    public void updatUsuarioSinContrasenna(AbaaTbPersona p) throws SQLException {
        String query = "update ABAA_TB_Persona set Pers_nomb = ?, Pers_ape1 = ?, Pers_ape2= ?,Pers_es_jefe=?,Depa_id_FK=? where Pers_cedu = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, p.getPersNomb());
        preparedStmt.setString(2, p.getPersApe1());
        preparedStmt.setString(3, p.getPersApe2());
        preparedStmt.setInt(4, p.getJefe());
        preparedStmt.setString(5, p.getDepartamento().getDeptoIdPk());
        preparedStmt.setString(6, p.getPersCedu());
        preparedStmt.executeUpdate();
        db.getConnection().close();
        
    }
    
    public void updateUsuarioConContrasenna(AbaaTbPersona p) throws SQLException {
        String query = "update ABAA_TB_Persona set Pers_nomb = ?, Pers_ape1 = ?, Pers_ape2= ?,Pers_es_jefe=?,Depa_id_FK=? where Pers_cedu = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, p.getPersNomb());
        preparedStmt.setString(2, p.getPersApe1());
        preparedStmt.setString(3, p.getPersApe2());
        preparedStmt.setInt(4, p.getJefe());
        preparedStmt.setString(5, p.getDepartamento().getDeptoIdPk());
        preparedStmt.setString(6, p.getPersCedu());
        preparedStmt.executeUpdate();
        this.actualizaContrasenna(p.getPersCedu(),p.getPasswAux());
        db.getConnection().close();
        
    }
    
    public void actualizaContrasenna(String ced, String clave) throws SQLException { 
        String query = "execute SBO_ActualizaContrasenna ?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, ced);
        preparedStmt.setString(2, clave);
        preparedStmt.executeUpdate();
    }
    
    public List<AbaaTbPersona> personasLista() {
        List<AbaaTbPersona> resultado = new ArrayList<AbaaTbPersona>();
        try {
            String sql = "select *\n"
                    + "	from ABAA_TB_Persona p inner join ABAA_TB_Catalogo_Departamento d\n"
                    + "	on p.Depa_id_FK=d.Cata_Depa_id_PK;";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(persona(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public AbaaTbPersona getPersona(String ced) throws SQLException, Exception {
        String sql = "select Pers_id_PK from ABAA_TB_Persona where Pers_cedu ='%s'";
        sql = String.format(sql, ced);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return persona2(rs);
        } else {
            throw new Exception("Bodega no Existe");
        }
    }
    
    public void InsertarUsuario(AbaaTbUsuario objeto) throws Exception {
        
        String query = "execute SBO_InsertarUsuario ?,?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, "1");
        preparedStmt.setString(2, objeto.getUsuaCont());
        preparedStmt.setString(3, objeto.getPersona().getPersCedu());
        preparedStmt.setInt(4, objeto.getPersona().getPersIdPK());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
    
    public void InsertarPersona(AbaaTbPersona objeto) throws Exception {
        //       @Pers_cedu varchar(50), @Pers_nomb varchar(50),@Pers_ape1 varchar(50), @Pers_ape2 varchar(50), @Pers_esJefe int,@Depa_id_FK int
        String query = "execute SBO_InsertarPersona ?,?,?,?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getPersCedu());
        preparedStmt.setString(2, objeto.getPersNomb());
        preparedStmt.setString(3, objeto.getPersApe1());
        preparedStmt.setString(4, objeto.getPersApe2());
        preparedStmt.setInt(5, objeto.getJefe());
        preparedStmt.setInt(6, Integer.parseInt(objeto.getDepartamento().getDeptoIdPk()));
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
}
