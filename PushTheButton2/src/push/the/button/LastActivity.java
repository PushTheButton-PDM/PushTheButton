package push.the.button;

import java.io.OutputStreamWriter;
import android.os.Bundle;
import android.view.Gravity;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import android.util.Log;
import java.io.DataOutputStream;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import java.io.DataInputStream;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;



public class LastActivity extends Activity {
	/** Called when the activity is first created. */
	EditText name;
	private void doFileUpload(){
		HttpURLConnection conn =    null;
		DataOutputStream dos = null;
		DataInputStream inStream = null;    
		String exsistingFileName = "/data/data/push.the.button/files/score.txt";

		String lineEnd = "rn";
		String twoHyphens = "--";
		String boundary =  "*****";

		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1*1024*1024;
		String responseFromServer = "";
		String urlString = "http://10.0.2.2/index.php";

		try
		{
			//------------------ CLIENT REQUEST 
			Log.e("MediaPlayer","Inside second Method");
			FileInputStream fileInputStream = new FileInputStream(new    File(exsistingFileName) );

                                        // open a URL connection to the Servlet
                                        URL url = new URL(urlString);

                                        // Open a HTTP connection to the URL
                                        conn = (HttpURLConnection) url.openConnection();

                                        // Allow Inputs
                                        conn.setDoInput(true);

                                        // Allow Outputs
                                        conn.setDoOutput(true);

                                        // Don't use a cached copy.
                                        conn.setUseCaches(false);

                                        // Use a post method.
                                        conn.setRequestMethod("POST");
                                        conn.setRequestProperty("Connection", "Keep-Alive");
                                        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

                                        dos = new DataOutputStream( conn.getOutputStream() );
                                        dos.writeBytes(twoHyphens + boundary + lineEnd);
                                        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
                                                            + exsistingFileName + "\"" + lineEnd);
                                        dos.writeBytes(lineEnd);
                                        Log.e("MediaPlayer","Headers are written");

                                        // create a buffer of maximum size
                                        bytesAvailable = fileInputStream.available();
                                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                                        buffer = new byte[bufferSize];

                                        // read file and write it into form...
                                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                                        while (bytesRead > 0){
                                                                dos.write(buffer, 0, bufferSize);
                                                                bytesAvailable = fileInputStream.available();
                                                                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                                                                bytesRead = fileInputStream.read(buffer, 0, bufferSize);                                                
                                        }

                                        // send multipart form data necesssary after file data...
                                        dos.writeBytes(lineEnd);
                                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                                        // close streams
                                        Log.e("MediaPlayer","File is written");
                                        fileInputStream.close();
                                        dos.flush();
                                        dos.close();

                    }

		catch (MalformedURLException ex)

		{

			Log.e("MediaPlayer", "error: " + ex.getMessage(), ex);

		}



		catch (IOException ioe)

		{

			Log.e("MediaPlayer", "error: " + ioe.getMessage(), ioe);

		}

		//------------------ read the SERVER RESPONSE
		try {
			inStream = new DataInputStream ( conn.getInputStream() );
			String str;

            while (( str = inStream.readLine()) != null)
            {
                 Log.e("MediaPlayer","Server Response"+str);
            }

            inStream.close();
      }

      catch (IOException ioex){
           Log.e("MediaPlayer", "error: " + ioex.getMessage(), ioex);
      }

    }
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
        setContentView(R.layout.submit);
        TextView namelabel = new TextView (this);
        namelabel.setTextSize(25);
        namelabel.setText("NickName :   ");
        name = new EditText(this);
        name.setWidth(250);
        TextView scorelabel = new TextView(this);
        scorelabel.setTextSize(25);
        scorelabel.setText("Score :   ");
        TextView score = new TextView(this);
        score.setText(getIntent().getExtras().getString("score"));
        score.setTextSize(25);
        score.setTextColor(Color.GREEN);
        score.setMinHeight(100);
        
        TableLayout tl = new TableLayout(this);	//Creo la tabella composta dalla due rigne che seguono ognuna delle quali ha due colonne come campi
        
        TableRow tr1 = new TableRow (this);		//Creo una riga della tabella in cui inserisco una textview e un edittext
        tr1.addView(namelabel);
        tr1.addView(name);
        
        TableRow tr2 = new TableRow(this);		//Creo una riga della tabella in cui inserisco una textview e un edittext
        tr2.addView(scorelabel);
        tr2.addView(score);
        
        tl.addView(tr1);
        tl.addView(tr2);
        tl.setGravity(Gravity.CENTER_VERTICAL);
        
        Button submit = new Button(this);
        submit.setText("  Submit  ");
        
        Button again = new Button(this);
        again.setText("  Try Again  ");
        
        Button exit =  new Button(this);
        exit.setText("   Exit   ");
        LinearLayout ll = new LinearLayout(this);	//Creo un linearlayout orizzontale su cui disporre due bottoni
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);
        ll.addView(submit);
        ll.addView(again);
        ll.addView(exit);
        
        LinearLayout ll2 = new LinearLayout (this);	//LinearLayout "finale" con orientazione verticale che contiene le altre View e ViewGroup
        ll2.setOrientation(LinearLayout.VERTICAL);
        ll2.setGravity(Gravity.CENTER_VERTICAL);
        ll2.addView(tl);
        ll2.addView(ll);
        setContentView(ll2);
        
        again.setOnClickListener(new OnClickListener() 
        {
        	
		@Override
			public void onClick(View v) 
			{	
				Intent intent = new Intent(LastActivity.this,Game.class);
				finish();
				startActivity(intent);
			}
		});
        
        
        submit.setOnClickListener(new OnClickListener() 
        {
        	
		@Override
			public void onClick(View v) 
			{	
		     try { 
		    	 	final String points =getIntent().getExtras().getString("score")+"  "+name.getText();            
		    	 	FileOutputStream fOut = openFileOutput("score.txt",MODE_WORLD_READABLE);
		    	 	OutputStreamWriter output = new OutputStreamWriter(fOut);
		    	 	output.write(points);
		    	 	output.flush();
		    	 	output.close();
		    	 	doFileUpload();
		      }
		     catch (IOException e)
		     {
		    	 
		     }
		     finally
		     {
		     }
		     Intent intent = new Intent(LastActivity.this,PushTheButton2Activity.class);
		     finish();
			 startActivity(intent);
			}
		});
       
        exit.setOnClickListener(new OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{	
				finish();
	            System.exit(0);
			}
		});
    }
}
    