public class DAY_RECORD{//Ãþ¦ücªºstructure
	public int year;
	public int month;
	public int day;
	public int breakfast_price;
	public int lunch_price;
	public int dinner_price;
	public String other;
	public DAY_RECORD(String initial_string, int initial_int){
		year			= initial_int;
		month			= initial_int;
		day				= initial_int;
		breakfast_price	= initial_int;
		lunch_price		= initial_int;
		dinner_price	= initial_int;
		other			= initial_string;
	}
}