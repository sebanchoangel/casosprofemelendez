package com.mercadoregional.comprobantes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComprobanteFactoryTest {

    @Test
    void creaComprobantePdf() {
        Comprobante comprobante = ComprobanteFactory.crear("PDF");
        assertEquals("PDF", comprobante.getFormato());
    }

    @Test
    void creaComprobanteHtml() {
        Comprobante comprobante = ComprobanteFactory.crear("HTML");
        assertEquals("HTML", comprobante.getFormato());
    }

    @Test
    void esInsensibleAMayusculasYEspacios() {
        Comprobante comprobante = ComprobanteFactory.crear("  pdf ");
        assertEquals("PDF", comprobante.getFormato());
    }

    @Test
    void tipoNoRegistradoLanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ComprobanteFactory.crear("JSON"));
    }

    @Test
    void permiteRegistrarUnFormatoNuevoSinModificarLaFabrica() {
        // Simula la incorporación de un aliado comercial en tiempo de ejecución,
        // sin tocar ComprobanteFactory ni GeneradorComprobante.
        ComprobanteFactory.registrar("JSON", ComprobanteJSONDePrueba::new);

        assertTrue(ComprobanteFactory.estaSoportado("JSON"));
        Comprobante comprobante = ComprobanteFactory.crear("JSON");
        assertEquals("JSON", comprobante.getFormato());
    }

    @Test
    void estaSoportadoDevuelveFalseParaTipoDesconocido() {
        assertFalse(ComprobanteFactory.estaSoportado("COBOL"));
    }

    /** Doble de prueba usado solo para validar el registro dinámico. */
    private static class ComprobanteJSONDePrueba implements Comprobante {
        @Override
        public void generar(String contenido) { /* no-op para la prueba */ }

        @Override
        public String getFormato() {
            return "JSON";
        }
    }
}
