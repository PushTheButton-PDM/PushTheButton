package push.the.button;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.app.Activity;

public class LastActivity extends Activity {
	/** Called when the activity is first created. */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); 	
        setContentView(R.layout.submit);
        TextView namelabel = new TextView (this);
        namelabel.setText("NickName :   ");
        EditText name = new EditText(this);
        name.setText("                                       ");
        TextView scorelabel = new TextView(this);
        scorelabel.setText("Score :   ");
        TextView score = new TextView(this);
        score.setText(getIntent().getExtras().getString("score"));
       
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
        
        Button save = new Button(this);
        save.setText("  Salva  ");
        
        Button delete = new Button(this);
        delete.setText("  Annulla  ");
        
        LinearLayout ll = new LinearLayout(this);	//Creo un linearlayout orizzontale su cui disporre due bottoni
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER_HORIZONTAL);
        ll.addView(save);
        ll.addView(delete);
        
        RadioGroup rg = new RadioGroup(this);	//Creo un radiogroup con orientazione orizzontale su cui disporre due radiobutton
        rg.setOrientation (RadioGroup.HORIZONTAL);
        rg.setGravity(Gravity.CENTER_HORIZONTAL);
        RadioButton rb1 = new RadioButton(this);
        RadioButton rb2 = new RadioButton(this);
        TextView tv1 = new TextView (this);
        tv1.setText("M        ");
        TextView tv2 = new TextView (this);
        tv2.setText("F        ");
        rg.addView(rb1);
        rg.addView(tv1);
        rg.addView(rb2);
        rg.addView(tv2);
        
        LinearLayout ll2 = new LinearLayout (this);	//LinearLayout "finale" con orientazione verticale che contiene le altre View e ViewGroup
        ll2.setOrientation(LinearLayout.VERTICAL);
        ll2.setGravity(Gravity.CENTER_VERTICAL);
        ll2.addView(tl);
        ll2.addView(rg);
        ll2.addView(ll);
        setContentView(ll2);
        
        
        
    }
}
    