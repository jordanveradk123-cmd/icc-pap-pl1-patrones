package icc.pap.u1.patrones.prototype;

import icc.pap.u1.patrones.modelo.TipoProducto;

public class Cafe extends ProductoBase {
    public Cafe(String id) {
        super(id, TipoProducto.CAFE, "Cafe", "2.20");
    }

    @Override
    public ProductoBase copiar(String nuevoId) {
        return new Cafe(nuevoId);
    }

}
