import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

class RunnableBatch implements Runnable {

    int startIndex;
    int endIndex;
    Connection co;

    public RunnableBatch(int startIndex, int endIndex, Connection conn) {
		// TODO Auto-generated constructor stub
    	this.startIndex = startIndex;
    	this.endIndex = endIndex;
    	this.co = conn;
	}

	@Override
    public void run() {
        int[] rs;
        try {
			PreparedStatement p = co.prepareStatement("INSERT into foo(a,b,c)  VALUES (?,?,?)");
			while(startIndex <= endIndex) {
				p.setInt(1, startIndex);
				p.setInt(2, startIndex % 3);
				p.setInt(3, startIndex % 5);
		        p.addBatch();
		        startIndex++;
			}
	        rs = p.executeBatch();
	        TestClass.incrementCounter();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }   
}