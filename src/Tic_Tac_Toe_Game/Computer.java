
package Tic_Tac_Toe_Game;

import java.util.Random;

public class Computer extends Player{
    
    int[][] win_combinations = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    
    public void minmax(){
        int[] btn_index = new int[9];
        int count = 0;
        int check; //value for line 19 purpose
        boolean found = false; // if rule is satisfied, then don't go for other rules
        //put the button states in to one array
        for(int[] row : Game.buttonState){
            for (int i : row) {
                btn_index[count] = i;
                count++;
            }
        }
        // Rule 1: If I have a winning move, take it.
        check = win(2, btn_index);
        if(check < 9){
            //the winning combination, go for it
             playTurn(2, check);
             found = true;
             Game.win = true; // state updated to win
        }
        // Rule 2: If the opponent has a winning move, block it.
        if(!found){
            check = win(1, btn_index);
             if(check < 9){
            //the winning combination, then block it
                playTurn(2, check);
                found = true;
            }
        }
        
        //Rule 3: If I can create a fork (two winning ways) after this move, do it.
        if(!found){
            for(int i = 0; i <9 ; i++){
                if(btn_index[i] == 0){
                    //if there is available btn, check it
                    btn_index[i] = 2;
                    check = win(2, btn_index);
                    if(check < 9){
                            //the winning combination, go for it
                        playTurn(2, check);
                        found = true;
                        break;
                    }
                    btn_index[i] = 0;

                }
            }
        }
            
        //Rule 4: Do not let the opponent creating a fork after my move. (Opponent may block your winning move and create a fork.)
        if(!found){
            for(int i = 0; i <9 ; i++){
                if(btn_index[i] == 0){
                    //if there is available btn, check it
                    btn_index[i] = 1;
                    check = win(1, btn_index);
                    if(check < 9){
                        //the winning combination, block it
                        playTurn(2, check);
                        found = true;
                        break;
                    }
                    btn_index[i] = 0;

                }
            }
        }
        //Rule 5: Place in the position such as I may win in the most number of possible ways.
        if(!found){
            Random random = new Random();
            while(true){
                //choose random number
                int number = random.nextInt(9);
                if(btn_index[number] == 0){
                    //button is available
                    playTurn(2,number);
                    found = true;
                    break;
                }
                
            }
        }
    }
    //this methord check the array of butten and check whether there is win for given player
    public int win(int player, int[] btn_index){
        int[] cheak_btn = btn_index;
        //cheak all the winning combinations with given array and return the if there is winning chance what key to pressed
        for(int[] win : win_combinations){
            if((cheak_btn[win[0]] == cheak_btn[win[1]])&&cheak_btn[win[1]] == player && cheak_btn[win[2]] == 0){
                return win[2]; // if there is winning chance in last element
            }
            if((cheak_btn[win[0]] == cheak_btn[win[2]])&&cheak_btn[win[0]] == player && cheak_btn[win[1]] == 0){
                return win[1]; // there is winning chance in middle element
            }
             if((cheak_btn[win[1]] == cheak_btn[win[2]])&&cheak_btn[win[0]] == player && cheak_btn[win[0]] == 0){
                return win[0]; // if there is winning chance in last element
            }
            
        }
        return 9; // if there is no winning chances to player then return out of range values
        
        
        
    }
  
    public void playTurn(int pl, int move) {
        
        switch(move)
        {
           case 0 : Game.buttonState[0][0]= pl;Game.buttonIndex[1]=pl;break;
            case 1 : Game.buttonState[0][1]= pl;Game.buttonIndex[2]=pl;break;
            case 2 : Game.buttonState[0][2]= pl;Game.buttonIndex[3]=pl;break;
            case 3 : Game.buttonState[1][0]= pl;Game.buttonIndex[4]=pl;break;
            case 4 : Game.buttonState[1][1]= pl;Game.buttonIndex[5]=pl;break;
            case 5 : Game.buttonState[1][2]= pl;Game.buttonIndex[6]=pl;break;
            case 6 : Game.buttonState[2][0]= pl;Game.buttonIndex[7]=pl;break;
            case 7 : Game.buttonState[2][1]= pl;Game.buttonIndex[8]=pl;break;
            case 8 : Game.buttonState[2][2]= pl;Game.buttonIndex[9]=pl;break;
        }
        Game.button_Click = 0;  

    }

    public Computer(String name, String symbol){
        super(name,symbol);
    }
    
}
