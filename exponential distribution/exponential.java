public class exponential{
	public static void main (String[] args){
		final long oneMinute = 60000;
		Random rng = new MersenneTwisterRNG();
		 
		// Generate events at an average rate of 10 per minute.
		ExponentialGenerator gen = new ExponentialGenerator(10, rng);
		boolean running = true;
		while (true)
		{
			long interval = Math.round(gen.nextValue() * oneMinute);
			Thread.sleep(interval);
			System.out.println();
			// Fire event here.
		}
	}
}
