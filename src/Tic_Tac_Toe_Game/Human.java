
package Tic_Tac_Toe_Game;

public class Human extends Player{

    int wins;
    
    public int play(String[] board,Player opponent,int pos) {
        setAlignment(pos, 1);
        return 0;
    }
    
    public Human(String name, String symbol){
        super(name,symbol);
    }
    
}
