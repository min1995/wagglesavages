import java.net.*;
import java.io.*;
import java.sql.*;

public class Server {

	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;

	public Server (int port) {

		String query = "";

		try
		{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" ) ;

			// Get a connection to the database
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost/waggle?user=root&password=abc1234&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ) ;

			// Print all warnings
			for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
			{
				System.out.println( "SQL Warning:" ) ;
				System.out.println( "State  : " + warn.getSQLState()  ) ;
				System.out.println( "Message: " + warn.getMessage()   ) ;
				System.out.println( "Error  : " + warn.getErrorCode() ) ;
			}

			// Get a statement from the connection
			Statement stmt = conn.createStatement() ;

			// Execute the query
			ResultSet rs = stmt.executeQuery( "SELECT * FROM battery_specs" ) ;


			// Loop through the result set
			while( rs.next() ) {
				System.out.println( "updated_time : " + rs.getString(11) ) ;
			}
			// Close the result set, statement and the connection

			rs.close() ;
			stmt.close() ;
			conn.close() ;

			server = new ServerSocket(port);
			System.out.println("Server started");

			System.out.println("Waiting for a client ...");

			socket = server.accept();
			System.out.println("Client accepted");

			// takes input from the client socket
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			//out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			//Scanner scan = new Scanner(System.in);
			//String cmd = scan.nextLine();

			String line = "";

			//read message from client until "BYE" is sent
			while (!line.equals("BYE")) {
				try {
					line = in.readUTF();
					System.out.println(line);
					if (line.equals("battery")){
						rs.next();
						System.out.println(rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
					}
				}
				catch (IOException e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}
			System.out.println("Closing connection");

			//close connection
			socket.close();
			in.close();

		}
		catch( SQLException se )
		{
			System.out.println( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null )
			{
				System.out.println( "State  : " + se.getSQLState()  ) ;
				System.out.println( "Message: " + se.getMessage()   ) ;
				System.out.println( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			System.out.println( e ) ;
		}

		/*
         try {
         server = new ServerSocket(port);
         System.out.println("Server started");

         System.out.println("Waiting for a client ...");

         socket = server.accept();
         System.out.println("Client accepted");

         // takes input from the client socket
         in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

         String line = "";

         if(line.equals("sql")){
         System.out.println("waggle_id : " + rs.getString(1));
         }{ }
         //read message from client until "BYE" is sent
         while (!line.equals("BYE")) {
         try {
         line = in.readUTF();
         System.out.println(line);
         }
         catch (IOException e) {
         // TODO: handle exception
         System.out.println(e);
         }
         }
         System.out.println("Closing connection");

         //close connection
         socket.close();
         in.close();
         }
         catch (IOException e) {
         // TODO: handle exception
         System.out.println(e);
         }
		 */
	}

	public static void main (String args[]) {
		Server server = new Server(8189);
	}


}
