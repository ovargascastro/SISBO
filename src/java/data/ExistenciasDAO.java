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

public class ExistenciasDAO {

    RelDatabase db;

    public ExistenciasDAO() {
        db = new RelDatabase();
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

    private SboTbBodega Bodega2(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeIdPk(rs.getInt("Exis_Id_Bode_PK"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbDepartamento departamento2(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Exist_Depa_PK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboSicop sicop(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboSicop sicop2(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopId(rs.getInt("Exis_Id_Sico_PK"));
            ob.setSicopDesc(rs.getString("Sico_Desc"));
            return ob;
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

    private SboTbArticulo ArticuloExist(ResultSet rs) {
        try {
            SboTbArticulo arti = new SboTbArticulo();
            AbaaTbDepartamento dpto = new AbaaTbDepartamento();
            SboTbCatArticulo cat = new SboTbCatArticulo();
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            SboSicop ob = new SboSicop();
            arti.setArtIdPk(rs.getInt("Arti_Id_PK"));
            arti.setArtDesc(rs.getString("Arti_Desc"));
            arti.setArtMode(rs.getString("Arti_Mode"));
            arti.setArtMarc(rs.getString("Arti_Marc"));
            arti.setArtPrecio(rs.getDouble("Arti_Prec"));
            arti.setSboSicop(sicop(rs));
            arti.setArtNumeSeri(rs.getString("Arti_Nume_Seri"));
            dpto.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            dpto.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            arti.setAbaaTbDepartamento(dpto);
            return arti;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbExistencia existenciaF(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setSboTbBodega(Bodega(rs));
            ob.setArticulo(ArticuloExist(rs));
            ob.setSboTbEsta(rs.getInt("Exis_Esta"));
            ob.setId(rs.getInt("Exis_Id_PK"));
            ob.setExistFingr(rs.getDate("Exis_Fech_Ingr"));
            return ob;
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

    private SboTbExistencia stock(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            SboTbBodega bodega = new SboTbBodega();
            SboTbArticulo articulo = new SboTbArticulo();
            AbaaTbDepartamento departamento = new AbaaTbDepartamento();
            SboSicop sicop = new SboSicop();
            
            //Construccion de los objetos correspondientes
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            departamento.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            sicop.setSicopDesc(rs.getString("Sico_Desc"));
            articulo.setAbaaTbDepartamento(departamento);
            articulo.setSboSicop(sicop);
                        
            //Construccion del objeto principal
            ob.setSboTbBodega(bodega);
            ob.setArticulo(articulo);
            ob.setSboTbEsta(rs.getInt("existencias"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<SboTbExistencia> listaExistencias(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select bode.Bode_Desc, dpto.Cata_Depa_nomb, sicop.Sico_Id_PK, sicop.Sico_Desc, exis.Exis_Cant\n"
                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"
                    + "SIBO_TB_Exis exis\n"
                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"
                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"
                    + "and exis.Exis_Id_Bode_PK=" + bodega + "\n"
                    + "and exis.Exist_Depa_PK=" + departamento + "\n"
                    + "and exis.Exis_Id_Sico_PK=" + articulo + ";";
            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> listaExistencias2(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select exis.Exis_Id_Bode_PK, bode.Bode_Desc, dpto.Cata_Depa_nomb, sicop.Sico_Desc, exis.Exis_Cant\n"
                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"
                    + "SIBO_TB_Exis exis\n"
                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"
                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"
                    + "and exis.Exis_Id_Bode_PK=" + bodega + "\n"
                    + "and exis.Exist_Depa_PK=" + departamento + "\n"
                    + "and exis.Exis_Id_Sico_PK=" + articulo + ";";

            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> listaExistenciasArticulos(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {

            String sql = "select * from SIBO_TB_Exis e inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Bode b on e.Exis_Id_Bode_PK= Bode_Id_Pk "
                    + "inner join ABAA_TB_Catalogo_Departamento d on a.Arti_Cata_Depa_FK=d.Cata_Depa_id_PK "
                    + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                    + "where e.Exis_Id_Bode_Pk=" + bodega + "and\n"
                    + "a.Arti_Cata_Depa_FK=" + departamento + "and a.Arti_Cod_Sico_FK=" + articulo + " and e.Exis_Esta='1';";

            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existenciaF(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> listaExistenciasTodosArticulos(String bodega, String departamento, String articulo) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {

            String sql = "select * from SIBO_TB_Exis e inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Bode b on e.Exis_Id_Bode_PK= Bode_Id_Pk "
                    + "inner join ABAA_TB_Catalogo_Departamento d on a.Arti_Cata_Depa_FK=d.Cata_Depa_id_PK "
                    + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                    + "where e.Exis_Id_Bode_Pk=" + bodega + "and\n"
                    + "a.Arti_Cata_Depa_FK=" + departamento + "and e.Exis_Esta='1';";

            sql = String.format(sql, bodega, departamento, articulo);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existenciaF(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> listaExistenciasfiltro(String depa) {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
            String sql = "select * from SIBO_TB_Exis e inner join SIBO_TB_Articulo a on e.Exis_Arti_FK=a.Arti_Id_PK\n"
                    + "inner join SIBO_TB_Bode b on e.Exis_Id_Bode_PK= Bode_Id_Pk "
                    + "inner join ABAA_TB_Catalogo_Departamento d on a.Arti_Cata_Depa_FK=d.Cata_Depa_id_PK "
                    + "inner join SIBO_TB_Sicop s on a.Arti_Cod_Sico_FK=s.Sico_Id_PK "
                    + "where a.Arti_Cata_Depa_FK=" + depa + "and\n"
                    + "e.Exis_Esta='1';";
            sql = String.format(sql, depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existenciaF(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public void actualizarExistencia(SboTbExistencia objeto) throws Exception {
        String query = "update SIBO_TB_Exis set Exis_Esta=? where Exis_Id_PK=?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, objeto.getSboTbEsta());
        preparedStmt.setInt(2, objeto.getId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void actualizarExistenciaSoliPendiente(SboTbExistencia objeto) throws Exception {
        String query = "update SIBO_TB_Exis set Exis_Esta=? where Exis_Id_PK=?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, 2);
        preparedStmt.setInt(2, objeto.getId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    //-------------- Metodos de disminucion de existencias --------------
    public List<SboTbExistencia> obtenerExistenciasSegunNumSolicitud(int idSoli) {
        List<SboTbExistencia> resultado = new ArrayList<>();
        try {
            String sql = "SELECT exis.Exis_Id_PK, exis.Exis_Esta\n"
                    + "FROM SIBO_TB_Exis exis, SIBO_TB_Soli_X_Arti SoliXArti,\n"
                    + "SIBO_TB_Soli_Arti soli, SIBO_TB_Bode bode\n"
                    + "where SoliXArti.Soli_Arti_Id_X_Soli_Arti_PK=soli.Soli_Arti_Id_PK\n"
                    + "and SoliXArti.Soli_Arti_Id_X_Exis_FK=exis.Exis_Id_PK\n"
                    + "and exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and soli.Soli_Arti_Id_PK=" + idSoli + ";";
            sql = String.format(sql, idSoli);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existenciaIdEstado(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    //Obtiene número total de existencias según codigo SICOP y ID del dpto
    public int obtenerCantidadExisPorDptoSicop(int idSicop, int idDpto) throws SQLException, Exception {
        String sql = "SELECT COUNT(*) Cantidad\n"
                + "FROM SIBO_TB_Exis exis, SIBO_TB_Articulo arti, ABAA_TB_Catalogo_Departamento dpto,\n"
                + "SIBO_TB_Sicop sicop, SIBO_TB_Limi_Depa limite, SIBO_TB_Bode bode\n"
                + "where exis.Exis_Arti_FK=arti.Arti_Id_PK and exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                + "and arti.Arti_Cod_Sico_FK=sicop.Sico_Id_PK and arti.Arti_Cata_Depa_FK=dpto.Cata_Depa_id_PK\n"
                + "and limite.Limi_Depa_Id_Dpto_PK=dpto.Cata_Depa_id_PK and limite.Limi_Depa_Id_Sico_PK=sicop.Sico_Id_PK\n"
                + "and sicop.Sico_Id_PK=" + idSicop + " and dpto.Cata_Depa_id_PK=" + idDpto + " and exis.Exis_Esta!=0;";
        sql = String.format(sql, idSicop, idDpto);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("Cantidad");
        } else {
            throw new Exception("Error general");
        }
    }

    private SboTbExistencia existenciaIdEstado(ResultSet rs) {
        try {
            SboTbExistencia ob = new SboTbExistencia();
            ob.setId(rs.getInt("Exis_Id_PK"));
            ob.setSboTbEsta(rs.getInt("Exis_Esta"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<SboTbExistencia> listaExistenciasStocks(String bodega, String departamento) {
        List<SboTbExistencia> resultado = new ArrayList<>();
        try {
            String sql = "Select bode.Bode_Desc,dpto.Cata_Depa_nomb, sicop.Sico_Desc, count(exis.Exis_Id_PK) existencias\n"
                    + "from SIBO_TB_Exis exis, SIBO_TB_Bode bode, SIBO_TB_Articulo arti, ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Sicop sicop\n"
                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK and exis.Exis_Arti_FK=arti.Arti_Id_PK and arti.Arti_Cod_Sico_FK=sicop.Sico_Id_PK\n"
                    + "and exis.Exis_Esta=1  and arti.Arti_Cata_Depa_FK=dpto.Cata_Depa_id_PK\n"
                    + "and bode.Bode_Id_PK='%s' and dpto.Cata_Depa_id_PK='%s'\n"
                    + "group by bode.Bode_Desc,dpto.Cata_Depa_nomb, sicop.Sico_Desc\n"
                    + "order by bode.Bode_Desc,dpto.Cata_Depa_nomb, sicop.Sico_Desc;";
            sql = String.format(sql, bodega, departamento);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(stock(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

}
