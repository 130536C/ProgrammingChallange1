
package io;

import Tic_Tac_Toe_Game.Human;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class DBHandler {
    
    /* This class solely responsible for all the operations with the database */
    
    private String url = "jdbc:derby:Players;create=true";  // url for database
    private String username = "";   // username for database
    private String password = "";   // password for database
    private Connection con = null;  // after connecting to database, connection will be assigned to this
    private PreparedStatement pst = null;   // sql statemnt for preparation
    private ResultSet rs = null;        // this will hold the results from a query
    private static DBHandler instance = null;   // singleton object
    private ArrayList<Human> players;   // to store the players from query
    
    private static Logger logger = Logger.getLogger(DBHandler.class);
    
    public ArrayList<Human> getPlayers() {
        return players;
    }
        
    private DBHandler(){
        /* Constuctor made private according to singleton design pattern. try to create the database */
        try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                con = DriverManager.getConnection(url,username,password);
                pst = con.prepareStatement("CREATE TABLE players(Name varchar(20),Wins int,Loses int, Ties int)");
                pst.execute();
            } catch (ClassNotFoundException ex) {
                logger.error(ex);
            } catch (SQLException ex) {
                logger.info("Database & Table may already created",ex);
            }finally{
                try {
                    if (rs != null) rs.close();
                    if (pst != null)pst.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    logger.error("Cannot close Connection",ex);
                }
            }
        }
        
    private boolean connect(){
        /* Connect to the database. connection is assign to a global variable*/
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");  // embedded database
            con = DriverManager.getConnection(url,username,password);
            return true;
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
            return false;
        } catch (SQLException ex) {
            logger.error(ex);
            return false;
        }
    }
    
    private void disconnect(){
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            logger.error("Cannot close connection",ex);
        }
    }
    
    public static DBHandler getInstance(){
        /* return singleton object */
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
            logger.error(ex);
        }
    }
        
    public synchronized void updatePlayer(Human player) {
        try {
            if (connect()) {
                pst = con.prepareStatement("UPDATE players SET Wins=?,Loses=?,Ties=? WHERE Name='"+player.getName()+"'");
                pst.setInt(1, player.getWins());
                pst.setInt(2, player.getLoses());
                pst.setInt(3, player.getTies());
                pst.executeUpdate();
                disconnect();
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public synchronized void deletePlayer(String name) {
        try {
            if (connect()) {
                if (name.equals("")){   // deleter all players
                    pst = con.prepareStatement("DELETE FROM players");
                    logger.info("Deleted all players");
                }else{      // delete only a specified player
                    pst = con.prepareStatement("DELETE FROM players WHERE Name='" + name + "'");
                }
                pst.executeUpdate();
                disconnect();
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public void refresh() {
        /* Select all data from database and wrap those data in player object  */
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
            logger.error(ex);
        }
    }

    public boolean isAlreadySaved(String name) {
        /* check whether a player exist in the database with given name */
        refresh();      // sync database and player list
        for (Human player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getPlayersNames() {
        /* give a list of all the names of players in database */
        refresh();      // sync
        ArrayList<String> names = new ArrayList<String>();
        for (Human human : players) {
            names.add(human.getName());
        }
        return names;
    }

    public Human getPlayerByName(String name) {
        /* if a player exists with given name return that object */
        for (Human human : players) {
            if (human.getName().equals(name)) {
                return human;
            }
        }
        return null;
    }
        
}
