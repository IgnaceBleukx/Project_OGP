package asteroids.model;

public class World {

	public World (double width, double height){
		this.setDimension(width, height);
	}
	
	public void setDimension(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	public double[] getDimension(){
		return new double[]{this.width, this.height};
	}
	
	public boolean isValidDimension(double width, double height){
		return true;
	}
	private double width;
	private double height;
}
