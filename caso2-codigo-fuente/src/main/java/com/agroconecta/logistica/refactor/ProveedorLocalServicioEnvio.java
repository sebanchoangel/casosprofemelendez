package com.agroconecta.logistica.refactor;

/**
 * Proveedor logístico propio de AgroConecta. Al ser código de la propia
 * organización, se implementó directamente contra ServicioEnvio y por lo
 * tanto NO necesita un Adapter: el patrón solo es necesario para envolver
 * interfaces de terceros incompatibles (ver RapidExpressAdapter).
 */
public class ProveedorLocalServicioEnvio implements ServicioEnvio {

    private static final double TARIFA_BASE = 3000.0;
    private static final double TARIFA_POR_KG = 1500.0;

    @Override
    public double calcularCosto(String origen, String destino, double peso) {
        return TARIFA_BASE + (peso * TARIFA_POR_KG);
    }
}
