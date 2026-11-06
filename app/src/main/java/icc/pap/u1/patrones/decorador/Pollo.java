package icc.pap.u1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.u1.patrones.modelo.Pedido;

public class Pollo  extends DecoradorIngrediente {
    public Pollo(Pedido p) {
        super(p);
    }

    @Override
    public String descripcion() {
        return pedido.descripcion() + " + Pollo";
    }

    @Override
    public BigDecimal precio() {
        return pedido.precio().add(new BigDecimal("1.50"));
    }

}
