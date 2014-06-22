public class thread extends java.lang.Thread { 

	public long waittime; 
	public String data; 

	public thread(long waittime, String value) {
		this.waittime = waittime; 
		this.data = value; 
	} 

	public void run(){// thread.start() will invoke run() 
		try { 
			while (true) {// make thread be executed forever ,only interrupt when force to stop 
				Thread.sleep(waittime);//wait a few second and then execute the 
				System.out.println(this.data); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
	} 

	public static void main(String args[]) { 
		// 1000(ms) = 1(s) 
		(new thread(1 * 1000, "Thread-1")).start(); //thread.start() will creat a new thread
		(new thread(1 * 1000, "Thread-2")).start(); 
	} 
}
