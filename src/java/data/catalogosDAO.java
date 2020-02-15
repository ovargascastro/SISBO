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
            ob.setFamiIdPk(rs.getString("Fami_Id_PK"));
            ob.setFamiEstado(rs.getString("Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    
    private SboTbCatContable CatContable(ResultSet rs){
        
         try {
            SboTbCatContable ob = new SboTbCatContable();
            ob.setCntDesc(rs.getString("Cont_Desc"));
            ob.setCntIdPk(rs.getInt("Cont_Id_PK"));
            ob.setCntEst(rs.getString("Cont_Esta")); 
            ob.setCntNivel(rs.getInt("Cont_Nive"));
            ob.setCntCodi(rs.getString("Cont_Codi"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private SboTbCatArticulo catArticulo(ResultSet rs) {
        try {
            SboTbCatArticulo ob = new SboTbCatArticulo();
            ob.setCatIdPk(rs.getInt("Cata_Id_PK"));
            ob.setCatDesc(rs.getString("Cata_Desc"));
            ob.setArtCat_Estado(rs.getString("Cata_Esta"));
            ob.setSboTbSubFamilia(Subfamilia(rs));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbSubFamilia Subfamilia(ResultSet rs) {
        try {
            SboTbSubFamilia ob = new SboTbSubFamilia();
            ob.setSubFamiIdPk(rs.getString("Sub_Fami_Id_PK"));
            ob.setSboTbFamilia(familia(rs));
            ob.setSubFamiDesc(rs.getString("Sub_Fami_Desc"));
            ob.setSubFamiEstado(rs.getString("Sub_Fami_Estado"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }
    
  

    public List<SboTbFamilia> listaFamilias(String filtro) {
        List<SboTbFamilia> resultado = new ArrayList<SboTbFamilia>();
        try {
            String sql = "select * from SIBO_TB_Familia f where f.Fami_Desc like '%%%s%%'";
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
            String sql = "select * from SIBO_TB_Sub_Fami s where s.Sub_Fami_Desc like '%%%s%%'";
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
            String sql = "select * from SIBO_TB_Cata_Arti a inner join SIBO_TB_Sub_Fami s on a.Cata_SubF_FK = s.Sub_Fami_Id_PK"
                    + " inner join SIBO_TB_Familia f on s.Sub_Fami_CodF_FK = f.Fami_Id_PK where a.Cata_Desc like '%%%s%%' order by Cata_Desc";
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
            String sql = "select * from SIBO_TB_Cata_Cont s where s.Cont_Desc like '%%%s%%'";
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
        String sql = "select * from SIBO_TB_Cata_Arti a inner join SIBO_TB_Sub_Fami s on a.Cata_SubF_FK = s.Sub_Fami_Id_PK"
                + " where a.Cata_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return catArticulo(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    public SboTbFamilia getSboTbFamilia(String filtro) throws Exception {
        String sql = "select * from SIBO_TB_Familia f where f.Fami_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return familia(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }

    
    public SboTbCatContable getSboTbCatContable(int filtro) throws Exception {
        String sql = "select * from SIBO_TB_Cata_Cont f where f.Cont_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return CatContable(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }
    
    public SboTbSubFamilia getSboTbSubFamilia(String filtro) throws Exception {
        String sql = "select * from SIBO_TB_Sub_Fami s inner join SIBO_TB_Familia f on s.Sub_Fami_CodF_FK = f.Fami_Id_PK"
                + " where s.Sub_Fami_Id_PK ='%s'";
        sql = String.format(sql, filtro);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return Subfamilia(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
    }
    
   
    
    public void actualizarCatContable(SboTbCatContable objeto) throws Exception {
        String query = "update SIBO_TB_Cata_Cont set Cont_Desc = ?, Cont_Esta = ?, Cont_Nive = ?, Cont_Codi = ? where Cont_Id_PK = ?";
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
        String query = "update SIBO_TB_Familia set Fami_Desc = ?,Fami_Estado = ? where Fami_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getFamiDesc());
        preparedStmt.setString(2, objeto.getFamiEstado());
        preparedStmt.setString(3, objeto.getFamiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void actualizarSubFamilia(SboTbSubFamilia objeto) throws Exception {
        String query = "update SIBO_TB_Sub_Fami set Sub_Fami_Desc = ?, Sub_Fami_CodF_FK = ?, Sub_Fami_Estado = ? where Sub_Fami_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSubFamiDesc());
        preparedStmt.setString(2, objeto.getSboTbFamilia().getFamiIdPk());
        preparedStmt.setString(3, objeto.getSubFamiEstado());
        preparedStmt.setString(4, objeto.getSubFamiIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void actualizarCatArticulo(SboTbCatArticulo objeto) throws Exception {
        String query = "update SIBO_TB_Cata_Arti set Cata_Desc = ?, Cata_SubF_FK = ?, Cata_Esta=? where Cata_Id_PK = ?";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getCatDesc());
        preparedStmt.setString(2, objeto.getSboTbSubFamilia().getSubFamiIdPk());
        preparedStmt.setString(3, objeto.getArtCat_Estado());
        preparedStmt.setInt(4, objeto.getCatIdPk());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void crearFamilia(SboTbFamilia objeto) throws Exception {
        String query = "insert into SIBO_TB_Familia(Fami_Id_PK,Fami_Desc,Fami_Estado)values(?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getFamiIdPk());
        preparedStmt.setString(2, objeto.getFamiDesc());
        preparedStmt.setString(3, "1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void crearSubFamilia(SboTbSubFamilia objeto) throws Exception {
        String query = "insert into SIBO_TB_Sub_Fami(Sub_Fami_Id_PK,Sub_Fami_Desc,Sub_Fami_CodF_FK,Sub_Fami_Estado)values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getSubFamiIdPk());
        preparedStmt.setString(2, objeto.getSubFamiDesc());
        preparedStmt.setString(3, objeto.getSboTbFamilia().getFamiIdPk());
        preparedStmt.setString(4, "1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public void crearCatArticulo(SboTbCatArticulo objeto) throws Exception {
        String query = "insert into SIBO_TB_Cata_Arti(Cata_Codi_Sico,Cata_SubF_FK,Cata_Desc,Cata_Esta)values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setString(1, objeto.getCatCodSicop());
        preparedStmt.setString(2, objeto.getSboTbSubFamilia().getSubFamiIdPk());
        preparedStmt.setString(3, objeto.getCatDesc());
        preparedStmt.setString(4, "1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
    
    public void crearCatContable(SboTbCatContable objeto) throws Exception{
        String query = "insert into SIBO_TB_Cata_Cont(Cont_Desc,Cont_Codi,Cont_Nive,Cont_Esta)values(?,?,?,?)";
        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
       
        preparedStmt.setString(1, objeto.getCntDesc());
        preparedStmt.setString(2, objeto.getCntCodi());
        preparedStmt.setInt(3, objeto.getCntNivel());
        preparedStmt.setString(4,"1");
        preparedStmt.executeUpdate();
        db.getConnection().close();
       }
    
}
