package Main.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
public class DatabaseConnection {

    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csit314", "root", "hopium314_");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from user");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_id") + "   " + resultSet.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}