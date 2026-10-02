package com.mercadoregional.comprobantes;

/**
 * Coordina la generación de comprobantes delegando la creación del objeto
 * concreto a {@link ComprobanteFactory}. No conoce ninguna clase concreta
 * (ComprobantePDF, ComprobanteHTML, ...), por lo que incorporar un nuevo
 * formato no requiere modificar esta clase.
 */
public class GeneradorComprobante {

    public void generar(String tipo, String contenido) {
        Comprobante comprobante = ComprobanteFactory.crear(tipo);
        comprobante.generar(contenido);
    }
}
