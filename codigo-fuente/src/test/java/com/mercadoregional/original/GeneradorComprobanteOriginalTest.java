package com.mercadoregional.original;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas de caracterización: documentan el comportamiento ACTUAL del código
 * original entregado, antes de cualquier refactor. Sirven como línea base
 * para demostrar que el comportamiento no cambia tras aplicar el patrón.
 */
class GeneradorComprobanteOriginalTest {

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
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> generador.generar("XML", "Factura 005"));
        assertEquals("Tipo de comprobante no soportado", ex.getMessage());
    }

    @Test
    void agregarUnFormatoNuevoExigeModificarEstaClase() {
        // No es una prueba ejecutable: es la evidencia documentada del problema.
        // Hoy, soportar "XML" obliga a editar GeneradorComprobante.generar()
        // agregando una nueva rama "else if", violando el Principio Abierto/Cerrado.
        assertThrows(IllegalArgumentException.class,
                () -> generador.generar("XML", "Factura 006"));
    }
}
