package com.turismocundinamarca.descuentos;

/** Nueva política: promoción regional (12%). Se incorpora igual que DescuentoAniversario. */
public class DescuentoRegional implements PoliticaDescuento {

    private static final double PORCENTAJE = 0.12;

    @Override
    public double calcular(double valorCompra) {
        return valorCompra * PORCENTAJE;
    }
}
