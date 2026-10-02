package com.turismocundinamarca.descuentos;

/** Política de descuento por temporada baja: 15% sobre el valor de compra. */
public class DescuentoTemporadaBaja implements PoliticaDescuento {

    private static final double PORCENTAJE = 0.15;

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * PORCENTAJE;
    }
}
