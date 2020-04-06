package Reportes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Osvaldo Vargas
 */
@RequiredArgsConstructor
public enum Reporte {
    REPORTE_CONSUMO_DEPARTAMENTO("Reporte de consumo por departamento", "/ReporteConsumoDepartamento.jasper");
    
    @Getter
    private final String nombre;
    
    @Getter
    private final String ruta;
    
    @Override
    public String toString() {
        return "Reporte=" + name();
    }
}
