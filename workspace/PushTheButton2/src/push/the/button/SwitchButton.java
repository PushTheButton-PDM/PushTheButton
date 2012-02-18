package push.the.button;
import java.io.InputStream;
import java.io.IOException;

import android.view.View;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

public class SwitchButton extends View{
	public Bitmap onButton;
	public Bitmap offButton;
	public Bitmap swc;
	public RectF onOffBound; 
	public boolean isOn=true;
	
	public SwitchButton(Context context)
	{
		super(context);				// Si richiama il costruttore della superclasse
		onOffBound=new RectF();		//Creo il bound per il pulsante on/off,che è un oggetto di classe "RectF"
		try{
			AssetManager assetManager=context.getAssets();			//Creo un oggetto di classe AssetManager necessario per prelevare le risorse di tipo Assets
			
			InputStream inputStream=assetManager.open("onbutton.png");  //Leggo e memorizzo nell'oggetto inputStream, la risorsa "onbutton.png" presente nella cartella Assets
			onButton=BitmapFactory.decodeStream(inputStream);			//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "onButton"
			
			inputStream=assetManager.open("offbutton.png");				//Leggo e memorizzo nell'oggetto inputStream, la risorsa "offbutton.png" presente nella cartella Assets
			offButton=BitmapFactory.decodeStream(inputStream);			//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "offButton"
			
			inputStream.close(); 								// Chiude il flusso di input e rilascia tutte le risorse di sistema associate al flusso.
		}
		catch(IOException e) 	{ }
	}
	
	protected void onDraw(Canvas canvas)	// Si utilizza il metodo oDraw per disegnare gli oggetti bitmap definiti in precedenza sullo schermo
	{
		if(isOn)	
			swc=onButton; // Se la variabile booleana di flag è true, allora nel bitmap "swc" si copia il bitmap "onbutton"
		else		
			swc=offButton;	// Se la variabile booleana di flag è true, allora nel bitmap "swc" si copia il bitmap "offbutton"
		
		onOffBound.set(0,0,45,58);						// Vengono settate le coordinate x e y degli spigoli in alto a sinistra e in basso a destra,dove deve essere visualizzato il bitmap
		canvas.drawBitmap(swc,null,onOffBound,null);	// Si utilizza il metodo "drawBitmap" della classe Canvas per disegnare sul display il bitmap passato come parametro, e il relativo Bound
		invalidate();									//Tramite il metodo statico "invalidate()" è possibile richiamare onDraw e ridisegnare gli oggetti bitmap
	}
	
	public void change()	//Il metodo "change" permette di modificare il bitmap associato
	{
		if(isOn)
		{
			isOn=false;
			this.postInvalidate();	//Tramite il metodo "postInvalidate()" viene richiamato "onDraw" e si ridisegna il bitmap sullo schermo
		}
		else
		{
			isOn=true;
			this.postInvalidate();	//Tramite il metodo "postInvalidate()" viene richiamato "onDraw" e si ridisegna il bitmap sullo schermo
		}
	}

}
