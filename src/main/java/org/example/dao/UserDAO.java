package org.example.dao;

import org.example.model.PageResponse;
import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public PageResponse<User> list(Connection conn,
                                   String userName,
                                   int page, int limit) throws SQLException {

        int offset = Math.max(0, (page - 1) * limit);

        int totalItems = countItens(conn, userName);

        int totalPages = (int) Math.ceil((double) totalItems / limit);

        String sql = """
                SELECT id_user,
                       user_name,
                       email
                FROM users
                WHERE user_name LIKE ?
                LIMIT ? OFFSET ?""";
        List<User> lista = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%"+userName+"%");
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    User user = new User();
                    user.setIdUser(rs.getInt("id_user"));
                    user.setUserName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email"));

                    lista.add(user);
                }
            }
        }

        return new PageResponse<>(
                lista,
                page,
                totalPages,
                totalItems
        );
    }

    public int countItens(Connection conn
            , String userName) throws SQLException {

        String sql = """
                SELECT COUNT(*)
                FROM users
                WHERE user_name LIKE ?""";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%"+userName+"%");

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }
}
