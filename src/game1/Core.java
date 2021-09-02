package game1;
import java.awt.*;
import javax.swing.*;
public abstract class Core {

	
	private boolean running;
	protected screenmanager s;
	
	//stop method
	public void stop()
	{
		running=false;
	}
	
	//call init and gameloop
	public void run()
	{
		try{
			init();
			gameLoop();
		}
		finally{
			s.restoreScreen();
		}
		
	}
	
	//set to full screen
	public void init()
	{
		s=new screenmanager();
		DisplayMode dm=s.findFirstCompatibleMode();
		s.setFullScreen(dm);
		
		Window w=s.getFullScreenWindow();
		w.setFont(new Font("Arial",Font.PLAIN,20));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.WHITE);
		running=true;
	}
	
	public void gameLoop()
	{
		long startingTime=System.currentTimeMillis();
		long sumTime=startingTime;
		while(running)
		{
			long timePassed=System.currentTimeMillis()-sumTime;
			sumTime+=timePassed;
			update(timePassed);
			//a.update(timePassed);
			
			Graphics2D g=s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			try{
				//System.out.println("coordinates: "+sprite.getX()+":"+sprite.getY());
				Thread.sleep(200);
			}catch(Exception ex){}
		}
	}
	public void update(long timePassed){}
	public abstract void draw(Graphics2D g);
}