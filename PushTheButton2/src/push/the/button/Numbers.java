package push.the.button;
import java.io.IOException;
import java.io.InputStream;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.content.Context;


import android.graphics.Bitmap;

public class Numbers {
	public Bitmap number1;
	public Bitmap number2;
	public RectF bound1;
	public RectF bound2;
	public Bitmap uno;
	public Bitmap due;
	public Bitmap tre;
	public Bitmap quattro;
	public Bitmap cinque;
	public Bitmap sei;
	public Bitmap sette;
	public Bitmap otto;
	public Bitmap nove;
	public Bitmap zero;
	
	public Numbers(Context context,boolean a)
	{
		try{
			
			/*La variabile booleana "a" è un flag, infatti nel caso assume valore "false", negli oggetti Bitmap che rappresentano i numeri, vengono copiati i relativi numeri del 
			  del display, di colore rosso; mentre se assume valore "true" invece, negli oggetti Bitmap, saranno copiati i relativi numeri del display, di colore verde */
			
			if(a==false)	
			{
				AssetManager assetManager=context.getAssets();				//Creo un oggetto di classe AssetManager necessario per prelevare le risorse di tipo Assets
				InputStream inputStream = assetManager.open("zero.png");	//Leggo e memorizzo nell'oggetto inputStream, la risorsa "zero.png" presente nella cartella Assets
				zero = BitmapFactory.decodeStream(inputStream);				//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "zero"
				inputStream = assetManager.open("uno.png");
				uno = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("due.png");
				due = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("tre.png");
				tre = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("quattro.png");
				quattro = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("cinque.png");
				cinque = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("sei.png");
				sei = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("sette.png");
				sette = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("otto.png");
				otto = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("nove.png");
				nove = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
				number1=zero;			//Si inizializza "number1" a "zero"
				number2=zero;			//Si inizializza "number2" a "zero"
			}
			else
			{
				AssetManager assetManager=context.getAssets();				//Creo un oggetto di classe AssetManager necessario per prelevare le risorse di tipo Assets
				InputStream inputStream = assetManager.open("zerog.png");	//Leggo e memorizzo nell'oggetto inputStream, la risorsa "zero.png" presente nella cartella Assets
				zero = BitmapFactory.decodeStream(inputStream);				//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "zero"
				inputStream = assetManager.open("unog.png");
				uno = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("dueg.png");
				due = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("treg.png");
				tre = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("quattrog.png");
				quattro = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("cinqueg.png");
				cinque = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("seig.png");
				sei = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("setteg.png");
				sette = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("ottog.png");
				otto = BitmapFactory.decodeStream(inputStream);
				inputStream = assetManager.open("noveg.png");
				nove = BitmapFactory.decodeStream(inputStream);
				inputStream.close();
				number1=zero;				//Si inizializza "number1" a "zero"
				number2=zero;				//Si inizializza "number1" a "zero"
			}
		}
		catch (IOException e) 
		{
			
		} 
		
		if(a)
		{
			bound1=new RectF(0,0,80,110);	//Se "a=true" allora si inizializzano i due "bound" relativi al punteggio
			bound2=new RectF(80,0,160,110);	//cioè i due oggetti RectF per la visualizzazione dei due numeri nel display
		}
		
		else
		{
			bound1=new RectF(320,0,400,110); //Se "a=false" allora si inizializzano i due "bound" relativi al cronometro
			bound2=new RectF(400,0,480,110); //cioè i due oggetti RectF per la visualizzazione dei due numeri nel display
		}
				
		
	}
	
	public RectF[] getBound()		//Il metodo "getBound()" restituisce i "bound" relativi ai due numeri che possono essere 
	{								//relativi al cronometro o al punteggio, a seconda del valore della variabile "a"
		RectF[] a=new RectF[2];
		a[0]=bound1;
		a[1]=bound2;
		return a;
	}
	public Bitmap[] getNumbers()	//Il metodo "getNumbers()" restituisce un array di 2 Bitmap, contenenti i due numeri del display
	{
		Bitmap[] a=new Bitmap[2];
		a[0]=number1;
		a[1]=number2;
		return a;
		
	}
	
	public void display(int number)		//Il metodo "display()" permette l'aggiornamento dei numeri visualizzati in funzione del parametro "number"
	{									//che poi sarà il valore del contatore del punteggio, o quello che scandisce i secondi, nel caso del cronometro
		if ((int) (number / 10) == 0) 
		{
			number1 = zero;
		} 
		else if ((int) (number / 10) == 1) 
		{
			number1 = uno;
		} 
		else if ((int) (number / 10) == 2) 
		{
			number1 = due;
		} 
		else if ((int) (number/ 10) == 3) 
		{
			number1 = tre;
		} 
		else if ((int) (number / 10) == 4) 
		{
			number1 = quattro;
		}
		if ((int) (number/ 10) == 5) 
		{
			number1 = cinque;
		}
		if ((int) (number / 10) == 6) 
		{
			number1 = sei;
		}
		if ((int) (number / 10) == 7) 
		{
			number1 = sette;
		}
		if ((int) (number / 10) == 8) 
		{
			number1 = otto;
		}
		if ((int) (number / 10) == 9) 
		{
			number1 = nove;
		}
		if ((int) (number - (int) (number / 10) * 10) == 0) 
		{
			number2 = zero;
		} 
		else if ((int) (number - (int) (number / 10) * 10) == 1) 
		{
			number2 = uno;
		} 
		else if ((int) (number - (int) (number/ 10) *10) == 2) 
		{
			number2 = due;
		} 
		else if ((int) (number - (int) (number / 10) * 10) == 3) 
		{
			number2 = tre;
		} 
		else if ((int) (number- (int) (number / 10) * 10) == 4) 
		{
			number2 = quattro;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 5) 
		{
			number2 = cinque;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 6) 
		{
			number2 = sei;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 7) 
		{
			number2 = sette;
		}
		if ((int) (number- (int) (number / 10) *10) == 8) 
		{
			number2 = otto;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 9) 
		{
			number2 = nove;
		}
	}

}
