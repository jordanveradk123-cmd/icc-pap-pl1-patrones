package icc.pap.u1.patrones.prototype;

import java.math.BigDecimal;

import icc.pap.u1.patrones.modelo.Pedido;
import icc.pap.u1.patrones.modelo.TipoProducto;

public abstract class ProductoBase implements Pedido, Prototipo<ProductoBase> {
    protected String id;
    protected TipoProducto tipo;
    protected String descripcion;
    protected BigDecimal precioBase;

    public ProductoBase(String id, TipoProducto tipo, String descripcion, String precio) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioBase = new BigDecimal(precio);
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public TipoProducto tipo() {
        return tipo;
    }

    @Override
    public String descripcion() {
        return descripcion;
    }

    @Override
    public BigDecimal precio() {
        return precioBase;
    }
}