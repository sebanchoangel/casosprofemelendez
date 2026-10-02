package com.agroconecta.logistica.original;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Pruebas de caracterización sobre LogisticaServiceAjustado, el código
 * ACTUAL entregado para el caso. Documentan el comportamiento real
 * observado, que sirve de línea base para el refactor.
 */
class LogisticaServiceAjustadoTest {

    private final LogisticaServiceAjustado servicio = new LogisticaServiceAjustado();

    @Test
    void proveedorLocalLanzaNullPointerPorFaltaDeInyeccion() {
        // proveedorLocal nunca se inicializa: no hay constructor ni setter.
        assertThrows(NullPointerException.class,
                () -> servicio.cotizar("LOCAL", "Fusagasugá", "Bogotá", 2.0));
    }

    @Test
    void proveedorRapidLanzaNullPointerPorFaltaDeInyeccion() {
        // rapidExpress tampoco se inicializa nunca.
        assertThrows(NullPointerException.class,
                () -> servicio.cotizar("RAPID", "Fusagasugá", "Bogotá", 2.0));
    }

    @Test
    void proveedorNoSoportadoLanzaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> servicio.cotizar("MARCOFLETES", "Fusagasugá", "Bogotá", 2.0));
    }

    @Test
    void agregarUnOperadorNuevoExigeModificarEstaClase() {
        // Evidencia documentada del problema: hoy, soportar un tercer operador
        // (p. ej. "MARCOFLETES") exige editar cotizar() agregando un nuevo
        // if, un nuevo campo y su correspondiente conversión de datos,
        // violando el Principio Abierto/Cerrado.
        assertThrows(IllegalArgumentException.class,
                () -> servicio.cotizar("MARCOFLETES", "Fusagasugá", "Bogotá", 2.0));
    }
}
