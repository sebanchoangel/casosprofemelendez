package com.mercadoregional.comprobantes;

public class ComprobantePDF implements Comprobante {

    @Override
    public void generar(String contenido) {
        System.out.println("Generando comprobante PDF: " + contenido);
    }

    @Override
    public String getFormato() {
        return "PDF";
    }
}
