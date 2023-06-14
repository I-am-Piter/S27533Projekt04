package Visuals;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableRenederer extends JLabel implements TableCellRenderer {
    public TableRenederer() {
        this.setOpaque(true);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int colorCode = (int) value;

        switch (colorCode) {
            case 1:
                setBackground(Color.RED);
                break;
            case 2:
                setBackground(Color.GREEN);
                break;
            case 3:
                setBackground(Color.BLUE);
                break;
            default:
                setBackground(Color.GRAY);
        }

        return this;
    }
}
