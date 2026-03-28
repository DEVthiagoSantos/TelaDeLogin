package org.example.service;

import org.example.dao.UserDAO;
import org.example.database.ConnectionFactory;
import org.example.exceptions.DatabaseException;
import org.example.exceptions.InvalidEmailException;
import org.example.exceptions.InvalidUserException;
import org.example.model.PageResponse;
import org.example.model.User;

import java.sql.Connection;

public class UserService {

    private static UserDAO userDAO = new UserDAO();

    public void createUser(String userName,
                           String email) throws
            InvalidEmailException, InvalidUserException
            , DatabaseException{

        Connection conn = ConnectionFactory.connection();

        userName = clearSpaces(userName);
        email = clearSpaces(email);

        verification(userName, email);

        userDAO.createUser(conn, userName, email);

    }

    public PageResponse<User> list(String userName,
                                   int page, int limit) throws DatabaseException {

        Connection conn = ConnectionFactory.connection();

        return userDAO.list(conn, userName, page, limit);
    }

    public static String clearSpaces(String ex) {

        return ex.trim();
    }

    public static void verification(String userName,
                                    String email) throws InvalidEmailException,
            InvalidUserException {

        if (!userName.matches("^[a-zA-ZÀ-ÿ ]+$")) {
            throw new InvalidUserException("Username is invalid.");
        } else if (email.isBlank()) {
            throw new InvalidEmailException("Email addresses cannot be empty.");
        } else if (!email.matches("^[^@\\s]+@[a-zA-Z0-9-]+\\.com$")) {
            throw new InvalidEmailException("Invalid email");
        }

    }
}
