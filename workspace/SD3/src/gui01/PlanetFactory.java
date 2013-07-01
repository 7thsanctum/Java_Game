package gui01;

import java.io.Serializable;

public class PlanetFactory implements Serializable {
	public PlanetFactory() {};
	
	public Planet createPlanet(String planettype)
	{
		Planet planet = null;
		
		if (planettype.equalsIgnoreCase("forge")) 
		{
			planet = new Forge();
			System.out.println("The gods have created a new forgeworld!");
		} 
		else if(planettype.equalsIgnoreCase("military_weak"))
		{
			planet = new Military(-1);
			System.out.println("The gods have created a new Weak Military World!");
		}
		else if(planettype.equalsIgnoreCase("military_med"))
		{
			planet = new Military(0);
			System.out.println("The gods have created a new Medium Military World!");
		}
		else if(planettype.equalsIgnoreCase("military_strong"))
		{
			planet = new Military(1);
			System.out.println("The gods have created a new Strong Military World!");
		}
		else if(planettype.equalsIgnoreCase("economic"))
		{
			planet = new Economic();
			System.out.println("The gods have created a new Economic World!");
		}
		if( planet == null)
		{
			System.out.println(planettype + " didn't get made properly");
		}
		
		return planet;
	}
	
	public Planet createPlanet()
	{
		Planet planet = null;
		int random = (int)(Math.random() * (5));
		if (random == 0) 
		{
			planet = new Forge();
			System.out.println("The gods have created a new forgeworld!");
		} 
		else if(random == 1)
		{
			planet = new Military(-1);
			System.out.println("The gods have created a new Weak Military World!");
		}
		else if(random == 2)
		{
			planet = new Military(0);
			System.out.println("The gods have created a new Medium Military World!");
		}
		else if(random == 3)
		{
			planet = new Military(1);
			System.out.println("The gods have created a new Strong Military World!");
		}
		else if(random == 4)
		{
			planet = new Economic();
			System.out.println("The gods have created a new Economic World!");
		}
		if(planet == null)
		{
			System.out.println(" Random planet didn't get generated properly");
		}
		
		return planet;
	}
}
