
package gui;

import Tic_Tac_Toe_Game.Player;
import io.DBHandler;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Board extends Canvas implements MouseListener{
    
    private int position;
    private int boundryX;
    private int boundryY;
    private String[] moves;
    private Player[] player;
    private int turn = 0;
    private boolean win,draw;
    private GameFrame game;
    private DBHandler dbHandler;
    
    public Board(GameFrame game,Player player1, Player player2,DBHandler dbHandler){
        moves = new String[]{"","","","","","","","",""};
        player = new Player[]{player1,player2};
        win = false; draw = false;
        this.game = game;
        this.dbHandler = dbHandler;
        this.setIgnoreRepaint(true);
    }
    
    public void drawBoard(){
        BufferedImage bg = null;
        try {
            bg = ImageIO.read(getClass().getResourceAsStream("/Resources/background.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Graphics2D g = (Graphics2D) this.getGraphics();
        g.drawImage(bg, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void drawX(){
        for (int i=20;i<=80;i++){
            int j = i;
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.BLUE);
            g.fillOval(i+boundryX, j+boundryY, 5, 5);
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for (int i=80;i>=20;i--){
            int j = 100-i;
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.BLUE);
            g.fillOval(i+boundryX, j+boundryY, 5, 5);
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
    }
    
    public void drawO(){
            Graphics2D g = (Graphics2D) this.getGraphics();
            g.setColor(Color.BLUE);
            g.setStroke(new BasicStroke(3));
            g.drawOval(boundryX+20,boundryY+20 , 60, 60);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
    }
    
    private void setPosition(int x, int y) {
        if (x<100 && y<100){position = 0; }
        else if(x<200 && y <100){position = 1; }
        else if(x<300 && y <100){position = 2; }
        else if(x<100 && y <200){position = 3; }
        else if(x<200 && y <200){position = 4; }
        else if(x<300 && y <200){position = 5; }
        else if(x<100 && y <300){position = 6; }
        else if(x<200 && y <300){position = 7; }
        else{position = 8;}
        setBoundries(position);
    }
    
    public void playComputerFirst(){
        position = player[turn%2].play(moves, player[(turn+1)%2], turn);
        setBoundries(position);
        mouseClicked(null);
    }
    
    private void setBoundries(int position){
        switch(position){
            case 0:
                boundryX=0; boundryY=0; break;
            case 1:
                boundryX=100; boundryY=0; break;
            case 2:
                boundryX=200; boundryY=0; break;
            case 3: 
                boundryX=0; boundryY=100; break;
            case 4:
                boundryX=100; boundryY=100; break;
            case 5:
                boundryX=200; boundryY=100; break;
            case 6:
                boundryX=0; boundryY=200; break;
            case 7:
                boundryX=100; boundryY=200; break;
            case 8: 
                boundryX=200; boundryY=200; break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!win && !draw){
            int currentPlayer = turn%2;
            if (!player[currentPlayer].getName().equals("Computer")){
                setPosition(e.getX(),e.getY());
            }
            if (moves[position].equals("")){
                if (currentPlayer==0){
                    drawX();
                    game.lblStatus.setText(player[1-currentPlayer].getName()+"'s turn - "+player[1-currentPlayer].getSymbol());
                }
                else{
                    drawO();
                    game.lblStatus.setText(player[1-currentPlayer].getName()+"'s turn - "+player[1-currentPlayer].getSymbol());
                }
                moves[position] = player[currentPlayer].getSymbol();
                if (!player[currentPlayer].getName().equals("Computer")){
                    player[currentPlayer].play(moves, player[1-currentPlayer], position);
                }
                if (Player.isWin(player[currentPlayer].getAlignments())){
                    win = true;
                    game.lblStatus.setText(player[currentPlayer].getName()+" wins!");
                    game.btnNextRound.setEnabled(true);
                    return;
                }
                if (turn==8){
                    draw = true;
                    game.lblStatus.setText("Game draw!");
                    game.btnNextRound.setEnabled(true);
                    return;
                }
                turn++;
                if (game.gameMode==1 && player[1-currentPlayer].getName().equals("Computer")){
                    position = player[1-currentPlayer].play(moves, player[currentPlayer], turn);
                    setBoundries(position);
                    mouseClicked(null);
                }
            }
        }
    }
        
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
