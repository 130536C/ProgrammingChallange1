
package Tic_Tac_Toe_Game;

import gui.GameFrame;

public class Human extends Player{

    int wins;
    
    public void playTurn(int pl, int turn) {
        
        switch(GameFrame.button_Click)
        {
            case 1 : GameFrame.buttonState[0][0]= pl;break;
            case 2 : GameFrame.buttonState[0][1]= pl;break;
            case 3 : GameFrame.buttonState[0][2]= pl;break;
            case 4 : GameFrame.buttonState[1][0]= pl;break;
            case 5 : GameFrame.buttonState[1][1]= pl;break;
            case 6 : GameFrame.buttonState[1][2]= pl;break;
            case 7 : GameFrame.buttonState[2][0]= pl;break;
            case 8 : GameFrame.buttonState[2][1]= pl;break;
            case 9 : GameFrame.buttonState[2][2]= pl;break;
        }
           
        
        GameFrame.button_Click = 0;  

    }
    
    public Human(String name, String symbol){
        super(name,symbol);
    }
    
}
