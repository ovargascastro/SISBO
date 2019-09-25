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

    private SboTbBodega ObtenerBodega(ResultSet rs) {
        try {
            SboTbBodega bod = new SboTbBodega();
            //bod.getBodeDesc();
            return bod;
        } catch (Exception e) {

            return null;

        }

    }

}
