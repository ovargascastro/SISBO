
import java.util.ArrayList;
import java.util.List;
import logic.*;
import data.*;

public class test {

    public static void main(String[] args) {
        OrdenCompraDAO OCDAO = new OrdenCompraDAO();
        List<SboTbArticulo> lista = new ArrayList<SboTbArticulo>();
        //lista = OCDAO.listaOrdenesCompra("");
        lista = OCDAO.listaOCxArt("");
        for (SboTbArticulo u : lista) {
            System.out.println(u.getArtDesc());
        }

    }
}
