package push.the.button;
import android.graphics.RectF;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;

public class Griglia {								//Creazione della classe griglia finalizzata alla costruzione della matrice di bottoni
	
	public Bitmap bigButton;
	public RectF bigBound;
	public Bitmap[][] griglia=new Bitmap[5][6];		//Array di Bitmap di dimensione 5 (colonne) * 6 (righe),ad ogni bitmap sarà associato un bottone
	public Bitmap green;							//Bitmap che sarà associato al green button
	public Bitmap green2;
	public Bitmap gray2;	
	public Bitmap red2;
	public Bitmap gray;								//Bitmap che sarà associato al gray button
	public Bitmap red;								//Bitmap che sarà associato al red button
	public RectF[][] bounds=new RectF[5][6];		//Array di oggetti RectF,che costituiscono il bound di ogni bottone
	
	public Griglia(Context context)					//Costruttore della classe "Griglia"
	{
		try{
			AssetManager assetManager=context.getAssets();					//Creo un oggetto di classe AssetManager necessario per prelevare le risorse di tipo Assets
			InputStream inputStream=assetManager.open("greenbutton.png");	//Leggo e memorizzo nell'oggetto inputStream, la risorsa "greenbutton.png" presente nella cartella Assets
			green=BitmapFactory.decodeStream(inputStream);					//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "green"
			inputStream=assetManager.open("graybutton.png");				//Leggo e memorizzo nell'oggetto inputStream, la risorsa "graybutton.png" presente nella cartella Assets
			gray=BitmapFactory.decodeStream(inputStream);					//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "gray"
			inputStream=assetManager.open("redbutton.png");					//Leggo e memorizzo nell'oggetto inputStream, la risorsa "redbutton.png" presente nella cartella Assets
			red=BitmapFactory.decodeStream(inputStream);					//Decodifico l'inputStream,cioè l'immagine, in un oggetto Bitmap, e viene memorizzatio nell'oggetto "red"
			inputStream=assetManager.open("greenbutton2.png");	//Leggo e memorizzo nell'oggetto inputStream, la risorsa "greenbutton.png" presente nella cartella Assets
			green2=BitmapFactory.decodeStream(inputStream);	
			inputStream=assetManager.open("redbutton2.png");	//Leggo e memorizzo nell'oggetto inputStream, la risorsa "greenbutton.png" presente nella cartella Assets
			red2=BitmapFactory.decodeStream(inputStream);	
			inputStream=assetManager.open("graybutton2.png");	//Leggo e memorizzo nell'oggetto inputStream, la risorsa "greenbutton.png" presente nella cartella Assets
			gray2=BitmapFactory.decodeStream(inputStream);	
			inputStream.close();							// Chiude il flusso di input e rilascia tutte le risorse di sistema associate al flusso.
		    }
		
		catch(IOException e) { }

		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++)
				{
					bounds[i][j]=new RectF(15 + i * 90, 220 + j * 90, 95 + i * 90,300 + j * 90); //Tramite i due cicli "for" annidati vengono creati tutti i bound(contenitori) per ciscun pulsante
					griglia[i][j]=gray;		//Si settano tutti i bottoni, rappresentati dall'array di bitmap "griglia", con il bottone grigio ovvero il bitmap "gray"
				}
		bigBound=new RectF(190,0,290,100);	//Si impostano le dimensioni del bound dei pulsante centrale
		bigButton=gray2;					//Si imposta il pulsante centrale uguale al pulsante grigio
	}
	public void Shuffle()		//Il metodo "shuffle" invocato su oggetto di classe "griglia" permette di posizionare i bottoni verdi e rossi, in modo casuale nell'array di bitmap
	{
		int randRow;
		int randColumn;
		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++)
				{
					bounds[i][j]=new RectF(15 + i * 90, 220 + j * 90, 95 + i * 90,300 + j * 90);	//Tramite i due cicli "for" annidati vengono creati tutti i bound(contenitori) per ciscun pulsante
					griglia[i][j]=gray;			//Si settano tutti i bottoni, rappresentati dall'array di bitmap "griglia", con il bottone grigio ovvero il bitmap "gray"
				}
		bigBound=new RectF(190,0,290,100);		//Si impostano le dimensioni del bound del pulsante centrale
		bigButton=green2;
		double b=Math.random();
		if(b<0.5)
			bigButton=red2;
		boolean cond=true;
		while(cond)			//questo ciclo termina quando almeno un bottone ha il colore diverso da quello del bigButton
		{
			int c=0;
			for(int i=0;i<4;i++)	//Il ciclo viene ripetuto 4 volte,in quanto 4 è in numero di bottoni si vorranno visualizzare sull'activity di gioco
			{
					double a=Math.random();				//Generazione di un numero casuale
					randRow=(int)(Math.random()*5);		//Generazione di una riga casuale
					randColumn=(int)(Math.random()*6);	//Generazione di una colonna casuale
					if(a<0.5)										
					     griglia[randRow][randColumn]=red;	//Se a<0.5 (probabilità del 50%) viene copiato nella posizione corrente della griglia, il bottone rosso
					else
						 griglia[randRow][randColumn]=green; //Se a>0.5,allora nella posizione corrente della griglia viene copiato il bottone verde
					if(this.getBigColor()!=this.getColor(randRow,randColumn))
						c++;		//si incrementa c se il colore del bigButton è diverso da quello del bottone casuale
			}
			if(c!=4)		        //se c diverso da 4 significa che almeno uno dei bottoni casuali ha il colore del bigButton quindi esce dal ciclo while
				cond=false;
			else			              //se c uguale 4 significa che tutti i 4 bottoni casuali hanno colore diverso dal bigButton
				for(int i=0;i<5;i++)      //reimposta tutti i bottoni grigi e resta nel ciclo while
					for(int j=0;j<6;j++)  
						{
							bounds[i][j]=new RectF(15 + i * 90, 220 + j * 90, 95 + i * 90,300 + j * 90);	//Tramite i due cicli "for" annidati vengono creati tutti i bound(contenitori) per ciscun pulsante
							griglia[i][j]=gray;			//Si settano tutti i bottoni, rappresentati dall'array di bitmap "griglia", con il bottone grigio ovvero il bitmap "gray"
						}
				
		}

	}
	
	public int getColor(int a, int b) //Il metodo "getColor()",tramite un flag int, permette di capire quale è il colore del pulsante con indice [a][b] dell'array 
	{
			if(griglia[a][b]==red)
				return 0;					//Se il bottone è rosso si ritorna il flag 0;
			else if(griglia[a][b]==green)
				return 1;					//Se il bottone è verde si ritorna il flag 1;
			else 		
				return 2;					//Se il bottone è grigio si ritorna il flag 2;
		
	}
	
	public int getBigColor()		//Il metodo "getBigColor()", tramite un flag int, permette di capire quale è il colore del pulsante centrale
	{
		if( bigButton==green2)
			return 1;				//Se il bottone è verde si ritorna il flag 1;
		else
			return 0;				//Se il bottone è rosso o grigio si ritorna il flag 0;
	}
	
	public RectF getBigBound()		//Il metodo "getBigBound()" ritorna il bound (oggetto RectF) del pulsante centrale
	{
		return bigBound;
	}
	
	public Bitmap getBigBitmap()	//Il metodo "getBigBitmap()" ritorna il Bitmap relativo al pulsante centrale
	{
		return bigButton;
	}
	
	
	public RectF getBound(int a, int b)		//Il metodo "getBound" restituisce la RectF corrispondente all'elemento dell'oggetto "Griglia" di indice [a][b]
	{
		return bounds[a][b];
	}
	
	public Bitmap getBitmap(int a, int b)   //Il metodo "getBitmap" restituisce il bitmap di indice [a][b] dell'oggetto "Griglia" sul quale viene invocato
	{
		return griglia[a][b];
	}
	
	public void setGrayButton(int a, int b) //Il metodo "setGrayButton" permette di associare il bottone grigio al bitmap di indice [a][b] dell'oggetto "Griglia"			
	{
		griglia[a][b]=gray;
	}
	public int[] coord(double x, double y) //Il metodo "coord" dati due interi x e y,restituisce gli indici del bitmap dell'oggetto "Griglia" intercettato da x e y
	{
		int[] a =new int[2];
		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++)
				{
					if(x>15 + i * 90 & y>220 + j * 90 & x<95 + i * 90 & y<300 + j * 90)
						{
							a[0]=i;
							a[1]=j;
						}
						
				}
		return a;
	}
}
