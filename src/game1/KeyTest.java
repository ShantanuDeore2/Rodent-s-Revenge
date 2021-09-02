package game1;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import javax.swing.*;
public class KeyTest extends wscreen implements KeyListener {
	public static void main(String[] args)
	{
		new KeyTest().run();
	}
//	private String mess = "";
	
	//init also call init from superclass
	public void init()
	{
		super.init();
		Window w=s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false);
		w.addKeyListener(this);
//		mess="press escape to exit";
	}
	
	public long oldtime = 0;
	
	//keypressed
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		if(keyCode==KeyEvent.VK_ESCAPE)
			stop();
		long timeNow = System.currentTimeMillis();
		long time = timeNow - oldtime;
		if (time < 0 || time > 220) {
		oldtime = timeNow;
		if(gameon==1)
		{
		switch(keyCode)
		{
		case KeyEvent.VK_N:
			if(next)
			{
				level+=1;
				levelchanged=true;
			};break;
		case KeyEvent.VK_ESCAPE :
			stop();break;
			
		case KeyEvent.VK_RIGHT:
			RBPressed();break;
			
		case KeyEvent.VK_LEFT:
			LBPressed();break;
			
		case KeyEvent.VK_UP:
			UBPressed();break;
			
		case KeyEvent.VK_DOWN:
			DBPressed();break;
		//if(keyCode==KeyEvent.VK_ESCAPE)
		 default:e.consume();		
		}
		}
		}
	}
	
	//keyRealesed
	public void keyReleased(KeyEvent e)
		{
			//int keyCode = e.getKeyCode();
			
//				mess="Realesed:"+KeyEvent.getKeyText(keyCode);
			e.consume();
		}
	
	//last method from interface
	public void keyTyped(KeyEvent e)
	{
		e.consume();
	}
	/*public synchronized void draw(Graphics2D g)
	{
		Window w=s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0,0,s.getWidth(),s.getHeight());
		g.setColor(w.getForeground());
		//g.drawString(mess, 400, 400);
	}*/
}
