package com.agroconecta.logistica.refactor;

/**
 * Interfaz Target del patrón Adapter: contrato único que LogisticaService
 * conoce y utiliza, sin importar el proveedor logístico real detrás de él.
 */
public interface ServicioEnvio {

    double calcularCosto(
        String origen,
        String destino,
        double peso
    );
}
