package View.utils;

import javax.swing.*;
import java.awt.*;

@Deprecated
public class ROptionString extends JComboBox<String> {

    public ROptionString(String[] opciones) {
        super(opciones);
        setSelectedIndex(0);
        setOpaque(false);
        setFocusable(true);

        // Escuchar selección
        addActionListener(e -> {
            repaint();
            System.out.println(getSelectedItem());
        });

        // Personalizar cómo se pinta el ítem seleccionado y los ítems de la lista
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setOpaque(false);
                label.setFont(Colors.SubTitles);
                label.setForeground(Color.DARK_GRAY);
                return label;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Fondo redondeado personalizado
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

        g2.dispose();

        paintCurrentValue(g);
    }

    // Este método pinta el ítem seleccionado dentro del combo (cuando está cerrado)
    private void paintCurrentValue(Graphics g) {
        Object selected = getSelectedItem();
        if (selected != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(Colors.SubTitles);
            g2.setColor(Color.BLACK);
            Insets insets = getInsets();
            g2.drawString(selected.toString(), 10 + insets.left, getHeight() / 2 + 5);
            g2.dispose();
        }
    }

    public String getText() {
        return getSelectedItem().toString();
    }
}
