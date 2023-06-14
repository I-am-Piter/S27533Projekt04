package Visuals;

import Logic.ScoreEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;

public class Best10Scores extends JPanel {
    String[] names;
    int[] scores;
    JTable table;
    DefaultTableModel tableModel;
    public Best10Scores(String[] names,int[] scores){
        this.scores = scores;
        this.names = names;
        tableModel = new DefaultTableModel(10,2){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Integer.class;
            }
        };
        for (int i = 0; i < scores.length; i++) {
            tableModel.setValueAt(names[i],i,0);
            tableModel.setValueAt(scores[i],i,1);
        }
        this.table = new JTable(tableModel);
        table.setPreferredSize(new Dimension(900,720));
        table.setRowHeight(72);
        this.add(table);
        this.setPreferredSize(new Dimension(900,720));
    }
}
