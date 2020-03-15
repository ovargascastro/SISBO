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
            bodega.setBodeEsta(rs.getInt("Bode_Esta"));
            return bodega;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<SboTbBodega> listaBodegas(String filtro) {
        List<SboTbBodega> resultado = new ArrayList<SboTbBodega>();
        try {
            String sql = "select *\n"
                    + "from SIBO_TB_Bode bod\n"
                    + "where bod.Bode_Id_PK like '%%%s%%' and bod.Bode_Esta<>0;";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(Bodega(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public void agregarBodega(SboTbBodega b) throws SQLException {
        String query = "insert into SIBO_TB_Bode(Bode_Ubic,Bode_Desc,Bode_Esta)values(?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, b.getBodeUbic());
        preparedStmt.setString(2, b.getBodeDesc());
        preparedStmt.setInt(3, 1);
        preparedStmt.execute();
        db.getConnection().close();
    }

    public void deleteBodega(SboTbBodega s) throws SQLException {

        String query = "update SIBO_TB_Bode set Bode_Esta = ? where Bode_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, s.getBodeEsta());
        preparedStmt.setInt(2, s.getBodeIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void updateBodega(SboTbBodega s) throws SQLException {

        String query = "update SIBO_TB_Bode set Bode_Ubic = ?, Bode_Desc = ? where Bode_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, s.getBodeUbic());
        preparedStmt.setString(2, s.getBodeDesc());
        preparedStmt.setInt(3, s.getBodeIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public SboTbBodega getBodega(String id) throws Exception {
        String sql = "select * from SIBO_TB_Bode where Bode_Id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Bodega(rs);
        } else {
            throw new Exception("Bodega no Existe");
        }
    }

}
