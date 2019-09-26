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
            arti.setArtIdPk(rs.getInt("Art_Id_PK"));
            arti.setArtDesc(rs.getString("Art_Desc"));
            arti.setArtMode(rs.getString("Art_Mode"));
            arti.setArtMarc(rs.getString("Art_Marc"));
            arti.setArtNumeSeri(rs.getString("Art_Nume_Seri"));
            cat.setCatIdPk(rs.getInt("Cat_Id_PK"));
            cat.setCatDesc(rs.getString("Cat_Desc"));
            dpto.setDeptoIdPk(rs.getString("Depto_Id_PK"));
            dpto.setDeptoNomb(rs.getString("Depto_Nomb"));
            arti.setSboTbCatArticulo(cat);
            arti.setAbaaTbDepartamento(dpto);
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

            catArti.setCatIdPk(rs.getInt("Cat_Id_PK"));
            catArti.setCatDesc(rs.getString("Cat_Desc"));

            subFami.setSubFamiDesc(rs.getString("SubFami_Desc"));
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
        String sql = "select art.Art_Id_PK, art.Art_Desc,art.Art_Mode,art.Art_Marc,art.Art_Nume_Seri,\n"
                + "carArt.Cat_Id_PK,carArt.Cat_Desc,dpto.Depto_Id_PK,dpto.Depto_Nomb\n"
                + "from Sbo_TB_CatArticulo carArt, ABAA_TB_Departamento dpto, Sbo_TB_Articulo art\n"
                + "where art.Art_Codi_Cat_Arti_FK=carArt.Cat_Id_PK and art.Art_Depa_FK=dpto.Depto_Id_PK\n"
                + "and art.Art_Id_PK=" + filtro + ";";
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Articulo2(rs);
        } else {
            throw new Exception();
        }
    }

    public SboTbArticulo DescripcionCatsPorArticulo(String filtro) throws Exception {
        String sql = "select cat.Cat_Id_PK,cat.Cat_Desc,sub.SubFami_Desc,fam.Fami_Desc\n"
                + "from Sbo_TB_CatArticulo cat, Sbo_TB_SubFamilia sub,Sbo_TB_Familia fam\n"
                + "where cat.Cat_SubF_FK=sub.SubFami_Id_PK and sub.SubFami_CodF_Fk=fam.Fami_Id_PK \n"
                + "and cat.Cat_Id_PK=" + filtro + ";";
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Articulo3(rs);
        } else {
            throw new Exception();
        }
    }

    public void disminuirCantPendienteArticulo(SboTbArticulo articulo) throws Exception {
        String query = "update Sbo_TB_Articulo set Art_Desc=?, Art_Mode= ?, Art_Marc=?,\n"
                + "Art_Nume_Seri=?, Art_FIngr=?, Art_FVenc=?, Art_Cant_Rest=Art_Cant_Rest-?\n"
                + "where Art_Id_PK=?;";

        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, articulo.getArtDesc());
        preparedStmt.setString(2, articulo.getArtMode());
        preparedStmt.setString(3, articulo.getArtMarc());
        preparedStmt.setString(4, articulo.getArtNumeSeri());
        java.util.Date utilStartDate = articulo.getArtFingr();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(5, sqlStartDate);
        utilStartDate = articulo.getArtFvenc();
        sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(6, sqlStartDate);
        preparedStmt.setInt(7, articulo.getArtCantRest());
        preparedStmt.setInt(8, articulo.getArtIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void aumentarExistencias(SboTbExistencia existencia) throws Exception {
        String query = "execute aumentaExistencias ?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, existencia.getSboTbBodega().getBodeIdPk());
        preparedStmt.setInt(2, existencia.getSboTbArticulo().getArtIdPk());
        preparedStmt.setDouble(3, existencia.getExisCant());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
