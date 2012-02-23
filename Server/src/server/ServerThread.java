package server;


import java.io.*;
import java.net.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ServerThread extends Thread
{
	Socket              client;
	ObjectInputStream   recvInput; 
	ObjectOutputStream  sendOutput; 
	

	//---------Costruttore--------//
	//Salva gli argomenti passati.
	//Istanzia gli oggetti per la gestione della connessione.

	public ServerThread(Socket client) 
	{
		super("Tread Gestore Avviato: " + client.getLocalAddress());
		try
		{
			recvInput   = new ObjectInputStream(client.getInputStream());
			sendOutput  = new ObjectOutputStream(client.getOutputStream());
			this.client = client;
			start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public void run() 
	{
		try 
		{
			String nomeGestore = "\tThread Gestore " + this.getId() + " - ";
			
			System.out.println(nomeGestore + "Avviato!");
			System.out.println(nomeGestore + "Aspetto comandi dal client...");
		
			DatiScambiati[] mess = recvMessage();
			if (mess == null)
				return;
			
			DatiScambiati[] risposta;
			switch (mess[0].id) 
			{
				case 0: //salva il file
					System.out.println("Ricevuto il messaggio 0");
					risposta = new DatiScambiati[1];
					risposta[0]=new DatiScambiati(0, "prova.log", "registration was successful");
					Save(mess[0]);
					sendOutput.writeObject(risposta);
					break;
				case 1: //dammi le statistiche
					System.out.println("Ricevuto il messaggio 1");
					risposta = Load();
					sendOutput.writeObject(risposta);
					break;
		
				default:
					break;
			}
			
			//Salva su file il file passato dal client (sta dentro mess)
			
			System.out.println(nomeGestore + "Client gestito!");
			client.close();
			recvInput.close();
			sendOutput.close();
		}
		catch (IOException e)
		{
			System.out.println("Errore");
			e.printStackTrace();
		}
	}

	/**
	 * 	//------Metodo-Di-Ricezione-Comando-----//
	//Metodo privato in polling sulla socket in attesa 
	//di un pacchetto DatiScambiati entrante.
	 */
	private DatiScambiati[] recvMessage()
	{
		try
		{
			Object tmpMess = recvInput.readObject();
	
			if (tmpMess instanceof DatiScambiati[])
				return (DatiScambiati[]) tmpMess;
			else
				return null;
		}
		catch (IOException e)
		{
			System.out.println("Server Thread - Errore durante la ricezione del messaggio!");
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//return di default
		return null;
	}
	
	public void Save(DatiScambiati data)		//Metodo per il salvataggio (scrittura) dell'array di "DatiScambiati" ricevuti dal client
	{
		File f= new File("/home/alex/data/data.dat");
		DatiScambiati[] g;
		if(f.length()>3)	//Questo controllo serve a verificare se il file "data.dat" è maggiore di 3 Byte, allora significa che già sono stati scritti dati al suo interno
		{
			try{
			 	FileInputStream fis = new FileInputStream(f);	//Si crea un nuovo flusso di lettura in ingresso dal path individuato dalla stringa "f"
		        GZIPInputStream gzis = new GZIPInputStream(fis);	//Si crea un nuovo flusso di lettura in ingresso per dati compressi GZIP
		        ObjectInputStream in = new ObjectInputStream(gzis);	//Si crea il flusso dati in ingresso per la lettura degli oggetti
		        DatiScambiati[] d = (DatiScambiati[])in.readObject();	//Si memorizza nell'array di "DatiScambiati" l'oggetto ricevuto
		        int a=d.length;
		        g=new DatiScambiati[a+1];
		        for(int i=0;i<a;i++)
		        	g[i]=d[i];
		        g[a]=data;
		        Sort(g);	//Ordina in modo decresecente rispetto al punteggio
		        in.close(); //Si rilascia il flusso d'ingresso
		        FileOutputStream fos = new FileOutputStream(f);
		        GZIPOutputStream gzos = new GZIPOutputStream(fos);
		        ObjectOutputStream out = new ObjectOutputStream(gzos);
		        out.writeObject(g);		//Scrivo l'array di "DatiScambiati" nel file con path "f"
		        out.flush();
		        out.close();	//Si rilascia il flusso d'uscita
			}
			catch(Exception e)
			{
			}
		}
		else	//Il file "data.dat" è vuoto,allora si inizializza un array di una dimensione, essendo il primo oggetto che si scrive in tale file
		{
			g=new DatiScambiati[1];
			g[0]=data;
			try{
				
				FileOutputStream fos = new FileOutputStream(f);
				GZIPOutputStream gzos = new GZIPOutputStream(fos);
				ObjectOutputStream out = new ObjectOutputStream(gzos);
				out.writeObject(g);
				out.flush();
				out.close();
			}
			catch(Exception e)
			{
				
			}
			
		}
		
	}
	
	public DatiScambiati[] Load()	//Questo metodo permette di leggere l'array di "DatiScambiati" dal file "f"
	{
		try
		{
			
			File f= new File("/home/alex/data/data.dat");
			if(f.length()>3)
			{
				FileInputStream fis = new FileInputStream(f);
				GZIPInputStream gzis = new GZIPInputStream(fis);
				ObjectInputStream in = new ObjectInputStream(gzis);
				DatiScambiati[] d = (DatiScambiati[])in.readObject();
				in.close();
				return d;
			}
			else 
				return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public void Sort(DatiScambiati[] a) 	//Questo metodo ordina l'array di "DatiScambiati" in modo decrescente in funzione del punteggio
	{
		DatiScambiati temp =null;
		for(int j=0;j<a.length;j++) 
		{
			for(int i=j;i<a.length;i++) 
			{
				if(Integer.parseInt(a[i].contenutoFile)>Integer.parseInt(a[j].contenutoFile))
				{
					temp=a[j]; 
					a[j]=a[i];
					a[i]=temp;
				}
			}
		}
	}
}
