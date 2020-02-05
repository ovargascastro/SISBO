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



/**
 *
 * @author oscar
 */
public class sicopDAO {
    
    
    RelDatabase db;
    
    public sicopDAO() {
        db = new RelDatabase();
    }
    
    
        
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
        
    
}
