package data;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import logic.AbaaTbDepartamento;

import logic.SboSicop;

import logic.SboTbBodega;

import logic.SboTbExistencia;

/**

 *

 * @author oscar

 */

public class ExistenciasDAO {
    RelDatabase db;
    public ExistenciasDAO() {

        db = new RelDatabase();

    }

    private SboTbBodega Bodega(ResultSet rs) {

        try {

            SboTbBodega bodega = new SboTbBodega();

            //bodega.setBodeIdPk(rs.getInt("Bode_Id_PK"));

           // bodega.setBodeUbic(rs.getString("Bode_Ubic"));

            bodega.setBodeDesc(rs.getString("Bode_Desc"));

            return bodega;

        } catch (SQLException ex) {

            return null;

        }

    }



    private AbaaTbDepartamento departamento(ResultSet rs) {

        try {

            AbaaTbDepartamento ob = new AbaaTbDepartamento();

          //  ob.setDeptoIdPk(rs.getString("Cata_Depa_id_PK"));

            ob.setDeptoNomb(rs.getString("Cata_Depa_nomb"));

            return ob;

        } catch (SQLException ex) {

            return null;

        }

    }



    private SboSicop sicop(ResultSet rs) {

        try {

            SboSicop ob = new SboSicop();

            ob.setSicopId(rs.getInt("Sico_Id_PK"));
//
//            ob.setSicopCodiClas(rs.getString("Sico_Codi_Clas"));
//
//            ob.setSicopCodiInden(rs.getString("Sico_Codi_Iden"));

            ob.setSicopDesc(rs.getString("Sico_Desc"));

            return ob;

        } catch (SQLException ex) {

            return null;

        }



    }

    private SboTbExistencia existencia(ResultSet rs) {

        try {

            SboTbExistencia ob = new SboTbExistencia();

            ob.setSboTbBodega(Bodega(rs));

            ob.setExisCant(rs.getDouble("Exis_Cant"));

            ob.setAbaaTbDepartamento(departamento(rs));

            ob.setSboTbSicop(sicop(rs));

            return ob;

        } catch (SQLException ex) {

            return null;

        }



    }

    public List<SboTbExistencia> listaExistencias(String bodega, String departamento, String articulo) {

        List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();

        try {

            String sql = "select bode.Bode_Desc, dpto.Cata_Depa_nomb, sicop.Sico_Id_PK, sicop.Sico_Desc, exis.Exis_Cant\n"

                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"

                    + "SIBO_TB_Exis exis\n"

                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"

                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"

                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"

                    + "and exis.Exis_Id_Bode_PK="+bodega+"\n"
                    + "and exis.Exist_Depa_PK="+departamento+"\n"
                    + "and exis.Exis_Id_Sico_PK="+articulo+";";
                              
            sql = String.format(sql,bodega,departamento,articulo);

            ResultSet rs = db.executeQuery(sql);

            while (rs.next()) {

                resultado.add(existencia(rs));

            }

        } catch (SQLException ex) {

        }

        return resultado;

    }

public List<SboTbExistencia> listaExistenciasfiltro(String depa){
  List<SboTbExistencia> resultado = new ArrayList<SboTbExistencia>();
        try {
          String sql = "select bode.Bode_Desc, dpto.Cata_Depa_nomb,sicop.Sico_Id_PK, sicop.Sico_Desc, exis.Exis_Cant\n"

                    + "from ABAA_TB_Catalogo_Departamento dpto, SIBO_TB_Bode bode, SIBO_TB_Sicop sicop,\n"

                    + "SIBO_TB_Exis exis\n"

                    + "where exis.Exis_Id_Bode_PK=bode.Bode_Id_PK\n"

                    + "and exis.Exist_Depa_PK=dpto.Cata_Depa_id_PK\n"

                    + "and exis.Exis_Id_Sico_PK=sicop.Sico_Id_PK\n"

                    + "and exis.Exist_Depa_PK="+depa;
            sql = String.format(sql,depa);
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                resultado.add(existencia(rs));
            }
        } catch (SQLException ex) {
        }
        return resultado;
 }

public SboTbExistencia getSboTbExistencia(String departamento,String articulo) throws SQLException, Exception{
  
          String sql = "select Exis_Cant from SIBO_TB_Exis exis\n"

                   + "where exis.Exist_Depa_PK="+departamento+"\n"

                    + "and exis.Exis_Id_Sico_PK="+articulo+";";
            sql = String.format(sql,departamento,articulo);
            ResultSet rs = db.executeQuery(sql);
                 if (rs.next()) {
               return existencia(rs);
        } else {
            throw new Exception("Bien no Existe");
        }
      
 }



}
