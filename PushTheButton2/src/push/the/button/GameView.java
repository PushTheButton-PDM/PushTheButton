package push.the.button;

import android.view.MotionEvent;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
public class GameView extends View {

	public int contatore = 0;
	public Griglia griglia;
	public Numbers numbers;

	public GameView(Context context)
	{
		super(context);
		griglia=new Griglia(context);
		numbers = new Numbers(context);
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
	}
	
	public void update()
	{
		griglia.Shuffle();
		this.postInvalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		int[] a=griglia.coord(x, y);
		if(griglia.getColor(a[0], a[1])==0 & contatore!=0)
		{
			contatore--;
			numbers.display(contatore);
			griglia.setGrayButton(a[0], a[1]);
			this.postInvalidate();
		}
		else if(griglia.getColor(a[0], a[1])==1)
		{
			contatore++;
			numbers.display(contatore);
			griglia.setGrayButton(a[0], a[1]);
			this.postInvalidate();
		}
		return false;
	}

}
