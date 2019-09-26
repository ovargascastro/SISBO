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
        resources.add(SISBO.Bodega.class);
        resources.add(SISBO.ListaOCxArt.class);
        resources.add(SISBO.catArticulos.class);
        resources.add(SISBO.descCatsArticulo.class);
        resources.add(SISBO.familias.class);
        resources.add(SISBO.listadoOCArtNuevos.class);
        resources.add(SISBO.subFamilias.class);
    }
    
}
