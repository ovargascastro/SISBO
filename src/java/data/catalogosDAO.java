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
            ob.setFamiEstado(rs.getString("Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    
    private SboTbCatContable CatContable(ResultSet rs){
        
         try {
            SboTbCatContable ob = new SboTbCatContable();
            ob.setCntDesc(rs.getString("Cnt_Desc"));
            ob.setCntIdPk(rs.getInt("Cnt_Id_PK"));
            ob.setCntEst(rs.getString("Cnt_Est")); 
            ob.setCntNivel(rs.getInt("Cnt_Nivel"));
            ob.setCntCodi(rs.getString("Cnt_Codi"));
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
            ob.setArtCat_Estado(rs.getString("Cat_Estado"));
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
            ob.setSubFamiEstado(rs.getString("SubFami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    
    
    public List<SboTbFamilia> listaFamilias(String filtro) {
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
            sql = String.format(sql, filtro);
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
    
    public List<SboTbCatContable> listaCatContable(String filtro) {
        List<SboTbCatContable> resultado = new ArrayList<SboTbCatContable>();
        try {
            String sql = "select * from Sbo_TB_CatContable s where s.Cnt_Desc like '%%%s%%'";
            sql = String.format(sql,filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(CatContable(rs));
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

    
    public SboTbCatContable getSboTbCatContable(int filtro) throws Exception {
        String sql = "select * from Sbo_TB_CatContable f where f.Cnt_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return CatContable(rs);
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
  
    
    public void actualizarCatContable(SboTbCatContable objeto) throws Exception {
        String query = "update Sbo_TB_CatContable set Cnt_Desc = ?, Cnt_Est = ?, Cnt_Nivel = ?, Cnt_Codi = ? where Cnt_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getCntDesc());
         preparedStmt.setString(2, objeto.getCntEst());
        preparedStmt.setInt(3, objeto.getCntNivel());
         preparedStmt.setString(4, objeto.getCntCodi());
          preparedStmt.setInt(5, objeto.getCntIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void actualizarFamilia(SboTbFamilia objeto) throws Exception {
        String query = "update Sbo_TB_Familia set Fami_Desc = ?,Fami_Estado = ? where Fami_Id_Pk = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getFamiDesc());
        preparedStmt.setString(2, objeto.getFamiEstado());
        preparedStmt.setString(3, objeto.getFamiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }


 
    

    public void actualizarSubFamilia(SboTbSubFamilia objeto) throws Exception {

        String query = "update Sbo_TB_SubFamilia set SubFami_Desc = ?, SubFami_CodF_Fk = ?, SubFami_Estado = ? where SubFami_Id_Pk = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSubFamiDesc());
        preparedStmt.setString(2, objeto.getSboTbFamilia().getFamiIdPk());
        preparedStmt.setString(3, objeto.getSubFamiEstado());
        preparedStmt.setString(4, objeto.getSubFamiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void actualizarCatArticulo(SboTbCatArticulo objeto) throws Exception {
        String query = "update Sbo_TB_CatArticulo set Cat_Desc = ?, Cat_SubF_FK = ?, Cat_Estado=? where Cat_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getCatDesc());
        preparedStmt.setString(2, objeto.getSboTbSubFamilia().getSubFamiIdPk());
        preparedStmt.setString(3, objeto.getArtCat_Estado());
        preparedStmt.setInt(4, objeto.getCatIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void crearFamilia(SboTbFamilia objeto) throws Exception {
        String query = "insert into Sbo_TB_Familia(Fami_Id_Pk,Fami_Desc,Fami_Estado)values(?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getFamiIdPk());
        preparedStmt.setString(2, objeto.getFamiDesc());
        preparedStmt.setString(3, "1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void crearSubFamilia(SboTbSubFamilia objeto) throws Exception {
        String query = "insert into Sbo_TB_SubFamilia(SubFami_Id_Pk,SubFami_Desc,SubFami_CodF_Fk,SubFami_Estado)values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSubFamiIdPk());
        preparedStmt.setString(2, objeto.getSubFamiDesc());
        preparedStmt.setString(3, objeto.getSboTbFamilia().getFamiIdPk());
        preparedStmt.setString(4, "1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void crearCatArticulo(SboTbCatArticulo objeto) throws Exception {
        String query = "insert into Sbo_TB_CatArticulo(Cat_Cod_Sicop,Cat_SubF_FK,Cat_Desc,Cat_Estado)values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getCatCodSicop());
        preparedStmt.setString(2, objeto.getSboTbSubFamilia().getSubFamiIdPk());
        preparedStmt.setString(3, objeto.getCatDesc());
        preparedStmt.setString(4, "1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
    
 
       public void crearCatContable(SboTbCatContable objeto) throws Exception{
        String query = "insert into Sbo_TB_CatContable(Cnt_Desc,Cnt_Codi,Cnt_Nivel,Cnt_Est)values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
       
        preparedStmt.setString(1, objeto.getCntDesc());
        preparedStmt.setString(2, objeto.getCntCodi());
        preparedStmt.setInt(3, objeto.getCntNivel());
        preparedStmt.setString(4,"1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
       }
            

}
