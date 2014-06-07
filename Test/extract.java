import java.io.*;


public class extract{
	public static void main(String args[]) throws IOException
	{
		int i=0;
		int j=0;
		int temp=0;
		char data[]=new char[2000];
		String leadname=""; 
		
		people[] person = new people[300];//300位同修
		for(i=0;i<300;i++){
			person[i] = new people("", "");
		}
		FileReader fr = new FileReader("data.txt");
				
		int num = fr.read(data);
		//String str = new String(bytes, "UTF-8");
		String str = new String(data,0,num);
		System.out.println("Characters read = "+num);
		System.out.println(str);
		i=0;
		while(data[i]!=9){
			leadname = leadname + data[i];
			i++;
		}
		System.out.println(leadname);
		System.out.println("---------------------------------------------------------------------------");
		j=1;
		for(i=0;i<100;i++){ //data[i]  person[j]
			System.out.println(i);
			System.out.println(data[i] +" int "+ (int)data[i]);
			if(data[i]==10){// data[i]=\n
				temp=i;
				i++;
				if(data[i]==9){//  data[i]=\t (tab) 代表同一輔導組
					person[j].leadername = leadname;
				}
				else{//  data[i]=\t (tab) 代表不同一輔導組
					if(data[i]!=9){
						leadname="";
						while(data[i]!=9){
							leadname = leadname + data[i];
							i++;
						}
					}
					person[j].leadername = leadname;
				}
				i++;
				while(data[i]!=9){//讀取名字
					person[j].name = person[j].name + data[i];
					i++;
				}
				i=temp;
				j++;
			}
		}		
		fr.close();
		System.out.println("---------------------------------------------------------------------------");
		for(i=0;i<10 ;i++){
			System.out.println("person["+i+"] = " + person[i].leadername +"   " + person[i].name);
		}
		
	}	
}
