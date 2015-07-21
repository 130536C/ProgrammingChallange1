
package gui;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class PlayerListModel extends AbstractListModel{
    
    private ArrayList<String> completeNames;
    private ArrayList<String> selecetedNames;
    
    @Override
    public int getSize() {
        return selecetedNames.size();
    }

    @Override
    public Object getElementAt(int index) {
        return selecetedNames.get(index);
    }
    
    public void setNames(ArrayList<String> names){
        this.completeNames = names;
        selecetedNames = completeNames;
        fireContentsChanged(this, 0, selecetedNames.size());
    }
    
    public void setSelectedNames(String key){
        if(key.equals(""))
            selecetedNames = completeNames;
        else{
            selecetedNames = new ArrayList<String>();
            for (String name : completeNames){
                if (name.toLowerCase().contains(key.toLowerCase()))
                    selecetedNames.add(name);
            }
        }
        fireContentsChanged(this, 0, selecetedNames.size());
    }
    
}
