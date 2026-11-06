package icc.pap.u1.patrones.vista;

import icc.pap.u1.patrones.modelo.Pedido;
import icc.pap.u1.patrones.modelo.TipoProducto;
import icc.pap.u1.patrones.servicio.ServicioPedidos;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.math.BigDecimal;

public class VentanaPedidos extends JFrame {

    private final ServicioPedidos servicio = new ServicioPedidos();
    private String tipoSeleccionado = "cafe";

    // COLORES EXACTOS DE TU DISEÑO
    private final Color FONDO = new Color(11, 18, 32);
    private final Color CARD = new Color(26, 36, 54);
    private final Color CARD_BORDE = new Color(45, 60, 85);
    private final Color AZUL = new Color(0, 149, 255);
    private final Color GRIS = new Color(148, 163, 184);
    private final Color GRIS_CLARO = new Color(203, 213, 225);
    private final Color INPUT_BG = new Color(15, 23, 42);

    private final JButton btnCafe = new JButton("Café");
    private final JButton btnPizza = new JButton("Pizza");
    private final JButton btnEnsalada = new JButton("Ensalada");
    private final JComboBox<String> cbIngrediente = new JComboBox<>();

    private final DefaultTableModel modelo = new DefaultTableModel(
            new String[] { "ID", "Producto", "Composición", "Total" }, 0) {
        @Override
        public boolean isCellEditable(int r, int c) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);

    private final JLabel lblId = new JLabel("-");
    private final JLabel lblProducto = new JLabel("-");
    private final JLabel lblIngredientes = new JLabel("-");
    private final JLabel lblTotal = new JLabel("$0.00");
    private final JLabel lblVacio = new JLabel("No hay pedidos creados actualmente.", SwingConstants.CENTER);

    public VentanaPedidos() {
        super("Bienvenido ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);
        getContentPane().setBackground(FONDO);
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(FONDO);
        header.setBorder(BorderFactory.createEmptyBorder(18, 22, 10, 22));

        JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        headerLeft.setBackground(FONDO);
        JLabel icono = new JLabel("🥖");
        icono.setFont(new Font("SansSerif", Font.BOLD, 22));
        icono.setForeground(Color.WHITE);
        icono.setOpaque(true);
        icono.setBackground(AZUL);
        icono.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setBackground(FONDO);
        JLabel t1 = new JLabel("PedidoLab");
        t1.setFont(new Font("SansSerif", Font.BOLD, 20));
        t1.setForeground(Color.WHITE);
        JLabel t2 = new JLabel("Su Cafeteria Espiga dorada");
        t2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        t2.setForeground(GRIS);
        textos.add(t1);
        textos.add(t2);

        headerLeft.add(icono);
        headerLeft.add(textos);
        header.add(headerLeft, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // ===== CENTRO =====
        JPanel centro = new JPanel(new GridLayout(1, 2, 12, 0));
        centro.setBackground(FONDO);
        centro.setBorder(BorderFactory.createEmptyBorder(0, 22, 0, 22));

        // ----- COLUMNA IZQUIERDA -----
        JPanel colIzq = new JPanel();
        colIzq.setLayout(new BoxLayout(colIzq, BoxLayout.Y_AXIS));
        colIzq.setBackground(FONDO);

        // Card 1
        JPanel card1 = crearCard();
        card1.add(tituloCard("Seleccionar producto base"));
        card1.add(Box.createVerticalStrut(10));

        JPanel seg = new JPanel(new GridLayout(1, 3, 6, 0));
        seg.setBackground(CARD);
        seg.add(btnCafe);
        seg.add(btnPizza);
        seg.add(btnEnsalada);
        for (JButton b : new JButton[] { btnCafe, btnPizza, btnEnsalada }) {
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createLineBorder(CARD_BORDE));
            b.setFont(new Font("SansSerif", Font.PLAIN, 13));
        }
        card1.add(seg);
        card1.add(Box.createVerticalStrut(10));

        colIzq.add(card1);
        colIzq.add(Box.createVerticalStrut(12));

        // Card 2
        JPanel card2 = crearCard();
        card2.add(tituloCard("Agregar ingredientes"));
        JLabel sub = new JLabel("Disponibles para el producto seleccionado");
        sub.setForeground(GRIS);
        sub.setFont(new Font("SansSerif", Font.PLAIN, 11));
        card2.add(sub);
        card2.add(Box.createVerticalStrut(10));

        cbIngrediente.setBackground(INPUT_BG);
        cbIngrediente.setForeground(Color.BLACK);
        cbIngrediente.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        card2.add(cbIngrediente);
        card2.add(Box.createVerticalStrut(6));

        JLabel primero = new JLabel("Primero selecciona un producto base");
        primero.setForeground(GRIS);
        primero.setFont(new Font("SansSerif", Font.PLAIN, 11));
        card2.add(primero);
        card2.add(Box.createVerticalStrut(8));

        JButton btnAgregar = botonAzul("+  Agregar ingrediente");
        btnAgregar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        btnAgregar.setBackground(new Color(30, 58, 95));
        card2.add(btnAgregar);
        card2.add(Box.createVerticalStrut(8));

        JPanel filaBtns = new JPanel(new GridLayout(1, 2, 8, 0));
        filaBtns.setBackground(CARD);
        JButton btnCancelar = botonAzul("✕   Cancelar pedido");
        // btnCancelar.setBackground(INPUT_BG);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        JButton btnCrear = botonAzul("✓   Crear pedido");
        filaBtns.add(btnCancelar);
        filaBtns.add(btnCrear);
        card2.add(filaBtns);
        card2.add(Box.createVerticalStrut(14));

        JLabel compatTitulo = new JLabel("Ingredientes compatibles por producto:");
        compatTitulo.setForeground(GRIS_CLARO);
        compatTitulo.setFont(new Font("SansSerif", Font.PLAIN, 11));
        card2.add(compatTitulo);
        JLabel compat = new JLabel(
                "<html>Café: Azúcar, Crema<br>Ensalada: Tomate, Pollo<br>Pizza: Queso, Pepperoni</html>");
        compat.setForeground(GRIS);
        compat.setFont(new Font("SansSerif", Font.PLAIN, 11));
        card2.add(compat);

        colIzq.add(card2);
        centro.add(colIzq);

        // ----- COLUMNA DERECHA -----
        JPanel colDer = new JPanel();
        colDer.setLayout(new BoxLayout(colDer, BoxLayout.Y_AXIS));
        colDer.setBackground(FONDO);

        JPanel cardTabla = crearCard();
        cardTabla.add(tituloCard("Pedidos creados"));
        cardTabla.add(Box.createVerticalStrut(8));

        tabla.setBackground(CARD);
        tabla.setForeground(Color.WHITE);
        tabla.setGridColor(CARD_BORDE);
        tabla.setRowHeight(28);
        tabla.setSelectionBackground(AZUL);
        JTableHeader th = tabla.getTableHeader();
        th.setBackground(INPUT_BG);
        th.setForeground(GRIS);
        th.setFont(new Font("SansSerif", Font.PLAIN, 11));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(0, 130));
        scroll.getViewport().setBackground(CARD);
        scroll.setBorder(BorderFactory.createLineBorder(CARD_BORDE));
        cardTabla.add(scroll);

        lblVacio.setForeground(GRIS);
        lblVacio.setFont(new Font("SansSerif", Font.ITALIC, 12));
        cardTabla.add(Box.createVerticalStrut(6));
        cardTabla.add(lblVacio);

        JLabel hint = new JLabel("✎  Selecciona un pedido de la tabla para personalizarlo.");
        hint.setForeground(GRIS);
        hint.setFont(new Font("SansSerif", Font.PLAIN, 11));
        cardTabla.add(Box.createVerticalStrut(6));
        cardTabla.add(hint);
        colDer.add(cardTabla);
        colDer.add(Box.createVerticalStrut(12));

        JPanel cardDetalle = crearCard();
        cardDetalle.add(tituloCard("Detalle del pedido seleccionado"));
        cardDetalle.add(Box.createVerticalStrut(10));
        cardDetalle.add(filaDetalle("ID de pedido:", lblId));
        cardDetalle.add(Box.createVerticalStrut(6));
        cardDetalle.add(filaDetalle("Producto:", lblProducto));
        cardDetalle.add(Box.createVerticalStrut(6));
        cardDetalle.add(filaDetalle("Ingredientes:", lblIngredientes));
        cardDetalle.add(Box.createVerticalStrut(18));

        JPanel filaTotal = new JPanel(new BorderLayout());
        filaTotal.setBackground(CARD);
        JLabel lt = new JLabel("Total del pedido");
        lt.setForeground(GRIS_CLARO);
        lt.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTotal.setForeground(Color.WHITE);
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 20));
        filaTotal.add(lt, BorderLayout.WEST);
        filaTotal.add(lblTotal, BorderLayout.EAST);
        cardDetalle.add(filaTotal);

        colDer.add(cardDetalle);
        centro.add(colDer);
        add(centro, BorderLayout.CENTER);

        // ===== FOOTER =====
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(FONDO);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 22, 18, 22));
        JButton btnFinalizar = botonAzul("✓   Finalizar pedido");
        btnFinalizar.setPreferredSize(new Dimension(170, 38));
        footer.add(btnFinalizar);
        add(footer, BorderLayout.SOUTH);

        // ===== LOGICA =====
        actualizarBotonesTipo();
        actualizarIngredientes(TipoProducto.CAFE);

        btnCafe.addActionListener(e -> seleccionarTipo("cafe"));
        btnPizza.addActionListener(e -> seleccionarTipo("pizza"));
        btnEnsalada.addActionListener(e -> seleccionarTipo("ensalada"));

        btnCrear.addActionListener(e -> {
            servicio.clonarProducto(tipoSeleccionado);
            refrescar();
            seleccionarUltimo();
        });

        btnAgregar.addActionListener(e -> {
            int f = tabla.getSelectedRow();
            if (f < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un pedido primero");
                return;
            }
            String id = (String) modelo.getValueAt(f, 0);
            boolean ok = servicio.agregarIngrediente(id, (String) cbIngrediente.getSelectedItem());
            if (!ok)
                JOptionPane.showMessageDialog(this, "Ingrediente incompatible - operación rechazada");
            refrescar();
            tabla.setRowSelectionInterval(f, f);
        });

        btnCancelar.addActionListener(e -> {
            int f = tabla.getSelectedRow();
            if (f < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un pedido para cancelar");
                return;
            }
            servicio.eliminarPedido((String) modelo.getValueAt(f, 0));
            refrescar();
            limpiarDetalle();
        });

        btnFinalizar.addActionListener(e -> {
            if (servicio.getPedidos().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay pedidos para finalizar");
                return;
            }
            BigDecimal total = servicio.totalGeneral();
            int r = JOptionPane.showConfirmDialog(this, "Total a pagar: $" + total + "\n¿Finalizar pedido?",
                    "Finalizar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Pedido finalizado. ¡Gracias!");
                servicio.reiniciar();
                refrescar();
                limpiarDetalle();
            }
        });

        tabla.getSelectionModel().addListSelectionListener(e -> mostrarDetalle());
        setVisible(true);
    }

    private void seleccionarTipo(String t) {
        tipoSeleccionado = t;
        actualizarBotonesTipo();
        actualizarIngredientes(TipoProducto.valueOf(t.toUpperCase()));
    }

    private void actualizarBotonesTipo() {
        btnCafe.setBackground(tipoSeleccionado.equals("cafe") ? new Color(45, 60, 85) : INPUT_BG);
        btnPizza.setBackground(tipoSeleccionado.equals("pizza") ? new Color(45, 60, 85) : INPUT_BG);
        btnEnsalada.setBackground(tipoSeleccionado.equals("ensalada") ? new Color(45, 60, 85) : INPUT_BG);
        btnCafe.setForeground(Color.WHITE);
        btnPizza.setForeground(Color.WHITE);
        btnEnsalada.setForeground(Color.WHITE);
    }

    private void actualizarIngredientes(TipoProducto t) {
        cbIngrediente.removeAllItems();
        servicio.ingredientesPara(t).forEach(cbIngrediente::addItem);
    }

    private void refrescar() {
        modelo.setRowCount(0);
        for (Pedido p : servicio.getPedidos()) {
            modelo.addRow(new Object[] { p.id(), p.tipo(), p.descripcion(), "$" + p.precio() });
        }
        lblVacio.setVisible(servicio.getPedidos().isEmpty());
    }

    private void seleccionarUltimo() {
        if (tabla.getRowCount() > 0) {
            tabla.setRowSelectionInterval(tabla.getRowCount() - 1, tabla.getRowCount() - 1);
        }
    }

    private void mostrarDetalle() {
        int f = tabla.getSelectedRow();
        if (f < 0)
            return;
        Pedido p = servicio.getPedidos().get(f);
        lblId.setText(p.id());
        lblProducto.setText(p.tipo().toString());
        lblIngredientes.setText(p.descripcion());
        lblTotal.setText("$" + p.precio());
        actualizarIngredientes(p.tipo());
    }

    private void limpiarDetalle() {
        lblId.setText("-");
        lblProducto.setText("-");
        lblIngredientes.setText("-");
        lblTotal.setText("$0.00");
    }

    private JPanel crearCard() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(CARD);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CARD_BORDE),
                BorderFactory.createEmptyBorder(14, 14, 14, 14)));
        return p;
    }

    private JLabel tituloCard(String t) {
        JLabel l = new JLabel(t);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("SansSerif", Font.BOLD, 13));
        return l;
    }

    private JPanel filaDetalle(String nombre, JLabel valor) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(CARD);
        JLabel n = new JLabel(nombre);
        n.setForeground(GRIS);
        n.setFont(new Font("SansSerif", Font.PLAIN, 12));
        valor.setForeground(Color.WHITE);
        valor.setFont(new Font("SansSerif", Font.PLAIN, 12));
        p.add(n, BorderLayout.WEST);
        p.add(valor, BorderLayout.CENTER);
        return p;
    }

    private JButton botonAzul(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(AZUL);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}