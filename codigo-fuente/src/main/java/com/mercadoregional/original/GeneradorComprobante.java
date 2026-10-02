package com.mercadoregional.original;

/** Copia literal del código inicial entregado, conservada como línea base para comparación. */
public class GeneradorComprobante {

    public void generar(String tipo, String contenido) {

        if (tipo.equalsIgnoreCase("PDF")) {

            ComprobantePDF comprobante = new ComprobantePDF();
            comprobante.generar(contenido);

        } else if (tipo.equalsIgnoreCase("HTML")) {

            ComprobanteHTML comprobante = new ComprobanteHTML();
            comprobante.generar(contenido);

        } else {
            throw new IllegalArgumentException(
                "Tipo de comprobante no soportado"
            );
        }
    }
}
