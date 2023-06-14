package Visuals;

import Controller.GetData;
import Events.DataChangedEvent;
import Events.DataChangedListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GamePanel extends JPanel implements DataChangedListener {
    static GetData getDataint;
    DefaultTableModel tableModel;
    int[][] data;
    JTable table;
    public GamePanel() {
        data = getDataint.getTable();
        tableModel = new DefaultTableModel(data.length, data[0].length) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Integer.class;
            }
        };

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                tableModel.setValueAt(data[row][col], row, col);
            }
        }
        table = new JTable(tableModel);
        int cellSize = 35;
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int col = 0; col < data[0].length; col++) {
            table.getColumnModel().getColumn(col).setPreferredWidth(cellSize);
        }
        table.setRowHeight(cellSize);
        this.add(table);
        table.setDefaultRenderer(Integer.class, new TableRenederer());

        this.setVisible(true);
    }

    @Override
    public void dataChanged(DataChangedEvent dce) {
        data = dce.getData();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                tableModel.setValueAt(data[row][col], row, col);
            }
        }
        tableModel.fireTableDataChanged();
    }
    public static void setDataInterface(GetData getData){
        getDataint = getData;
    }

}
