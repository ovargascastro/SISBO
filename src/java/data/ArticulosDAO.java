/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import logic.SboSicop;
import logic.SboTbArticulo;
import logic.SboTbBodega;
import logic.SboTbCatArticulo;
import logic.SboTbFamilia;
import logic.SboTbOrdenCompra;
import logic.SboTbSubFamilia;
import logic.SboTbExistencia;

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
            ob.setFamiIdPk(rs.getString("Fami_Id_PK"));
            ob.setFamiEstado(rs.getString("Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbCatArticulo catArticulo(ResultSet rs) {
        try {
            SboTbCatArticulo ob = new SboTbCatArticulo();
            ob.setCatIdPk(rs.getInt("Cata_Id_PK"));
            ob.setCatDesc(rs.getString("Cata_Desc"));
            ob.setArtCat_Estado(rs.getString("Cata_Esta"));
            ob.setSboTbSubFamilia(Subfamilia(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbCatArticulo catArticuloSimple(ResultSet rs) {
        try {
            SboTbCatArticulo ob = new SboTbCatArticulo();
            ob.setCatIdPk(rs.getInt("Cata_Id_PK"));
            ob.setCatDesc(rs.getString("Cata_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbSubFamilia Subfamilia(ResultSet rs) {
        try {
            SboTbSubFamilia ob = new SboTbSubFamilia();
            ob.setSubFamiIdPk(rs.getString("Sub_Fami_Id_PK"));
            ob.setSboTbFamilia(familia(rs));
            ob.setSubFamiDesc(rs.getString("Sub_Fami_Desc"));
            ob.setSubFamiEstado(rs.getString("Sub_Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private AbaaProyectos proyecto(ResultSet rs) {
        try {
            AbaaProyectos ob = new AbaaProyectos();
            ob.setProyIdPk(Integer.parseInt(rs.getString("Proy_id_PK")));
            ob.setProyDesc(rs.getString("Proy_desc"));
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

    private SboTbOrdenCompra OrdenCompra(ResultSet rs) {
        try {
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            oc.setOcIdPk(rs.getInt("OC_Id_PK"));
            oc.setOcFecha(rs.getDate("OC_Fecha"));
            oc.setOcPrecTota(rs.getDouble("OC_Prec_Tota"));
            oc.setOcEsta(rs.getString("OC_Esta"));
            oc.setAbaaTbProveedor(Proveedor(rs));
            oc.setOcPlazoEntrega(rs.getString("OC_Plaz_Entr"));
            oc.setOcEntregarA(rs.getString("OC_Entr_A"));
            return oc;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbProveedor Proveedor(ResultSet rs) {
        try {
            AbaaTbProveedor pro = new AbaaTbProveedor();
            pro.setProveIdProvePk(rs.getInt("Prove_Id_Prove_PK"));
            pro.setProveCodigo(rs.getString("Prove_Codi"));
            pro.setProveCedula(rs.getString("Prove_Cedu"));
            pro.setProveTelefono(rs.getInt("Prove_Tele"));
            pro.setProveCorreo(rs.getString("Prove_Corre"));
            pro.setProveFax(rs.getString("Prove_Fax"));
            pro.setProveNomb(rs.getString("Prove_Nomb"));
            return pro;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbArticulo Articulo2(ResultSet rs) {
        try {
            SboTbArticulo arti = new SboTbArticulo();
            AbaaTbDepartamento dpto = new AbaaTbDepartamento();
            SboTbCatArticulo cat = new SboTbCatArticulo();
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            arti.setArtIdPk(rs.getInt("Arti_Id_PK"));
            arti.setArtDesc(rs.getString("Arti_Desc"));
            arti.setArtMode(rs.getString("Arti_Mode"));
            arti.setArtMarc(rs.getString("Arti_Marc"));
            arti.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            cat.setCatIdPk(rs.getInt("Cata_Id_PK"));
            cat.setCatDesc(rs.getString("Cata_Desc"));
            dpto.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            dpto.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            arti.setSboTbCatArticulo(cat);
            arti.setAbaaTbDepartamento(dpto);
            oc.setOcIdPk(rs.getInt("OC_Id_PK"));
            arti.setSboTbOrdenCompra(oc);

            return arti;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbExistencia existencia(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setSboTbBodega(Bodega(rs));
            ob.setArticulo(Articulo2(rs));
            ob.setSboTbEsta(rs.getInt("Exis_Esta"));
            //ob.setExisCant(rs.getDouble("Exis_Cant"));
            //ob.setAbaaTbDepartamento(departamento(rs));
            // ob.setSboTbSicop(sicop(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbBodega Bodega(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            //bodega.setBodeIdPk(rs.getInt("Bode_Id_PK"));
            // bodega.setBodeUbic(rs.getString("Bode_Ubic"));
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboSicop sicop(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopId(rs.getInt("Sico_Id_PK"));
            //  ob.setSicopCodiClas(rs.getString("Sico_Codi_Clas"));
            //  ob.setSicopCodiInden(rs.getString("Sico_Codi_Iden"));
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    public void agregarArticulo(SboTbArticulo objeto) throws Exception {
        String query = "insert into SIBO_TB_Articulo(Arti_Prec,Arti_Cant,Arti_Cant_Rest,Arti_Desc,"
                + "Arti_Mode,Arti_Nume_Seri,Arti_Marc,Arti_Codi_Pres,"
                + "Arti_Codi_Cata_Arti_FK,Arti_Cata_Depa_FK,Arti_Unid_Medi,Arti_Orde_Comp_FK) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
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
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void agregarArticuloConProyecto(SboTbArticulo objeto) throws Exception {
        String query = "insert into SIBO_TB_Articulo(Arti_Prec,Arti_Cant,Arti_Cant_Rest,"
                + "Arti_Desc,Arti_Mode,Arti_Nume_Seri,Arti_Marc,Arti_Codi_Pres,"
                + "Arti_Codi_Cata_Arti_FK,Arti_Proy_FK,Arti_Cata_Depa_FK,"
                + "Arti_Unid_Medi,Arti_Orde_Comp_FK)"
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
        preparedStmt.setInt(13, objeto.getSboSicop().getSicopId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public int getLastInsertArticulo() throws Exception {
        String sql = " select IDENT_CURRENT( 'SIBO_TB_Articulo' ) as seq ";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return lastInsertOrdenCompra(rs);
        } else {
            throw new Exception("error");
        }
    }

    public void insertarExistencias(SboTbArticulo articulo, SboTbExistencia existencia) throws SQLException {

        int cantidad = articulo.getArtCant();
        for (int i = 0; i < cantidad; i++) {
            SboTbExistencia aux = new SboTbExistencia();
            aux.setArticulo(articulo);
            aux.setSboTbBodega(existencia.getSboTbBodega());
            aux.setSboTbEsta(1);
            aux.setExistFingr(articulo.getArtFingr());
            insertarExist(aux);
        }

    }

    public void insertarExist(SboTbExistencia exist) throws SQLException {
        String query = "insert into SIBO_TB_Exis(Exis_Id_Bode_PK, Exis_Arti_FK, Exis_Esta,Exis_Fech_Ingr)"
                + "values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, exist.getSboTbBodega().getBodeIdPk());
        preparedStmt.setInt(2, exist.getArticulo().getArtIdPk());
        preparedStmt.setInt(3, 1);

        java.util.Date utilStartDate = exist.getExistFingr();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(4, sqlStartDate);

        preparedStmt.executeUpdate();
        db.getConnection().close();

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
            String sql = "select * from SIBO_TB_Articulo a inner join ABAA_TB_Catalogo_Departamento d on a.Arti_Cata_Depa_FK=d.Cata_Depa_id_PK"
                    + " inner join SIBO_TB_Cata_Arti ca on a.Arti_Codi_Cata_Arti_FK=ca.Cata_Id_PK where a.Arti_Orde_Comp_FK = '%s'";
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
            String sql = "select * from SIBO_TB_Articulo a inner join SIBO_TB_Cata_Arti c on a.Arti_Codi_Cata_Arti_FK=c.Cata_Id_PK"
                    + " inner join SIBO_TB_Sub_Fami sf on c.Cata_SubF_FK=sf.Sub_Fami_Id_PK"
                    + " inner join SIBO_TB_Orde_Comp o on a.Arti_Orde_Comp_FK=o.OC_Id_PK"
                    + " where a.Arti_Orde_Comp_FK = '%s'";
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
            ar.setArtIdPk(rs.getInt("Arti_Id_PK"));
            ar.setArtPrecio(rs.getDouble("Arti_Prec"));
            ar.setArtCant(rs.getInt("Arti_Cant"));
            ar.setArtCantRest(rs.getInt("Arti_Cant_Rest"));
            ar.setArtFingr(rs.getDate("Arti_Fech_Ingr"));
            ar.setArtFvenc(rs.getDate("Arti_Fech_Venc"));
            ar.setArtDesc(rs.getString("Arti_Desc"));
            ar.setArtMode(rs.getString("Arti_Mode"));
            ar.setArtCodiPresup(rs.getString("Arti_Codi_Pres"));
            ar.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            ar.setArtMarc(rs.getString("Arti_Marc"));
            ar.setArtNumeFact(rs.getString("Arti_Nume_Fact"));
            ar.setArtEsAc(rs.getBoolean("Arti_EsAc"));
            ar.setArtCodiCont(rs.getString("Arti_Codi_Cont"));
            ar.setSboTbCatArticulo(catArticulo(rs));
            ar.setAbaaProyectos(proyecto(rs));
            ar.setAbaaTbDepartamento(departamento(rs));
            ar.setArtUnidadMedida(rs.getString("Arti_Unid_Medi"));
            ar.setSboTbOrdenCompra(OrdenCompra(rs));
            ar.setSboSicop(sicop(rs));
            return ar;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbArticulo articuloComp(ResultSet rs) {
        try {
            SboTbArticulo ar = new SboTbArticulo();
            ar.setArtIdPk(rs.getInt("Arti_Id_PK"));
            ar.setArtPrecio(rs.getDouble("Arti_Prec"));
            ar.setArtCant(rs.getInt("Arti_Cant"));
            ar.setArtCantRest(rs.getInt("Arti_Cant_Rest"));
            ar.setArtFingr(rs.getDate("Arti_Fech_Ingr"));
            ar.setArtFvenc(rs.getDate("Arti_Fech_Venc"));
            ar.setArtDesc(rs.getString("Arti_Desc"));
            ar.setArtMode(rs.getString("Arti_Mode"));
            ar.setArtCodiPresup(rs.getString("Arti_Codi_Pres"));
            ar.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            ar.setArtMarc(rs.getString("Arti_Marc"));
            ar.setArtNumeFact(rs.getString("Arti_Nume_Fact"));
            ar.setArtEsAc(rs.getBoolean("Arti_EsAc"));
            ar.setArtCodiCont(rs.getString("Arti_Codi_Cont"));
            ar.setSboTbCatArticulo(catArticulo(rs));
            ar.setAbaaProyectos(proyecto(rs));
            ar.setAbaaTbDepartamento(departamento(rs));
            ar.setArtUnidadMedida(rs.getString("Arti_Unid_Medi"));
            ar.setSboTbOrdenCompra(OrdenCompra(rs));
            ar.setSboSicop(sicop(rs));
            ar.setSboTbCatArticulo(catArticuloSimple(rs));
            return ar;
        } catch (SQLException ex) {
            return null;
        }
    }

    public void actualizarCodigCont(SboTbArticulo objeto) throws Exception {
        String query = "update SIBO_TB_Articulo set Arti_Codi_Cont = ? where Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getArtCodiCont());
        preparedStmt.setInt(2, objeto.getArtIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void actualizarLote(SboTbArticulo objeto) throws Exception {
        String query = "update SIBO_TB_Articulo set Arti_Fech_Ingr = ?, Arti_Desc = ?, "
                + "Arti_Mode = ?, Arti_Marc = ?, Arti_Fech_Venc = ? where Arti_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);

        java.util.Date utilStartDate = objeto.getArtFingr();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(1, sqlStartDate);

        preparedStmt.setString(2, objeto.getArtDesc());
        preparedStmt.setString(3, objeto.getArtMode());
        preparedStmt.setString(4, objeto.getArtMarc());

        if(objeto.getArtFvenc()==null){
         preparedStmt.setDate(5, null);
        }else{
        java.util.Date utilStartDate2 = objeto.getArtFvenc();
        java.sql.Date sqlStartDate2 = new java.sql.Date(utilStartDate2.getTime());
        preparedStmt.setDate(5, sqlStartDate2);
        }
        


        preparedStmt.setInt(6, objeto.getArtIdPk());
        
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public SboTbArticulo getArticulo(int id) throws Exception {
        String sql = "select * from SIBO_TB_Articulo a inner join SIBO_TB_Orde_Comp o on a.Arti_Orde_Comp_FK=o.OC_Id_PK where a.Arti_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return articulo(rs);
        } else {
            throw new Exception("Solicitud no Existe");
        }
    }

    public SboTbArticulo getArticuloCompleto(int id) throws Exception {
        String sql = "select * from SIBO_TB_Articulo a inner join SIBO_TB_Orde_Comp o on a.Arti_Orde_Comp_FK=o.OC_Id_PK "
                + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                + " inner join SIBO_TB_Cata_Arti c on a.Arti_Codi_Cata_Arti_FK =c.Cata_Id_PK where a.Arti_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return articuloComp(rs);
        } else {
            return getArticuloCompleto2(id);
        }
    }

    public SboTbArticulo getArticuloCompleto2(int id) throws Exception {
        String sql = "select * from SIBO_TB_Articulo a "
                + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                + " inner join SIBO_TB_Cata_Arti c on a.Arti_Codi_Cata_Arti_FK =c.Cata_Id_PK where a.Arti_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return articuloComp(rs);
        } else {
            throw new Exception("Solicitud no Existe");
        }
    }

    public SboTbArticulo getArticuloSimple(int id) throws Exception {
        String sql = "select * from SIBO_TB_Articulo a where a.Arti_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return articulo(rs);
        } else {
            throw new Exception("Solicitud no Existe");
        }
    }

    public SboTbArticulo getArticulo3(int id) throws Exception {
        String sql = "select * from SIBO_TB_Articulo where Arti_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return articulo(rs);
        } else {
            throw new Exception("Solicitud no Existe");
        }
    }

    public SboTbArticulo getArticulo2(int id) throws Exception {
        String sql = "select * "
                + "from SIBO_TB_Articulo art, ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Cata_Arti cat "
                + "where art.Arti_Cata_Depa_FK = dpto.Cata_Depa_id_PK and cat.Cata_Id_PK=art.Arti_Codi_Cata_Arti_FK "
                + "and art.Arti_Id_PK=" + id + ";";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return articulo(rs);
        } else {
            throw new Exception("Solicitud no Existe");
        }
    }

    public void agregarArticuloSinOrden(SboTbArticulo objeto) throws Exception {
        String query = "insert into SIBO_TB_Articulo(Arti_Prec,Arti_Cant,Arti_Cant_Rest,Arti_Desc,"
                + "Arti_Mode,Arti_Nume_Seri,Arti_Marc,"
                + "Arti_Codi_Cata_Arti_FK,Arti_Cata_Depa_FK,Arti_Unid_Medi, "
                + "Arti_Cod_Sico_FK, Arti_Fech_Ingr, Arti_Fech_Venc) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setDouble(1, objeto.getArtPrecio());
        preparedStmt.setInt(2, objeto.getArtCant());
        preparedStmt.setInt(3, objeto.getArtCant());
        preparedStmt.setString(4, objeto.getArtDesc());
        preparedStmt.setString(5, objeto.getArtMode());
        preparedStmt.setString(6, objeto.getArtNumeSeri());
        preparedStmt.setString(7, objeto.getArtMarc());
        preparedStmt.setInt(8, objeto.getSboTbCatArticulo().getCatIdPk());
        preparedStmt.setString(9, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setString(10, objeto.getArtUnidadMedida());
        preparedStmt.setInt(11, objeto.getSboSicop().getSicopId());

        java.util.Date utilStartDate = objeto.getArtFingr();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(12, sqlStartDate);

        if (objeto.getArtFvenc() != null) {
            utilStartDate = objeto.getArtFvenc();
            sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            preparedStmt.setDate(13, sqlStartDate);
        } else {
            preparedStmt.setDate(13, null);
        }

        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
