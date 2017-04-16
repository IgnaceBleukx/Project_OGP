import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;

public class Object {
	
	public double[] getVelocity(){
		double[] velocity;
		velocity = new double[] {this.xVelocity, this.yVelocity};
		return velocity;		
	}
	
	public void setVelocity(double xVelocity, double yVelocity){
			this.xVelocity = xVelocity;
			this.yVelocity = yVelocity;	
			}
	
	private double xVelocity;
	private double yVelocity;

}
