package com.agroconecta.logistica.refactor;

import java.util.Map;

/**
 * Cliente del patrón Adapter. Ya no conoce RapidExpressAPI ni ningún otro
 * proveedor concreto: recibe, inyectado por constructor, un registro
 * (Map) de proveedores ya adaptados a ServicioEnvio, indexado por código
 * de proveedor. Seleccionar el proveedor es una simple búsqueda en el mapa,
 * no una cadena de condicionales; incorporar un operador nuevo consiste en
 * agregar una entrada al mapa que se inyecta, sin modificar esta clase.
 */
public class LogisticaService {

    private final Map<String, ServicioEnvio> proveedores;

    public LogisticaService(Map<String, ServicioEnvio> proveedores) {
        this.proveedores = proveedores;
    }

    public double cotizar(String proveedor, String origen, String destino, double pesoKg) {
        ServicioEnvio servicio = proveedores.get(proveedor);
        if (servicio == null) {
            throw new IllegalArgumentException("Proveedor logístico no soportado: " + proveedor);
        }
        return servicio.calcularCosto(origen, destino, pesoKg);
    }
}
