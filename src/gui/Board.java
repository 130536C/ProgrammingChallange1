
package gui;

import Tic_Tac_Toe_Game.Player;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
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
    
    public Board(GameFrame game,Player player1, Player player2){
        moves = new String[]{"","","","","","","","",""};
        player = new Player[]{player1,player2};
        win = false; draw = false;
        this.game = game;
        this.setIgnoreRepaint(true);
    }
    
    public void drawBoard(){
        BufferedImage bg = null;
        try {
            bg = ImageIO.read(new File("src/Resources/Background.jpg"));
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
        if (x<100 && y<100){position = 0; boundryX=0; boundryY=0;}
        else if(x<200 && y <100){position = 1; boundryX=100; boundryY=0;}
        else if(x<300 && y <100){position = 2; boundryX=200; boundryY=0;}
        else if(x<100 && y <200){position = 3; boundryX=0; boundryY=100;}
        else if(x<200 && y <200){position = 4; boundryX=100; boundryY=100;}
        else if(x<300 && y <200){position = 5; boundryX=200; boundryY=100;}
        else if(x<100 && y <300){position = 6; boundryX=0; boundryY=200;}
        else if(x<200 && y <300){position = 7; boundryX=100; boundryY=200;}
        else{position = 8; boundryX=200; boundryY=200;}
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!win && !draw){
            setPosition(e.getX(),e.getY());
            if (moves[position].equals("")){
                if (turn%2==0){
                    drawX();
                    game.lblStatus.setText(player[(turn+1)%2].getName()+"'s turn - "+player[(turn+1)%2].getSymbol());
                }
                else{
                    drawO();
                    game.lblStatus.setText(player[(turn+1)%2].getName()+"'s turn - "+player[(turn+1)%2].getSymbol());
                }
                moves[position] = player[turn%2].getSymbol();
                player[turn%2].setAlignment(position, 1);
                if (player[turn%2].isWin()){
                    win = true;
                    game.lblStatus.setText(player[turn%2].getName()+" wins!");
                    game.btnNextRound.setEnabled(true);
                }else if (turn==8){
                    draw = true;
                    game.lblStatus.setText("Game draw!");
                    game.btnNextRound.setEnabled(true);
                }
                turn++;
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
