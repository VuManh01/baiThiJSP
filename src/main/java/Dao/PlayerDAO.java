package Dao;

import Model.Player;
import Util.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class PlayerDAO {
    public List<Player> getAllPlayers() {
        List<Player> list = new ArrayList<>();
        String sql = "SELECT p.player_id, p.name, p.age, i.name AS index_name, pi.value " +
                "FROM player p " +
                "JOIN indexer i ON p.index_id = i.index_id " +
                "JOIN player_index pi ON p.player_id = pi.player_id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("player_id"));
                p.setName(rs.getString("name"));
                p.setAge(rs.getString("age"));
                p.setIndexName(rs.getString("index_name"));
                p.setValue(rs.getFloat("value"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insertPlayer(Player p) {
        String insertPlayer = "INSERT INTO player (name, full_name, age, index_id) VALUES (?, ?, ?, ?)";
        String insertPlayerIndex = "INSERT INTO player_index (player_id, index_id, value) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(insertPlayer, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getName());
            ps.setString(2, p.getFullName());
            ps.setString(3, p.getAge());
            ps.setInt(4, p.getIndexId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int playerId = rs.getInt(1);

                PreparedStatement ps2 = conn.prepareStatement(insertPlayerIndex);
                ps2.setInt(1, playerId);
                ps2.setInt(2, p.getIndexId());
                ps2.setFloat(3, p.getValue());
                ps2.executeUpdate();
            }

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePlayer(int id) {
        String sql1 = "DELETE FROM player_index WHERE player_id=?";
        String sql2 = "DELETE FROM player WHERE player_id=?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, id);
            ps1.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, id);
            ps2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player getPlayerById(int id) {
        Player p = null;
        String sql = "SELECT p.player_id, p.name, p.full_name, p.age, p.index_id, pi.value " +
                "FROM player p " +
                "JOIN player_index pi ON p.player_id = pi.player_id " +
                "WHERE p.player_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Player();
                p.setId(rs.getInt("player_id"));
                p.setName(rs.getString("name"));
                p.setFullName(rs.getString("full_name"));
                p.setAge(rs.getString("age"));
                p.setIndexId(rs.getInt("index_id"));
                p.setValue(rs.getFloat("value"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public void updatePlayer(Player p) {
        String updatePlayer = "UPDATE player SET name=?, full_name=?, age=?, index_id=? WHERE player_id=?";
        String updatePlayerIndex = "UPDATE player_index SET value=? WHERE player_id=? AND index_id=?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(updatePlayer);
            ps.setString(1, p.getName());
            ps.setString(2, p.getFullName());
            ps.setString(3, p.getAge());
            ps.setInt(4, p.getIndexId());
            ps.setInt(5, p.getId());
            ps.executeUpdate();

            PreparedStatement ps2 = conn.prepareStatement(updatePlayerIndex);
            ps2.setFloat(1, p.getValue());
            ps2.setInt(2, p.getId());
            ps2.setInt(3, p.getIndexId());
            ps2.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Có thể thêm các phương thức update/getById nếu cần
}
