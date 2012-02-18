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
			if(a)
			{
				AssetManager assetManager=context.getAssets();
				InputStream inputStream = assetManager.open("zero.png");
				zero = BitmapFactory.decodeStream(inputStream);
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
				number1=zero;
				number2=zero;
			}
			else
			{
				AssetManager assetManager=context.getAssets();
				InputStream inputStream = assetManager.open("zerog.png");
				zero = BitmapFactory.decodeStream(inputStream);
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
				number1=zero;
				number2=zero;
			}
		}
		catch (IOException e) {

		} finally {

		}
		if(a){
			bound1=new RectF(0,0,80,110);
			bound2=new RectF(80,0,160,110);
		}
		else{
			bound1=new RectF(320,0,400,110);
			bound2=new RectF(400,0,480,110);
		}
				
		
	}
	public RectF[] getBound()
	{
		RectF[] a=new RectF[2];
		a[0]=bound1;
		a[1]=bound2;
		return a;
	}
	public Bitmap[] getNumbers()
	{
		Bitmap[] a=new Bitmap[2];
		a[0]=number1;
		a[1]=number2;
		return a;
		
	}
	public void display(int number)
	{
		if ((int) (number / 10) == 0) // aggiornamento dei numeri sul Display
		{
			number1 = zero;
		} else if ((int) (number / 10) == 1) {
			number1 = uno;
		} else if ((int) (number / 10) == 2) {
			number1 = due;
		} else if ((int) (number/ 10) == 3) {
			number1 = tre;
		} else if ((int) (number / 10) == 4) {
			number1 = quattro;
		}
		if ((int) (number/ 10) == 5) {
			number1 = cinque;
		}
		if ((int) (number / 10) == 6) {
			number1 = sei;
		}
		if ((int) (number / 10) == 7) {
			number1 = sette;
		}
		if ((int) (number / 10) == 8) {
			number1 = otto;
		}
		if ((int) (number / 10) == 9) {
			number1 = nove;
		}
		if ((int) (number - (int) (number / 10) * 10) == 0) {
			number2 = zero;
		} else if ((int) (number - (int) (number / 10) * 10) == 1) {
			number2 = uno;
		} else if ((int) (number - (int) (number/ 10) *10) == 2) {
			number2 = due;
		} else if ((int) (number - (int) (number / 10) * 10) == 3) {
			number2 = tre;
		} else if ((int) (number- (int) (number / 10) * 10) == 4) {
			number2 = quattro;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 5) {
			number2 = cinque;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 6) {
			number2 = sei;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 7) {
			number2 = sette;
		}
		if ((int) (number- (int) (number / 10) *10) == 8) {
			number2 = otto;
		}
		if ((int) (number - (int) (number/ 10) * 10) == 9) {
			number2 = nove;
		}
	}

}
