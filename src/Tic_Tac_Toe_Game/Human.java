

package Tic_Tac_Toe_Game;

import java.util.Arrays;

public class Human extends Player{

    
    public void playTurn(int pl, int turn) {
        
        switch(Game.button_Click)
        {
            case 1 : Game.buttonState[0][0]= pl;break;
            case 2 : Game.buttonState[0][1]= pl;break;
            case 3 : Game.buttonState[0][2]= pl;break;
            case 4 : Game.buttonState[1][0]= pl;break;
            case 5 : Game.buttonState[1][1]= pl;break;
            case 6 : Game.buttonState[1][2]= pl;break;
            case 7 : Game.buttonState[2][0]= pl;break;
            case 8 : Game.buttonState[2][1]= pl;break;
            case 9 : Game.buttonState[2][2]= pl;break;
        }
           
        
        Game.button_Click = 0;  

    }
    
}
