package com.agroconecta.logistica.original;

public interface ServicioEnvio {

    double calcularCosto(
        String origen,
        String destino,
        double peso
    );

}
