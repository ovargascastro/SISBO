package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.SboTbCatArticulo;
import logic.SboTbCatContable;
import logic.SboTbFamilia;
import logic.SboTbSubFamilia;

public class BitacoraDAO {

    RelDatabase db;

    public BitacoraDAO() {
        db = new RelDatabase();
    }

    public void insertarEnBitacora(String responsable, String accion, String descRegistro) throws Exception {
        String sql = "Execute registrarBitacora ?,?,?;";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(sql);
        preparedStmt.setString(1, responsable);
        preparedStmt.setString(2, accion);
        preparedStmt.setString(3, descRegistro);
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
