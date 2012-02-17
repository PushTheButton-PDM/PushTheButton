package push.the.button;
import android.graphics.RectF;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;

public class Griglia {
	public Bitmap[][] griglia=new Bitmap[5][6];
	public Bitmap green;
	public Bitmap gray;
	public Bitmap red;
	public RectF[][] bounds=new RectF[5][6];
	public Griglia(Context context)
	{
		try{
			AssetManager assetManager=context.getAssets();
			InputStream inputStream=assetManager.open("greenbutton.png");
			green=BitmapFactory.decodeStream(inputStream);
			inputStream=assetManager.open("graybutton.png");
			gray=BitmapFactory.decodeStream(inputStream);
			inputStream=assetManager.open("redbutton.png");
			red=BitmapFactory.decodeStream(inputStream);
			inputStream.close();
		    }
		catch(IOException e)
		{
			
		}
		finally
		{
			
		}
		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++)
				{
					bounds[i][j]=new RectF(15 + i * 90, 220 + j * 90, 95 + i * 90,300 + j * 90);
					griglia[i][j]=gray;
				}
		
	}
	public void Shuffle()
	{
		int randRow;
		int randColumn;
		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++)
				{
					bounds[i][j]=new RectF(15 + i * 90, 220 + j * 90, 95 + i * 90,300 + j * 90);
					griglia[i][j]=gray;
				}
		for(int i=0;i<4;i++)
		{
				double a=Math.random();
				randRow=(int)(Math.random()*5);
				randColumn=(int)(Math.random()*6);
				if(a<0.2)
				     griglia[randRow][randColumn]=red;
				else
					 griglia[randRow][randColumn]=green;
		}
	}
	
	public int getColor(int a, int b)
	{
			if(griglia[a][b]==red)
				return 0;
			else if(griglia[a][b]==green)
				return 1;
			else 
				return 2;
		
	}
	
	public RectF getBound(int a, int b)
	{
		return bounds[a][b];
	}
	public Bitmap getBitmap(int a, int b)
	{
		return griglia[a][b];
	}
	
	public void setGrayButton(int a,int b)
	{
		griglia[a][b]=gray;
	}
	public int[] coord(double x, double y)
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
