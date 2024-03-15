import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Construction {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getConnection();
		//createConstruction("3","홍길동","");
		ArrayList<String> list = getConstructionList();
		
		for (String item : list) {
			System.out.println(item);
		}
	}
	*/
	
	/**
	 *  현장을 등록하는 매소드
	 * @param coName
	 * @param consName
	 * @param ceo
	 */
	public static void createConstruction (String coName, String consName, String ceo) {
		
		try {
			
			Connection conn = getConnection();
			PreparedStatement insert = conn.prepareStatement("insert into test_table (id, name) value ('"+coName+"','"+consName+"')");
			
			insert.executeUpdate();
		} catch (Exception e)  {
			System.out.println(e.getLocalizedMessage());
		}
	}

	
	public static ArrayList<String> getConstructionList(){
		try {
			
			Connection conn = getConnection();
			PreparedStatement statement = conn.prepareStatement("select id, name   from test_table");
			ResultSet result = statement.executeQuery();
			ArrayList<String> list = new ArrayList<String> ();
			
			while(result.next()) {
				list.add("ID : "+ result.getString("id") + " NAME : " + result.getString("name")  );
			}
			
			return list;
		
		} catch (Exception e)  {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
	
	

	public static Connection getConnection() {
		
		try {
            // MariaDB 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");
            
			// MariaDB JDBC URL - 연결할 데이터베이스의 URL을 입력하세요.
	        String jdbcUrl = "jdbc:mysql://192.168.45.183:3306/dsw";
	        String username = "dsw0311";
	        String password = "1234";
	        
            // 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
            if (connection != null) {
                System.out.println("MariaDB에 성공적으로 연결되었습니다.");
                // 여기에서 데이터베이스 작업을 수행할 수 있습니다.
                // 예를 들어, 쿼리 실행 등의 작업을 수행할 수 있습니다.

                // 연결을 닫습니다.
                
            } else {
                System.out.println("MariaDB 연결에 실패했습니다.");
            }
            
            return connection;
			
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
		
		
	}
	
}

