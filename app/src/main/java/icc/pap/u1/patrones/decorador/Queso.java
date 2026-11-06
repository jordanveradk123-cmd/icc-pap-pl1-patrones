package icc.pap.u1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.u1.patrones.modelo.Pedido;

public class Queso extends DecoradorIngrediente {
    public Queso(Pedido p) {
        super(p);
    }

    @Override
    public String descripcion() {
        return pedido.descripcion() + " + Queso";
    }

    @Override
    public BigDecimal precio() {
        return pedido.precio().add(new BigDecimal("1.00"));
    }

}
