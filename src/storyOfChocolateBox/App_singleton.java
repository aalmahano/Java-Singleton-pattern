package storyOfChocolateBox;

import java.util.ArrayList;
import java.util.Random;

public class App_singleton {

	public static void main(String[] args) {
		
		// We buy a box of filled chocolate
		ChocolateBox box = ChocolateBox.BuyChocolateBox();
		
		// Achtung! There are people who wants ours Chocolates!
		System.out.println("Suddenly, 4 people introduced us and ask for the box...");
		ArrayList<Person> people = new ArrayList<>();
		people.add(Person.MyNameIs("Martha"));
		people.add(Person.MyNameIs("Bruce"));
		people.add(Person.MyNameIs("Clark"));
		people.add(Person.MyNameIs("Lois"));
		
		Random r = new Random();
		// Well... I guess I could share a few with them
		System.out.println("We know how many times they open it...");
		while(box.ThereAreChocolates())
		{
			box.PickupOneChocolate(people.get(r.nextInt(4)));
		}
		
		// Ahhh... Something is rotten in the state of Denmark!! 
		System.out.println("...but we don't know how many chocolate at once they picked up!");
		for(Person p: people)
		{
			System.out.println("	" + p.WhoAmI() + " eat " + p.HowManyBonbonsEaten()+" pieces"); 
		}
		
		// Are you sure that we may buy another box?
		ChocolateBox box2 = ChocolateBox.BuyChocolateBox();
		
		if(box2.ThereAreChocolates())
		{
			System.out.println("Chocolate everywhere!");
		}
		else
		{
			System.out.println("What the hell? There not left any chocolate and I can't buy another box!");
		}
		// The end
	}
}

class ChocolateBox
{
	private int chocolateLeft;
	private int chocolateTaken;
	private static ChocolateBox newBox = new ChocolateBox();
	private Random r = new Random();
	
	private ChocolateBox()
	{
		// Number of Chocolates between 10-30
		chocolateLeft =r.nextInt(20)+10;
		System.out.println("Someone bought a box of filled chocolate...");
	}
	
	public static ChocolateBox BuyChocolateBox()
	{
		return newBox;
	}
	
	public void PickupOneChocolate(Person person)
	{
		if(ThereAreChocolates())
		{
			// Random number of chocolate 
			chocolateTaken = r.nextInt(2)+1;
			chocolateLeft-=chocolateTaken;
			System.out.println("	- " + person.WhoAmI() + " says: Mmmm... delicious!");
			person.EatChocolate(chocolateTaken);
		}
		else
		{
			System.out.println("	- " + person.WhoAmI() + " couldn't pick up any chocolate :(");
		}
	}
	
	public boolean ThereAreChocolates()
	{
		return (chocolateLeft>0)? true: false;
	}
}

class Person 
{
	private String name;
	private int chocolateEaten;
	
	private Person(String name)
	{
		this.name = name;
		System.out.println("	Hi everyone! My name is "+ this.name);
	}
	
	public static Person MyNameIs(String name)
	{
		return new Person(name);
	}
	
	public String WhoAmI()
	{
		return this.name;
	}
	
	public void EatChocolate(int chocolate)
	{
		this.chocolateEaten += chocolate;
	}
	
	public int HowManyBonbonsEaten()
	{
		return this.chocolateEaten;
	}
}
