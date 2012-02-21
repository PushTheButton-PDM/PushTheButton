package push.the.button;

import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
public class GameView extends View {

	public int contatore = 0;
	public Griglia griglia;			
	public Numbers numbers;			//Si istanzia un oggetto "numbers" di classe Numbers, che rappresenta il campo unità del contatore e del cronometro
	public Numbers numbers2;		//Si istanzia un oggetto "numbers" di classe Numbers, che rappresenta il campo decine del contatore e del cronometro
	
	public GameView(Context context)
	{
		super(context);
		griglia=new Griglia(context);
		numbers = new Numbers(context,true);
		numbers2 = new Numbers(context,false);
	}

	protected void onDraw(Canvas canvas)
	{
		for(int i=0;i<5;i++)				//Tramite i due cicli for annidati,e il metodo "onDraw" si disegnano tutti i pulsanti della griglia
			for(int j=0;j<6;j++)			//di gioco, infatti si passano come parametri il bitmap in posizione [i][j] e il relativo bound
			{
					canvas.drawBitmap(griglia.getBitmap(i,j),null,griglia.getBound(i, j),null);
			}
		canvas.drawBitmap(numbers.getNumbers()[0], null,numbers.getBound()[0],null);	//Si disegnano sullo schermo i due numeri relativi al contatore e al cronometro
		canvas.drawBitmap(numbers.getNumbers()[1], null,numbers.getBound()[1],null);
		canvas.drawBitmap(numbers2.getNumbers()[0],null,numbers2.getBound()[0],null);
		canvas.drawBitmap(numbers2.getNumbers()[1],null,numbers2.getBound()[1],null);
		canvas.drawBitmap(griglia.getBigBitmap(),null,griglia.getBigBound(),null);		//Si disegna sullo schermo il pulsante centrale
	}
	
	public void update()		//Il metodo "update()",permette di aggiornare il colore dei pulsanti, richiamando il metodo "shuffle()" sull'oggetto di classe "Griglia"
	{							//e poi tramite "postInvalidate()" è si richiama "onDraw()" per ridisegnare nuovamente i pulsanti, con i nuovi colori
		griglia.Shuffle();
		this.postInvalidate();
	}
	public void timeUpdate(int a)
	{
		numbers2.display(a);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)		//Si sovrascrive il metodo "onTouchEvent" per gestire l'evento di touch nel modo desiderato
	{
		float x = event.getX();				//Restituisce la coordinata x del "touch" sullo schermo
		float y = event.getY();				//Restituisce la coordinata y del "touch" sullo schermo
		int[] a=griglia.coord(x, y);		//Il vettore a contiene gli indici del "pulsante" premuto sullo schermo
		int c=griglia.getBigColor();		//La variabile c, contiene il numero che rappresenta il colore del pulsante premuto
		int p=griglia.getColor(a[0], a[1]);	//La variabile p, contiene il numero che rappresenta il colore del pulsante premuto
		if(p!=c & p!=2)						//Se il colore del pulsante centrale è diverso da quello del pulsante premuto
		{									//e il contatore è diverso da 0, allora si decrementa il contatore
			if(contatore!=0)
				contatore--;
			numbers.display(contatore);			//Si aggiorna il contatore sul display
			griglia.setGrayButton(a[0], a[1]);	//Si imposta a grigio il pulsante premuto 
			this.postInvalidate();				//Tramtite "postInvalidate()" si richiama il metodo "onDraw" e si ridisegna la griglia
		}
		else if(p==c)							//Se il pulsante premuto è uguale a quello centrale allora si incrementa di
		{										//una unità il contatore
			contatore++;
			numbers.display(contatore);			//Si aggiorna il contatore sul display
			griglia.setGrayButton(a[0], a[1]);	//Si imposta a grigio il pulsante premuto
			this.postInvalidate();				//Tramtite "postInvalidate()" si richiama il metodo "onDraw" e si ridisegna la griglia
		}
		return true;
	}
	
	public String getCount ()			//Il metodo "getCount()" ritorna il valore del contatore sotto forma di stringa
	{
		String str = String.valueOf(contatore);
		return str; 
	}

}
