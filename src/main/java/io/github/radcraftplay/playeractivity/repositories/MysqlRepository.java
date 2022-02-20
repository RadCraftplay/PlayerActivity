package io.github.radcraftplay.playeractivity.repositories;

import io.github.radcraftplay.playeractivity.PlayerConnectionInfo;
import io.github.radcraftplay.playeractivity.repositories.mysql.MysqlSettings;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;

public class MysqlRepository implements Repository<String, PlayerConnectionInfo> {

    private final Statement statement;

    public MysqlRepository(MysqlSettings settings) throws SQLException {
        String connectionUrl =
                "jdbc:mysql://" + settings.getServerAddress() + "/" + settings.getDatabaseName();

        Connection connection = DriverManager.getConnection(connectionUrl, settings.getUsername(), settings.getPassword());
        this.statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate(MysqlQueries.getCreateTableQuery());
    }

    @Override
    public Collection<PlayerConnectionInfo> getAll() {
        ArrayList<PlayerConnectionInfo> infos = new ArrayList<>();

        try {
            ResultSet set = statement.executeQuery(MysqlQueries.getAllPlayersQuery());

            while (set.next()) {
                String name = set.getString(1);
                boolean connected = set.getBoolean(2);
                Timestamp timestamp = set.getTimestamp(3);

                infos.add(new PlayerConnectionInfo(name, connected, timestamp
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime()));
            }
        } catch (SQLException ignored) {

        }

        return infos;
    }

    @Override
    public PlayerConnectionInfo get(String id) {
        try {
            ResultSet set = statement.executeQuery(MysqlQueries.getPlayerByNameQuery(id));
            if (!set.next())
                return null;

            String name = set.getString(1);
            boolean connected = set.getBoolean(2);
            Timestamp timestamp = set.getTimestamp(3);

            return new PlayerConnectionInfo(name, connected, timestamp
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
        } catch (SQLException ignored) {
            return null;
        }
    }


    @Override
    public String add(PlayerConnectionInfo info) {
        try {
            if (statement.execute(MysqlQueries.getAddPlayerConnectionInfoUpdate(info)))
                return info.getName();
            else
                return null;
        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    public PlayerConnectionInfo update(String id, PlayerConnectionInfo info) {
        try {
            if (statement.execute(MysqlQueries.getUpdatePlayerConnectionInfoUpdate(id, info)))
                return info;
            else
                return null;
        } catch (SQLException ignored) {
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            return statement.execute(MysqlQueries.getRemovePlayerConnectionInfoUpdate(id));
        } catch (SQLException ignored) {
            return false;
        }
    }

    private static class MysqlQueries
    {
        public static String getCreateTableQuery() {
            return "CREATE TABLE IF NOT EXISTS player_connection_info (\n" +
            "    username varchar(16),\n" +
            "    connected boolean NOT NULL,\n" +
            "    lastDisconnected datetime,\n" +
            "    CONSTRAINT\n" +
            "    \tPK_player_connection_info\n" +
            "    \tPRIMARY KEY (username)\n" +
            ");";
        }

        public static String getAllPlayersQuery() {
            return "SELECT * FROM player_connection_info;";
        }

        public static String getPlayerExistsQuery(String username) {
            return String
                    .format("SELECT (EXISTS (SELECT username FROM player_connection_info WHERE username = \"%s\"));"
                            , username);
        }

        public static String getPlayerByNameQuery(String username) {
            return String
                    .format("SELECT * FROM player_connection_info WHERE username = \"%s\";"
                            , username);
        }

        public static String getAddPlayerConnectionInfoUpdate(PlayerConnectionInfo info) {
            return String.format("INSERT INTO player_connection_info VALUE (\"%s\", %b, \"%s\");",
                    info.getName(),
                    info.isConnected(),
                    Timestamp.valueOf(info.getLastDisconnected()));
        }

        public static String getRemovePlayerConnectionInfoUpdate(String username) {
            return String.format("DELETE FROM player_connection_info WHERE username = \"%s\";", username);
        }

        public static String getUpdatePlayerConnectionInfoUpdate(String id, PlayerConnectionInfo info) {
            return String.format("UPDATE player_connection_info\n" +
                    "SET username = \"%s\",\n" +
                    "connected = %b,\n" +
                    "lastDisconnected = \"%s\"\n" +
                    "WHERE username = \"%s\";",
                    info.getName(),
                    info.isConnected(),
                    Timestamp.valueOf(info.getLastDisconnected()),
                    id);
        }
    }
}
