package server;

import java.io.*;
import java.net.*;
import java.util.GregorianCalendar;

public class Server 
{
	ServerSocket     server;
	BufferedReader   recvInput;
	DataOutputStream sendOutput;
	
	
	
	/**
	 * //---------Costruttore--------//
	//Controlla che la path dell'indice passata come argomento
	//sia valida quindi salva tutti i dati passati come argomento.
	 */
	public Server(int portaAscolto) 
	{
		try 
		{
			server = new ServerSocket(portaAscolto);
		} 
		catch (IOException e) 
		{
			System.out.println("Essore nella creazione della socket sulla porta " + portaAscolto);
			
			e.printStackTrace();
		}
	}

	
	/**
	 * //-------Metodo--Accept-------//
	//Metodo in polling continuo in attesa di connessioni entranti.
	//Ogni connessione viene delegata ad un nuovo thread servente.
	 */
	public void acceptConnession() 
	{
		try 
		{
			while (true) 
			{
				
				String time = new GregorianCalendar().getTime().toString();
				System.out.println(time + ": Server "+ server.getInetAddress()+ " - In ascolto...");
				Socket client = server.accept();

				System.out.println(time + ": Server - Client: '"
						+ client.getInetAddress() + "' connesso!");
				
				//Salva il file in locale e aspetta la prossima connessione
				new ServerThread(client);
				
				System.out.println(time + ": Server - Client: '"
						+ client.getInetAddress() + "' gestito!\n");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	
	/**
	 * // --------Metodo--Close-------//
	 */
	public void closeConnection() {
		try {
			sendOutput.close();
			recvInput.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
