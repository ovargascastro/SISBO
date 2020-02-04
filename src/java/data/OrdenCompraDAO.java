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
            oc.setOcPlazoEntrega(rs.getString("OC_Plaz_Entr"));
            //oc.setOcEsta(rs.getString("OC_Esta_FK"));
            oc.setOcEntregarA(rs.getString("OC_Entr_A"));
            return oc;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbProveedor Proveedor(ResultSet rs) {
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

    private SboTbArticulo ObtenerArticulo(ResultSet rs) {
        try {
            SboTbArticulo art = new SboTbArticulo();
            art.setArtIdPk(rs.getInt("Arti_Id_PK"));
            art.setArtDesc(rs.getString("Arti_Desc"));
            art.setArtCant(rs.getInt("Arti_Cant"));
            art.setArtCantRest(rs.getInt("Arti_Cant_Rest"));
            return art;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<SboTbArticulo> listaOCxArt(String filtro) {
        List<SboTbArticulo> resultado = new ArrayList<SboTbArticulo>();
        try {
            String sql = "select SIBO_TB_Articulo.Arti_Id_PK,SIBO_TB_Articulo.Arti_Desc,SIBO_TB_Articulo.Arti_Cant,SIBO_TB_Articulo.Arti_Cant_Rest\n"
                    + "FROM SIBO_TB_Articulo,SIBO_TB_Orde_Comp\n"
                    + "where SIBO_TB_Articulo.Arti_Orde_Comp_FK=SIBO_TB_Orde_Comp.OC_Id_PK\n"
                    + "AND SIBO_TB_Articulo.Arti_Orde_Comp_FK=" + filtro + ";";
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
            String sql = "select distinct oc.OC_Id_PK, oc.OC_Fecha, oc.OC_Prec_Tota, oc.OC_Esta,oc.OC_Prove_FK,oc.OC_Plaz_Entr,oc.OC_Entr_A\n"
                    + "from SIBO_TB_Orde_Comp oc, SIBO_TB_Articulo art\n"
                    + "where art.Arti_Orde_Comp_Fk=oc.OC_Id_PK \n"
                    + "and (oc.OC_Esta='No Procesada' or oc.OC_Esta='Parcialmente Procesada')\n"
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
        String sql = "select IDENT_CURRENT( 'SIBO_TB_Orde_Comp' ) as seq;";
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
        String query = "insert into SIBO_TB_Orde_Comp(OC_Fecha,OC_Prec_Tota,"
                + "OC_Esta,OC_Prove_FK,OC_Plaz_Entr,OC_Entr_A)"
                + "values(?,?,?,?,?,?);";

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
            String sql = "select oc.OC_Id_PK, oc.OC_Fecha, oc.OC_Prec_Tota, oc.OC_Esta,oc.OC_Prove_FK,oc.OC_Plaz_Entr,oc.OC_Entr_A\n"
                    + "from SIBO_TB_Orde_Comp oc, SIBO_TB_Articulo art\n"
                    + "where art.Arti_Orde_Comp_FK=oc.OC_Id_PK and oc.OC_Id_PK like '%%%s%%';";
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
            String sql = "select * from SIBO_TB_Orde_Comp o where o.OC_Id_Pk like '%%%s%%'";
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
            String sql = "select * from SIBO_TB_Orde_Comp o where o.OC_Esta='asignar codigos' and o.OC_Id_Pk like '%%%s%%'";
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
        String query = "update SIBO_TB_Orde_Comp set OC_Esta = ? where OC_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getOcEsta());
        preparedStmt.setInt(2, objeto.getOcIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
