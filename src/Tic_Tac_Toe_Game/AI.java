/* Need to improve the logic */
package Tic_Tac_Toe_Game;

public class AI {
    String[] board;
    Player[] players;
    int turn;
    private int depth;
    private int[] nextMoves;
    
    public AI(){
        players = new Player[2];
    }

    public int minmax(int d, boolean computerPlayer){       // minmax algorithm
        if (d == depth || turn == 9){   
            return getScore();
        }
        int bestValue;
        if (computerPlayer){
            bestValue = -1000;
            for (int i = 0; i < 9;i++){
                if ("".equals(board[i])){
                    update(i,false);    // play the next available move for computer
                    int val = minmax(d+1, false);
                    if (val>=bestValue){bestValue = val;}
                    if (d==0){
                        nextMoves[i] = val;
                    }
                    update(i,true);     // undo the changes from the abouve play turn
                }
            }
            return bestValue;
        }else{
            bestValue = 1000;
            for (int i = 0; i < board.length;i++){
                if ("".equals(board[i])){
                    update(i,false);    // play the next available move for opponent
                    int val = minmax(d+1, true);
                    if (val<=bestValue){bestValue = val;}
                    if (d == 0){
                        nextMoves[i] = val;
                    }
                    update(i,true);     // undo the changes from the abouve play turn
                }
            }
        }
        return bestValue;
    }
    
    private int getScore(){
        /* heuristic function to calculate the score of a state */
        int[] currentPlayerAlignments = players[1-turn%2].getAlignments();
        int[] nextPlayerAlignments = players[turn%2].getAlignments();
        int total = 0;
        for (int i = 0; i<8;i++){
            if (currentPlayerAlignments[i]==3){total += 100;}
            else if (currentPlayerAlignments[i]==2 && nextPlayerAlignments[i]==0){total += 10;}
            else if (currentPlayerAlignments[i]==1 && nextPlayerAlignments[i]==0){total += 1;}
            else if (currentPlayerAlignments[i]==0 && nextPlayerAlignments[i]==1){total += -1;}
            else if (currentPlayerAlignments[i]==0 && nextPlayerAlignments[i]==2){total += -10;}
            else if (nextPlayerAlignments[i]==3){total += -100;}
        }
        return total;
    }
    
    public int getNext(String[] board,Player computer,Player human,int turn,int depth){
        this.board = board;
        players[turn%2] = computer;
        players[(turn+1)%2] = human;
        this.turn = turn;
        this.depth = depth;
        nextMoves = new int[]{-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000,-1000};
        minmax(0,true);
        //System.out.print(nextMoves[0]+" ");
        int next = nextMoves[0],nextIndex = 0;
        for (int i = 1; i<9;i++){
            //System.out.print(nextMoves[i]+" ");
            if (next<=nextMoves[i]){
                next = nextMoves[i];
                nextIndex = i;
            }
        }
        //System.out.println("");
        return nextIndex;
    }
    
    private void update(int pos,boolean undo){
        if(undo){
            board[pos] = ""; turn--;
            players[turn%2].setAlignment(pos, -1);
        }
        else{
            players[turn%2].setAlignment(pos, 1);
            board[pos] = players[turn%2].getSymbol(); turn++;
        }
    }
}
