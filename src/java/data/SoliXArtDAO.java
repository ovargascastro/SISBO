package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.SboSicop;
import logic.SboTbArticulo;
import logic.SboTbBodega;
import logic.SboTbCatArticulo;
import logic.SboTbExistencia;
import logic.SboTbOrdenCompra;
import logic.SboTbSoliArti;
import logic.SboTbSolixArti;

public class SoliXArtDAO {

    RelDatabase db;

    public SoliXArtDAO() {
        db = new RelDatabase();
    }

    public void insertarSolxArt(SboTbSolixArti objeto) throws Exception {
        String sql = "Execute agregarSoliXarti ?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
        preparedStmt.setInt(1, objeto.getSboTbSoliArti().getSolArtiIdPk());
        preparedStmt.setInt(2, objeto.getExistencia().getId());
        preparedStmt.setString(3, objeto.getSolArtiDeta());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    // se selecciona los datos de la tabla solixarti por medio del id
    public List<SboTbSolixArti> filtraSolixArti(String filtro) {
        List<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql = "select sxa.Soli_Arti_Id_X_Soli_Arti_PK,sxa.Soli_Arti_Id_X_Sico_PK,sxa.Soli_Arti_X_Cant,sxa.Soli_Arti_Deta,sxa.Soli_Arti_Fech_Sali,\n"
                    + "soli.Soli_Arti_Id_Depa_FK\n"
                    + "from SIBO_TB_Soli_X_Arti sxa, SIBO_TB_Soli_Arti soli\n"
                    + "where sxa.Soli_Arti_Id_X_Soli_Arti_PK=soli.Soli_Arti_Id_PK\n"
                    + "and Soli_Arti_Id_X_Soli_Arti_PK='%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(solixArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
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
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbBodega Bodega(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbSolixArti solixArti(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setSboTbSoliArti(soliArti(rs));
            solxArt.setExistencia(existencia(rs));
            solxArt.setSolArtiDeta(rs.getString("Soli_Arti_Deta"));
            solxArt.setSolArtiSali(rs.getDate("Soli_Arti_Fech_Sali"));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }

    //se crea un objeto de tipo solicitud
    private SboTbSoliArti soliArti(ResultSet rs) {
        try {
            SboTbSoliArti solArt = new SboTbSoliArti();
            solArt.setSolArtiIdPk(rs.getInt("Soli_Arti_Id_X_Soli_Arti_PK"));
            solArt.setAbaaTbDepartamento(departamento(rs));
            return solArt;
        } catch (SQLException ex) {
            return null;
        }
    }
// se crea un un objeto de tipo articulo de sicop

    private SboSicop articulo(ResultSet rs) {
        try {
            SboSicop sicop = new SboSicop();
            sicop.setSicopId(rs.getInt("Soli_Arti_Id_X_Sico_PK"));
            return sicop;
        } catch (SQLException ex) {
            return null;
        }
    }
// se crea un objeto de tipo departamento

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Soli_Arti_Id_Depa_FK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    // se crea otro objeto de tipo sicop con todos sus atributos
    private SboSicop sicop(ResultSet rs) {

        try {
            SboSicop ob = new SboSicop();
            ob.setSicopId(rs.getInt("Sico_Id_PK"));
            ob.setSicopCodiClas(rs.getString("Sico_Codi_Clas"));
            ob.setSicopCodiInden(rs.getString("Sico_Codi_Iden"));
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    public ArrayList<SboTbSolixArti> reporteConsumo(String depa, String inicio, String fin, String articulo) {
        ArrayList<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {

            String sql = "select  si.Sico_Codi_Clas,Sico_Codi_Iden, Sico_Desc,Sico_Id_PK,count(si.Sico_Id_PK) as 'Cantidad' from SIBO_TB_Soli_X_Arti s\n"
                    + "inner join SIBO_TB_Exis e on s.Soli_Arti_Id_X_Exis_FK=e.Exis_Id_PK\n"
                    + "inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Sicop si on a.Arti_Cod_Sico_FK=si.Sico_Id_PK\n"
                    + "inner join SIBO_TB_Soli_Arti so on s.Soli_Arti_Id_X_Soli_Arti_PK=so.Soli_Arti_Id_PK\n"
                    + "where so.Soli_Arti_Id_Depa_FK='%s'\n"
                    + "and s.Soli_Arti_Fech_Sali between '" + inicio + "'and '" + fin + "'\n"
                    + "and si.Sico_Id_PK='"+articulo+"'\n"
                    + "group by si.Sico_Id_PK, si.Sico_Desc, si.Sico_Codi_Iden, si.Sico_Codi_Clas";

            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(objetoReporte(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private SboTbArticulo Articulo3(ResultSet rs) {
        SboTbArticulo ar = new SboTbArticulo();
        ar.setSboSicop(sicop(rs));
        return ar;
    }

    private SboTbExistencia existencia2(ResultSet rs) {
        SboTbExistencia ob = new SboTbExistencia();
        ob.setArticulo(Articulo3(rs));
        return ob;
    }

    private SboTbSolixArti objetoReporte(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setExistencia(existencia2(rs));
            solxArt.setSolArtiCant(rs.getInt("Cantidad"));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }
//        
//        
    public  ArrayList<SboTbSolixArti>  reporteConsumoTodos(String depa, String inicio, String fin) {
        ArrayList<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {

            String sql = "select  si.Sico_Codi_Clas,Sico_Codi_Iden, Sico_Desc,Sico_Id_PK,count(si.Sico_Id_PK) as 'Cantidad' from SIBO_TB_Soli_X_Arti s\n"
                    + "inner join SIBO_TB_Exis e on s.Soli_Arti_Id_X_Exis_FK=e.Exis_Id_PK\n"
                    + "inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Sicop si on a.Arti_Cod_Sico_FK=si.Sico_Id_PK\n"
                    + "inner join SIBO_TB_Soli_Arti so on s.Soli_Arti_Id_X_Soli_Arti_PK=so.Soli_Arti_Id_PK\n"
                    + "where so.Soli_Arti_Id_Depa_FK='%s'\n"
                    + "and s.Soli_Arti_Fech_Sali between '" + inicio + "'and '" + fin + "'\n"
                    + "group by si.Sico_Id_PK, si.Sico_Desc, si.Sico_Codi_Iden, si.Sico_Codi_Clas";

            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(objetoReporte(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
}
