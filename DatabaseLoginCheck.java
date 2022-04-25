
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
public class DatabaseLoginCheck {

    public static void main(String[] args) {
        //Username modify to accept input etc
        String UN = "Mindy";
        //Password modify to accept input etc
        String PW = "security123";
        //User Profile modify to accept input etc
        String Profile = "manager";
        try {
            //SQL connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csit314", "root", "root");

            Statement statement = connection.createStatement();
            //SQL query stuff
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM user WHERE username ='"
                    +UN+
                    "' AND password = '"
                    +PW+
                    "' AND user_profile = '"
                    +Profile+
                    "'"
            );
            if(rs.next() == false){
                //This means no account found
                System.out.println("No account found");
            }
            else{
                //This means account found
                System.out.println("Success");
            }

        } catch (Exception e) {
            //Catches any SQl query issues
            e.printStackTrace();
        }


    }
}