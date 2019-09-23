package logic;

import data.*;
import java.sql.SQLException;
import java.util.List;

public class Model {

    private final catalogosDAO catdao;
    private static Model uniqueInstance;

    public static Model instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    private Model() {
        catdao = new catalogosDAO();

    }

    public List<SboTbFamilia> listaFamilias(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaFamilias(filtro);
        return result;

    }

    public List<SboTbSubFamilia> listaSubFamilias(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaSubFamilias(filtro);
        return result;

    }

    public List<SboTbCatArticulo> listaCatArticulos(String filtro) throws ClassNotFoundException, SQLException {
        List result = catdao.listaCatArticulos(filtro);
        return result;

    }

    public SboTbFamilia getSboTbFamilia(String filtro) throws Exception {
        SboTbFamilia ob = catdao.getSboTbFamilia(filtro);
        return ob;
    }

    public SboTbCatArticulo getCatArticulo(String filtro) throws Exception {
        int filtro2 = Integer.parseInt(filtro);
        SboTbCatArticulo ob = catdao.getCatArticulo(filtro2);
        return ob;

    }

    public SboTbSubFamilia getSboTbSubfamilia(String filtro) throws Exception {

        SboTbSubFamilia ob = catdao.getSboTbSubFamilia(filtro);
        return ob;

    }
<<<<<<< HEAD

    public void actualizarFamilia(SboTbFamilia familia) throws Exception {
=======
    
    public SboTbOrdenCompra getSboTbArtxOrden(String filtro) throws Exception{
     /*   SboTBoc oc = */  
        return null;
    }
    
    public void actualizarFamilia(SboTbFamilia familia) throws Exception{
>>>>>>> bbf1f7e578a21250e7389f0239ac4d9082f53a72
        catdao.actualizarFamilia(familia);
    }

    public void actualizarSubFamilia(SboTbSubFamilia subfamilia) throws Exception {
        catdao.actualizarSubFamilia(subfamilia);
    }

<<<<<<< HEAD
    public void actualizarCatArticulo(SboTbCatArticulo articulo) throws Exception {
=======
      public void actualizarCatArticulo(SboTbCatArticulo articulo) throws Exception{
>>>>>>> bbf1f7e578a21250e7389f0239ac4d9082f53a72
        catdao.actualizarCatArticulo(articulo);
    }

    public void crearFamilia(SboTbFamilia familia) throws Exception {
        catdao.crearFamilia(familia);
<<<<<<< HEAD
    }

    public void crearSubFamilia(SboTbSubFamilia subfamilia) throws Exception {
        catdao.crearSubFamilia(subfamilia);
    }

    public void crearCatArticulo(SboTbCatArticulo articulo) throws Exception {
        catdao.crearCatArticulo(articulo);
    }
=======
             }
      public void crearSubFamilia(SboTbSubFamilia subfamilia) throws Exception{
                   catdao.crearSubFamilia(subfamilia);
             }
      
      public void crearCatArticulo(SboTbCatArticulo articulo) throws Exception{
                   catdao.crearCatArticulo(articulo);
             }

           

>>>>>>> bbf1f7e578a21250e7389f0239ac4d9082f53a72
}
