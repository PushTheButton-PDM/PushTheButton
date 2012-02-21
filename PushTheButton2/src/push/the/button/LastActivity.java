package push.the.button;

import android.os.Bundle;
import android.view.Gravity;
import server.ClientSocket;
import server.DatiScambiati;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;


public class LastActivity extends Activity {
	/** Called when the activity is first created. */
	EditText name;
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
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
		    	 	 String s=getIntent().getExtras().getString("score");
		    	 	 DatiScambiati data= new DatiScambiati(0,name.getText().toString(),s);
		    	 	 DatiScambiati[] d=new DatiScambiati[1];
		    	 	 d[0]=data;
		    	 	 ClientSocket socket = new ClientSocket("10.0.2.2");
		    	 	 socket.sendMessage(d);
		    	 	 socket.recvMessage();
		      	  }
		     catch (Exception e)
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
    