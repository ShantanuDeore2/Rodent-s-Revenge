package game1;
import java.util.*;
import java.awt.*;
//import javax.swing.JFrame;



import javax.swing.ImageIcon;
public class wscreen  {

	/*public static void main(String[] args) {
    
	}*/
	public boolean next=false;
	public int level=1;
	public boolean levelchanged=false;
	public int catsnum = 2;
	public int score=0;
	public int deadcat=0;
	public int gameon=1;
	public int Ydisplacement[];
	public int Xdisplacement[];
	protected screenmanager s;
	public Image rat;
	private Sprite mouse,cats[];
	//private Cat[] cat;
	private Sprite[][] blocks;
	private Image bg;
	private animation a2; //a2=animation rat
	private animation[] ac; //animation cat
	private Image block,wall,cheese[];
	//private Image rat;
	public int i=0,ci=0;
	private int centreX;
	private int centreY;
	private int cheeseX[]={0},cheeseflag[]={0},cheeseY[]={0};
	private boolean running=true;
	private int catmoved[]={0};
	private animation[][] ab; //animation blocks
	private Image face2;
	public int onlyafterlevel1=0;
	public void loadPics()
	{
		
		bg=new ImageIcon("E:\\New folder\\java\\game1\\res\\back.png").getImage();
		wall=new ImageIcon("E:\\New folder\\java\\game1\\res\\wall.png").getImage();
		face2=new ImageIcon("E:\\New folder\\java\\game1\\res\\cat.png").getImage();
		block=new ImageIcon("E:\\New folder\\java\\game1\\res\\block1.png").getImage();
		rat=new ImageIcon("E:\\New folder\\java\\game1\\res\\rat.png").getImage();
				

		loadcats(face2);
		/*
		cats[0].setX((centreX)+20*9f);
		cats[0].setY((centreY)+20*9f);
		cats[1].setX((centreX)-20*9f);
		cats[1].setY((centreY)-20*9f);
		*/
		
		
		
		
		//blocks
		ab=new animation[15][15];
		blocks = new Sprite[15][15];
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{
					ab[i][j]=new animation();
					ab[i][j].addScenes(block,250);
				}
			}
			
		}//merge two loops
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{
					blocks[i][j]=new Sprite(ab[i][j]);
				}
			}
			
		}
		
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)	
				if(i!=7 || j!=7)
				{
					blocks[i][j].setX((centreX)-20*8+j*20);
					blocks[i][j].setY((centreY)-20*8+i*20);
					
				}
		
		//rat
		a2 = new animation();
		a2.addScenes(rat,250);
		mouse = new Sprite(a2);
		mouse.setX((centreX)-20);
		mouse.setY((centreY)-20);
		mouse.setVx(0.0f);
		mouse.setVy(0.0f);
		
		
		
		
		
		
	}

	private void loadcats(Image face2) {
		cheese=new Image[catsnum];
		for(ci=0;ci<catsnum;ci++)
			cheese[ci]=new ImageIcon("E:\\New folder\\java\\game1\\res\\cheese.png").getImage();
		ac = new animation[catsnum];
		cats = new Sprite[catsnum];
		cheeseX = new int[catsnum];
		cheeseY = new int[catsnum];
		//catmoved=new int[catsnum];
		//catmoved[0]=0;
		//catmoved[1]=0;
		catmoved=new int[catsnum];
		cheeseflag=new int[catsnum];
		for(int i=0;i<catsnum;i++)
		{
			catmoved[i]=0;
			cheeseflag[i]=0;
		}
		deadcat=0;
		//cheeseflag = new int[catsnum];
		Ydisplacement=new int[catsnum];
		Ydisplacement[0]=20;
		Ydisplacement[1]=-20;
		Xdisplacement=new int[catsnum];
		Xdisplacement[0]=0;
		Xdisplacement[1]=0;
		Random generateXY=new Random();
		for(ci=0;ci<catsnum;ci++)
		{
			ac[ci]=new animation();
			ac[ci].addScenes(face2, 250);
			cats[ci] = new Sprite(ac[i]);
			
			cats[ci].setVx(0.0f);
			cats[ci].setVy(0.0f);
			int randY,rx;
			int randX,ry;
			do
			{
				rx=generateXY.nextInt(22)-11;
				ry=generateXY.nextInt(20)-10;
			 randX=(centreX+rx*20);
			 randY=(centreY+ry*20);
			}while(!(  ((randX<centreX-8*20)||(randX>centreX+7*20)) && ((randY<centreY-8*20)||(randY>centreY+7*20)) && (randX>=centreX-11*20) && (randY>=centreY-10*20)));
			cats[ci].setX(randX);
			cats[ci].setY(randY);
		}
		/*cats[0].setX((centreX)+20*9f);
		cats[0].setY((centreY)+20*9f);
		cats[1].setX((centreX)-20*9f);
		cats[1].setY((centreY)-20*9f);*/
	}
	
	public void init()
	{
		s=new screenmanager();
		DisplayMode dm=s.findFirstCompatibleMode();
		s.setFullScreen(dm);
		
		//Window w=s.getFullScreenWindow();
		running=true;
	}
	
	public void run()
	{
		
		try{
			init();
			centreX = s.getWidth()/2;
			centreY = s.getHeight()/2;
			loadPics();
			//cats.run();
			movieLoop();
		}finally{
			s.restoreScreen();
		}
	}
	public void stop()
	{
		running=false;
	}
	public void movieLoop()
	{
		long startingTime=System.currentTimeMillis();
		long sumTime=startingTime;
	
		while(running)
		{
			
			long timePassed=System.currentTimeMillis()-sumTime;
			sumTime+=timePassed;
			if(levelchanged)
			{
				catsnum++;
				loadcats(face2);
				levelchanged=false;
			}
			
			update(timePassed);
			//a.update(timePassed);
			
			Graphics2D g=s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			try{
				//System.out.println("coordinates: "+sprite.getX()+":"+sprite.getY());
				Thread.sleep(250);
				//cats.sleep(250);
			}catch(Exception e){}
		}
		
	}
	public void draw(Graphics g)
	{
		g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, 20));
		g.drawImage(bg, 0, 0, null);
		if(deadcat==catsnum)
		{
			
			g.drawString("Level Cleared!",centreX-70, 130);
			next=true;
			if(onlyafterlevel1==1)
			g.drawString("Press n to go to level "+(level+1) ,centreX-140, centreY+13*20);
		}
		g.drawString("Score : "+score, centreX+155, 130);
		
		if(gameon==0)
		{
			g.drawString("Game Over!", centreX-55, 130);
		}
		
		for(ci=0;ci<catsnum;ci++)
			g.drawImage(cats[ci].getImage(),Math.round(cats[ci].getX()),Math.round(cats[ci].getY()),null);
		//g.drawImage(sprite.getImage(),Math.round(sprite.getX()),Math.round(sprite.getY()),null);
		//g.drawImage(sprite1.getImage(),Math.round(sprite1.getX()),Math.round(sprite1.getY()),null);
		g.drawImage(mouse.getImage(),Math.round(mouse.getX()),Math.round(mouse.getY()),null);
		for(ci=0;ci<catsnum;ci++)
		if(cheeseflag[ci]==1)
		{
			g.drawImage(cheese[ci], cheeseX[ci], cheeseY[ci], null);
		}
		for(int i=0;i<23;i++)
			for(int j=0;j<25;j++)	
				if(i==0 || i==22 || j==0 || j==24)
					g.drawImage(wall, (centreX)-20*13+j*20, (centreY)-20*12+i*20, null);
		
		for(int i=0;i<15;i++)
			for(int j=0;j<15;j++)	
				if(i!=7 || j!=7)
				{
					g.drawImage(blocks[i][j].getImage(), Math.round(blocks[i][j].getX()), Math.round(blocks[i][j].getY()), null);
					
				}	
		/*else
			g.drawImage(rat, (centreX)-20*8+j*20, (centreY)-20*8+i*20, null);*/
	}
	public void update(long timePassed)
	{
		if(gameon==1)
		{
		switch(level)
		{
		case 1:
		{
			onlyafterlevel1=1;	
		for(int cind=0;cind<catsnum;cind++)
		{
		//System.out.println(cind);
			/*if(cats[cind].getX()<centreX - 11*20 )
		{
			Xdisplacement[cind]=0;
			
		}
			else if (cats[cind].getX()+1.5*cats[cind].getWidth()>=centreX + 11*20)
		{
			Xdisplacement[cind]=0;
			
		}*/
		//cats[cind].setX(cats[cind].getX()+Xdisplacement[cind]);
		
	
		if(cats[cind].getY()<centreY - 20*10 || Upblocked(cats[cind]))
		{
			if(!Downblocked(cats[cind])&&!(cats[cind].getY()+cats[cind].getHeight()>=centreY + 20*10))
				Ydisplacement[cind]=20;
			else
				Ydisplacement[cind]=0;
			if(!Leftblocked(cats[cind])&&!(cats[cind].getX()<centreX - 11*20))
			{
				//cats[cind].setX(cats[cind].getX()-20);
				Xdisplacement[cind]=-20;
			}
			else if(!Rightblocked(cats[cind])&&!(cats[cind].getX()+1.5*cats[cind].getWidth()>=centreX + 11*20))
			{
				//cats[cind].setX(cats[cind].getX()+20);
				Xdisplacement[cind]=20;
			} 
		}
		
		else if(cats[cind].getY()+cats[cind].getHeight()>=centreY + 20*10 || Downblocked(cats[cind]))
		{
			if(!Upblocked(cats[cind])&&!(cats[cind].getY()<centreY - 20*10))
				Ydisplacement[cind]=-20;
			else
				Ydisplacement[cind]=0;
		
			Random rnd = new Random();
			if((rnd.nextInt())%2==0)
			{
			if(!Leftblocked(cats[cind])&&!(cats[cind].getX()<centreX - 11*20))
			{
				//cats[cind].setX(cats[cind].getX()-20);
				Xdisplacement[cind]=-20;
			}
			else if(!Rightblocked(cats[cind])&&!(cats[cind].getX()+1.5*cats[cind].getWidth()>=centreX + 11*20))
			{
				//cats[cind].setX(cats[cind].getX()+20);
				Xdisplacement[cind]=20;
			}
			}
			else
			{
				
				if(!Rightblocked(cats[cind])&&!(cats[cind].getX()+1.5*cats[cind].getWidth()>=centreX + 11*20))
				{
					//cats[cind].setX(cats[cind].getX()+20);
					Xdisplacement[cind]=20;
				}
				else if(!Leftblocked(cats[cind])&&!(cats[cind].getX()<centreX - 11*20))
				{
					//cats[cind].setX(cats[cind].getX()-20);
					Xdisplacement[cind]=-20;
				}
			}

		}
		
		if(cheeseflag[cind]==1)
		{
			Ydisplacement[cind]=0;
			Xdisplacement[cind]=0;
		}
		if((!Upblocked(cats[cind])||!Downblocked(cats[cind]))&&(cheeseflag[cind]==0))
		{
		cats[cind].setY(cats[cind].getY()+Ydisplacement[cind]);
		cats[cind].update(timePassed);
		}
		if(!Rightblocked(cats[cind])||!Leftblocked(cats[cind])&&(cheeseflag[cind]==0))
		{
			cats[cind].setX(cats[cind].getX()+Xdisplacement[cind]);
			Xdisplacement[cind]=0;
			cats[cind].update(timePassed);
		}
		if(Upblocked(cats[cind])&&Downblocked(cats[cind])&&Rightblocked(cats[cind])&&Leftblocked(cats[cind]))	
		{
			cheeseflag[cind]=1;
			cheeseX[cind]=(int)cats[cind].getX();
			cheeseY[cind]=(int)cats[cind].getY();
			score=score+20;
			if(catmoved[cind]==0)
			{
			cats[cind].setX((centreX)-20*13+cind*20);
			cats[cind].setY((centreY)+20*14);
			cats[cind].update(timePassed);
			catmoved[cind]=1;
			deadcat++;
			}
		}
		if(cats[cind].getX()==mouse.getX()&&cats[cind].getY()==mouse.getY())
		{
		gameon=0;
		}
		/*
		if(cheeseflag[cind]==1)
		{
			Ydisplacement[cind]=0;
			Xdisplacement[cind]=0;
		}
		if((!Upblocked(cats[cind])||!Downblocked(cats[cind]))&&(cheeseflag[cind]==0))
		{
		cats[cind].setY(cats[cind].getY()+Ydisplacement[cind]);
		cats[cind].update(timePassed);
		}
		if(!Rightblocked(cats[cind])||!Leftblocked(cats[cind])&&(cheeseflag[cind]==0))
		{
			cats[cind].setX(cats[cind].getX()+Xdisplacement[cind]);
			Xdisplacement[cind]=0;
			cats[cind].update(timePassed);
		}
		
		if(Upblocked(cats[cind])&&Downblocked(cats[cind])&&Rightblocked(cats[cind])&&Leftblocked(cats[cind]))	
		{
			cheeseflag[cind]=1;
			cheeseX[cind]=(int)cats[cind].getX();
			cheeseY[cind]=(int)cats[cind].getY();
			score=score+20;
			if(catmoved[cind]==0)
			{
			cats[cind].setX((centreX)-20*13+cind*20);
			cats[cind].setY((centreY)+20*14);
			cats[cind].update(timePassed);
			catmoved[cind]=1;
			deadcat++;
			}
		}
		if(cats[cind].getX()==mouse.getX()&&cats[cind].getY()==mouse.getY())
		{
			gameon=0;
		}*/
		}
		
		mouse.update(timePassed);
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{
					blocks[i][j].update(timePassed);
				}
			}
			
		}
		}
			;
			break;
			
			case 2:
			{
				
				onlyafterlevel1=2;
				
				for(int cind=0;cind<catsnum;cind++)
				{
					Random random2=new Random();
					int XorY=(random2.nextInt())%2;
					if(XorY==1)
					{
					L2moveX(cind);
					}
					else
					{
						L2moveY(cind);
						
					}
					if(cheeseflag[cind]==1)
					{
						Ydisplacement[cind]=0;
						Xdisplacement[cind]=0;
					}	
				if((!Upblocked(cats[cind])||!Downblocked(cats[cind]))&&(cheeseflag[cind]==0))
				{
				cats[cind].setY(cats[cind].getY()+Ydisplacement[cind]);
				Ydisplacement[cind]=0;
				cats[cind].update(timePassed);
				}
				if(!Rightblocked(cats[cind])||!Leftblocked(cats[cind])&&(cheeseflag[cind]==0))
				{
					cats[cind].setX(cats[cind].getX()+Xdisplacement[cind]);
					Xdisplacement[cind]=0;
					cats[cind].update(timePassed);
				}
				
				if(Upblocked(cats[cind])&&Downblocked(cats[cind])&&Rightblocked(cats[cind])&&Leftblocked(cats[cind]))	
				{
					cheeseflag[cind]=1;
					cheeseX[cind]=(int)cats[cind].getX();
					cheeseY[cind]=(int)cats[cind].getY();
					score=score+20;
					if(catmoved[cind]==0)
					{
					cats[cind].setX((centreX)-20*13+cind*20);
					cats[cind].setY((centreY)+20*14);
					cats[cind].update(timePassed);
					catmoved[cind]=1;
					deadcat++;
					}
				}
				if(cats[cind].getX()==mouse.getX()&&cats[cind].getY()==mouse.getY())
				{
					gameon=0;
				}
				}
				
				
				
				
				
				mouse.update(timePassed);
				for(int i=0;i<15;i++)
				{
					for(int j=0;j<15;j++)
					{
						if(i!=7||j!=7)
						{
							blocks[i][j].update(timePassed);
						}
					}
					
				}
				
			};break;
			
			
			
	
	}
			
	}
	}

	private void L2moveY(int cind) {
		if(cats[cind].getY()<mouse.getY()&&!Downblocked(cats[cind]))
		{
			Xdisplacement[cind]=0;
			Ydisplacement[cind]=20;
		}
		else if(cats[cind].getY()>mouse.getY()&&!Upblocked(cats[cind]))
		{
			Xdisplacement[cind]=0;
			Ydisplacement[cind]=-20;
		}
		else if(cats[cind].getY()==mouse.getY())
		{
			if((Rightblocked(cats[cind])&&cats[cind].getX()<mouse.getX())||(Leftblocked(cats[cind])&&cats[cind].getX()>mouse.getX()))
			{
			Random random1=new Random();
			int a=random1.nextInt()%2;
			if(!Upblocked(cats[cind])&&a==0)
			{Xdisplacement[cind]=0;
			Ydisplacement[cind]=-20;}
			if(!Downblocked(cats[cind])&&a==1)
			{Xdisplacement[cind]=0;
			Ydisplacement[cind]=20;}
			}
			else
			{
				//L2moveX(cind);
			}
		}
	}

	private void L2moveX(int cind) {
		if(cats[cind].getX()<mouse.getX()&&!Rightblocked(cats[cind]))
		{
			Xdisplacement[cind]=20;
			Ydisplacement[cind]=0;
		}
		else if(cats[cind].getX()>mouse.getX()&&!Leftblocked(cats[cind]))
		{
			Xdisplacement[cind]=-20;
			Ydisplacement[cind]=0;
		}
		else if(cats[cind].getX()==mouse.getX())
		{
			if((Upblocked(cats[cind])&&cats[cind].getY()>mouse.getY())||(Downblocked(cats[cind])&&cats[cind].getY()<mouse.getY()))
			{
			Random random3=new Random();
			int b=random3.nextInt()%2;
			if(!Leftblocked(cats[cind])&&b==0)
			{Xdisplacement[cind]=-20;
			Ydisplacement[cind]=0;}
			if(!Rightblocked(cats[cind])&&b==1)
			{Xdisplacement[cind]=20;
			Ydisplacement[cind]=0;}
			}
			
			else
			{
				//L2moveY(cind);
			}
		}
	}
		
	
	public boolean Upblocked(Sprite cat)
	{	
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{
					//if(cat[ci].getY()-20==blocks[i][j].getY()&&cat[ci].getX()==blocks[i][j].getX()||!(cat[ci].getY()>=centreY - 20*10))
					if((cat.getY()-20==blocks[i][j].getY()&&cat.getX()==blocks[i][j].getX())||!(cat.getY()>=centreY - 20*10))
					{
						return true;
					}
					
				}
			}
		}
		return /*cat.getX()>=centreX+11*20?true:*/false;
	}
	
	public boolean Rightblocked(Sprite cat)
	{	//for(int cind=0;cind<catsnum;cind++)
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{
					if((cat.getX()+20==blocks[i][j].getX()&&cat.getY()==blocks[i][j].getY())||!(cat.getX()<centreX + 10*20))
					{
						return true;
					}
					
				}
			}
			
		}
		return false;
	}
	
	public boolean Downblocked(Sprite cat)
	{	//for(int cind=0;cind<catsnum;cind++)
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{		
					if((cat.getY()+cat.getHeight()==blocks[i][j].getY()&&cat.getX()==blocks[i][j].getX())||!(cat.getY()<centreY + 20*9))
					{
						
						return true;
					}
					
				}
			}
			
		}
		return /*cat.getX()>=centreX+11*20?true:*/false;
	}
	
	public boolean Leftblocked(Sprite cat)
	{	//for(int cind=0;cind<catsnum;cind++)
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				if(i!=7||j!=7)
				{
					if((cat.getX()-20==blocks[i][j].getX()&&cat.getY()==blocks[i][j].getY())||!(cat.getX()>=centreX - 11*20))
					{
						return true;
					}
					
				}
			}
			
		}
		return false;
	}
	
	public void RBPressed()
	{
		float x,y;
		int flag=0,flag2=0,a=0,b=0;
		x=mouse.getX();
		y=mouse.getY();
		//System.out.println(x+" "+blocks[7][8].getX());
		if(mouse.getX()+1.5*mouse.getWidth()<=centreX + 11*20/*&&sprite.getX()!=sprite2.getX()+sprite2.getWidth()*/)
		{
			
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					
					if(i!=7||j!=7)
					{
						//System.out.println(blocks[i][j].getX() +" "+ blocks[i][j].getY());
						if(blocks[i][j].getX()==x+20.0f  && blocks[i][j].getY()== y)
						{
							flag=1;
							a=i;b=j;
						}
						/*else
						{
							flag=0;
							System.out.println(1);
						}*/
					}
				}
				
			}
			if(flag==1)
			{
				flag2=moveblock(a,b,'r');
			}
			else
				{mouse.setX(mouse.getX()+20.0f);
				
				}
			
			if(flag2==1)
				mouse.setX(mouse.getX()+20.0f);
					
		}
		for(ci=0;ci<catsnum;ci++)
		if(mouse.getX()==cheeseX[ci]&&mouse.getY()==cheeseY[ci])
		{
			if(cheese[ci]!=null)
			score=score+10;
			cheese[ci]=null;
			
		}
	}
	
	public void LBPressed()
	{
		
		float x,y;
		int flag=0,flag2=0,a=0,b=0;
		x=mouse.getX();
		y=mouse.getY();
		if(mouse.getX()>=centreX - 11*20/*&&sprite.getX()!=sprite2.getX()-20*/)
		{
			
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					
					if(i!=7||j!=7)
					{
						if(blocks[i][j].getX()==x-20.0f  && blocks[i][j].getY()== y)
						{
							//System.out.println(1);

							flag=1;
							a=i;b=j;
						}
						/*else
						{
							flag=0;
						}*/
					}
				}
				
			}
			if(flag==1)
			{
				flag2=moveblock(a,b,'l');
			}
			else
				mouse.setX(mouse.getX()-20.0f);
			if(flag2==1)
				mouse.setX(mouse.getX()-20.0f);
		}
		
		
		for(ci=0;ci<catsnum;ci++)
			if(mouse.getX()==cheeseX[ci]&&mouse.getY()==cheeseY[ci])
			{
				if(cheese[ci]!=null)
					score=score+10;
				cheese[ci]=null;
			}
		
	}
	

	public void UBPressed()
	{
		
		float x,y;
		int flag=0,flag2=0,a=0,b=0;
		x=mouse.getX();
		y=mouse.getY();
		//System.out.println(x+" "+blocks[7][8].getX());
		if(mouse.getY()>=centreY - 20*10)
		{
			
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					
					if(i!=7||j!=7)
					{
						//System.out.println(blocks[i][j].getX() +" "+ blocks[i][j].getY());
						if(blocks[i][j].getX()==x  && blocks[i][j].getY()== y-20.0f)
						{

							flag=1;
							a=i;b=j;
						}
						/*else
						{
							flag=0;
							System.out.println(1);
						}*/
					}
				}
				
			}
			//System.out.println("\n"+flag);
			if(flag==1)
			{
				flag2=moveblock(a,b,'u');
			}
			else
				mouse.setY(mouse.getY()-20.0f);
			if(flag2==1)
				mouse.setY(mouse.getY()-20.0f);
		}
		
		
		for(ci=0;ci<catsnum;ci++)
			if(mouse.getX()==cheeseX[ci]&&mouse.getY()==cheeseY[ci])
			{
				if(cheese[ci]!=null)
					score=score+10;
				cheese[ci]=null;
			}
		
	}
	

	public void DBPressed()
	{
		
		float x,y;
		int flag=0,flag2=0,a=0,b=0;
		x=mouse.getX();
		y=mouse.getY();
		//System.out.println(x+" "+blocks[7][8].getX());
		if(mouse.getY()+mouse.getHeight()<centreY + 20*10)
		{
			
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					
					if(i!=7||j!=7)
					{
						//System.out.println(blocks[i][j].getX() +" "+ blocks[i][j].getY());
						if(blocks[i][j].getX()==x  && blocks[i][j].getY()== y+20.0f)
						{

							flag=1;
							a=i;b=j;
						}
						/*else
						{
							flag=0;
							System.out.println(1);
						}*/
					}
				}
				
			}
			//System.out.println("\n"+flag);
			if(flag==1)
			{
				flag2=moveblock(a,b,'d');
			}
			else
				mouse.setY(mouse.getY()+20.0f);
			if(flag2==1)
				mouse.setY(mouse.getY()+20.0f);
		}
		
		for(ci=0;ci<catsnum;ci++)
			if(mouse.getX()==cheeseX[ci]&&mouse.getY()==cheeseY[ci])
			{
				if(cheese[ci]!=null)
					score=score+10;
				cheese[ci]=null;			}
	}
	
	
	
	
	
	
	public int moveblock(int x,int y,char z)
	{
		int dx=0,dy=0;
		boolean t=false;
		float x1,y1;
		int flag2=0,flag=0,a=0,b=0;
		x1=blocks[x][y].getX();
		y1=blocks[x][y].getY();
		
		switch(z)
		{
		case 'r':
			dx=20;dy=0;
			t=(boolean)(x1+1.5*blocks[x][y].getWidth()<=centreX + 11*20);
			break;
		case 'l':
			dx=-20;dy=0;
			t=(boolean)(blocks[x][y].getX()>=centreX - 11*20);
			break;
			
		case 'u':
			dx=0;dy=-20;
			t=(boolean)(blocks[x][y].getY()>=centreY - 20*10/*&&(blocks[x][y].getY()-20!=cats[ci].getY()||blocks[x][y].getX()!=cats[ci].getX())*/);break;
			
		case 'd':
			dx=0;dy=20;
			t=(boolean)(blocks[x][y].getY()+blocks[x][y].getHeight()<centreY + 20*10/*&&(blocks[x][y].getY()+blocks[x][y].getHeight()!=cats[ci].getY()||blocks[x][y].getX()!=cats[ci].getX())*/);
			break;
		}
		
		
		for(int cind=0;cind<catsnum;cind++)
		{
			t=t && (boolean)((blocks[x][y].getY()+dy!=cats[cind].getY()||blocks[x][y].getX()+dx!=cats[cind].getX()));
			if(!t)
				break;
		}
				
			if(t)
			{				
				for(int i=0;i<15;i++)
				{
					for(int j=0;j<15;j++)
					{
						if(i!=7||j!=7)
						{
							if(blocks[i][j].getX()== x1+dx && blocks[i][j].getY()== y1+dy)
							{
								flag=1;
								a=i;b=j;
							}
						}
					}
					
				}
				
				if(flag==1)
				{
					flag2=moveblock(a,b,z);
				}
				else 
				{
					blocks[x][y].setX((float)(blocks[x][y].getX()+dx));
					blocks[x][y].setY((float)(blocks[x][y].getY()+dy));
					for(int cind = 0;cind<catsnum;cind++)
					if(blocks[x][y].getX()==cheeseX[cind]&&blocks[x][y].getY()==cheeseY[cind])
					{
						cheese[cind]=null;
					}
					return 1;
				}
					
				if(flag2!=0)
					{
					blocks[x][y].setX((float)(blocks[x][y].getX()+dx));
					blocks[x][y].setY((float)(blocks[x][y].getY()+dy));
					for(int cind = 0;cind<catsnum;cind++)
					if(blocks[x][y].getX()==cheeseX[cind]&&blocks[x][y].getY()==cheeseY[cind])
					{
							cheese[cind]=null;
					}
					return 1;
					}
					else
						return 0;
			}
			else
				return 0;
		
			
	}
	
	
	
	
}
	