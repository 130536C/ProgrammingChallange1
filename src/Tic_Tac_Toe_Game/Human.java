

package Tic_Tac_Toe_Game;

import java.util.Arrays;

public class Human extends Player{

    
    public void playTurn(int pl, int turn) {
        
        switch(Game.button_Click)
        {
            case 1 : Game.button_state[0][0]= pl;break;
            case 2 : Game.button_state[0][1]= pl;break;
            case 3 : Game.button_state[0][2]= pl;break;
            case 4 : Game.button_state[1][0]= pl;break;
            case 5 : Game.button_state[1][1]= pl;break;
            case 6 : Game.button_state[1][2]= pl;break;
            case 7 : Game.button_state[2][0]= pl;break;
            case 8 : Game.button_state[2][1]= pl;;break;
            case 9 : Game.button_state[2][2]= pl;break;
        }
           
        
        Game.button_Click = 0;  

    }
    
}
