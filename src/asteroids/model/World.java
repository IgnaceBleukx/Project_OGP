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
	
	public void addShipToWorld(Ship ship) throws IllegalArgumentException{
		try{
		this.allShips.add(ship);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	public void removeShipFromWorld(Ship ship) throws IllegalArgumentException{
		try{
		this.allShips.remove(ship);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
		
	}
		
	public Set<Bullet> getAllBullets() {
		return allBulletsWorld;
	}

	public void setAllBullets(Set<Bullet> allBullets){
		this.allBulletsWorld = allBullets;
		
	}
	
	public void addBulletToWorld(Bullet bullet) throws IllegalArgumentException{
		try{
		this.allBulletsWorld.add(bullet);
		bullet.setWorld(this);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
		
	}
	
	public void removeBulletToWorld(Bullet bullet) throws IllegalArgumentException{
		try{
		this.allBulletsWorld.remove(bullet);
		}
		catch(IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}
	
	



	private double width;
	private double height;
	private Set<Ship> allShips = new HashSet<Ship>();
	private Set<Bullet> allBulletsWorld = new HashSet<Bullet>();
	
}
