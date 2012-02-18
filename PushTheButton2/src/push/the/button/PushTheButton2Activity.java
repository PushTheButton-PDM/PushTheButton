package push.the.button;



import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.Window;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class PushTheButton2Activity extends Activity{
    /** Called when the activity is first created. */
	
	FrameLayout layout;
	ptbMenuView menu;	 //Istanzio una variabile della classe ptbMenuView
	SwitchButton swc;	// Istanzio una variabile della classe SwitchButton
	
	public void startGame()			// Metodo che avvierà l'activity di gioco, nel caso venga premuto il tasto "start"
	{
		Intent intent = new Intent();
	    intent.setClass(this, Game.class);		// Intent per il lancio dell'activity di gioco
	    finish();
	    startActivity(intent);
		
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layout = new FrameLayout(this); 
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Metodo statico per ottenere l'activity in fullscreen 
        menu= new ptbMenuView(this);
        layout.addView(menu);	//Si aggiunge la view "menu" al framelayout "layout"
        
        swc=new SwitchButton(this);
        swc.setLayoutParams(new FrameLayout.LayoutParams(45,58));	//Si impostano i parametri dell'oggetto "swc"
        swc.layout(350,670,395,728);
        swc.setPadding(45, 58, 45, 58);
        swc.measure(45, 58);
        
        layout.addView(swc);		//Si aggiunge "swc" al framelayout "layout"
        
        setContentView(layout);		//Si imposta "layout" come elemento radice associato all'Activity 
        
        menu.setOnTouchListener(new View.OnTouchListener() {	//Si associa un evento di listener all'oggetto "menu" in modo da controllare l'activity principale
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {	//Si gestisce l'evento di touch tramite sovrascrittura del metodo
				  float x = event.getX();						//Si ottiene la coordinata x dell'evento di touch
			      float y = event.getY();						//Si ottiene la coordinata y dell'evento di touch
				 if(x>130 && x<330 && y>10 && y<210) 			//Se il touch è stato rilevato su tali coordinate,che sono quelle corrispondenti al bound del
			      {												//del tasto "start" allora si invoca il metodo "startgame" che lancia la nuova activity				
					 startGame();
			      }
			      else if(x>130 && x<330 && y>240 && y<440)		//Se il touch è stato rilevato su tali coordinate,che sono quelle corrispondenti al bound del
			      {												//tasto "exit" allora si chiude l'activity corrente, tramite il metodo statico "finish()"
			    	    finish();
			            System.exit(0);
			      }
			      else if(x>130 && x<330 && y>470 && y<670)		//Se il touch è stato rilevato su tali coordinate,che sono quelle corrispondenti al bound del
			      {												//tasto "rating" allora viene lanciata l'activity relativa ai punteggi memorizzati su server
			    	  finish();
			    	  startActivity(new Intent(PushTheButton2Activity.this, Game.class));
			      }
				
				return false;
			}
		});
       
        swc.setOnClickListener(new OnClickListener() { //Aggancio all'oggetto "swc" un evento di Listener,in modo tale da gestire tramite clock il pulsante di on/off
			
			public void onClick(View v) 
			{
				swc.change();	// Tramite il metodo "change" della classe "SwitchButton",si cambia il bitmap associato all'oggetto "swc"
			}					// utilizzando il metodo onDraw, richiamato tramite "postInvalidate()"			
		});
    }
    
}
