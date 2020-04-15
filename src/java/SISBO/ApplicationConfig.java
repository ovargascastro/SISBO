package SISBO;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(SISBO.AprobacionSolicitud.class);
        resources.add(SISBO.ArituculoXSolicitudTemporal.class);
        resources.add(SISBO.ArticulosTemporales.class);
        resources.add(SISBO.Bodega.class);
        resources.add(SISBO.BodegaListaOC.class);
        resources.add(SISBO.Bodegas.class);
        resources.add(SISBO.Consumo.class);
        resources.add(SISBO.ExistenciaSoliPendiente.class);
        resources.add(SISBO.Existencias.class);
        resources.add(SISBO.ExistenciasTemp.class);
        resources.add(SISBO.ListaOCxArt.class);
        resources.add(SISBO.LogIn.class);
        resources.add(SISBO.SolicitudFuncionario.class);
        resources.add(SISBO.SolicitudJf.class);
        resources.add(SISBO.SolicitudTI.class);
        resources.add(SISBO.SolicitudesPendientes.class);
        resources.add(SISBO.SolxArt.class);
        resources.add(SISBO.artOrdenInner.class);
        resources.add(SISBO.articulos.class);
        resources.add(SISBO.articulosXorden.class);
        resources.add(SISBO.canExistPorArti.class);
        resources.add(SISBO.catArticulos.class);
        resources.add(SISBO.catContable.class);
        resources.add(SISBO.consumo2.class);
        resources.add(SISBO.departamentos.class);
        resources.add(SISBO.descCatsArticulo.class);
        resources.add(SISBO.familias.class);
        resources.add(SISBO.infoArticulo.class);
        resources.add(SISBO.listadoOCArtNuevos.class);
        resources.add(SISBO.ordenesCompCont.class);
        resources.add(SISBO.ordenesCompra.class);
        resources.add(SISBO.proveedores.class);
        resources.add(SISBO.proyectos.class);
        resources.add(SISBO.sicop.class);
        resources.add(SISBO.solicitudXarticulo.class);
        resources.add(SISBO.solicitudesArticulos.class);
        resources.add(SISBO.solicitudesxAprobar.class);
        resources.add(SISBO.subFamilias.class);
    }   
}
