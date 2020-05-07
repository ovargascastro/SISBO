/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Osvaldo Vargas
 */
@RequiredArgsConstructor
public enum Reporte {
    REPORTE_CONSUMO("Reporte de Consumo por Departamento","/ReporteConsumo.jasper");
    
    /**
     * Nombre del reporte.
     */
    @Getter
    private final String nombre;
    /**
     * Ruta del reporte.
     */
    @Getter
    private final String ruta;

    @Override
    public String toString() {
        return "Reporte=" + name();
    }
}
