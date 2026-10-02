package com.agroconecta.logistica.refactor;

/**
 * Adapter (patrón GoF estructural). Envuelve una instancia de RapidExpressAPI
 * (Adaptee) y la expone bajo el contrato ServicioEnvio (Target) que el resto
 * del sistema entiende, traduciendo:
 *   - origen + destino  -> una única cadena "origen-destino" (route)
 *   - peso en kilogramos -> peso en gramos (weightInGrams)
 *
 * LogisticaService nunca ve RapidExpressAPI directamente; solo conoce esta
 * clase a través de la interfaz ServicioEnvio.
 */
public class RapidExpressAdapter implements ServicioEnvio {

    private final RapidExpressAPI rapidExpress;

    public RapidExpressAdapter(RapidExpressAPI rapidExpress) {
        this.rapidExpress = rapidExpress;
    }

    @Override
    public double calcularCosto(String origen, String destino, double peso) {
        String ruta = origen + "-" + destino;
        int gramos = (int) (peso * 1000);
        return rapidExpress.getShippingPrice(ruta, gramos);
    }
}
