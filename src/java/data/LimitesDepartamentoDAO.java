/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.SboSicop;
import logic.SboTbExistencia;
import logic.SboTbLimiteDpto;

/**
 *
 * @author Marco
 */
public class LimitesDepartamentoDAO {

    RelDatabase db;

    public LimitesDepartamentoDAO() {
        db = new RelDatabase();
    }

    private SboTbLimiteDpto limites(ResultSet rs) {
        try {
            SboTbLimiteDpto li = new SboTbLimiteDpto();
            li.setLimiteDptoLimite(rs.getInt("Limi_Depa_limi"));
            return li;
        } catch (SQLException ex) {
            return null;
        }
    }

    public SboTbLimiteDpto getLimiteDepaPorExis(SboTbExistencia exis) throws SQLException, Exception {
        String sql = "select limi.Limi_Depa_limi  \n"
                + "from SIBO_TB_Limi_Depa limi, SIBO_TB_Sicop sicop, ABAA_TB_Catalogo_Departamento depa\n"
                + "where sicop.Sico_Id_PK = limi.Limi_Depa_Id_Sico_PK "
                + "and limi.Limi_Depa_Id_Dpto_PK = depa.Cata_Depa_id_PK and "
                + "limi.Limi_Depa_Id_Dpto_PK=" + exis.getAbaaTbDepartamento().getDeptoIdPk()
                + " and limi.Limi_Depa_Id_Sico_PK=" + exis.getSboTbSicop().getSicopId();
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return limites(rs);
        } else {
            throw new Exception("LimiteDepto no Existe");
        }

    }
}
