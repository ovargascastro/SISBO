
import java.util.ArrayList;
import java.util.List;
import logic.*;
import data.*;

public class test {

    public static void main(String[] args) {
<<<<<<< HEAD
//        OrdenCompraDAO OCDAO = new OrdenCompraDAO();
//        List<SboTbOrdenCompra> lista = new ArrayList<SboTbOrdenCompra>();
//        lista = OCDAO.listaFamilias("");
//        for (SboTbOrdenCompra u : lista) {
//            System.out.println(u.getOcIdPk());
//        }
=======
        OrdenCompraDAO OCDAO = new OrdenCompraDAO();
        List<SboTbArticulo> lista = new ArrayList<SboTbArticulo>();
        //lista = OCDAO.listaOrdenesCompra("");
        lista = OCDAO.listaOCxArt("1");
        for (SboTbArticulo u : lista) {
            System.out.println(u.getArtDesc());
        }
>>>>>>> 1a36aae80b66a7c97e38292fbf6ec92e1616596d

    }
}
