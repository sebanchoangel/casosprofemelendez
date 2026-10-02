package com.agroconecta.logistica.refactor;

/**
 * Adaptee. Librería del proveedor externo RapidExpress, entregada como
 * dependencia de terceros: NO puede modificarse. Su interfaz (getShippingPrice,
 * en rutas de texto y gramos) es incompatible con ServicioEnvio.
 */
public class RapidExpressAPI {

    public double getShippingPrice(
            String route,
            int weightInGrams) {

        // Simulación de API externa
        return weightInGrams * 0.0025;
    }
}
