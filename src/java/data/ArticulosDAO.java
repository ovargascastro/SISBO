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
import logic.AbaaProyectos;
import logic.AbaaTbDepartamento;
import logic.AbaaTbProveedor;
import logic.SboTbArticulo;
import logic.SboTbCatArticulo;
import logic.SboTbFamilia;
import logic.SboTbOrdenCompra;
import logic.SboTbSubFamilia;

/**
 *
 * @author Osvaldo Vargas
 */
public class ArticulosDAO {

    RelDatabase db;

    public ArticulosDAO() {
        db = new RelDatabase();
    }

    private SboTbFamilia familia(ResultSet rs) {
        try {
            SboTbFamilia ob = new SboTbFamilia();
            ob.setFamiDesc(rs.getString("Fami_Desc"));
            ob.setFamiIdPk(rs.getString("Fami_Id_Pk"));
            ob.setFamiEstado(rs.getString("Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbCatArticulo catArticulo(ResultSet rs) {
        try {
            SboTbCatArticulo ob = new SboTbCatArticulo();
            ob.setCatIdPk(rs.getInt("Cat_Id_Pk"));
            ob.setCatDesc(rs.getString("Cat_Desc"));
            ob.setArtCat_Estado(rs.getString("Cat_Estado"));
            ob.setSboTbSubFamilia(Subfamilia(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbSubFamilia Subfamilia(ResultSet rs) {
        try {
            SboTbSubFamilia ob = new SboTbSubFamilia();
            ob.setSubFamiIdPk(rs.getString("SubFami_Id_Pk"));
            ob.setSboTbFamilia(familia(rs));
            ob.setSubFamiDesc(rs.getString("SubFami_Desc"));
            ob.setSubFamiEstado(rs.getString("SubFami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private AbaaProyectos proyecto(ResultSet rs) {
        try {
            AbaaProyectos ob = new AbaaProyectos();
            ob.setProyIdPk(Integer.parseInt(rs.getString("Proy_Id_PK")));
            ob.setProyDesc(rs.getString("Proy_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Depto_Id_Pk"));
            ob.setDeptoNomb(rs.getString("Depto_Nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
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

    public void agregarArticulo(SboTbArticulo objeto) throws Exception {
        String query = "insert into Sbo_TB_Articulo(Art_Precio,Art_Cant,Art_Cant_Rest,Art_Desc,"
                + "Art_Mode,Art_Nume_Seri,Art_Marc,Art_Codi_Presup,"
                + "Art_Codi_Cat_Arti_FK,Art_Depa_FK,Art_Unid_Medi,Art_Orde_Comp_FK,Art_EsAc)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setDouble(1, objeto.getArtPrecio());
        preparedStmt.setInt(2, objeto.getArtCant());
        preparedStmt.setInt(3, objeto.getArtCant());
        preparedStmt.setString(4, objeto.getArtDesc());
        preparedStmt.setString(5, objeto.getArtMode());
        preparedStmt.setString(6, objeto.getArtNumeSeri());
        preparedStmt.setString(7, objeto.getArtMarc());
        preparedStmt.setString(8, objeto.getArtCodiPresup());
        preparedStmt.setInt(9, objeto.getSboTbCatArticulo().getCatIdPk());
        preparedStmt.setString(10, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setString(11, objeto.getArtUnidadMedida());
        preparedStmt.setInt(12, objeto.getSboTbOrdenCompra().getOcIdPk());
        preparedStmt.setBoolean(13, objeto.getArtEsAc());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void agregarArticuloConProyecto(SboTbArticulo objeto) throws Exception {
        String query = "insert into Sbo_TB_Articulo(Art_Precio,Art_Cant,Art_Cant_Rest,Art_Desc,"
                + "Art_Mode,Art_Nume_Seri,Art_Marc,Art_Codi_Presup,"
                + "Art_Codi_Cat_Arti_FK,Art_Proy_FK,Art_Depa_FK,Art_Unid_Medi,"
                + "Art_Orde_Comp_FK)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setDouble(1, objeto.getArtPrecio());
        preparedStmt.setInt(2, objeto.getArtCant());
        preparedStmt.setInt(3, objeto.getArtCant());
        preparedStmt.setString(4, objeto.getArtDesc());
        preparedStmt.setString(5, objeto.getArtMode());
        preparedStmt.setString(6, objeto.getArtNumeSeri());
        preparedStmt.setString(7, objeto.getArtMarc());
        preparedStmt.setString(8, objeto.getArtCodiPresup());
        preparedStmt.setInt(9, objeto.getSboTbCatArticulo().getCatIdPk());
        preparedStmt.setInt(10, objeto.getAbaaProyectos().getProyIdPk());
        preparedStmt.setString(11, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setString(12, objeto.getArtUnidadMedida());
        preparedStmt.setInt(13, objeto.getSboTbOrdenCompra().getOcIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public int getLastInsertArticulo() throws Exception {
        String sql = " select IDENT_CURRENT( 'Sbo_TB_Articulo' ) as seq ";
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

    public List<SboTbArticulo> listadoArticulosPorOrden(int filtro) {
        List<SboTbArticulo> resultado = new ArrayList<SboTbArticulo>();
        try {
            String sql = "select * from Sbo_TB_Articulo a inner join ABAA_TB_Departamento d on a.Art_Depa_FK=d.Depto_Id_PK"
                    + " inner join Sbo_TB_CatArticulo ca on a.Art_Codi_Cat_Arti_Fk=ca.Cat_Id_PK where a.Art_Orde_Comp_FK = '%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(articulo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbArticulo> listadoArticulosPorOrdenConta(int filtro) {
        List<SboTbArticulo> resultado = new ArrayList<SboTbArticulo>();
        try {
            String sql = "select * from Sbo_TB_Articulo a inner join Sbo_TB_CatArticulo c on a.Art_Codi_Cat_Arti_FK=c.Cat_Id_Pk"
                    + " inner join Sbo_TB_SubFamilia sf on c.Cat_SubF_FK=sf.SubFami_Id_Pk"
                    + " inner join Sbo_TB_OrdenCompra o on a.Art_Orde_Comp_FK=o.OC_Id_PK"
                    + " where a.Art_Orde_Comp_FK = '%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(articulo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private SboTbArticulo articulo(ResultSet rs) {
        try {
            SboTbArticulo ar = new SboTbArticulo();
            ar.setArtIdPk(rs.getInt("Art_Id_Pk"));
            ar.setArtPrecio(rs.getDouble("Art_Precio"));
            ar.setArtCant(rs.getInt("Art_Cant"));
            ar.setArtCantRest(rs.getInt("Art_Cant_Rest"));
            ar.setArtFingr(rs.getDate("Art_FIngr"));
            ar.setArtFvenc(rs.getDate("Art_FVenc"));
            ar.setArtDesc(rs.getString("Art_Desc"));
            ar.setArtMode(rs.getString("Art_Mode"));
            ar.setArtCodiPresup(rs.getString("Art_Codi_Presup"));
            ar.setArtNumeSeri(rs.getString("Art_Nume_Seri"));
            ar.setArtMarc(rs.getString("Art_Marc"));
            ar.setArtNumeFact(rs.getString("Art_Nume_Fact"));
            ar.setArtEsAc(rs.getBoolean("Art_EsAc"));
            ar.setArtCodiCont(rs.getString("Art_Codi_Cont"));
            ar.setSboTbCatArticulo(catArticulo(rs));
            ar.setAbaaProyectos(proyecto(rs));
            ar.setAbaaTbDepartamento(departamento(rs));
            ar.setArtUnidadMedida(rs.getString("Art_Unid_Medi"));
            ar.setSboTbOrdenCompra(OrdenCompra(rs));
            return ar;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void actualizarCodigCont(SboTbArticulo objeto) throws Exception {
        String query = "update Sbo_TB_Articulo set Art_Codi_Cont = ? where Art_Id_Pk = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getArtCodiCont());
        preparedStmt.setInt(2, objeto.getArtIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
