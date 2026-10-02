package com.turismocundinamarca.descuentos;

/** Nueva política: alianza con cajas de compensación (18%). */
public class DescuentoCajaCompensacion implements PoliticaDescuento {

    private static final double PORCENTAJE = 0.18;

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * PORCENTAJE;
    }
}
