package com.agroconecta.logistica.original;

/**
 * Código ACTUAL entregado para el caso (equivalente a LogisticaServiceAjustado.java
 * del enunciado). Se conserva literal, incluido el defecto real que contiene:
 * proveedorLocal y rapidExpress nunca se inicializan (no existe constructor ni
 * inyección de dependencias), por lo que ambas ramas terminan lanzando
 * NullPointerException en tiempo de ejecución. Este defecto se documenta como
 * evidencia adicional del problema de diseño (ver sección de diagnóstico).
 */
public class LogisticaServiceAjustado {

    private ServicioEnvio proveedorLocal;
    private RapidExpressAPI rapidExpress;

    public double cotizar(
            String proveedor,
            String origen,
            String destino,
            double pesoKg) {

        if (proveedor.equals("LOCAL")) {

            return proveedorLocal.calcularCosto(
                origen,
                destino,
                pesoKg
            );

        }

        if (proveedor.equals("RAPID")) {

            String ruta =
                origen + "-" + destino;

            int gramos =
                (int) (pesoKg * 1000);

            return rapidExpress.getShippingPrice(
                ruta,
                gramos
            );
        }

        throw new IllegalArgumentException();
    }
}
