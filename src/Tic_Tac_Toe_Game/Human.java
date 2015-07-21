
package Tic_Tac_Toe_Game;

public class Human extends Player{

    private int wins = 0;
    private int loses = 0;
    private int ties = 0;
    
    @Override
    public int play(String[] board,Player opponent,int pos) {
        setAlignment(pos, 1);
        return 0;
    }
    
    public Human(String name, String symbol){
        super(name,symbol);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }
    
}
