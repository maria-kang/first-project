import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    public static void main(String[] args) {
        // MariaDB JDBC URL - 연결할 데이터베이스의 URL을 입력하세요.
        String jdbcUrl = "jdbc:mysql://192.168.45.183:3306/dsw";
        String username = "dsw0311";
        String password = "1234";

        try {
            // MariaDB 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
                System.out.println("MariaDB에 성공적으로 연결되었습니다.");
                // 여기에서 데이터베이스 작업을 수행할 수 있습니다.
                // 예를 들어, 쿼리 실행 등의 작업을 수행할 수 있습니다.

                // 연결을 닫습니다.
                connection.close();
            } else {
                System.out.println("MariaDB 연결에 실패했습니다.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}