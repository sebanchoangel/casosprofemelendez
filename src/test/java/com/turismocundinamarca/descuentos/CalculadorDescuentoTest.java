package com.turismocundinamarca.descuentos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Suite de pruebas para CalculadorDescuento.
 *
 * El primer bloque (LineaBaseComportamientoOriginal) reproduce EXACTAMENTE
 * los casos que ya cubría la clase original con if/else, y sirve como
 * evidencia de que la refactorización no alteró el comportamiento
 * funcional (paso 8 de la ruta de aprendizaje).
 *
 * El segundo bloque (NuevoRequisitoDeCambio) valida el nuevo requisito de
 * cambio: incorporar y reemplazar políticas sin modificar el código de
 * CalculadorDescuento (paso 9, medición de impacto).
 */
class CalculadorDescuentoTest {

    private CalculadorDescuento calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadorDescuento();
    }

    @Nested
    @DisplayName("Línea base: comportamiento heredado de la versión original")
    class LineaBaseComportamientoOriginal {

        @Test
        @DisplayName("FRECUENTE aplica 10%")
        void calculaDescuentoFrecuente() {
            assertEquals(10.0, calculadora.calcular("FRECUENTE", 100.0), 0.0001);
        }

        @Test
        @DisplayName("TEMPORADA_BAJA aplica 15%")
        void calculaDescuentoTemporadaBaja() {
            assertEquals(15.0, calculadora.calcular("TEMPORADA_BAJA", 100.0), 0.0001);
        }

        @Test
        @DisplayName("CONVENIO aplica 20%")
        void calculaDescuentoConvenio() {
            assertEquals(20.0, calculadora.calcular("CONVENIO", 100.0), 0.0001);
        }

        @Test
        @DisplayName("Un tipo desconocido no aplica descuento (0.0), igual que el código original")
        void retornaCeroParaTipoDesconocido() {
            assertEquals(0.0, calculadora.calcular("NO_EXISTE", 100.0), 0.0001);
        }

        @Test
        @DisplayName("Un valor de compra de 0 siempre retorna 0 sin importar la política")
        void valorDeCompraCeroRetornaCero() {
            assertEquals(0.0, calculadora.calcular("CONVENIO", 0.0), 0.0001);
        }
    }

    @Nested
    @DisplayName("Nuevo requisito de cambio: políticas que cambian con frecuencia")
    class NuevoRequisitoDeCambio {

        @Test
        @DisplayName("Se puede INCORPORAR una política nueva (aniversario) sin tocar CalculadorDescuento")
        void incorporaNuevaPoliticaSinModificarLaClase() {
            calculadora.registrarPolitica("ANIVERSARIO", new DescuentoAniversario());
            assertEquals(25.0, calculadora.calcular("ANIVERSARIO", 100.0), 0.0001);
        }

        @Test
        @DisplayName("Se puede REEMPLAZAR una política existente en tiempo de ejecución")
        void reemplazaUnaPoliticaExistente() {
            calculadora.registrarPolitica("FRECUENTE", valorCompra -> valorCompra * 0.30);
            assertEquals(30.0, calculadora.calcular("FRECUENTE", 100.0), 0.0001);
        }

        @Test
        @DisplayName("Se puede DESACTIVAR una política; vuelve a comportarse como tipo desconocido")
        void eliminaUnaPoliticaExistente() {
            calculadora.eliminarPolitica("CONVENIO");
            assertEquals(0.0, calculadora.calcular("CONVENIO", 100.0), 0.0001);
        }

        @Test
        @DisplayName("Varias políticas nuevas conviven sin interferir entre sí")
        void multiplesPoliticasNuevasCoexisten() {
            calculadora.registrarPolitica("REGIONAL", new DescuentoRegional());
            calculadora.registrarPolitica("MUNICIPIO", new DescuentoMunicipio());
            calculadora.registrarPolitica("CAJA_COMPENSACION", new DescuentoCajaCompensacion());

            assertEquals(12.0, calculadora.calcular("REGIONAL", 100.0), 0.0001);
            assertEquals(8.0, calculadora.calcular("MUNICIPIO", 100.0), 0.0001);
            assertEquals(18.0, calculadora.calcular("CAJA_COMPENSACION", 100.0), 0.0001);
        }
    }
}
