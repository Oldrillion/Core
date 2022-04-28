package fr.leleurch.oldrillioncore.player;

import fr.leleurch.oldrillioncore.Main;
import fr.leleurch.oldrillioncore.connectors.SQLConnector;
import fr.leleurch.oldrillioncore.utils.enums.Ranks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerManager {
    public static Connection sqlConnection;

    public static PlayerData loadAccount(UUID playerUUID) {
        PlayerData pd = new PlayerData();

        sqlConnection = SQLConnector.getConnection();
        if(!SQLConnector.isOnline()) Main.instance.sqlConnector.connectToBase();

        try {
            PreparedStatement initialStatement = sqlConnection.prepareStatement("SELECT * FROM `players` WHERE uuid = ?");
            initialStatement.setString(1, playerUUID.toString());
            ResultSet resultSet = initialStatement.executeQuery();
            if(resultSet.next()) {
                Ranks rank = Ranks.valueOf(resultSet.getString("rank"));
                int characterLimit = resultSet.getInt("characterLimit");

                pd.setRank(rank);
                pd.setCharacterLimit(characterLimit);
            } else {
                Ranks rank = Ranks.JOUEUR;
                int characterLimit = 1;
                pd.setRank(rank);
                pd.setCharacterLimit(characterLimit);
            }

            initialStatement.close();
            resultSet.close();
            return pd;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void storeAccount(PlayerData pd) {
        sqlConnection = SQLConnector.getConnection();
        if(!SQLConnector.isOnline()) Main.instance.sqlConnector.connectToBase();

        try {
            PreparedStatement initialStatement = sqlConnection.prepareStatement("SELECT * FROM `players` WHERE uuid = ?");
            initialStatement.setString(1, pd.getUuid().toString());
            ResultSet resultSet = initialStatement.executeQuery();
            if(resultSet.next()) {
                PreparedStatement insertStatement = sqlConnection.prepareStatement("UPDATE `players` SET `rank`=?, `characterLimit`=? WHERE uuid = ?");
                insertStatement.setString(1, pd.getRank().name());
                insertStatement.setInt(2, pd.getCharacterLimit());
                insertStatement.setString(3, pd.getUuid().toString());

                insertStatement.executeUpdate();
                insertStatement.close();
            } else {
                PreparedStatement insertStatement = sqlConnection.prepareStatement("INSERT INTO `players` (`uuid`, `rank`, `characters`, `characterLimit`, `permissions`) VALUES (?, ?, ?, ?, ?)");
                insertStatement.setString(1, pd.getUuid().toString());
                insertStatement.setString(2, pd.getRank().name());
                insertStatement.setString(3, "[]");
                insertStatement.setInt(4, pd.getCharacterLimit());
                insertStatement.setString(5, "[]");

                insertStatement.executeUpdate();
                insertStatement.close();
            }

            initialStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
