package gui01;

import java.security.SecureRandom;
import java.util.Random;

public class NameFactory {
	public String createName(String type)
	{	
		String name = "noname";
		if (type.equalsIgnoreCase("enemy")) 
		{
			int random = (int)(Math.random() * (21));	// Make sure the number is +1 of the highest value
			if (random == 0) 	 name = ("The Mad Barnacle"); 
			else if(random == 1) name = ("The Wandering Shame"); 
			else if(random == 2) name = ("The Fear of Hades");
			else if(random == 3) name = ("The Sad Murderer"); 
			else if(random == 4) name = ("The Black Anger"); 
			else if(random == 5) name = ("The Howling Hangman"); 
			else if(random == 6) name = ("The Brotherhood Lord"); 
			else if(random == 7) name = ("The Choral Thunder"); 
			else if(random == 8) name = ("The Pride of the East"); 
			else if(random == 9) name = ("The Curse Madness"); 
			else if(random == 10)name = ("The Fallen Wolf of the Sea"); 
			else if(random == 11)name = ("The Mad Serpant"); 
			else if(random == 12)name = ("The Bloody Cannon"); 
			else if(random == 13)name = ("Sea's Plunder"); 
			else if(random == 14)name = ("The Dishonourable Slave"); 
			else if(random == 15)name = ("The Screaming Mermaid"); 
			else if(random == 16)name = ("The Barnacle Bucket"); 
			else if(random == 17)name = ("The Vile Compass"); 
			else if(random == 18)name = ("The Pirate's Deceit"); 
			else if(random == 19)name = ("The Scurvy Killer of the Ocean"); 
			else if(random == 20)name = ("Pirates Lust"); 
		}
		if (type.equalsIgnoreCase("civilian")) 
		{
			int random = (int)(Math.random() * (23));	// Make sure the number is +1 of the highest value
			if (random == 0) 	 name = ("Leslie"); 
			else if(random == 1) name = ("The Wooden Boat"); 
			else if(random == 2) name = ("Gary Barlow");
			else if(random == 3) name = ("John Owens"); 
			else if(random == 4) name = ("HMS William Washington"); 
			else if(random == 5) name = ("HMS Manilow"); 
			else if(random == 6) name = ("The Brotherhood Lord"); 
			else if(random == 7) name = ("Barbara's Safety"); 
			else if(random == 8) name = ("The Cow Bashers"); 
			else if(random == 9) name = ("The Ancestral Eagle"); 
			else if(random == 10) name = ("The Defiant Smuggler"); 
			else if(random == 11) name = ("Squeezebox hooligan"); 
			else if(random == 12) name = ("The Exalted Vigor"); 
			else if(random == 13) name = ("The Nomad's Rampart"); 
			else if(random == 14) name = ("The Benzotinian Caravaneer"); 
			else if(random == 15) name = ("The Beer Space Truck"); 
			else if(random == 16) name = ("Mysterious Space Samurai"); 
			else if(random == 17) name = ("Butter Beagle 8.0"); 
			else if(random == 18) name = ("Wandering Stranger"); 
			else if(random == 19) name = ("Space Hulk"); 
			else if(random == 20) name = ("Duel Alchemists of the Sky"); 
			else if(random == 21) name = ("Unmanned Mitsubishi Drone"); 	
			else if(random == 22) name = ("The Nightingale"); 	
		}
		if (type.equalsIgnoreCase("planet")) 
		{
			int random = (int)(Math.random() * (14));	// Make sure the number is +1 of the highest value
			if (random == 0) 	 name = "Tazgonia"; 
			else if(random == 1) name = "The Rajosphere"; 
			else if(random == 2) name = "Curios C.a.T";
			else if(random == 3) name = "Finous Prime"; 
			else if(random == 4) name = "Rear Alliance"; 
			else if(random == 5) name = "Unification of Free Killers"; 
			else if(random == 6) name = "Space Monarchy"; 
			else if(random == 7) name = "Republic of Asda"; 
			else if(random == 8) name = "Tyrannical Imperial BakeOff"; 
			else if(random == 9) name = "The Honourable Federation of Bakers"; 
			else if(random == 10) name = "Bhangra Nation"; 
			else if(random == 11) name = "Sandwich Collective"; 
			else if(random == 12) name = "The Baking Imperium"; 
			else if(random == 13) name = "The Corporate Coalition of Ingredients"; 
		}
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("" + name);
		return name;
	}

}
