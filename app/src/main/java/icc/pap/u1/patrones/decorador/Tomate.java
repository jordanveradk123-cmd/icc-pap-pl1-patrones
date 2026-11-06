package icc.pap.u1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.u1.patrones.modelo.Pedido;

public class Tomate extends DecoradorIngrediente {
    public Tomate(Pedido p) {
        super(p);
    }

    @Override
    public String descripcion() {
        return pedido.descripcion() + " + Tomate";
    }

    @Override
    public BigDecimal precio() {
        return pedido.precio().add(new BigDecimal("1.10"));
    }

}
