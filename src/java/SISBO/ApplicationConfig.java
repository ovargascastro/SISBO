/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SISBO;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author oscar
 */
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
        resources.add(SISBO.ArticulosTemporales.class);
        resources.add(SISBO.artOrdenInner.class);
        resources.add(SISBO.articulos.class);
        resources.add(SISBO.articulosXorden.class);
        resources.add(SISBO.catArticulos.class);
        resources.add(SISBO.catContable.class);
        resources.add(SISBO.departamentos.class);
        resources.add(SISBO.familias.class);
        resources.add(SISBO.ordenesCompCont.class);
        resources.add(SISBO.ordenesCompra.class);
        resources.add(SISBO.proveedores.class);
        resources.add(SISBO.proyectos.class);
        resources.add(SISBO.subFamilias.class);
    }
    
}
