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
import logic.AbaaTbProveedor;
import logic.SboTbSubFamilia;

/**
 *
 * @author Osvaldo Vargas
 */
public class ProveedoresDAO {
    RelDatabase db;
    
    public ProveedoresDAO() {
        db = new RelDatabase();
    }
    
    private AbaaTbProveedor proveedor(ResultSet rs) {
        try {
            AbaaTbProveedor ob = new AbaaTbProveedor();
            ob.setProveIdProvePk(Integer.parseInt(rs.getString("Prove_Id_Prove_PK")));
            ob.setProveCodigo(rs.getString("Prove_Codi"));
            ob.setProveCedula(rs.getString("Prove_Cedu"));
            ob.setProveTelefono(Integer.parseInt(rs.getString("Prove_Tele")));
            ob.setProveCorreo(rs.getString("Prove_Corre"));
            ob.setProveFax(rs.getString("Prove_Fax"));
            ob.setProveNomb(rs.getString("Prove_Nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    public List<AbaaTbProveedor> listaProveedor() {
        List<AbaaTbProveedor> resultado = new ArrayList<AbaaTbProveedor>();
        try {
            String sql = "select * from ABAA_TB_Prove";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(proveedor(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
//        public List<AbaaTbProveedor> listaProveedorFiltro(String filtro) {
//        List<AbaaTbProveedor> resultado = new ArrayList<AbaaTbProveedor>();
//        try {
//            String sql = "select * from ABAA_TB_Prove p where p.Prove_Nomb like '%%%s%%'";
//            sql = String.format(sql, filtro);
//            ResultSet rs = db.executeQuery(sql);
//            while (rs.next()) {
//                resultado.add(proveedor(rs));
//            }
//        } catch (SQLException ex) {
//        }
//        return resultado;
//    }
        
        
        
            public List<AbaaTbProveedor> listaProveedorFiltro(String filtro) {
        List<AbaaTbProveedor> resultado = new ArrayList<AbaaTbProveedor>();
        try {
            String sql = "select * from ABAA_TB_Prove p where p.Prove_Nomb like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(proveedor(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
            
           public void actualizarProveedor(AbaaTbProveedor objeto) throws Exception {
        String query = "update ABAA_TB_Prove set Prove_Codi = ?, Prove_Cedu = ?, Prove_Tele = ?, Prove_Corre = ?, "
                + "Prove_Fax = ?, Prove_Nomb = ? where Prove_Id_Prove_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getProveCodigo());
        preparedStmt.setString(2, objeto.getProveCedula());
        preparedStmt.setInt(3, objeto.getProveTelefono());
        preparedStmt.setString(4, objeto.getProveCorreo());
        preparedStmt.setString(5, objeto.getProveFax());
        preparedStmt.setString(6, objeto.getProveNomb());
        preparedStmt.setInt(7, objeto.getProveIdProvePk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }     
           
    
    public AbaaTbProveedor getProveedor(int id) throws Exception {
        String sql = "select * from ABAA_TB_Prove where Prove_Id_Prove_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return proveedor(rs);
        } else {
            throw new Exception("Proveedor no Existe");
        }
    }
    
    
    
        public void crearProveedor(AbaaTbProveedor objeto) throws Exception{
        String query = "insert into ABAA_TB_Prove(Prove_Codi,Prove_Cedu,Prove_Tele,Prove_Corre,Prove_Fax,Prove_Nomb)values(?,?,?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
       
        preparedStmt.setString(1, objeto.getProveCodigo());
        preparedStmt.setString(2, objeto.getProveCedula());
        preparedStmt.setInt(3, objeto.getProveTelefono());
        preparedStmt.setString(4, objeto.getProveCorreo());
        preparedStmt.setString(5, objeto.getProveFax());
        preparedStmt.setString(6, objeto.getProveNomb());
        preparedStmt.executeUpdate();
        db.getConnection().close();
       }
}
