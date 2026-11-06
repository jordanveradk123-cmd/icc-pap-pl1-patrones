package icc.pap.u1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.u1.patrones.modelo.Pedido;

public class Azucar extends DecoradorIngrediente {
    public Azucar(Pedido p) {
        super(p);
    }

    @Override
    public String descripcion() {
        return pedido.descripcion() + " + Azucar";
    }

    @Override
    public BigDecimal precio() {
        return pedido.precio().add(new BigDecimal("0.50"));
    }
}