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

public class BodegaDAO {

    RelDatabase db;

    public BodegaDAO() {
        db = new RelDatabase();
    }

    private SboTbBodega Bodega(ResultSet rs) {
        try {

            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeIdPk(rs.getInt("Bode_Id_PK"));
            bodega.setBodeUbic(rs.getString("Bode_Ubic"));
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            return bodega;
        } catch (SQLException e) {
            return null;
        }
    }

    public List<SboTbBodega> listaBodegas(String filtro) {
        List<SboTbBodega> resultado = new ArrayList<SboTbBodega>();
        try {
            String sql = "select bod.Bode_Id_PK,bod.Bode_Ubic,bod.Bode_Desc\n"
                    + "from Sbo_TB_Bodega bod\n"
                    + "where bod.Bode_Id_PK like '%%%s%%';";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(Bodega(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public List<SboTbBodega> listaBodega() {
        List<SboTbBodega> resultado = new ArrayList<SboTbBodega>();
        try {
            String sql = "select Sbo_TB_Bodega.Bode_Id_PK,Sbo_TB_Bodega.Bode_Desc \n" +
                         "from Sbo_TB_Bodega";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(Bodega(rs));
            }
        } catch (SQLException ex) {}
    
        return resultado;

    }
}