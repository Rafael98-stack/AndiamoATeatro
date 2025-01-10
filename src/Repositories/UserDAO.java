package Repositories;

import Configurations.JDBC;

import java.sql.Connection;

public class UserDAO {
    Connection connection = JDBC.getConnection();


}
