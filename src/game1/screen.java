package game1;

import java.awt.*;
import javax.swing.JFrame;

public class screen {

	private GraphicsDevice vc;
	
	public screen()
	{
		GraphicsEnvironment env=GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc=env.getDefaultScreenDevice();
		
	}
	public void setScreen(DisplayMode dm,JFrame window)
	{
	window.setUndecorated(false);
	window.setResizable(true);
	//window.setSize(400,400);
	vc.setFullScreenWindow(window);
	
	if(dm!=null && vc.isDisplayChangeSupported())
	{
		try{
			vc.setDisplayMode(dm);
		}
		catch(Exception e){
			
		}
		
	}
	
	}
	
	public Window getFullScreenWindow()
	{
		
		return vc.getFullScreenWindow();
	}
	public void restoreScreen()
	{
		Window w=vc.getFullScreenWindow();
		if(w!=null)
		{
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
}
