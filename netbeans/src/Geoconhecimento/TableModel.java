/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Geoconhecimento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Chalkos
 */
public class TableModel extends AbstractTableModel{
    private String[] columnNames;
    private String[][] data;
    
    public TableModel(String[] columnNames){
        super();
        this.columnNames = new String[2];
        this.columnNames[0] = columnNames[0];
        this.columnNames[1] = columnNames[1];
        
        data = new String[0][0];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    
    public void setDados(String id, HashMap<String,String> propriedades){
        data = new String[1+propriedades.size()][2];
        
        data[0][0] = "ID";
        data[0][1] = id;
        int i=1;
        
        if( propriedades.containsKey("Local") ){
            data[i][0] = "Local";
            data[i][1] = propriedades.get("Local");
            i++;
        }
        
        for(Map.Entry<String, String> pair : propriedades.entrySet()) {
            if(pair.getKey().equals("Local"))
                continue;
            data[i][0] = pair.getKey();
            data[i][1] = pair.getValue();
            i++;
        }
        
        fireTableStructureChanged();
    }
    
    public void setCaminho(ArrayList<String> nomes, ArrayList<Double> distancias){
        data = new String[1+distancias.size()][2];
        
        int i=0;
        Double soma = 0.0;
        
        for(i=0; i<distancias.size(); i++){
            data[i][0] = nomes.get(i) + "->" + nomes.get(i+1);
            data[i][1] = String.format("%.2f",distancias.get(i));
            soma += distancias.get(i);
        }
        
        if( soma != 0 ){
            data[i][0] = "Total";
            data[i][1] = String.format("%.2f",soma);
        }else{
            data[i][0] = "Caminho desconhecido.";
            data[i][1] = "";
        }
        
        fireTableStructureChanged();
    }
    
    public void setEncontrados(HashMap<String,String> id_nome){
        String cabecalhos[] = new String[2];
        cabecalhos[0] = columnNames[0];
        cabecalhos[1] = columnNames[1];
        
        this.columnNames[0] = "ID";
        this.columnNames[1] = "Local";
        
        data = new String[id_nome.size()][2];
        
        int i=0;
        
        for(Map.Entry<String, String> pair : id_nome.entrySet()) {
            data[i][0] = pair.getKey();
            data[i][1] = pair.getValue();
            i++;
        }
        
        fireTableStructureChanged();
        
        columnNames[0] = cabecalhos[0];
        columnNames[1] = cabecalhos[1];
    }
}
