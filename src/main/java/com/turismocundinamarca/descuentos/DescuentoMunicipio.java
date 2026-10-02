package com.turismocundinamarca.descuentos;

/** Nueva política: descuento por municipio (8%). */
public class DescuentoMunicipio implements PoliticaDescuento {

    private static final double PORCENTAJE = 0.08;

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * PORCENTAJE;
    }
}
