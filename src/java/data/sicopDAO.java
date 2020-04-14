package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.SboSicop;

public class sicopDAO {

    RelDatabase db;

    public sicopDAO() {
        db = new RelDatabase();
    }

    // se crea un objeto de tipo articulos de sicop
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
//se muesta los datos registrados en la tabla sicop

    public List<SboSicop> getListaSicop(String filtro) {
        List<SboSicop> resultado = new ArrayList<SboSicop>();
        try {
            String sql = "select * from SIBO_TB_Sicop s order by s.Sico_Desc;";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(sicop(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    //se muestra los datos de un objeto de la tabla sicop por medio de la descripcion
    public List<SboSicop> getListaSicopFiltro(String filtro) {
        List<SboSicop> resultado = new ArrayList<SboSicop>();
        try {
            String sql = "select * from SIBO_TB_Sicop s where s.Sico_Desc like '%%%s%%' order by s.Sico_Desc;";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(sicop(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    // se selecciona un registro de la tabla sicop por medio del id
    public SboSicop getSboSicop(String id) throws Exception {
        String sql = "select * from SIBO_TB_Sicop where Sico_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return sicop(rs);
        } else {
            throw new Exception("Departamento no Existe");
        }
    }
// se actualiza el articulo de sicop con todos sus atributos

    public void actualizarSicop(SboSicop s) throws SQLException {

        String query = "update SIBO_TB_Sicop set Sico_Codi_Iden = ?, Sico_Codi_Clas=?, Sico_Desc=? where Sico_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, s.getSicopCodiInden());
        preparedStmt.setString(2, s.getSicopCodiClas());
        preparedStmt.setString(3, s.getSicopDesc());
        preparedStmt.setInt(4, s.getSicopId());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    // se agrega un artiuclo al catalogo de sicop
    public void agregarSicop(SboSicop s) throws SQLException {

        String query = "insert into SIBO_TB_Sicop(Sico_Codi_Iden,Sico_Codi_Clas,Sico_Desc)values(?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);

        preparedStmt.setString(1, s.getSicopCodiInden());
        preparedStmt.setString(2, s.getSicopCodiClas());
        preparedStmt.setString(3, s.getSicopDesc());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    //Se obtiene una lista de los codigos de SICOP relacionados a una solicitud de articulos
    public List<SboSicop> listadoCodigosSicopSegunSoliArti(int filtro) {
        List<SboSicop> resultado = new ArrayList<SboSicop>();
        try {
            String sql = "SELECT DISTINCT sicop.Sico_Id_PK\n"
                    + "FROM SIBO_TB_Exis exis, SIBO_TB_Soli_X_Arti SoliXArti, SIBO_TB_Soli_Arti soli, \n"
                    + "SIBO_TB_Bode bode, SIBO_TB_Sicop sicop, SIBO_TB_Articulo arti\n"
                    + "where SoliXArti.Soli_Arti_Id_X_Soli_Arti_PK=soli.Soli_Arti_Id_PK\n"
                    + "and SoliXArti.Soli_Arti_Id_X_Exis_FK=exis.Exis_Id_PK\n"
                    + "and exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"
                    + "and arti.Arti_Id_PK=exis.Exis_Arti_FK\n"
                    + "and arti.Arti_Cod_Sico_FK= sicop.Sico_Id_PK\n"
                    + "and soli.Soli_Arti_Id_PK=" + filtro + ";";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(sicopId(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    private SboSicop sicopId(ResultSet rs) {
        try {
            SboSicop ob = new SboSicop();
            ob.setSicopId(rs.getInt("Sico_Id_PK"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

}
