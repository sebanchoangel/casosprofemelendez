package com.turismocundinamarca.descuentos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Componente que procesa una compra y aplica la política de descuento
 * correspondiente (Contexto del patrón Strategy).
 *
 * Mantiene el mismo contrato público que la versión original
 * (calcular(String tipo, double valorCompra)) para no romper a quienes
 * ya lo consumen, pero internamente delega el cálculo en un objeto
 * PoliticaDescuento resuelto a partir de un registro (Map).
 *
 * Las políticas se pueden activar, reemplazar o incorporar en tiempo
 * de ejecución mediante registrarPolitica(...), sin tocar esta clase
 * ni recompilar el proceso principal de compra.
 */
public class CalculadorDescuento {

    private static final PoliticaDescuento SIN_DESCUENTO = valorCompra -> 0.0;

    private final Map<String, PoliticaDescuento> politicas = new HashMap<>();

    public CalculadorDescuento() {
        // Catálogo inicial: equivalente exacto al comportamiento original.
        registrarPolitica("FRECUENTE", new DescuentoFrecuente());
        registrarPolitica("TEMPORADA_BAJA", new DescuentoTemporadaBaja());
        registrarPolitica("CONVENIO", new DescuentoConvenio());
    }

    /**
     * Activa, reemplaza o incorpora una política de descuento identificada por
     * su tipo. Si el tipo ya existía, la nueva política sustituye a la anterior.
     */
    public void registrarPolitica(String tipo, PoliticaDescuento politica) {
        Objects.requireNonNull(tipo, "El tipo de descuento no puede ser nulo");
        Objects.requireNonNull(politica, "La política de descuento no puede ser nula");
        politicas.put(tipo, politica);
    }

    /** Elimina una política previamente registrada (deja de estar disponible). */
    public void eliminarPolitica(String tipo) {
        politicas.remove(tipo);
    }

    /**
     * Calcula el descuento aplicable. Si el tipo no está registrado se
     * conserva el comportamiento original: no se aplica descuento (0.0).
     */
    public double calcular(String tipo, double valorCompra) {
        PoliticaDescuento politica = politicas.getOrDefault(tipo, SIN_DESCUENTO);
        return politica.calcular(valorCompra);
    }
}
