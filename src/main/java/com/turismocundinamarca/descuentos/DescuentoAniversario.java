package com.turismocundinamarca.descuentos;

/**
 * Nueva política anunciada por Mercadeo: campaña de aniversario (25%).
 * Clase agregada al sistema SIN modificar CalculadorDescuento ni ninguna
 * otra política existente: evidencia directa de que el diseño queda
 * abierto a extensión y cerrado a modificación (OCP).
 */
public class DescuentoAniversario implements PoliticaDescuento {

    private static final double PORCENTAJE = 0.25;

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * PORCENTAJE;
    }
}
