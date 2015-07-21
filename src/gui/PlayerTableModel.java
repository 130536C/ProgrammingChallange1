
package gui;

import Tic_Tac_Toe_Game.Human;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel{
    
    private final String[] COLUMN_NAMES = new String[]{"Name","Wins","Loses","Ties"};
    ArrayList<Human> players;
    
    public PlayerTableModel(ArrayList<Human> players){
        this.players = players;
    }

    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return players.get(rowIndex).getName();
            case 1:
                return players.get(rowIndex).getWins();
            case 2:
                return players.get(rowIndex).getLoses();
            case 3:
                return players.get(rowIndex).getTies();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return COLUMN_NAMES[column];
    }
    
    @Override
    public void setValueAt(Object anObject, int row, int column){
        switch(column){
            case 0:
                players.get(row).setName(anObject.toString());
            case 1:
                players.get(row).setWins(Integer.parseInt(anObject.toString()));
            case 2:
                players.get(row).setLoses(Integer.parseInt(anObject.toString()));
            case 3:
                players.get(row).setTies(Integer.parseInt(anObject.toString()));
        }
        fireTableCellUpdated(row, column);
    }
    
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    
    public void setPlayers(ArrayList<Human> players){
        this.players = players;
        fireTableStructureChanged();
    }
    
    public ArrayList<Human> getPlayers() {
        return players;
    }
    
}
