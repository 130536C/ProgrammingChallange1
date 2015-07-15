

package test_case_Game;

import java.util.Arrays;

public class Test_Human extends Test_Player{

    
    public void playTurn(int pl, int turn) {
        
        switch(Test_Game.button_Click)
        {
            case 1 : Test_Game.button_state[0][0]= pl;break;
            case 2 : Test_Game.button_state[0][1]= pl;break;
            case 3 : Test_Game.button_state[0][2]= pl;break;
            case 4 : Test_Game.button_state[1][0]= pl;break;
            case 5 : Test_Game.button_state[1][1]= pl;break;
            case 6 : Test_Game.button_state[1][2]= pl;break;
            case 7 : Test_Game.button_state[2][0]= pl;break;
            case 8 : Test_Game.button_state[2][1]= pl;;break;
            case 9 : Test_Game.button_state[2][2]= pl;break;
        }
           
        
        Test_Game.button_Click = 0;  

    }
    
}
