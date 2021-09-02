package game1;
import java.awt.*;
import javax.swing.JFrame;



import javax.swing.ImageIcon;
public class first  {

	public static void main(String[] args) {
		DisplayMode dm=new DisplayMode(1024,768,16,DisplayMode.REFRESH_RATE_UNKNOWN);
     first b=new first();
     b.run(dm);
	}
	private screen scrin;
	private Image bg;
	private animation a;
	private Image block;
	private Image rat;
	
	public void loadPics()
	{
		bg=new ImageIcon("C:\\Users\\Admin\\Desktop\\img1.jpg").getImage();
		Image face1=new ImageIcon("C:\\Users\\Admin\\Desktop\\cat_awaiting.png").getImage();
		Image face2=new ImageIcon("C:\\Users\\Admin\\Desktop\\cat.png").getImage();
		block=new ImageIcon("C:\\Users\\Admin\\Desktop\\block1.png").getImage();
		rat=new ImageIcon("C:\\Users\\Admin\\Desktop\\rat.png").getImage();
		a=new animation();
		a.addScenes(face1, 250);
		a.addScenes(face2, 250);
	}
	
	public void run(DisplayMode dm)
	{
		scrin =new screen();
		try{
			scrin.setScreen(dm,new JFrame());
			loadPics();
			movieLoop();
		}finally{
			scrin.restoreScreen();
		}
	}
	
	public void movieLoop()
	{
		long startingTime=System.currentTimeMillis();
		long sumTime=startingTime;
		while(sumTime-startingTime<5000)
		{
			long timePassed=System.currentTimeMillis()-sumTime;
			sumTime+=timePassed;
			a.update(timePassed);
			Graphics g=scrin.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			try{
				Thread.sleep(200);
			}catch(Exception e){}
		}
		
	}
	public void draw(Graphics g)
	{
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(), 150, 150, null);
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)	
		if(!(i==7 && j==7))
			g.drawImage(block, 200+j*16, 200+i*16, null);
		else
			g.drawImage(rat, 200+j*16, 200+i*16, null);
	}
}
	/*
	screen s=new screen();
	Image ab;
	Image cd;
	public boolean loader=false;
	public void run(DisplayMode dm)
	{
		getContentPane().setBackground(Color.RED);
		setForeground(Color.YELLOW);
		setFont(new Font("Arial",Font.PLAIN,24));
		
		
		try{
			s.setScreen(dm,this);
			loadpics();
			try{
				Thread.sleep(5000);
			}finally{}
			
		}
		catch(Exception e){}
	finally{
		s.restoreScreen();
	}

}
	public void loadpics()
	{
		ab=new ImageIcon("C:\\Users\\Admin\\Desktop\\img1.jpg").getImage();
		cd=new ImageIcon("C:\\Users\\Admin\\Desktop\\block1.png").getImage();
		loader=true;
		repaint();
	}
	public void paint(Graphics g)
	{
		if(g instanceof Graphics2D)
		{
			Graphics2D g2=(Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
        super.paint(g); 
		g.drawString("hi thereW",200, 400);
		if(loader)
		{
			g.drawImage(ab,0,0,null);
			g.drawImage(cd,200,500,null);
		}
		
	}
}*/