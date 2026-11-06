package icc.pap.u1.patrones.prototype;

import java.util.HashMap;
import java.util.Map;

public class RegistroPrototipos {
    private final Map<String, ProductoBase> registro = new HashMap<>();
    public RegistroPrototipos() {
        registro.put("cafe", new Cafe("PROTO-CAFE"));
        registro.put("ensalada", new Ensalada("PROTO-ENSALADA"));
        registro.put("pizza", new Pizza("PROTO-PIZZA"));
    }
    // NUNCA retorna la instancia registrada, siempre una copia nueva
    public ProductoBase clonar(String clave, String nuevoId) {
        ProductoBase proto = registro.get(clave.toLowerCase());
        if (proto == null) return null; // CA09: clave inexistente
        return proto.copiar(nuevoId);
    }
}