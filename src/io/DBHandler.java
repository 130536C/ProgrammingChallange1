
package io;

import Tic_Tac_Toe_Game.Human;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBHandler {
        
    private String url = "jdbc:derby:Players;create=true";
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
                pst = con.prepareStatement("CREATE TABLE players(Name varchar(20),Wins int,Loses int, Ties int)");
                pst.execute();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                try {
                    if (rs != null) rs.close();
                    if (pst != null)pst.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    private boolean connect(){
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(url,username,password);
            return true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void disconnect(){
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static DBHandler getInstance(){
        if (instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }
        
    public synchronized void addNewPlayer(Human player){
        try {
            if (connect()) {
                pst = con.prepareStatement("INSERT INTO players VALUES(?,0,0,0)");
                pst.setString(1, player.getName());
                pst.executeUpdate();
                disconnect();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
    public synchronized void updatePlayer(Human player) {
        try {
            if (connect()) {
                pst = con.prepareStatement("UPDATE players SET Wins='?',Loses='?',Ties='?' WHERE Name='?'");
                pst.setInt(1, player.getWins());
                pst.setInt(2, player.getLoses());
                pst.setInt(3, player.getTies());
                pst.setString(4, player.getName());
                System.out.println(pst.executeUpdate());
                disconnect();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void deletePlayer(String name) {
        try {
            if (connect()) {
                if (name.equals("")){
                    pst = con.prepareStatement("DELETE FROM players");
                }else{
                    pst = con.prepareStatement("DELETE FROM players WHERE Name='" + name + "'");
                }
                pst.executeUpdate();
                disconnect();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void refresh() {
        try {
            if (connect()) {
                rs = con.createStatement().executeQuery("SELECT * FROM players");
                players = new ArrayList<Human>();
                while (rs.next()) {
                    Human human = new Human(rs.getString(1), "");
                    human.setWins(rs.getInt(2));
                    human.setLoses(rs.getInt(3));
                    human.setTies(rs.getInt(4));
                    players.add(human);
                }
                disconnect();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isAlreadySaved(String name) {
        refresh();
        for (Human player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getPlayersNames() {
        refresh();
        ArrayList<String> names = new ArrayList<String>();
        for (Human human : players) {
            names.add(human.getName());
        }
        return names;
    }

    public Human getPlayerByName(String name) {
        for (Human human : players) {
            if (human.getName().equals(name)) {
                return human;
            }
        }
        return null;
    }
        
}
