/**
 * Handler class containing the logic for echoing results back
 * to the client. 
 *
 * This conforms to RFC 862 for echo servers.
 *
 * @author Greg Gagne 
 */

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Handler 
{
	
	/**
	 * this method is invoked by a separate thread
	 */
	public void process(Socket client) throws IOException {
		BufferedReader  fromClient = null;
		BufferedWriter toClient = null;

		try {
			/**
			 * get the input and output streams associated with the socket.
			 */
			fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
			toClient = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			String clientInput = fromClient.readLine();
			try {

				toClient.write("HTTP/1.1 200 OK\r\n");
				toClient.write(clientInput + "\r\n");
				toClient.flush();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
		finally {
			// close streams and socket
			if (fromClient != null)
				fromClient.close();
			if (toClient != null)
				toClient.close();
		}
	}
}
