import java.io.*;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;
public class daybook {
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";	
	public static void  main(String arg[]) {
		//-----------------------------declare variable-----------------------------
		int i;
		int year,month,day,hour,minute,second;
		int daynum;//EX if month=6 then daynum = 30
		int daybooktxt_num=0;//there are 4 record(2013-07-12 ~ 2013-07-15)then daybooktxt_num=4
		boolean Leap_year = false; //Leap_year = true then this year is  Leap year
		boolean FileExist;//FileExist = true then daybook.txt file exist 
		String[] tokens = new String[5];
		String[] YMD_tokens = new String[3];//yyyy-MM-dd
		String[] HMS_tokens = new String[3];//HH:mm:ss
		String input_str;//when user input meal price,then store in this variable  
		
		DAY_RECORD[] day_record = new DAY_RECORD[300];
		for(i=0;i<300;i++){//initial the day record
			day_record[i] = new DAY_RECORD("",-1);
		}
		//--------------invoking console for read from command line-----------------
		Console c = System.console(); // invoking console
		if (c == null){// If the Console object is available, this method returns it. otherwise it return NULL 
			System.err.println("No console.");
			System.exit(1);
		}
		try{
			while(true){
				//---------------------read the current time---------------------
				String current_time = daybook.now();
				tokens = current_time.split("\\s",-1);
				YMD_tokens = tokens[0].split("-",-1);
				HMS_tokens = tokens[1].split(":",-1);
				year   = Integer.parseInt(YMD_tokens[0]);
				month  = Integer.parseInt(YMD_tokens[1]);
				day    = Integer.parseInt(YMD_tokens[2]);
				hour   = Integer.parseInt(HMS_tokens[0]);
				minute = Integer.parseInt(HMS_tokens[1]);
				second = Integer.parseInt(HMS_tokens[2]);
				System.out.println("--------  Time : " + current_time + "  --------" );
				/*//----------------------------TEST----------------------------
				for(i=0;i<YMD_tokens.length;i++)	System.out.println("YMD_tokens[" + i +"] = " + YMD_tokens[i]);
				for(i=0;i<HMS_tokens.length;i++)	System.out.println("HMS_tokens[" + i +"] = " + HMS_tokens[i]);*/
				Leap_year = (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0);
				//System.out.println(Leap_year);
				//----------------------check daybook.txt exist or not--------------------------
				File f = new File("daybook.txt");//use to check whether daybook.txt exist or not
				//   f.createNewFile() = false : file exist
				//   f.createNewFile() = true  : file not exist
				FileExist = ! f.createNewFile();				
				if(! FileExist){//File not exist at the beginning ,but have been created already.
					daybooktxt_num = write_record_into_txt(f,day_record,year,month,day,Leap_year,daybooktxt_num,"original_setup");
					day_record = read_record_from_txt(day_record,tokens,YMD_tokens);//update day_record variable
				}
				else{				//File exist ,and old record will not disappear
					day_record = read_record_from_txt(day_record,tokens,YMD_tokens);//update day_record variable	
					daybooktxt_num = return_daybooktxt_num(day_record);
					//if today is first day in the month,then create this month into daybook.txt 				
					if(day==1 && day_record[daybooktxt_num-1].month != month){ //daybooktxt_num-1 is the last one record in the file
						System.out.println("in else : day_record[daybooktxt_num].month = "+day_record[daybooktxt_num].month+"\t month = "+month); 
						daybooktxt_num = write_record_into_txt(f,day_record,year,month,day,Leap_year,daybooktxt_num,"extend_new_Month");
						day_record = read_record_from_txt(day_record,tokens,YMD_tokens);//update day_record variable
					}
				}
				for(i=0;i<daybooktxt_num;i++){//check whether user want to update the record
					if(day_record[i].year==year && day_record[i].month==month && day_record[i].day==day){
						if(hour>6 && hour<11 && day_record[i].breakfast_price==-1){//breakfast don't have any record
							input_str = c.readLine("How much do you spend on your breakfast ?");
							day_record[i].breakfast_price = Integer.parseInt(input_str);						
						}
						if(hour>13 && hour<18 && day_record[i].lunch_price==-1){//Lunch don't have any record
							input_str = c.readLine("How much do you spend on your lunch ?");
							day_record[i].lunch_price = Integer.parseInt(input_str);
						}
						if(hour>18 && day_record[i].dinner_price==-1){//Dinner don't have any record
							input_str = c.readLine("How much do you spend on your dinner ?");
							day_record[i].dinner_price = Integer.parseInt(input_str);
						}
					}
				}
				daybooktxt_num = write_record_into_txt(f,day_record,year,month,day,Leap_year,daybooktxt_num,"update_data");
				day_record = read_record_from_txt(day_record,tokens,YMD_tokens);//update day_record variable
				System.out.println("daybooktxt_num = " + daybooktxt_num);//There are "daybooktxt_num" record in daybook.txt.
				for(i=0;i<daybooktxt_num;i++){//Test
					System.out.println (day_record[i].year  + "-"+ 
										day_record[i].month + "-"+
										day_record[i].day   + "\t"+
										day_record[i].breakfast_price + "\t"+
										day_record[i].lunch_price     + "\t"+
										day_record[i].dinner_price    + "\t"+
										day_record[i].other
										);
				}
			}
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	public static String now(){//find the time in computer
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	public static int write_record_into_txt(File write_file,DAY_RECORD[] day_record,int year,int month,int day,boolean Leap_year,int record_num,String state){
		int daynum;
		int i; 
		try{
			//--------------------------declare for writing-----------------------
			//We don't need "DataOutputStream"
			FileOutputStream fos;
			BufferedWriter bw;
			if(state.equals("original_setup")){
				System.out.println("in original state");//Test
				fos = new FileOutputStream(write_file);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("Date\tBreakfast\tLunch\tDinner\tOthers");
				bw.newLine();
				if(month==1 || month==3 || month==5 || month==7|| month==8|| month==10 || month==12)
					daynum = 31;
				else if(month==4 || month==6 || month==9 || month==11)
					daynum = 31;
				else{//month = 2
					if(Leap_year)
						daynum = 29;
					else
						daynum = 28;
				}
				for(i=0;i<(daynum-day+1);i++){
					bw.write(year+"-"+month+"-"+(day+i)+"\t\t\t\t");
					bw.newLine();
				}
				record_num = daynum-day+1;//There are "record_num" record in daybook.txt.
				bw.flush();
			}
			else if(state.equals("extend_new_Month")){
				System.out.println("in extend_new_Month state");//Test
				fos = new FileOutputStream(write_file,true);//"true" if for append after original content
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				if(month==1 || month==3 || month==5 || month==7|| month==8|| month==10 || month==12)
					daynum = 31;
				else if(month==4 || month==6 || month==9 || month==11)
					daynum = 31;
				else{//month = 2
					if(Leap_year)
						daynum = 29;
					else
						daynum = 28;
				}						
				for(i=1;i<=daynum;i++){
					bw.write(year+"-"+month+"-"+i+"\t\t\t\t");
					bw.newLine();
				}
				bw.flush();
			}
			else if(state.equals("update_data")){
				System.out.println("in update_data state");//Test
				fos = new FileOutputStream(write_file);
				bw = new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("Date\tBreakfast\tLunch\tDinner\tOthers");
				bw.newLine();
				for(i=0;i<record_num;i++){
					bw.write(	day_record[i].year	+ "-" + 
								day_record[i].month	+ "-" +
								day_record[i].day	+ "\t"+
								day_record[i].breakfast_price	+ "\t" +
								day_record[i].lunch_price		+ "\t" +
								day_record[i].dinner_price		+ "\t" +
								day_record[i].other							);
					bw.newLine();
				}
				bw.flush();
			}
			else 
				System.out.println("in else");;
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return(record_num);//return how many record in the daybook.txt
	}
	public static DAY_RECORD[] read_record_from_txt(DAY_RECORD[] day_record,String[] tokens,String[] YMD_tokens){	
		int i;
		try{
			//--------------------------Open daybook.txt for read------------------------
			FileInputStream fin_daybook = new FileInputStream("daybook.txt");//read from the txt file
			DataInputStream in_daybook = new DataInputStream(fin_daybook);// Get the object of DataInputStream
			BufferedReader br_daybook = new BufferedReader(new InputStreamReader(in_daybook));			
			//-------------------------read from daybook.txt file------------------------
			String strLineinput;
			strLineinput = br_daybook.readLine();//ignore the first line data
			for(i=0;(strLineinput = br_daybook.readLine())!= null;i++){//Read File Line By Line
				tokens = strLineinput.split("\t",-1);
				YMD_tokens = tokens[0].split("-",-1);
				day_record[i].year	= Integer.parseInt(YMD_tokens[0]);
				day_record[i].month	= Integer.parseInt(YMD_tokens[1]);
				day_record[i].day	= Integer.parseInt(YMD_tokens[2]);
				if(tokens[1].equals(""))	day_record[i].breakfast_price	= -1;//There is no record
				else						day_record[i].breakfast_price	= Integer.parseInt(tokens[1]);
				if(tokens[2].equals(""))	day_record[i].lunch_price		= -1;//There is no record
				else						day_record[i].lunch_price		= Integer.parseInt(tokens[2]);
				if(tokens[3].equals(""))	day_record[i].dinner_price		= -1;//There is no record
				else						day_record[i].dinner_price		= Integer.parseInt(tokens[3]);
				if(tokens[4].equals(""))	day_record[i].other				= "No record";//There is no record
				else						day_record[i].other				= tokens[4];	
			}
			br_daybook.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		return(day_record);
	}
	public static int return_daybooktxt_num(DAY_RECORD[] day_record){//return the daybook.txt record number
		int i;
		int daybooktxt_num = 0;
		for(i=0;i<300;i++){
			if(day_record[i].year != -1)
				daybooktxt_num++;
		}
		return daybooktxt_num;
	}
}