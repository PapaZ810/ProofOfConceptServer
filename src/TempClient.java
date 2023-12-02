/**
 * The client-side of the date server
 *
 * @author Greg Gagne 
 */

import java.net.*;
import java.io.*;

public class TempClient
{
	// the default port
	public static final int PORT = 8192;
	
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java TempClient <host>");
			System.exit(0);
		}

		BufferedReader fromServer = null;
		BufferedWriter toServer = null;
		Socket server = null;
		
		try {
			// create socket and connect to default port 
			server = new Socket(args[0], PORT);

			toServer = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			toServer.write("GET / HTTP/1.1\r\n\r\n");
			toServer.flush();

			System.out.println(fromServer.readLine());
			System.out.println(fromServer.readLine());
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			// let's close streams and sockets
			// closing the input stream closes the socket as well
			if (fromServer!= null)
				fromServer.close();
			if (toServer != null)
				toServer.close();
			if (server != null)
				server.close();
		}
	}
}
