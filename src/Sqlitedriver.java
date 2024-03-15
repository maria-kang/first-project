import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Sqlitedriver {
	
	public void createSQL() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite.test.db");
		Statement stat = conn.createStatement();
		stat.executeUpdate("");
		PreparedStatement prep = conn.prepareStatement("");
		
	}
	
	public static void main(String[] args) {
		
		
	}

}
