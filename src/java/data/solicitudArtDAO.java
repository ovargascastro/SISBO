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
import logic.AbaaProyectos;
import logic.AbaaTbDepartamento;
import logic.AbaaTbProveedor;
import logic.SboTbArticulo;
import logic.SboTbBodega;
import logic.SboTbCatArticulo;
import logic.SboTbExistencia;
import logic.SboTbFamilia;
import logic.SboTbOrdenCompra;
import logic.SboTbSoliArti;
import logic.SboTbSolixArti;
import logic.SboTbSolixArtiId;
import logic.SboTbSubFamilia;

/**
 *
 * @author Osvaldo Vargas
 */
public class solicitudArtDAO {

    RelDatabase db;

    public solicitudArtDAO() {
        db = new RelDatabase();
    }

    private SboTbArticulo articulo(ResultSet rs) {
        try {
            SboTbArticulo ar = new SboTbArticulo();
            ar.setArtIdPk(rs.getInt("Art_Id_Pk"));
            ar.setArtPrecio(rs.getDouble("Art_Precio"));
            ar.setArtCant(rs.getInt("Art_Cant"));
            ar.setArtCantRest(rs.getInt("Art_Cant_Rest"));
            ar.setArtFingr(rs.getDate("Art_FIngr"));
            ar.setArtFvenc(rs.getDate("Art_FVenc"));
            ar.setArtDesc(rs.getString("Art_Desc"));
            ar.setArtMode(rs.getString("Art_Mode"));
            ar.setArtCodiPresup(rs.getString("Art_Codi_Presup"));
            ar.setArtNumeSeri(rs.getString("Art_Nume_Seri"));
            ar.setArtMarc(rs.getString("Art_Marc"));
            ar.setArtNumeFact(rs.getString("Art_Nume_Fact"));
            ar.setArtEsAc(rs.getBoolean("Art_EsAc"));
            ar.setArtCodiCont(rs.getString("Art_Codi_Cont"));
            ar.setSboTbCatArticulo(catArticulo(rs));
            ar.setAbaaProyectos(proyecto(rs));
            ar.setAbaaTbDepartamento(departamento(rs));
            ar.setArtUnidadMedida(rs.getString("Art_Unid_Medi"));
            ar.setSboTbOrdenCompra(OrdenCompra(rs));
            return ar;
        } catch (SQLException ex) {
            return null;
        }
    }

    public List<SboTbArticulo> getArticuloExistencia(String filtro) {
        List<SboTbArticulo> resultado = new ArrayList<SboTbArticulo>();
        try {
            String sql = "select *"
                    + "from Sbo_TB_Articulo art, Sbo_TB_CatArticulo catArt, ABAA_TB_Departamento dpto, "
                    + "Sbo_TB_Existencia exist, Sbo_TB_Bodega bod "
                    + "where art.Art_Codi_Cat_Arti_FK = catArt.Cat_Id_PK and art.Art_Depa_FK = dpto.Depto_Id_PK "
                    + "and exist.Exis_Id_Arti = art.Art_Id_PK and exist.Exis_Id_Bode = bod.Bode_Id_PK  "
                    + "and exist.Exis_Cant > 0 and dpto.Depto_Id_PK='%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(articulo(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public List<SboTbExistencia> existenciasXarticuloxdepto(String idDepto, int idCatArt) throws Exception {
        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
        String sql = "select *"
                + "from Sbo_TB_Existencia exist, Sbo_TB_Articulo art, "
                + "ABAA_TB_Departamento dpto, Sbo_TB_CatArticulo cat "
                + "where art.Art_Depa_FK = dpto.Depto_Id_PK and exist.Exis_Id_Arti = art.Art_Id_PK "
                + "and cat.Cat_Id_PK=art.Art_Codi_Cat_Arti_FK "
                + "and Depto_Id_PK=" + idDepto + " and art.Art_Codi_Cat_Arti_FK=" + idCatArt + ";";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    private SboTbExistencia existencia(ResultSet rs) {
        try {
            SboTbExistencia existencia = new SboTbExistencia();
            existencia.setSboTbBodega(Bodega(rs));
            existencia.setSboTbArticulo(articulo(rs));
            existencia.setExisCant(rs.getDouble("Exis_Cant"));
            return existencia;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    private SboTbBodega Bodega(ResultSet rs) {
        try {
            SboTbBodega bodega = new SboTbBodega();
            bodega.setBodeIdPk(rs.getInt("Bode_Id_PK"));
            bodega.setBodeUbic(rs.getString("Bode_Ubic"));
            bodega.setBodeDesc(rs.getString("Bode_Desc"));
            return bodega;
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

    private AbaaTbDepartamento departamento(ResultSet rs) {
        try {
            AbaaTbDepartamento ob = new AbaaTbDepartamento();
            ob.setDeptoIdPk(rs.getString("Depto_Id_Pk"));
            ob.setDeptoNomb(rs.getString("Depto_Nomb"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaProyectos proyecto(ResultSet rs) {
        try {
            AbaaProyectos ob = new AbaaProyectos();
            ob.setProyIdPk(Integer.parseInt(rs.getString("Proy_Id_PK")));
            ob.setProyDesc(rs.getString("Proy_Desc"));
            return ob;
        } catch (SQLException ex) {
            return null;
        }

    }

    private SboTbOrdenCompra OrdenCompra(ResultSet rs) {
        try {
            SboTbOrdenCompra oc = new SboTbOrdenCompra();
            oc.setOcIdPk(rs.getInt("OC_Id_PK"));
            oc.setOcFecha(rs.getDate("OC_Fecha"));
            oc.setOcPrecTota(rs.getDouble("OC_Prec_Tota"));
            oc.setOcEsta(rs.getString("OC_Esta"));
            oc.setAbaaTbProveedor(Proveedor(rs));
            oc.setOcPlazoEntrega(rs.getString("OC_PlazoEntrega"));
            oc.setOcEntregarA(rs.getString("OC_EntregarA"));
            return oc;
        } catch (SQLException ex) {
            return null;
        }
    }

    private AbaaTbProveedor Proveedor(ResultSet rs) {
        try {
            AbaaTbProveedor pro = new AbaaTbProveedor();
            pro.setProveIdProvePk(rs.getInt("Prove_Id_Prove_PK"));
            pro.setProveCodigo(rs.getString("Prove_Codigo"));
            pro.setProveCedula(rs.getString("Prove_Cedula"));
            pro.setProveTelefono(rs.getInt("Prove_Telefono"));
            pro.setProveCorreo(rs.getString("Prove_Correo"));
            pro.setProveFax(rs.getString("Prove_Fax"));
            pro.setProveNomb(rs.getString("Prove_Nomb"));
            return pro;
        } catch (SQLException ex) {
            return null;
        }
    }
    public void agregarSolicitudArticulo(SboTbSoliArti objeto) throws Exception {
        String query = "insert into Sbo_TB_Soli_Arti(Sol_Arti_Fech_Soli,Sol_Arti_Id_Depa_Fk,"
                + "Sol_Arti_Id_Func_Fk,Sol_Arti_Esta)"
                + "values(?,?,?,?)";

        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        java.util.Date utilStartDate = objeto.getSolArtiFechSoli();
        java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
        preparedStmt.setDate(1, sqlStartDate);

        preparedStmt.setString(2, objeto.getAbaaTbDepartamento().getDeptoIdPk());
        preparedStmt.setInt(3, objeto.getAbaaTbFuncionario().getFuncIdPk());
        preparedStmt.setString(4, objeto.getSolArtiEsta());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }

    public int getLastInsertSolicitudArticulo() throws Exception {
        String sql = " select IDENT_CURRENT( 'Sbo_TB_Soli_Arti' ) as seq ";
        sql = String.format(sql);
        ResultSet rs = db.executeQuery(sql);
        if (rs.next()) {
            return lastInsertSolicitudArticulo(rs);
        } else {
            throw new Exception("error");
        }
    }

    private int lastInsertSolicitudArticulo(ResultSet rs) {
        try {
            int x;
            x = rs.getInt("seq");
            return x;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public void agregarSolicitudXArticulo(SboTbSolixArti objeto) throws Exception {
        String query = "insert into Sbo_TB_SolixArti(SolixArti_Id_Soli_Arti_PK,SolixArti_Id_Arti_PK,"
                + "Sol_Arti_Cant)"
                + "values(?,?,?)";

        PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
        preparedStmt.setInt(1, objeto.getSboTbSoliArti().getSolArtiIdPk());
        preparedStmt.setInt(2, objeto.getSboTbArticulo().getArtIdPk());
        preparedStmt.setInt(3, objeto.getSolArtiCant());
        preparedStmt.executeUpdate();
        db.getConnection().close();
    }
    
    public List<SboTbSoliArti> listadoSolicitudesArticulos(String filtro) {
        List<SboTbSoliArti> resultado = new ArrayList<SboTbSoliArti>();
        try {
            String sql = "select * from Sbo_TB_Soli_Arti sa, ABAA_TB_Departamento dep "
                    + "where sa.Sol_Arti_Id_PK like '%%%s%%'"
                    + "and sa.Sol_Arti_Id_Depa_Fk=dep.Depto_Id_PK";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(soliArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    private SboTbSoliArti soliArti(ResultSet rs) {
        try {
            SboTbSoliArti solArt = new SboTbSoliArti();
            solArt.setSolArtiIdPk(rs.getInt("Sol_Arti_Id_PK"));
            solArt.setSolArtiFechSoli(rs.getDate("Sol_Arti_Fech_Soli"));
            solArt.setAbaaTbDepartamento(departamento(rs));
            solArt.setSolArtiEsta(rs.getString("Sol_Arti_Esta"));
            return solArt;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<SboTbSolixArti> listadoArticulosPorSolicitud(int filtro) {
        List<SboTbSolixArti> resultado = new ArrayList<SboTbSolixArti>();
        try {
            String sql ="select * "
                    + "from Sbo_TB_Articulo art, Sbo_TB_Soli_Arti solArt, Sbo_TB_SolixArti sxa "
                    + "where art.Art_Id_PK = sxa.SolixArti_Id_Arti_PK "
                    + "and solart.Sol_Arti_Id_PK = sxa.SolixArti_Id_Soli_Arti_PK "
                    + "and solArt.Sol_Arti_Id_PK = '%s'";
            sql = String.format(sql, filtro);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(solixArti(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }
    
    private SboTbSolixArti solixArti(ResultSet rs) {
        try {
            SboTbSolixArti solxArt = new SboTbSolixArti();
            solxArt.setSboTbArticulo(articulo(rs));
            solxArt.setSboTbSoliArti(soliArti(rs));
            solxArt.setSolArtiCant(rs.getInt("Sol_Arti_Cant"));
            return solxArt;
        } catch (SQLException ex) {
            return null;
        }
    }

}
