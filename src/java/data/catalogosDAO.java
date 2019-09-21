/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.SboTbCatArticulo;
import logic.SboTbFamilia;
import logic.SboTbSubFamilia;

/**
 *
 * @author oscar
 */
public class catalogosDAO {

    RelDatabase db;

    public catalogosDAO() {
        db = new RelDatabase();

    }

    private SboTbFamilia familia(ResultSet rs) {
        try {
            SboTbFamilia ob = new SboTbFamilia();
            ob.setFamiDesc(rs.getString("Fami_Desc"));
            ob.setFamiIdPk(rs.getString("Fami_Id_Pk"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbCatArticulo catArticulo(ResultSet rs) {
        try {
            SboTbCatArticulo ob = new SboTbCatArticulo();
            ob.setCatIdPk(rs.getInt("Cat_Id_Pk"));
            ob.setCatDesc(rs.getString("Cat_Desc"));
            ob.setSboTbSubFamilia(Subfamilia(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbSubFamilia Subfamilia(ResultSet rs) {
        try {
            SboTbSubFamilia ob = new SboTbSubFamilia();
            ob.setSubFamiIdPk(rs.getString("SubFami_Id_Pk"));
            ob.setSboTbFamilia(familia(rs));
            ob.setSubFamiDesc(rs.getString("SubFami_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    
        public List<SboTbFamilia> listaFamilias (String filtro) {
        List<SboTbFamilia> resultado = new ArrayList<SboTbFamilia>();
        try {
            String sql = "select * from Sbo_TB_Familia f where f.Fami_Desc like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(familia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbSubFamilia> listaSubFamilias(String filtro) {
        List<SboTbSubFamilia> resultado = new ArrayList<SboTbSubFamilia>();
        try {
            String sql = "select * from Sbo_TB_SubFamilia s where s.SubFami_Desc like '%%%s%%'";
            sql = String.format(sql,filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(Subfamilia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbCatArticulo> listaCatArticulos(String filtro) {
        List<SboTbCatArticulo> resultado = new ArrayList<SboTbCatArticulo>();
        try {
            String sql = "select * from Sbo_Tb_CatArticulo a inner join Sbo_TB_SubFamilia s on a.Cat_SubF_FK = s.SubFami_Id_Pk"
                    + " inner join Sbo_TB_Familia f on s.SubFami_CodF_Fk = f.Fami_Id_Pk where a.Cat_Desc like '%%%s%%'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(catArticulo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public SboTbCatArticulo getCatArticulo(int filtro) throws Exception {
        String sql = "select * from Sbo_Tb_CatArticulo a inner join Sbo_TB_SubFamilia s on a.Cat_SubF_FK = s.SubFami_Id_Pk"
                + " where a.Cat_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return catArticulo(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    public SboTbFamilia getSboTbFamilia(String filtro) throws Exception {
        String sql = "select * from Sbo_TB_Familia f where f.Fami_Id_Pk ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return familia(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    public SboTbSubFamilia getSboTbSubFamilia(String filtro) throws Exception {
        String sql = "select * from Sbo_TB_SubFamilia s inner join Sbo_TB_Familia f on s.SubFami_CodF_Fk = f.Fami_Id_Pk"
                + " where s.SubFami_Id_Pk ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Subfamilia(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    public void actualizarFamilia(SboTbFamilia objeto) throws Exception {
        String query = "update Sbo_TB_Familia set Fami_Desc = ? where Fami_Id_Pk = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getFamiDesc());
        preparedStmt.setString(2, objeto.getFamiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

}
