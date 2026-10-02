package com.mercadoregional.comprobantes;

/**
 * Abstracción común para todo comprobante generado por el sistema.
 * Cada formato concreto (PDF, HTML, XML, JSON, aliados comerciales, etc.)
 * implementa este contrato sin que el resto del sistema conozca su tipo real.
 */
public interface Comprobante {

    /**
     * Genera el comprobante a partir del contenido recibido.
     *
     * @param contenido contenido de negocio que debe representarse en el comprobante
     */
    void generar(String contenido);

    /**
     * Identificador del formato (usado únicamente con fines de trazabilidad/registro).
     *
     * @return nombre del formato, por ejemplo "PDF" o "HTML"
     */
    String getFormato();
}
