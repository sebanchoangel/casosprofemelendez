package com.agroconecta.logistica.refactor;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogisticaServiceTest {

    private Map<String, ServicioEnvio> proveedoresBase() {
        Map<String, ServicioEnvio> proveedores = new HashMap<>();
        proveedores.put("LOCAL", new ProveedorLocalServicioEnvio());
        proveedores.put("RAPID", new RapidExpressAdapter(new RapidExpressAPI()));
        return proveedores;
    }

    @Test
    void cotizaConElProveedorLocal() {
        LogisticaService servicio = new LogisticaService(proveedoresBase());
        double costo = servicio.cotizar("LOCAL", "Fusagasugá", "Bogotá", 2.0);
        assertEquals(3000.0 + 2.0 * 1500.0, costo, 0.0001);
    }

    @Test
    void cotizaConRapidExpressATravesDelAdapter() {
        LogisticaService servicio = new LogisticaService(proveedoresBase());
        double costo = servicio.cotizar("RAPID", "Fusagasugá", "Bogotá", 2.0);
        assertEquals(2000 * 0.0025, costo, 0.0001);
    }

    @Test
    void proveedorNoRegistradoLanzaIllegalArgumentExceptionConMensaje() {
        LogisticaService servicio = new LogisticaService(proveedoresBase());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> servicio.cotizar("MARCOFLETES", "Fusagasugá", "Bogotá", 2.0));
        assertEquals("Proveedor logístico no soportado: MARCOFLETES", ex.getMessage());
    }

    @Test
    void permiteIncorporarUnOperadorNuevoSinModificarLogisticaService() {
        // Simula la incorporación de uno de los "tres operadores adicionales"
        // previstos: solo se agrega una entrada al mapa inyectado; esta clase
        // no cambia.
        Map<String, ServicioEnvio> proveedores = proveedoresBase();
        proveedores.put("MARCOFLETES", new MarcoFletesAdapter(new MarcoFletesAPI()));

        LogisticaService servicio = new LogisticaService(proveedores);
        double costo = servicio.cotizar("MARCOFLETES", "Colombia", "Perú", 3.0);
        assertEquals(3.0 * 800.0, costo, 0.0001);
    }
}
