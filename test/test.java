
import java.util.ArrayList;
import java.util.List;
import logic.*;
import data.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {

    public static void main(String[] args) {
//        OrdenCompraDAO OCDAO = new OrdenCompraDAO();
//        List<SboTbOrdenCompra> lista = new ArrayList<SboTbOrdenCompra>();
//        lista = OCDAO.listaFamilias("");
//        for (SboTbOrdenCompra u : lista) {
//            System.out.println(u.getOcIdPk());
//        }
//        OrdenCompraDAO OCDAO = new OrdenCompraDAO();
//        List<SboTbArticulo> lista = new ArrayList<SboTbArticulo>();
//        lista = OCDAO.listaOrdenesCompra("");
//        lista = OCDAO.listaOCxArt("1");
//        for (SboTbArticulo u : lista) {
//            System.out.println(u.getArtDesc());
//        }
        ArticuloOCDAO artDAO = new ArticuloOCDAO();
        SboTbArticulo articulo = new SboTbArticulo();
        try {
//            articulo = artDAO.datosArticulo("1");
            System.out.println("Id articulo: " + articulo.getArtIdPk()
                    + "/ Id Dpto: " + articulo.getAbaaTbDepartamento().getDeptoIdPk()
                    + "/Id Cat: " + articulo.getSboTbCatArticulo().getCatIdPk());
        } catch (Exception ex) {
        }
        try {
            articulo = artDAO.DescripcionCatsPorArticulo("1");
            System.out.println(articulo.getSboTbCatArticulo().getCatDesc() + ","
                    + articulo.getSboTbCatArticulo().getSboTbSubFamilia().getSubFamiDesc());
        } catch (Exception ex) {
        }
    }
}
