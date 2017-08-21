package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Ecouteur du socket coté serveur.
 */
public class RequestHandler extends Thread{

	private Socket socket;

	/**
	 * Constructeur canonique
	 * @param socket le socket à écouter
	 */
	public RequestHandler( Socket socket )	{
		this.socket = socket;
	}

	@Override
	public void run()	{
		try
		{
			System.out.println( "Received a connection" );

			// Get input and output streams
			BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter( socket.getOutputStream() ) );

			// Echo lines back to the client until the client closes the connection or we receive an empty line
			String line = in.readLine();
			String code;
			String name;


			while( line != null && line.length() > 0 )
			{

				System.out.println(line);

				code = line.split("=")[0];
				name = line.split("=")[1];

				switch (code){

				case "00" :
					String retour = "Valeur retournée = ...";

					out.write(retour);
					out.newLine();
					out.flush();

				    break;
				}
				line = in.readLine();
			}

			// Close our connection
			in.close();
			out.close();
			socket.close();

			System.out.println( "Connection closed" );
		}
		
		catch (EOFException oef){
        	System.out.println("exception dans requestHandler : EOFException");
        	try {
				this.socket.close();
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
        	oef.printStackTrace();
        }
		
		catch (NullPointerException npe){
			System.out.println("exception dans requestHandler : NullPointerException");
			try {
				this.socket.close();
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
			npe.printStackTrace();
		}

		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		finally {
			// Close our connection
			if (socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
