package com.agroconecta.logistica.original;

/**
 * Versión histórica, previa a la incorporación de RapidExpress: solo existía
 * un proveedor logístico, inyectado correctamente por constructor. Se conserva
 * únicamente como contexto; el código que presenta el problema de diseño a
 * resolver es {@link LogisticaServiceAjustado}.
 */
public class LogisticaService {

    private final ServicioEnvio servicioEnvio;

    public LogisticaService(
            ServicioEnvio servicioEnvio) {

        this.servicioEnvio = servicioEnvio;
    }

    public double cotizar(
            String origen,
            String destino,
            double peso) {

        return servicioEnvio.calcularCosto(
            origen,
            destino,
            peso
        );
    }
}
