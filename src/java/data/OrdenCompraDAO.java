package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.SboTbOrdenCompra;
import logic.AbaaTbProveedor;
import logic.SboTbArticulo;
import logic.SboTbCatArticulo;
import logic.AbaaTbDepartamento;
import logic.SboTbBodega;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbProveedor;
import logic.SboTbOrdenCompra;

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
            oc.setOcPlazoEntrega(rs.getString("OC_PlazoEntrega"));
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

    private SboTbArticulo ObtenerArticulo(ResultSet rs) {
        try {
            SboTbArticulo art = new SboTbArticulo();
            art.setArtIdPk(rs.getInt("Art_Id_PK"));
            art.setArtDesc(rs.getString("Art_Desc"));
            art.setArtCant(rs.getInt("Art_Cant"));
            art.setArtCantRest(rs.getInt("Art_Cant_Rest"));
            return art;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<SboTbArticulo> listaOCxArt(String filtro) {
        List<SboTbArticulo> resultado = new ArrayList<SboTbArticulo>();
        try {
            String sql = "select Sbo_TB_Articulo.Art_Id_PK,Sbo_TB_Articulo.Art_Desc, Sbo_TB_Articulo.Art_Cant,Sbo_TB_Articulo.Art_Cant_Rest\n"
                    + "from Sbo_TB_Articulo, Sbo_TB_OrdenCompra\n"
                    + "where Sbo_TB_Articulo.Art_Orde_Comp_FK = Sbo_TB_OrdenCompra.OC_Id_PK \n"
                    + "and Sbo_TB_Articulo.Art_Orde_Comp_FK=" + filtro + ";";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(ObtenerArticulo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbOrdenCompra> listaOrdenesCompra(String filtro) {
        List<SboTbOrdenCompra> resultado = new ArrayList<SboTbOrdenCompra>();
        try {
            String sql = "select distinct oc.OC_Id_PK, oc.OC_Fecha, OC_Prec_Tota, oc.OC_Esta,OC_Prove_FK,OC_PlazoEntrega,OC_EntregarA\n"
                    + "from Sbo_TB_OrdenCompra oc, Sbo_TB_Articulo art\n"
                    + "where art.Art_Orde_Comp_Fk=oc.OC_Id_PK \n"
                    + "and (oc.OC_Esta='No Procesada' or oc.OC_Esta='Parcialmente Procesada') \n"
                    + "and oc.OC_Id_PK like '%%%s%%';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(OrdenCompra(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // ------------------ A partir de aquí, está todo lo de Oscar/Orlando ------------------
    
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

    public void agregarOrdenCompra(SboTbOrdenCompra objeto) throws Exception {
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

        
        preparedStmt.setString(5, objeto.getOcPlazoEntrega());

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

    public List<SboTbOrdenCompra> listadoOrdenesCompraConta(String filtro) {
        List<SboTbOrdenCompra> resultado = new ArrayList<SboTbOrdenCompra>();
        try {
            String sql = "select * from Sbo_TB_OrdenCompra o where o.OC_Esta='asignar codigos' and o.OC_Id_Pk like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(OrdenCompra(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public void actualizaEstadoOC(SboTbOrdenCompra objeto) throws SQLException {
        String query = "update Sbo_TB_OrdenCompra set OC_Esta = ? where OC_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getOcEsta());
        preparedStmt.setInt(2, objeto.getOcIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();

    }

}
