package push.the.button;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import java.util.Timer;
import java.util.TimerTask;


public class Game extends Activity {
	GameView game;
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
        timer.schedule(sendStart,1000,2000);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(game);
    }
}
