package com.turismocundinamarca.descuentos;

/**
 * Abstracción del algoritmo de cálculo de descuento (patrón Strategy).
 *
 * Cada política de descuento (frecuente, temporada baja, convenio,
 * aniversario, regional, municipio, promoción temporal, caja de
 * compensación, etc.) implementa esta interfaz. El componente que
 * procesa la compra (CalculadorDescuento) nunca conoce las reglas
 * concretas: solo invoca calcular(valorCompra).
 *
 * Esto permite que las políticas se activen, reemplacen o incorporen
 * sin modificar el proceso principal de compra, respondiendo a la
 * pregunta orientadora del caso.
 */
public interface PoliticaDescuento {

    double calcular(double valorCompra);
}
