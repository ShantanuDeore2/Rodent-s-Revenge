package game1;
import java.awt.Image;

public class Sprite {

private animation a;
private float x;
private float y;
private float vx;
private float vy;
	
public Sprite()
{
	
}
public Sprite(animation a)
{
	this.a=a;
	vx=0.0f;
	vy=0.0f;
}

public synchronized void  update(long timePassed)
{
	y=y+vy*timePassed;
	x=x+vx*timePassed;
	a.update(timePassed);
}

public float getX() {
	return x;
}

public void setX(float x) {
	this.x = x;
}

public float getY() {
	return y;
}

public void setY(float y) {
	this.y = y;
}

public float getVx() {
	return vx;
}

public void setVx(float vx) {
	this.vx = vx;
}

public float getVy() {
	return vy;
}

public void setVy(float vy) {
	this.vy = vy;
}

public int getWidth()
{
return a.getImage().getWidth(null);	
}

public int getHeight()
{
return a.getImage().getHeight(null);	
}

public Image getImage()
{
return a.getImage();	
}
}
