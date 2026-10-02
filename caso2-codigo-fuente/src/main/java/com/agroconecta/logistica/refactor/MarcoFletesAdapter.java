package com.agroconecta.logistica.refactor;

/**
 * Adapter agregado DESPUÉS del refactor, como evidencia de que un nuevo
 * operador se incorpora sin modificar LogisticaService, ServicioEnvio,
 * RapidExpressAdapter ni ProveedorLocalServicioEnvio.
 */
public class MarcoFletesAdapter implements ServicioEnvio {

    private final MarcoFletesAPI marcoFletes;

    public MarcoFletesAdapter(MarcoFletesAPI marcoFletes) {
        this.marcoFletes = marcoFletes;
    }

    @Override
    public double calcularCosto(String origen, String destino, double peso) {
        return marcoFletes.cotizarPorKilo(origen, destino, peso);
    }
}
