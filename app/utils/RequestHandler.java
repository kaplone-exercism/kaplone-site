package server;

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

import org.jongo.MongoCursor;

import com.mongodb.MongoTimeoutException;

/**
 * Ecouteur du socket coté serveur.
 */
class RequestHandler extends Thread{

	private Socket socket;
	
	private MongoCursor<Enregistrable> cursor_e;
	private MongoCursor<Destinataire> cursor_d;

	/**
	 * Constructeur canonique
	 * @param socket le socket à écouter
	 */
	RequestHandler( Socket socket )	{
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

				case "0" : if (line.split("&").length == 3 && !line.split("&")[2].equals("")){

					ArrayList<String> retour_array = new ArrayList<>();
					String retour;

					cursor_e = MongoAccess.requestAnyMatch(line.split("&")[0], line.split("&")[1], line.split("&")[2], true).as(Enregistrable.class);
                    
					
					if (cursor_e != null){
						while (cursor_e.hasNext()){
							retour_array.add(cursor_e.next().getNom());
						}
					}
					retour = retour_array.stream().sorted().collect(Collectors.joining("&"));

					System.out.println("retour : " + retour);

					out.write(retour);
					out.newLine();
					out.flush();
				}   

				else{

					ArrayList<String> retour_array = new ArrayList<>();
					String retour;

					cursor_e = MongoAccess.request(line.split("&")[0]).as(Enregistrable.class);


					if (cursor_e != null){
						while (cursor_e.hasNext()){
							retour_array.add(cursor_e.next().getNom());
						}
					}

					retour = retour_array.stream().sorted().collect(Collectors.joining("&"));

					System.out.println("retour : " + retour);

					out.write(retour);
					out.newLine();
					out.flush();
				} 
				break;


				case "1" :
					ArrayList<String> retour_array = new ArrayList<>();
					String retour;

					Materiel materiel = MongoAccess.request("materiel", "nom", line).as(Materiel.class);

					cursor_d = MongoAccess.requestIn("destinataire", materiel.getTags()).as(Destinataire.class);

                    if (cursor_d != null){
                    	while (cursor_d.hasNext()){
    						Destinataire d = cursor_d.next();
    						System.out.println("destinataire.getNom() : " + d.getNom());
    						retour_array.add(d.getNom());
    					}
                    }
				
					retour = retour_array.stream().collect(Collectors.joining("&"));

					System.out.println("retour : " + retour);

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
				this.cursor_e.close();
				this.cursor_d.close();
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
        	oef.printStackTrace();
        }
		
		catch (NullPointerException npe){
			System.out.println("exception dans requestHandler : NullPointerException");
			try {
				this.socket.close();
				this.cursor_e.close();
				this.cursor_d.close();
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
			npe.printStackTrace();
		}
		
		catch (MongoTimeoutException mte){
			System.out.println("exception dans requestHandler : MongoTimeoutException");
			try {
				this.socket.close();
				this.cursor_e.close();
				this.cursor_d.close();
			} catch (IOException | NullPointerException e) {
				e.printStackTrace();
			}
			mte.printStackTrace();
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
			if (cursor_e != null){
				try {
					cursor_e.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (cursor_d != null){
				try {
					cursor_d.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
