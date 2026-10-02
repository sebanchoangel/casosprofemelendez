package com.mercadoregional.comprobantes;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Fábrica responsable de crear instancias de {@link Comprobante} a partir
 * de un identificador de formato ("PDF", "HTML", "XML", ...).
 *
 * Implementa el patrón GoF Factory Method mediante un registro dinámico
 * (Map de proveedores). Incorporar un nuevo formato consiste únicamente en
 * llamar a {@link #registrar(String, Supplier)}; no se modifica ni esta
 * clase ni GeneradorComprobante, cumpliendo el Principio Abierto/Cerrado.
 */
public final class ComprobanteFactory {

    private static final Map<String, Supplier<Comprobante>> REGISTRO = new ConcurrentHashMap<>();

    static {
        // Formatos base disponibles al arrancar el sistema.
        registrar("PDF", ComprobantePDF::new);
        registrar("HTML", ComprobanteHTML::new);
        registrar("XML", ComprobanteXML::new);
    }

    private ComprobanteFactory() {
        // Utilidad estática: no se instancia.
    }

    /**
     * Registra (o reemplaza) el creador asociado a un formato.
     * Es el punto de extensión: nuevos formatos —JSON, aliados comerciales,
     * etc.— se agregan aquí, sin tocar el resto del sistema.
     *
     * @param tipo     identificador del formato, no sensible a mayúsculas/minúsculas
     * @param creador  fábrica concreta (referencia a constructor) del comprobante
     */
    public static void registrar(String tipo, Supplier<Comprobante> creador) {
        REGISTRO.put(normalizar(tipo), creador);
    }

    /**
     * Crea el comprobante correspondiente al tipo solicitado.
     *
     * @param tipo identificador del formato solicitado
     * @return una nueva instancia de {@link Comprobante}
     * @throws IllegalArgumentException si el tipo no está registrado
     */
    public static Comprobante crear(String tipo) {
        Supplier<Comprobante> creador = REGISTRO.get(normalizar(tipo));
        if (creador == null) {
            throw new IllegalArgumentException("Tipo de comprobante no soportado: " + tipo);
        }
        return creador.get();
    }

    /**
     * Indica si existe un creador registrado para el tipo dado.
     */
    public static boolean estaSoportado(String tipo) {
        return REGISTRO.containsKey(normalizar(tipo));
    }

    private static String normalizar(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de comprobante no puede ser nulo");
        }
        return tipo.trim().toUpperCase();
    }
}
