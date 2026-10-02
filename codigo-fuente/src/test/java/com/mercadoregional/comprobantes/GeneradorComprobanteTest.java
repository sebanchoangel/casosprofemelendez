package com.mercadoregional.comprobantes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Replica, sobre el código refactorizado, las mismas pruebas de caracterización
 * ejecutadas sobre el código original (ver GeneradorComprobanteOriginalTest),
 * y agrega la prueba del formato XML incorporado sin modificar esta clase.
 */
class GeneradorComprobanteTest {

    private final GeneradorComprobante generador = new GeneradorComprobante();

    @Test
    void generaComprobantePdfSinLanzarExcepcion() {
        generador.generar("PDF", "Factura 001");
    }

    @Test
    void generaComprobanteHtmlSinLanzarExcepcion() {
        generador.generar("HTML", "Factura 002");
    }

    @Test
    void esInsensibleAMayusculasParaTiposConocidos() {
        generador.generar("pdf", "Factura 003");
        generador.generar("html", "Factura 004");
    }

    @Test
    void tipoNoSoportadoLanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> generador.generar("COBOL", "Factura 005"));
    }

    @Test
    void nuevoFormatoXmlFuncionaSinModificarGeneradorComprobante() {
        // XML se agregó únicamente en ComprobanteFactory; esta clase no cambió.
        generador.generar("XML", "Factura 006");
    }
}
