public class student
{
	//Field 
	public String name;
	public int energy;
	
	//Mwthod
	public void sleep()
	{
		this.energy = this.energy + 20;
		this.eat();
	}
	public void eat()
	{
		this.energy = this.energy + 10;
	}
	public void exercide()
	{
		this.energy = this.energy - 15;
	}
}