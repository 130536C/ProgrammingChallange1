
package Tic_Tac_Toe_Game;

import java.util.Arrays;


public class Node {
   
    private int movement;
    private int value;
    private Node[] btn_Nodes;
    
    // in there given parametors player = previous state player, level begin with 1, btn counter = movement of press btn
    public Node minMax(int player, int level, int btn[], int btn_counter){
        player = (player == 1)? 2 : 1 ;
        btn_Nodes = new Node[9 - btn_counter]; //nodes only of vacancy buttons
        System.out.println("minmax: player "+player+ " level "+ level+ " btn counter " + btn_counter+ " btn[] "+ Arrays.toString(btn));
        if(win(1,btn)){
            setVariables(player,btn_counter,level);
            return this;
        }else if(win(2,btn)){
            setVariables(player,btn_counter, level);
            return this;
        }

        if(btn.length == 1){
            setVariables(0, btn_counter, level);
            return this;
            //termination condition
        }else{
            Node node;
            int count = 0;
            for( int i = 1; i < btn.length ; i++){
                if(btn[i]== 0){
                    node = new Node(); //create new node for each of the tree
                    btn[i] = player; // set btn as pressed
                    node.minMax(player, level++, btn,btn_counter++); // call minmax for new node
                    btn_Nodes[count].setMovement(node.movement); // store the states of node and set variable values
                    btn_Nodes[count].setValue(node.value);
                    count++;
                    btn[i] = 0; //reset the btn states , becouse we define next loop what if we press another btn
                }
            }
            System.out.println(" btnNodes : "+ Arrays.toString(btn_Nodes));
            chooseValue(btn_Nodes, player);
            
        }
        return this;
    }
   //this methord cheak the array of butten and cheak whether there is win for given player
    public boolean win(int player, int[] btn_index){
        int[] cheak_btn = btn_index;
        //row 1  
        if(((cheak_btn[1] == cheak_btn[2] && cheak_btn[1] == cheak_btn[3]) ||(cheak_btn[1] == cheak_btn[2] && cheak_btn[2] == cheak_btn[3] )) && cheak_btn[1] == player ){
            return true;
        }
        //row 2
        if(((cheak_btn[4] == cheak_btn[5] && cheak_btn[4] == cheak_btn[6])||(cheak_btn[4] == cheak_btn[5] && cheak_btn[6] == cheak_btn[5]) )&& cheak_btn[4] == player ){
            return true;
        }
        //row 3
        if(((cheak_btn[7] == cheak_btn[8] && cheak_btn[7] == cheak_btn[9]) || (cheak_btn[7] == cheak_btn[8] && cheak_btn[8] == cheak_btn[9]) )&& cheak_btn[7] == player ){
            return true;

        }
        //column 1
        if(((cheak_btn[1] == cheak_btn[4] && cheak_btn[1] == cheak_btn[7]) || (cheak_btn[1] == cheak_btn[4] && cheak_btn[4] == cheak_btn[7]) )&& cheak_btn[1] == player ){
            return true;
        }
        //column 2
        if(((cheak_btn[2] == cheak_btn[5] && cheak_btn[2] == cheak_btn[8]) || (cheak_btn[2] == cheak_btn[5] && cheak_btn[5] == cheak_btn[8]))&& cheak_btn[2] == player ){
            return true;
        }
        //column 3
        if(((cheak_btn[3] == cheak_btn[6] && cheak_btn[3] == cheak_btn[9] )||(cheak_btn[3] == cheak_btn[6] &&  cheak_btn[6] == cheak_btn[9] ))&& cheak_btn[3] == player ){
            return true;
        }
        //cross diagonals
        if(((cheak_btn[1] == cheak_btn[5] && cheak_btn[1] == cheak_btn[9] )||(cheak_btn[1] == cheak_btn[5] && cheak_btn[5] == cheak_btn[9]) )&& cheak_btn[1] == player ){
            return true;
        }
        if(((cheak_btn[3] == cheak_btn[5] && cheak_btn[3] == cheak_btn[7] )||(cheak_btn[3] == cheak_btn[5] &&  cheak_btn[5] == cheak_btn[7]) )&& cheak_btn[3] == player ){
            return true;
        }
        return false;
        
    }
    // methord for set variable values with minmax algorithm
    private void setVariables(int player, int btn_count, int level){
        if(player == 1){
            //human player
            this.movement = btn_count;
            this.value = 10 - level;
        }else if(player == 2){
            //computer player
            this.movement = btn_count;
            this.value = level - 10;
        }else{
            this.movement = btn_count;
            this.value = 100;// drow value (special)
        }
       
    }
    //choose from minmax algorithm to best node for return
    private void chooseValue( Node node[], int player){
        Node newNode = new Node();
        if(player == 1){ // if player is human return max value
            newNode.value = -25; // set min value to the new node, get max value
            for(Node T : node){
                System.out.println("set min t.value"+T.value + " newNode. value "+newNode.value);
                if( T.value > newNode.value){
                    newNode = T;
                }
            }
            this.movement = newNode.movement;
            this.value = newNode.value;
            
        }else{ // palyer  = computer and return min value
            newNode.value = 50; // set max value to the new node
            for(Node T : node){
                System.out.println("set max  t.value"+T.value + " newNode. value "+newNode.value);
                if( T.value < newNode.value){
                    newNode = T;
                }
            }
            this.movement = newNode.movement;
            this.value = newNode.value;
            
        }
    }
    //getter - setters
    public void setMovement(int movement) {
        this.movement = movement;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getMovement() {
        return movement;
    }
    public int getValue() {
        return value;
    }

   
   
}
