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


/**
 *
 * @author Marco
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
    
    private SboTbArticulo Articulo(ResultSet rs){
        try {
            SboTbArticulo art = new SboTbArticulo();
            art.getSboTbCatArticulo().getCatDesc();
            art.getArtDesc();
            art.getArtMode();
            art.getArtMarc();
            art.getArtNumeSeri();
            art.getAbaaTbDepartamento().getDeptoNomb();
            art.getArtFingr();
            art.getArtFvenc();
            art.setArtCant(rs.getInt("Art_Cant"));
            return art;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<SboTbOrdenCompra> listaOCxArt(String filtro) {
        List<SboTbOrdenCompra> resultado = new ArrayList<SboTbOrdenCompra>();
        try {
            String sql = "select Sbo_TB_CatArticulo.Cat_Desc 'Nombre',Sbo_TB_Articulo.Art_Desc 'Descripcion', Sbo_TB_Articulo.Art_Mode 'Modelo', Sbo_TB_Articulo.Art_Nume_Seri 'N. Serie',\n" +
                         "Sbo_TB_Articulo.Art_Marc 'Marca', Sbo_TB_OrdenCompra.OC_Id_PK 'Orden de Compra', ABAA_TB_Departamento.Depto_Nomb 'Unidad Usuaria'\n" +
                         "\n" +
                         "from Sbo_TB_CatArticulo ,Sbo_TB_Articulo, Sbo_TB_OrdenCompra, ABAA_TB_Departamento\n" +
                         "\n" +
                         "where Sbo_TB_CatArticulo.Cat_Id_PK = Sbo_TB_Articulo.Art_Codi_Cat_Arti_FK and Sbo_TB_Articulo.Art_Orde_Comp_FK = Sbo_TB_OrdenCompra.OC_Id_PK \n" +
                         "and Sbo_TB_Articulo.Art_Depa_FK = ABAA_TB_Departamento.Depto_Id_PK like '%%%s%%';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(OrdenCompra(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbOrdenCompra> listaOrdenesCompra(String filtro) {
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
}
