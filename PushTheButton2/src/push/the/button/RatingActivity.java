package push.the.button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import server.ClientSocket;
import server.DatiScambiati;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class RatingActivity extends Activity {
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	ListView lv = new ListView (this);		//Si crea una listview per la memorizzazione dei punteggi
    	ArrayAdapter<String> adapter;
    	try{									//Gestione eccezioni connessione server
    			super.onCreate(savedInstanceState);
    			DatiScambiati data= new DatiScambiati(1);	//Si crea un oggetto "DatiScambiati" passando come parametro l'id=0,
        											//che nella parte server significa "richiesta della classifica"
    			DatiScambiati[] d=new DatiScambiati[1];
    			d[0]=data;
    			ClientSocket socket = new ClientSocket("10.0.2.2");	//Tramite il "socket" si apre una connessione con una porta di default predefinita (4321) e verso l'indirizzo IP 10.0..2.2 (ip del server)
    			socket.sendMessage(d);						//Si invia l'array d
    			DatiScambiati[] a= socket.recvMessage();	//Si riceve ciò che viene inviato dal server sulla connessione aperta
    			String[] arraydata=null;					//Si crea l'array di stringhe all'interno del quale si memorizzano i punteggi inviati dal server
    			if(a!=null)
    			{
    				arraydata = new String[a.length];
    				for (int i=0; i<a.length; i++)			//Se l'array "a" ricevuto dal server non è vuoto, allora ciascun elemento viene memorizzato nell'array di stringhe "arraydata"
    				{
    					arraydata[i]=a[i].nomeFile+"		"+a[i].contenutoFile;
    				}
    			}
    			else
    			{
    				arraydata=new String[1];
    				arraydata[0]="Nessun punteggio memorizzato";	//Se l'array "a" ricevuto dal server è vuoto, allora al posto della classifica appare il messaggio "Nessun punteggio memorizzato"
    			}
    			adapter = new ArrayAdapter<String> (this,R.layout.row,R.id.textView1,arraydata);	//Si crea l'array adapter per "passare" le stringhe alla listview 	
    	}
    	catch(Exception e)
    	{
    		String[] arraydata=new String[1];
			arraydata[0]="Server momentaneamente fuori servizio";
			adapter = new ArrayAdapter<String> (this,R.layout.row,R.id.textView1,arraydata);
    		
    	}
    	lv.setAdapter(adapter);	//Si associa l'array adapter alla listview
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
    	setContentView(lv);		//Si imposta come view radice dell'activity la listview "lv"
	 	 
    }
}
