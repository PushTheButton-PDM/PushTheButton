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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
	 	 DatiScambiati data= new DatiScambiati(1);
	 	 DatiScambiati[] d=new DatiScambiati[1];
	 	 d[0]=data;
	 	 ClientSocket socket = new ClientSocket("10.0.2.2");
	 	 socket.sendMessage(d);
	 	 DatiScambiati[] a= socket.recvMessage();
	 	 String[] arraydata=null;
	 	 if(a!=null)
	 	 {
	 		 arraydata = new String[a.length];
	 		 for (int i=0; i<a.length; i++)
	 	 		{
	 		 		arraydata[i]=a[i].nomeFile+"		"+a[i].contenutoFile;
	 	 		}
	 	 }
	 	 else
	 	 {
	 		 arraydata=new String[1];
	 		 arraydata[0]="Nessun punteggio memorizzato";
	 	 }
	 	 ListView lv = new ListView (this);
	 	 ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.row,R.id.textView1,arraydata);
	 	 lv.setAdapter(adapter);
	 	 requestWindowFeature(Window.FEATURE_NO_TITLE);
	 	 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	 	 WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
	 	 setContentView(lv);
	 	 
    }
}
