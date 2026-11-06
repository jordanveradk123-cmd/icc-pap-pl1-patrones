package icc.pap.u1.patrones.prototype;

import icc.pap.u1.patrones.modelo.TipoProducto;

public class Pizza extends ProductoBase {
    public Pizza(String id) {
        super(id, TipoProducto.PIZZA, "Pizza", "5.00");
    }

    @Override
    public ProductoBase copiar(String nuevoId) {
        return new Pizza(nuevoId);
    }

}
