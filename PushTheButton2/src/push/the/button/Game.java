package push.the.button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import java.util.Timer;
import java.util.TimerTask;
 

public class Game extends Activity {
	
		GameView game;			//Si crea una variabile di istanza di classe GameView
		int time=10;
		Timer timer2=new Timer();				//Si crea un nuovo timer
		TimerTask sendStart2= new TimerTask() 
		{
			@Override
			public void run()
			{
				game.timeUpdate(time);		//Visualizza sul cronometro il numero pari al valore della variabile "time"
				time--;						
				if(time==0)
				{
					sendStart.cancel();			//Se il cronometro è scaduto, ovvero time=0, allora vengono stoppati i due timer
					sendStart2.cancel();
					Intent intent = new Intent(Game.this, LastActivity.class);		
					intent.putExtra("score",game.getCount());	//Si associa all'intent il punteggio totalizzato, dato da "game.getCount()"
					finish();									//Si chiude l'activity corrent, cioè "Game"
					startActivity(intent);						//Si lancia la nuova activity di fine gioco, cioè "LastActivity"
				}
			}	
		};
		Timer timer=new Timer();				//Si crea un nuovo timer
		TimerTask sendStart= new TimerTask() 
		{
			@Override
			public void run()
			{
				game.update();		//Questo secondo task, non fa altro che aggiornare la griglia,cambiando periodicamente il colore dei pulsanti
			}
		};
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game=new GameView(this);						
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
        timer.schedule(sendStart,2000,1500);	//Lo "schedule()" permette di ripetere il TimerTask periodicamente,in questo caso ogni 1500 ms,in quanto è quello che regola l'update 
        timer2.schedule(sendStart2,2000,1000);	//Lo "schedule()" permette di ripetere il TimerTask periodicamente,in questo caso ogni 1000 ms,in quanto è quello che regola il cronometro
        setContentView(game);					//Si imposta con layout la View "game"
    }
}
