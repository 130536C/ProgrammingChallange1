
package Tic_Tac_Toe_Game;

import java.util.Random;

public class Computer extends Player{
    
    private AI ai;
    private int depth;

    public Computer(String name, String symbol,int depth){
        super(name,symbol);
        ai = new AI();
        this.depth = depth;
    }

    @Override
    public int play(String[] board,Player opponent,int turn) {
        //int temp = ai.getNext(board, (Player) this, (Player) opponent, turn, depth);
        //System.out.println(temp);
        int next;
        if (turn<3){
            Random r = new Random();
            next = r.nextInt(9);
            while (!board[next].equals("")){
            next = r.nextInt(9);
            }
        }else{
            next = ai.getNext(board, (Player) this, (Player) opponent, turn, depth);
        }
        setAlignment(next, 1);
        return next;
    }
    
}
