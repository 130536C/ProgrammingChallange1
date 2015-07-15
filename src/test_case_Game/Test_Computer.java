

package test_case_Game;
import java.util.Arrays;
import java.util.Random;

public class Test_Computer extends Test_Player{
    
    int[][] win_combinations = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    
    public void minmax(){
        int[] btn_index = new int[9];
        int count = 0;
        int cheak; //value for line 19 purpose
        boolean found = false; // if rule is satisfied, then don't go for other rules
        //put the button states in to one array
        for(int[] row : Test_Game.button_state){
            for (int i : row) {
                btn_index[count] = i;
                count++;
            }
        }
        System.out.println("line 23 btn index: "+ Arrays.toString(btn_index));
        // Rule 1: If I have a winning move, take it.
        cheak = win(2, btn_index);
        if(cheak < 9){
            //the winning combination, go for it
             playTurn(2, cheak);
             found = true;
             System.out.println("win !!! move: "+ cheak);
             Test_Game.win = true; // state updated to win
        }
        // Rule 2: If the opponent has a winning move, block it.
        if(!found){
            cheak = win(1, btn_index);
             if(cheak < 9){
            //the winning combination, then block it
                playTurn(2, cheak);
                found = true;
                 System.out.println("line 40 / computer");
            }
        }
        
        //Rule 3: If I can create a fork (two winning ways) after this move, do it.
        if(!found){
            for(int i = 0; i <9 ; i++){
                if(btn_index[i] == 0){
                    //if there is available btn, cheak it
                    btn_index[i] = 2;
                    cheak = win(2, btn_index);
                    if(cheak < 9){
                            //the winning combination, go for it
                        playTurn(2, cheak);
                        found = true;
                        System.out.println(" line 55 / computer");
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
                    //if there is available btn, cheak it
                    btn_index[i] = 1;
                    cheak = win(1, btn_index);
                    if(cheak < 9){
                        //the winning combination, block it
                        playTurn(2, cheak);
                        found = true;
                        System.out.println(" line 75 / computer");
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
                    System.out.println(" line 93 / computer");
                    break;
                }
                
            }
        }
    }
    //this methord cheak the array of butten and cheak whether there is win for given player
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
            case 0 : Test_Game.button_state[0][0]= pl;Test_Game.buttonIndex[1]=pl;break;
            case 1 : Test_Game.button_state[0][1]= pl;Test_Game.buttonIndex[2]=pl;break;
            case 2 : Test_Game.button_state[0][2]= pl;Test_Game.buttonIndex[3]=pl;break;
            case 3 : Test_Game.button_state[1][0]= pl;Test_Game.buttonIndex[4]=pl;break;
            case 4 : Test_Game.button_state[1][1]= pl;Test_Game.buttonIndex[5]=pl;break;
            case 5 : Test_Game.button_state[1][2]= pl;Test_Game.buttonIndex[6]=pl;break;
            case 6 : Test_Game.button_state[2][0]= pl;Test_Game.buttonIndex[7]=pl;break;
            case 7 : Test_Game.button_state[2][1]= pl;Test_Game.buttonIndex[8]=pl;break;
            case 8 : Test_Game.button_state[2][2]= pl;Test_Game.buttonIndex[9]=pl;break;
        }
        Test_Game.button_Click = 0;  

    }

    
}
