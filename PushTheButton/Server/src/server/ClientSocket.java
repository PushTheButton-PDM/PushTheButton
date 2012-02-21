package server;


import java.io.*;
import java.net.*;


public class ClientSocket
{
	Socket              client;
	ObjectOutputStream  sendOutput; 
	ObjectInputStream   recvInput; 
	int                 portaDefault = 4321;
		
	
	
	/**
	 * 
	//-----------Costruttore----------//
	//Istanzia la socket risolvendo il nome dell'host.
	 * 
	 */
	public ClientSocket(String nomeHost)
	{
		try
		{
			client = new Socket(nomeHost, portaDefault);
		}
		catch (Exception e)
		{
			
		}
	}
	
	
	
	/**
	 * //----------Funzioni--Send---------//
	//Accetta come argomento un oggetto serializable 
	//di tipo DatiScambiati contenente diverse informazioni.
	//L'oggetto viene dunque inviato sulla socket.
	 * @param messaggio
	 */
	public void sendMessage(DatiScambiati[] messaggio) 
	{
		try
		{
			sendOutput = new ObjectOutputStream(client.getOutputStream());
			sendOutput.writeObject(messaggio);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	
	/**
	 * //----------Funzioni--Receve-------//
	//Si riceve dalla socket un oggetto del tipo DatiScambiati.
	//che viene ritornato al chiamante.
	 */
	public DatiScambiati[] recvMessage()
	{
		try
		{
			recvInput = new ObjectInputStream(client.getInputStream());
			Object tmpMess = recvInput.readObject();
			
			//controllo di aver ricevuto effettivamente un 
			//oggetto di tipo DatiScambiati
			DatiScambiati message[];
			if (tmpMess instanceof DatiScambiati[])
				message = (DatiScambiati[]) tmpMess;
			else
				message = null;
			
			//ritorno message
			return message;
		}
		catch (EOFException e)
		{
			System.out.println("Eccezione nel client socket");
			//e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		//ritorno null in caso di eccezioni
		return null;
	}
	
	
	
	/**
	 * //----------Funzione--Close--------// 
	   //Chiude la socket, l'ObjectOutputStream e l'ObjectInputStream.
	 */
	public void closeConnection()
	{
		try
		{
			client.close();
			sendOutput.close();
			recvInput.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
