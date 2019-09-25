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
import logic.SboTbOrdenCompra;




/**
 *
 * @author Osvaldo Vargas
 */
public class OrdenCompraDAO {
    RelDatabase db;
    

    public OrdenCompraDAO() {
        db = new RelDatabase();
    }
    
    private SboTbOrdenCompra OrdenCompra(ResultSet rs) {
        try {
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            oc.setOcIdPk(rs.getInt("OC_Id_PK"));
            oc.setOcFecha(rs.getDate("OC_Fecha"));
            oc.setOcPrecTota(rs.getDouble("OC_Prec_Tota"));
            oc.setOcEsta(rs.getString("OC_Esta"));
            oc.setAbaaTbProveedor(Proveedor(rs));
            oc.setOcPlazoEntrega(rs.getDate("OC_PlazoEntrega"));
            oc.setOcEntregarA(rs.getString("OC_EntregarA"));
            return oc;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbProveedor Proveedor(ResultSet rs) {
        try {
            AbaaTbProveedor pro = new AbaaTbProveedor();
            pro.setProveIdProvePk(rs.getInt("Prove_Id_Prove_PK"));
            pro.setProveCodigo(rs.getString("Prove_Codigo"));
            pro.setProveCedula(rs.getString("Prove_Cedula"));
            pro.setProveTelefono(rs.getInt("Prove_Telefono"));
            pro.setProveCorreo(rs.getString("Prove_Correo"));
            pro.setProveFax(rs.getString("Prove_Fax"));
            pro.setProveNomb(rs.getString("Prove_Nomb"));
            return pro;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public int getLastInsertOrdenesCompra() throws Exception {
        String sql = " select IDENT_CURRENT( 'Sbo_TB_OrdenCompra' ) as seq ";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return lastInsertOrdenCompra(rs);
        } else {
            throw new Exception("error");
        }
    }
    
    private int lastInsertOrdenCompra(ResultSet rs) {
        try {
            int x;
	    x = rs.getInt("seq");
            return x;
        } catch (SQLException ex) {
            return 0;
        }
    }

    
    public void agregarOrdenCompra(SboTbOrdenCompra objeto) throws Exception{
    String query = "insert into Sbo_TB_OrdenCompra(OC_Fecha,OC_Prec_Tota,"
            + "OC_Esta,OC_Prove_FK,OC_PlazoEntrega,OC_EntregarA)"
            + "values(?,?,?,?,?,?)";
    
    PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
    java.util.Date utilStartDate = objeto.getOcFecha();
    java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
     preparedStmt.setDate(1, sqlStartDate);
     
    preparedStmt.setDouble(2, objeto.getOcPrecTota());
    preparedStmt.setString(3, objeto.getOcEsta());
    preparedStmt.setInt(4, objeto.getAbaaTbProveedor().getProveIdProvePk());
    
    java.util.Date utilStartDate2 = objeto.getOcPlazoEntrega();
    java.sql.Date sqlStartDate2 = new java.sql.Date(utilStartDate2.getTime());
    preparedStmt.setDate(5, sqlStartDate2);
    
    preparedStmt.setString(6, objeto.getOcEntregarA());
    preparedStmt.executeUpdate();
    db.getConnection().close();
   }

    public List<SboTbOrdenCompra> listaOrdenes(String filtro) {
        List<SboTbOrdenCompra> resultado = new ArrayList<SboTbOrdenCompra>();
        try {
            String sql = "select oc.OC_Id_PK, oc.OC_Fecha, OC_Prec_Tota, oc.OC_Esta,OC_Prove_FK,OC_PlazoEntrega,OC_EntregarA\n"
                    + "from Sbo_TB_OrdenCompra oc, Sbo_TB_Articulo art\n"
                    + "where art.Art_Orde_Comp_Fk=oc.OC_Id_PK and oc.OC_Id_PK like '%%%s%%';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(OrdenCompra(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    
       public List<SboTbOrdenCompra> listadoOrdenesC(String filtro) {
        List<SboTbOrdenCompra> resultado = new ArrayList<SboTbOrdenCompra>();
        try {
            String sql = "select * from Sbo_TB_OrdenCompra o where o.OC_Id_Pk like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(OrdenCompra(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
}
