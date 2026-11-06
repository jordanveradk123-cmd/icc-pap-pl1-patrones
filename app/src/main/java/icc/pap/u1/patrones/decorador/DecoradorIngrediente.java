package icc.pap.u1.patrones.decorador;

import java.math.BigDecimal;

import icc.pap.u1.patrones.modelo.Pedido;
import icc.pap.u1.patrones.modelo.TipoProducto;

public abstract class DecoradorIngrediente implements Pedido {
    protected Pedido pedido; // referencia al pedido envuelto

    public DecoradorIngrediente(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String id() {
        return pedido.id();
    } // conserva ID

    @Override
    public TipoProducto tipo() {
        return pedido.tipo();
    }

    @Override
    public String descripcion() {
        return pedido.descripcion();
    }

    @Override
    public BigDecimal precio() {
        return pedido.precio();
    }
}