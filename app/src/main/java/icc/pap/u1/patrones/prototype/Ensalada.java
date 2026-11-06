package icc.pap.u1.patrones.prototype;

import icc.pap.u1.patrones.modelo.TipoProducto;

public class Ensalada extends ProductoBase {
    public Ensalada(String id) {
        super(id, TipoProducto.ENSALADA, "Ensalada", "3.00");
    }

    @Override
    public ProductoBase copiar(String nuevoId) {
        return new Ensalada(nuevoId);
    }

}
