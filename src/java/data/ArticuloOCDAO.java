package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.SboTbArticulo;
import logic.SboTbCatArticulo;
import logic.AbaaTbDepartamento;
import logic.SboTbCatArticulo;
import logic.SboTbExistencia;
import logic.SboTbSubFamilia;
import logic.SboTbFamilia;
import logic.SboTbOrdenCompra;

public class ArticuloOCDAO {

    RelDatabase db;

    public ArticuloOCDAO() {
        db = new RelDatabase();
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

    private SboTbArticulo Articulo3(ResultSet rs) {
        try {
            SboTbArticulo arti = new SboTbArticulo();
            SboTbCatArticulo catArti = new SboTbCatArticulo();
            SboTbSubFamilia subFami = new SboTbSubFamilia();
            SboTbFamilia fami = new SboTbFamilia();

            catArti.setCatIdPk(rs.getInt("Cata_Id_PK"));
            catArti.setCatDesc(rs.getString("Cata_Desc"));

            subFami.setSubFamiDesc(rs.getString("Sub_Fami_Desc"));
            fami.setFamiDesc(rs.getString("Fami_Desc"));
            subFami.setSboTbFamilia(fami);
            catArti.setSboTbSubFamilia(subFami);
            arti.setSboTbCatArticulo(catArti);
            return arti;
        } catch (SQLException ex) {
            return null;
        }
    }

    public SboTbArticulo datosArticulo(String filtro) throws Exception {
        String sql = "select art.Arti_Id_PK, art.Arti_Desc,art.Arti_Mode,art.Arti_Marc,art.Arti_Nume_Seri,\n"
    + "carArt.Cata_Id_PK,carArt.Cata_Desc,dpto.Cata_Depa_id_PK,dpto.Cata_Depa_nomb,oc.OC_Id_PK\n"
    + "from SIBO_TB_Cata_Arti carArt, ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Articulo art, SIBO_TB_Orde_Comp oc\n"
    + "where art.Arti_Codi_Cata_Arti_FK=carArt.Cata_Id_PK and art.Arti_Cata_Depa_FK=dpto.Cata_Depa_id_PK\n"
    + "and art.Arti_Orde_Comp_FK = oc.OC_Id_PK and Arti_Id_PK = " + filtro + ";";
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Articulo2(rs);
        } else {
            throw new Exception();
        }
    }

    public SboTbArticulo DescripcionCatsPorArticulo(String filtro) throws Exception {
        String sql = "select cat.Cata_Id_PK,cat.Cata_Desc,sub.Sub_Fami_Desc,fam.Fami_Desc\n" +
                    "from SIBO_TB_Cata_Arti cat, SIBO_TB_Sub_Fami sub,SIBO_TB_Familia fam\n" +
                    "where cat.Cata_SubF_FK=sub.Sub_Fami_Id_PK and sub.Sub_Fami_CodF_FK=fam.Fami_Id_PK\n" +
                    "and cat.Cata_Id_PK = " + filtro + ";";
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Articulo3(rs);
        } else {
            throw new Exception();
        }
    }
    
    public void actualizaRestante(SboTbArticulo articulo) throws SQLException{
    String query = "update SIBO_TB_Articulo set Arti_Cant_Rest=? where Arti_Id_PK=?";
    PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
    preparedStmt.setInt(1, articulo.getArtCantRest());
    preparedStmt.setInt(2, articulo.getArtIdPk());
    preparedStmt.executeUpdate();
    db.getConnection().close();
    }
    
    
    public void actualizaSicop(SboTbArticulo articulo) throws SQLException{
    String query = "update SIBO_TB_Articulo set Arti_Cod_Sico_FK=? where Arti_Id_PK=?";
    PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
    preparedStmt.setInt(1, articulo.getSboSicop().getSicopId());
    preparedStmt.setInt(2, articulo.getArtIdPk());
    preparedStmt.executeUpdate();
    db.getConnection().close();
    }

    public void disminuirCantPendienteArticulo(SboTbArticulo articulo) throws Exception {
        String query = "update SIBO_TB_Articulo set Arti_Desc=?, Arti_Mode= ?, Arti_Marc=?,\n" +
                       "Arti_Nume_Seri=?, Arti_Fech_Ingr=?, Arti_Fech_Venc=?, Arti_Cant_Rest=Arti_Cant_Rest-?,"
                        +"Arti_Cod_Sico_FK= ? \n" +
                       "where Arti_Id_PK=?;";

        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, articulo.getArtDesc());
        preparedStmt.setString(2, articulo.getArtMode());
        preparedStmt.setString(3, articulo.getArtMarc());
        preparedStmt.setString(4, articulo.getArtNumeSeri());
        java.util.Date utilStartDate = articulo.getArtFingr();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(5, sqlStartDate);
        if (articulo.getArtFvenc() != null) {
            utilStartDate = articulo.getArtFvenc();
            sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            preparedStmt.setDate(6, sqlStartDate);
        }
        else{
            preparedStmt.setDate(6, null);
        }

        preparedStmt.setInt(7, articulo.getArtCantRest());
        preparedStmt.setInt(8, articulo.getSboSicop().getSicopId());
        preparedStmt.setInt(9, articulo.getArtIdPk());
        preparedStmt.executeUpdate();
        verificarEstadoOCs(articulo);
        db.getConnection().close();
    }

//    public void aumentarExistencias(SboTbExistencia existencia) throws Exception {
//        String query = "execute aumentaExistencias ?,?,?,?;";
//        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
//        preparedStmt.setInt(1, existencia.getSboTbBodega().getBodeIdPk());
//        preparedStmt.setString(2, existencia.getAbaaTbDepartamento().getDeptoIdPk());
//        preparedStmt.setInt(3, existencia.getSboTbSicop().getSicopId());
//        preparedStmt.setDouble(4, existencia.getExisCant());
//        preparedStmt.executeUpdate();
//        db.getConnection().close();
//    }

    public void verificarEstadoOCs(SboTbArticulo articulo) throws Exception {
        String query = "execute actualizarEstadoOC ?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, articulo.getSboTbOrdenCompra().getOcIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}