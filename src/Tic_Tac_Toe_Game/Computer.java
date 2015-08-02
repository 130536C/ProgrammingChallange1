
package Tic_Tac_Toe_Game;

import java.util.Random;

public class Computer extends Player{
    
    private AI ai;  // contains logic to find the next poasition
    private int depth;
    private String[] board;
    private Player opponent;

    public Computer(String name, String symbol,int depth){
        super(name,symbol);
        ai = new AI();
        this.depth = depth;
    }

    public int play(String[] board,Player opponent,int turn) {
        //int temp = ai.getNext(board, (Player) this, (Player) opponent, turn, depth);
        //System.out.println(temp);
        /* opponent player and positions of player symbols are needed for computer to decide next move */
        this.board = board;
        this.opponent = opponent;
        return play(turn);
    }

    @Override
    public int play(int turn) {
        int next;
        if (turn<3){    // within first 3 play turns randomly return a position
            Random r = new Random();
            next = r.nextInt(9);
            while (!board[next].equals("")){
            next = r.nextInt(9);
            }
        }else{  // after first 3 play turns return postion according to minmax algorithm
            next = ai.getNext(board, this, opponent, turn, depth);
        }
        setAlignment(next, 1);      // update allignment for the move
        return next;
    }
    
}
