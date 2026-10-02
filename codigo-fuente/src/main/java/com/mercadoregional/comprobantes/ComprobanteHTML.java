package com.mercadoregional.comprobantes;

public class ComprobanteHTML implements Comprobante {

    @Override
    public void generar(String contenido) {
        System.out.println("Generando comprobante HTML: " + contenido);
    }

    @Override
    public String getFormato() {
        return "HTML";
    }
}
