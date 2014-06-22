public class program01
{
	public static void main (String[] args)
	{
		student stu1 = new student();   //set up the object
		student stu2 = new student();   //set up the object
		//initize
		stu1.name = "John";
		stu1.energy = 60;
		stu2.name = "Mary";
		stu2.energy = 60;
		//print to the screen
		System.out.print("    stu1.name = "+ stu1.name);
		System.out.println("  stu1.energy = "+ stu1.energy);
		System.out.print("    stu2.name = "+ stu2.name);
		System.out.println("  stu2.energy = "+ stu2.energy);
		
		stu1.sleep();
		stu2.exercide();
		//print to the screen
		System.out.print("    stu1.name = "+ stu1.name);
		System.out.println("  stu1.energy = "+ stu1.energy);
		System.out.print("    stu2.name = "+ stu2.name);
		System.out.println("  stu2.energy = "+ stu2.energy);
	}
	
}
