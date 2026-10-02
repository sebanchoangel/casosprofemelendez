package com.turismocundinamarca.descuentos;

/** Política de descuento para clientes frecuentes: 10% sobre el valor de compra. */
public class DescuentoFrecuente implements PoliticaDescuento {

    private static final double PORCENTAJE = 0.10;

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * PORCENTAJE;
    }
}
