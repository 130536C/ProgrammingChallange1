
package io;

import Tic_Tac_Toe_Game.Human;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHandler {
        
    private String url = "jdbc:derby:Players;create=false";
    private String username = "";
    private String password = "";
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private static DBHandler instance = null;
    private ArrayList<Human> players;
    
    public ArrayList<Human> getPlayers() {
        return players;
    }
        
    private DBHandler(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                con = DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        public static DBHandler getInstance(){
            if (instance == null){
                instance = new DBHandler();
            }
            return instance;
        }
        
        public synchronized void addNewPlayer(Human player){
            try {
                pst = con.prepareStatement("INSERT INTO players VALUES(?,0,0,0)");
                pst.setString(1, player.getName());
                pst.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        public synchronized void updatePlayer(Human player){
            try {
                pst = con.prepareStatement("UPDATE players SET Wins='?',Loses='?',Ties='?' WHERE Name='?'");
                pst.setInt(1, player.getWins());
                pst.setInt(2, player.getWins());
                pst.setInt(3, player.getWins());
                pst.setString(4, player.getName());
                pst.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        public synchronized void deletePlayer(String name){
            try {
                pst = con.prepareStatement("DELETE FROM players WHERE Name='"+name+"'");
                pst.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        public void refresh(){
            try {
                rs = con.createStatement().executeQuery("SELECT * FROM players");
                players = new ArrayList<Human>();
                while (rs.next()){
                    Human human = new Human(rs.getString(1),"");
                    human.setWins(rs.getInt(2));
                    human.setLoses(rs.getInt(3));
                    human.setTies(rs.getInt(4));
                    players.add(human);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
        public boolean isAlreadySaved(String name){
            refresh();
            for (Human player : players){
                if (player.getName().equals(name))
                    return true;
            }
            return false;
        }
        
        public ArrayList<String> getPlayersNames(){
            refresh();
            ArrayList<String> names = new ArrayList<String>();
            for (Human human : players){
                names.add(human.getName());
            }
            return names;
        }
        
        public Human getPlayerByName(String name){
            for (Human human : players)
                if (human.equals(name))
                    return human;
            return null;
        }
        
}
