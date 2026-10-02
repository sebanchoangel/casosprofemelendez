package com.mercadoregional.comprobantes;

/**
 * Formato incorporado DESPUÉS del refactor, como prueba de que el sistema
 * admite nuevos tipos de comprobante sin modificar GeneradorComprobante
 * ni ComprobanteFactory.registrarFormatosBase().
 */
public class ComprobanteXML implements Comprobante {

    @Override
    public void generar(String contenido) {
        System.out.println("Generando comprobante XML: " + contenido);
    }

    @Override
    public String getFormato() {
        return "XML";
    }
}
