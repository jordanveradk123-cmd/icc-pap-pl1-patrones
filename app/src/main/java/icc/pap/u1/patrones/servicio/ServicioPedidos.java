package icc.pap.u1.patrones.servicio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import icc.pap.u1.patrones.decorador.Azucar;
import icc.pap.u1.patrones.decorador.Crema;
import icc.pap.u1.patrones.decorador.Pepperoni;
import icc.pap.u1.patrones.decorador.Pollo;
import icc.pap.u1.patrones.decorador.Queso;
import icc.pap.u1.patrones.decorador.Tomate;
import icc.pap.u1.patrones.modelo.Pedido;
import icc.pap.u1.patrones.modelo.TipoProducto;
import icc.pap.u1.patrones.prototype.RegistroPrototipos;

public class ServicioPedidos {
    private final RegistroPrototipos registro = new RegistroPrototipos();
    private final List<Pedido> pedidos = new ArrayList<>();
    private int contador = 0;

    public Pedido clonarProducto(String clave) {
        String nuevoId = String.format("P-%03d", ++contador);
        Pedido clon = registro.clonar(clave, nuevoId);
        if (clon == null) {
            contador--;
            return null;
        }
        pedidos.add(clon);
        return clon;
    }

    public boolean esCompatible(TipoProducto tipo, String ingrediente) {
        return switch (tipo) {
            case CAFE -> ingrediente.equals("Azucar") || ingrediente.equals("Crema");
            case ENSALADA -> ingrediente.equals("Tomate") || ingrediente.equals("Pollo");
            case PIZZA -> ingrediente.equals("Queso") || ingrediente.equals("Pepperoni");
        };
    }

    public boolean agregarIngrediente(String idPedido, String ingrediente) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.id().equals(idPedido)) {
                if (!esCompatible(p.tipo(), ingrediente))
                    return false; // CA07
                Pedido decorado = switch (ingrediente) {
                    case "Azucar" -> new Azucar(p);
                    case "Crema" -> new Crema(p);
                    case "Tomate" -> new Tomate(p);
                    case "Pollo" -> new Pollo(p);
                    case "Queso" -> new Queso(p);
                    case "Pepperoni" -> new Pepperoni(p);
                    default -> null;
                };
                if (decorado == null)
                    return false;
                pedidos.set(i, decorado); // sustituye por su versión decorada
                return true;
            }
        }
        return false; // CA08 sin pedido
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void reiniciar() {
        pedidos.clear();
        contador = 0;
    } // CA11: prototipos no se tocan

    public List<String> ingredientesPara(TipoProducto t) {
        return switch (t) {
            case CAFE -> List.of("Azucar", "Crema");
            case ENSALADA -> List.of("Tomate", "Pollo");
            case PIZZA -> List.of("Queso", "Pepperoni");
        };
    }

    public boolean eliminarPedido(String id) {
        return pedidos.removeIf(p -> p.id().equals(id));
    }

    public BigDecimal totalGeneral() {
        BigDecimal total = BigDecimal.ZERO;
        for (Pedido p : pedidos) {
            total = total.add(p.precio());
        }
        return total;
    }
}