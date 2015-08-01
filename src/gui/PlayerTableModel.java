
package gui;

import Tic_Tac_Toe_Game.Human;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel{
    
    private final String[] COLUMN_NAMES = new String[]{"Name","Wins","Loses","Ties"};
    ArrayList<Human> completePlayersList;
    ArrayList<Human> selectedPlayersList;
    
    public PlayerTableModel(ArrayList<Human> players){
        this.completePlayersList = players;
        selectedPlayersList = completePlayersList;
    }

    @Override
    public int getRowCount() {
        return selectedPlayersList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return selectedPlayersList.get(rowIndex).getName();
            case 1:
                return selectedPlayersList.get(rowIndex).getWins();
            case 2:
                return selectedPlayersList.get(rowIndex).getLoses();
            case 3:
                return selectedPlayersList.get(rowIndex).getTies();
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return COLUMN_NAMES[column];
    }
    
    @Override
    public void setValueAt(Object anObject, int row, int column){}
    
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    
    public void setPlayers(ArrayList<Human> players){
        this.completePlayersList = players;
        selectedPlayersList = completePlayersList;
        fireTableStructureChanged();
    }
    
    public ArrayList<Human> getPlayers() {
        return selectedPlayersList;
    }
    
    public void setSelectedPlayerList(String key){
        if(key.equals(""))
            selectedPlayersList = completePlayersList;
        else{
            selectedPlayersList = new ArrayList<Human>();
            for (Human human : completePlayersList){
                if (human.getName().toLowerCase().contains(key.toLowerCase()))
                    selectedPlayersList.add(human);
            }
        }
        fireTableStructureChanged();
    }
    
}
