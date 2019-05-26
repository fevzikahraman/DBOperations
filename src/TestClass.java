import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Properties;


public class TestClass {
	static int counter = 0;
    static long startTime = System.nanoTime();
	static Integer threadCount = 100;

	public static void main(String[] args) throws IOException, SQLException {
		Integer numberRange = 1000000;
		Integer increaseAmount = numberRange / threadCount;
		Integer startIndex = 0;
        String url = "jdbc:postgresql://localhost/foo";
       // long startTime = System.nanoTime();
        String url2 = "jdbc:postgresql://localhost/bar";

        Properties props = new Properties();
		props.setProperty("user","fevzi");
        
		Connection conn = DriverManager.getConnection(url,props);
		Connection connbar = DriverManager.getConnection(url2,props);
/*		for(int i = 0; i < threadCount; i++) {
			Runnable r = new RunnableBatch(0 + startIndex, startIndex + increaseAmount - 1, conn);
			new Thread(r).start();
			startIndex = startIndex + increaseAmount;
		}*/
		
		copyDbtoCSV(connbar, conn);
		copyDbFromCSV(connbar, conn);
	}
	private static void copyDbFromCSV(Connection connbar, Connection conn) {
		try {
			PreparedStatement p2 = connbar.prepareStatement("COPY foo FROM '/tmp/table.csv' DELIMITER ',';");
			p2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void copyDbtoCSV(Connection c, Connection c2) {
		// TODO Auto-generated method stub
		try {			
			PreparedStatement p1 = c2.prepareStatement("COPY foo TO '/tmp/table.csv' DELIMITER ',';");
			p1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	synchronized public static  void incrementCounter() {
		counter += 1; 
		if(counter == threadCount) {
			long endTime   = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println(totalTime);
		}
	}

}
