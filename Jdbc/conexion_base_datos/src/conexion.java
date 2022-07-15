import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class conexion {

    public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/java_curso";
    String username = "root";
    String password = "123456789";

    Connection conex = DriverManager.getConnection(url, username, password);


}

}
