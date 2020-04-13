package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.AbaaTbDepartamento;
import logic.SboSicop;
import logic.SboTbExistencia;
import logic.SboTbLimiteDpto;

public class LimitesDepartamentoDAO {

    RelDatabase db;

    public LimitesDepartamentoDAO() {
        db = new RelDatabase();
    }

    //objeto de tipo limite por departamento
    private SboTbLimiteDpto limites(ResultSet rs) {
        try {
            SboTbLimiteDpto li = new SboTbLimiteDpto();
            SboSicop sicop = new SboSicop();
            AbaaTbDepartamento dpto = new AbaaTbDepartamento();

            dpto.setDeptoIdPk(rs.getString("Limi_Depa_Id_Dpto_PK"));
            dpto.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            sicop.setSicopId(rs.getInt("Limi_Depa_Id_Sico_PK"));
            sicop.setSicopDesc(rs.getString("Sico_Desc"));

            li.setLimite(rs.getInt("Limi_Depa_limi"));
            li.setAbaaTbDepartamento(dpto);
            li.setSboSicop(sicop);
            return li;
        } catch (SQLException ex) {
            return null;
        }
    }

    public SboTbLimiteDpto getLimiteDepaPorExis(int idSicop, int idDpto) throws SQLException, Exception {
        String sql = "select limi.Limi_Depa_Id_Dpto_PK,limi.Limi_Depa_Id_Sico_PK,\n"
                + "limi.Limi_Depa_limi,sicop.Sico_Desc, dpto.Cata_Depa_nomb\n"
                + "from SIBO_TB_Limi_Depa limi,SIBO_TB_Sicop sicop,ABAA_TB_Catalogo_Departamento dpto\n"
                + "where sicop.Sico_Id_PK=Limi_Depa_Id_Sico_PK and limi.Limi_Depa_Id_Dpto_PK=dpto.Cata_Depa_id_PK\n"
                + "and Limi_Depa_Id_Dpto_PK=" + idDpto + " and Limi_Depa_Id_Sico_PK=" + idSicop + ";";
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return limites(rs);
        } else {
            throw new Exception("LimiteDepto no Existe");
        }

    }
}
