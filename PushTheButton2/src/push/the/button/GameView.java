package push.the.button;

import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
public class GameView extends View {

	public int contatore = 0;
	public Griglia griglia;
	public Numbers numbers;
	public Numbers numbers2;
	
	public GameView(Context context)
	{
		super(context);
		griglia=new Griglia(context);
		numbers = new Numbers(context,true);
		numbers2 = new Numbers(context,false);
	}

	protected void onDraw(Canvas canvas)
	{
		for(int i=0;i<5;i++)
			for(int j=0;j<6;j++)
			{
					canvas.drawBitmap(griglia.getBitmap(i,j),null,griglia.getBound(i, j),null);
					invalidate();
			}
		canvas.drawBitmap(numbers.getNumbers()[0], null,numbers.getBound()[0],null);
		canvas.drawBitmap(numbers.getNumbers()[1], null,numbers.getBound()[1],null);
		canvas.drawBitmap(numbers2.getNumbers()[0],null,numbers2.getBound()[0],null);
		canvas.drawBitmap(numbers2.getNumbers()[1],null,numbers2.getBound()[1],null);
		canvas.drawBitmap(griglia.getBigBitmap(),null,griglia.getBigBound(),null);
	}
	
	public void update()
	{
		griglia.Shuffle();
		this.postInvalidate();
	}
	public void timeUpdate(int a)
	{
		numbers2.display(a);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		int[] a=griglia.coord(x, y);
		int c=griglia.getBigColor();
		int p=griglia.getColor(a[0], a[1]);
		if(p!=c & p!=2)
		{
			if(contatore!=0)
				contatore--;
			numbers.display(contatore);
			griglia.setGrayButton(a[0], a[1]);
			this.postInvalidate();
		}
		else if(p==c)
		{
			contatore++;
			numbers.display(contatore);
			griglia.setGrayButton(a[0], a[1]);
			this.postInvalidate();
		}
		return false;
	}
	
	public String getCount ()
	{
		String str = String.valueOf(contatore);
		return str; 
	}

}
