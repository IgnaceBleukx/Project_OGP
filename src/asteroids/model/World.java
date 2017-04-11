package asteroids.model;

import java.util.HashSet;
import java.util.Set;

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
	public Set<Ship> getAllShips() {
		return allShips;
	}
	
	public void setAllShips(Set<Ship> allShips){
		this.allShips = allShips;
		
	}
	
	public void addShipToWorld(World world, Ship ship) throws IllegalArgumentException{
		try{
		world.allShips.add(ship);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	public void removeShipFromWorld(World world, Ship ship) throws IllegalArgumentException{
		try{
		world.allShips.remove(ship);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
		
	}
		
	public Set<Bullet> getAllBullets() {
		return allBullets;
	}

	public void setAllBullets(Set<Bullet> allBullets){
		this.allBullets = allBullets;
		
	}
	
	public void addBulletToWorld(World world, Bullet bullet) throws IllegalArgumentException{
		try{
		world.allBullets.add(bullet);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
		
	}
	
	public void removeBulletToWorld(World world, Bullet bullet) throws IllegalArgumentException{
		try{
		world.allBullets.remove(bullet);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	



	private double width;
	private double height;
	private Set<Ship> allShips = new HashSet<Ship>();
	private Set<Bullet> allBullets = new HashSet<Bullet>();
}
