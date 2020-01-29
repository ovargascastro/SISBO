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
import logic.AbaaTbDepartamento;

/**
 *
 * @author Osvaldo Vargas
 */
public class DepartamentoDAO {
    
    RelDatabase db;
    
    public DepartamentoDAO() {
        db = new RelDatabase();
    }
    
    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));
            ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }
    
//    	[Cata_Depa_id_PK] [int] IDENTITY(1,1) NOT NULL,
//	[Cata_Depa_id_madr] [varchar](50) NULL,
//	[Cata_Depa_num] [varchar](50) NOT NULL,
//	[Cata_Depa_nomb] [varchar](50) NOT NULL,
//	[Cata_Depa_aten_publ] [int] NULL,
    
    public List<AbaaTbDepartamento> listaDepartamento() {
        List<AbaaTbDepartamento> resultado = new ArrayList<AbaaTbDepartamento>();
        try {
            String sql = "select * from ABAA_TB_Catalogo_Departamento";
            sql = String.format(sql);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(departamento(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    public AbaaTbDepartamento getDepartamento(String id) throws Exception {
        String sql = "select * from ABAA_TB_Catalogo_Departamento where Cata_Depa_id_PK='%s'";
        sql = String.format(sql, id);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return departamento(rs);
        } else {
            throw new Exception("Departamento no Existe");
        }
    }
}
