package game1;
import java.awt.Image;
import java.util.ArrayList;

public class animation {
private ArrayList<OneScene> scenes;
private int sceneIndex;
private long totalTime;
private long movieTime;

public animation()
{
	scenes=new ArrayList<OneScene>();
	totalTime=0;
	start();
}
public synchronized void addScenes(Image i,long t)
{
	totalTime+=t;
	scenes.add(new OneScene(i,t));
}
public synchronized void start()
{
movieTime=0;
sceneIndex=0;
}
public synchronized void update(long timePassed)
{
if(scenes.size()>=1)
{
movieTime+=timePassed;
if(movieTime>=totalTime)
{
movieTime=0;
sceneIndex=0;
}
if(movieTime>=getScene(sceneIndex).endTime)
{
sceneIndex=(sceneIndex+1);	
}
}
}
public synchronized Image getImage()
	{
		if(scenes.size()==0)
			return null;
		else
			return getScene(sceneIndex).pic;
	}

private OneScene getScene(int x)
{
return (OneScene)scenes.get(x);	
}

private class OneScene{
	Image pic;
	long endTime;
	public OneScene(Image pic, long endTime)
	{
		this.pic=pic;
		this.endTime=endTime;
	}
	
}

}
