package logic;

import data.*;
import java.sql.SQLException;
import java.util.List;

public class Model {

//    private FamiliaDAO familiaDAO;
//    private SubFamiliaDAO subFamiliaDAO;
//    private CatArticulosDAO catArticulosDAO;
//
//    private static Model uniqueInstance;
//
//    public static Model instance() {
//        if (uniqueInstance == null) {
//            uniqueInstance = new Model();
//        }
//        return uniqueInstance;
//    }
//
//    private Model() {
//        familiaDAO = new FamiliaDAO();
//        subFamiliaDAO = new SubFamiliaDAO();
//        catArticulosDAO = new CatArticulosDAO();
//    }
//
//    public List<SboTbFamilia> listaFamilias() throws ClassNotFoundException, SQLException {
//        List result = familiaDAO.findAll();
//        return result;
//    }
//
//    public List<SboTbSubFamilia> listaSubFamilias() throws ClassNotFoundException, SQLException {
//        List result = subFamiliaDAO.findAll();
//        return result;
//    }
//
//    public List<SboTbCatArticulo> listaCatArticulos() throws ClassNotFoundException, SQLException {
//        List result = catArticulosDAO.findAll();
//        return result;
//    }
//
//    public SboTbFamilia getSboTbFamilia(String filtro) throws Exception {
//        SboTbFamilia ob = familiaDAO.findById(filtro);
//        return ob;
//    }
//
//    public SboTbCatArticulo getCatArticulo(String filtro) throws Exception {
//        int filtro2 = Integer.parseInt(filtro);
//        SboTbCatArticulo ob = catArticulosDAO.findById(filtro2);
//        return ob;
//    }
//
//    public SboTbSubFamilia getSboTbSubfamilia(String filtro) throws Exception {
//        SboTbSubFamilia ob = subFamiliaDAO.findById(filtro);
//        return ob;
//    }
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

    public List<SboTbFamilia> listaFamilias() throws ClassNotFoundException, SQLException {
        List result = catdao.listaFamilias();
        return result;

    }

    public List<SboTbSubFamilia> listaSubFamilias() throws ClassNotFoundException, SQLException {
        List result = catdao.listaSubFamilias();
        return result;

    }

    public List<SboTbCatArticulo> listaCatArticulos() throws ClassNotFoundException, SQLException {
        List result = catdao.listaCatArticulos();
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
    

}
