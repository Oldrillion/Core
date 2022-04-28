package fr.leleurch.oldrillioncore.connectors;

import fr.leleurch.oldrillioncore.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {

    private String address;
    private String database;
    private String username;
    private Integer port;
    private String password;
    private String url;
    private static Connection connection;

    public SQLConnector(){

        this.url = Main.instance.getConfig().getString("MySQL.url");
        this.address = Main.instance.getConfig().getString("MySQL.address");
        this.database = Main.instance.getConfig().getString("MySQL.default_database");
        this.username = Main.instance.getConfig().getString("MySQL.user");
        this.port = Main.instance.getConfig().getInt("MySQL.port");
        this.password = Main.instance.getConfig().getString("MySQL.password");

    }

    public static Connection getConnection() {
        return connection;
    }

    public String getAddress() {
        return address;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public void connectToBase(){
        if(!isOnline()){
            try {
                connection = DriverManager.getConnection(this.url + this.address + ":" + this.port + "/" + this.database, this.username, this.password);
                System.out.println("> Class:" + this + " I: La base de donnée s'est connectée.");
                return;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void disconnectBase(){
        if(isOnline()){
            try {
                connection.close();
                System.out.println("> Class:" + this + " I: La base de donnée s'est déconnectée.");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean isOnline(){
        try {
            if((connection == null) || connection.isClosed()){
                return false;
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}