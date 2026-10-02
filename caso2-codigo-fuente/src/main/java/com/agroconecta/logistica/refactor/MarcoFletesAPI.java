package com.agroconecta.logistica.refactor;

/**
 * Adaptee simulado. Representa a uno de los "tres operadores adicionales"
 * previstos en el cambio futuro del caso: una librería externa distinta,
 * con su propia interfaz incompatible (cotiza en pesos por kilo y usa
 * países en vez de origen/destino de texto libre).
 */
public class MarcoFletesAPI {

    public double cotizarPorKilo(String paisOrigen, String paisDestino, double kilos) {
        // Simulación de una segunda API externa de un aliado logístico
        double tarifaKm = 800.0;
        return kilos * tarifaKm;
    }
}
