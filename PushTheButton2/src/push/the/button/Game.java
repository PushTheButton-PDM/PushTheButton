package push.the.button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;
 

public class Game extends Activity {
	
		GameView game;
		int time=0;
		Timer timer2=new Timer();
		TimerTask sendStart2= new TimerTask() {
		 @Override
		 public void run(){
			 game.timeUpdate(time);
			 time++;
			 if(time>10)
			 
			 {
				 sendStart2.cancel();
				 Intent intent = new Intent(Game.this, LastActivity.class);
				 intent.putExtra("score",game.getCount());
				 finish();
				 startActivity(intent);
			 }
		 }
	 };
		Timer timer=new Timer();
		TimerTask sendStart= new TimerTask() {
		 @Override
		 public void run(){
			 game.update();
		 }
	 };
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game=new GameView(this);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
        timer.schedule(sendStart,2000,1000);
        timer2.schedule(sendStart2,2000,1000);
        setContentView(game);
    }
}
