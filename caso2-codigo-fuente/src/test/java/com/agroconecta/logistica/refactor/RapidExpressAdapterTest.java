package com.agroconecta.logistica.refactor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RapidExpressAdapterTest {

    private final ServicioEnvio adapter = new RapidExpressAdapter(new RapidExpressAPI());

    @Test
    void convierteKilogramosAGramosYCalculaElCostoEsperado() {
        // 2.5 kg -> 2500 g; RapidExpressAPI cobra 0.0025 por gramo.
        double costo = adapter.calcularCosto("Fusagasugá", "Bogotá", 2.5);
        assertEquals(2500 * 0.0025, costo, 0.0001);
    }

    @Test
    void exponeElContratoServicioEnvioSinExponerRapidExpressAPI() {
        // El adapter es usable donde se espera un ServicioEnvio, ocultando
        // por completo el tipo RapidExpressAPI al resto del sistema.
        double costo = adapter.calcularCosto("Ibagué", "Cali", 1.0);
        assertEquals(1000 * 0.0025, costo, 0.0001);
    }
}
