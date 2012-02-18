package push.the.button;
import android.content.Context;
import java.io.IOException;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.graphics.Bitmap;
import java.io.InputStream;
import android.content.res.AssetManager;
public class ptbMenuView extends View{
	
	boolean on;
	public RectF startButtonBound;
	public RectF exitButtonBound;
	public RectF ratingButtonBound;
	public RectF backgroundBound;
	public RectF onOffBound;
	public Bitmap startButton;
	public Bitmap exitButton;
	public Bitmap ratingButton;
	public Bitmap background;
	public Bitmap onButton;
	public Bitmap offButton;
	public Context c;
	
	public ptbMenuView(Context context)	//Costruttore della classe "ptbMenuView"
	{
		super(context);
		c=context;
		on=true;
		startButtonBound=new RectF();	// Creo il bound (contenitore) all'interno del quale verrà inserito il tasto "start"
		exitButtonBound=new RectF();	// Creo il bound (contenitore) all'interno del quale verrà inserito il tasto "exit"
		ratingButtonBound=new RectF();	// Creo il bound (contenitore) all'interno del quale verrà inserito il tasto "rating"
		backgroundBound=new RectF();	// Creo il bound (contenitore) all'interno del quale verrà inserita l'immagine di background
		onOffBound= new RectF();		// Creo il bound (contenitore) all'interno del quale verrà inserito il tasto "on/off"
		
		try{
			AssetManager assetManager=context.getAssets();			//Creo un oggetto di classe AssetManager necessario per prelevare le risorse di tipo Assets
			InputStream inputStream=assetManager.open("startbutton2.png"); //Leggo e memorizzo nell'oggetto inputStream, la risorsa "startbutton2.png" presente nella cartella Assets
			startButton=BitmapFactory.decodeStream(inputStream);	//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "startButton"
			
			inputStream=assetManager.open("exitbutton2.png");		//Leggo e memorizzo nell'oggetto inputStream, la risorsa "exitbutton2.png" presente nella cartella Assets
			exitButton=BitmapFactory.decodeStream(inputStream);		//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "exitButton"
			
			inputStream=assetManager.open("ratingbutton2.png");		//Leggo e memorizzo nell'oggetto inputStream, la risorsa "ratingbutton2.png" presente nella cartella Assets
			ratingButton=BitmapFactory.decodeStream(inputStream);	//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "ratingButton"
			
			inputStream=assetManager.open("backgroundF.png");		//Leggo e memorizzo nell'oggetto inputStream, la risorsa "backgroundF.png" presente nella cartella Assets
			background=BitmapFactory.decodeStream(inputStream);		//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "background"
			
			inputStream.close();									// Chiude il flusso di input e rilascia tutte le risorse di sistema associate al flusso.
		}
		catch (IOException e) { }
	}
	
	protected void onDraw(Canvas canvas)	// Si utilizza il metodo oDraw per disegnare gli oggetti bitmap definiti in precedenza sullo schermo
	{
		
		backgroundBound.set(0,0,480,800);						 // Vengono settate le coordinate x e y degli spigoli in alto a sinistra e in basso a destra,dove deve essere visualizzato il bitmap
		canvas.drawBitmap(background,null,backgroundBound,null); // Si utilizza il metodo "drawBitmap" della classe Canvas per disegnare sul display il bitmap passato come parametro, e il relativo Bound
		
		startButtonBound.set(130,10,330,210);						// Vengono settate le coordinate x e y degli spigoli in alto a sinistra e in basso a destra,dove deve essere visualizzato il bitmap
		canvas.drawBitmap(startButton,null,startButtonBound,null);	// Si utilizza il metodo "drawBitmap" della classe Canvas per disegnare sul display il bitmap passato come parametro, e il relativo Bound
		
		exitButtonBound.set(130,240,330,440);						// Vengono settate le coordinate x e y degli spigoli in alto a sinistra e in basso a destra,dove deve essere visualizzato il bitmap
		canvas.drawBitmap(exitButton, null, exitButtonBound,null);	// Si utilizza il metodo "drawBitmap" della classe Canvas per disegnare sul display il bitmap passato come parametro, e il relativo Bound
		
		ratingButtonBound.set(130,470,330,670);						// Vengono settate le coordinate x e y degli spigoli in alto a sinistra e in basso a destra,dove deve essere visualizzato il bitmap
		canvas.drawBitmap(ratingButton,null,ratingButtonBound,null);// Si utilizza il metodo "drawBitmap" della classe Canvas per disegnare sul display il bitmap passato come parametro, e il relativo Bound
		
		invalidate();	//Tramite il metodo statico "invalidate()" è possibile richiamare onDraw e ridisegnare gli oggetti bitmap,in quanto sono presenti delle variazioni (tasto on/off)
	}
	 
	
}


