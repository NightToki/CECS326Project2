import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningPhilosophers {
	   public static void main(String args[]) throws Exception
	   {
		//create an executor service so I can keep track of multiple threads asynchronously
		ExecutorService executorService = null;
		//Philosopher Object
		Philosopher[] philosophers = null;
		try {
			//I want 5 philosphers
			philosophers = new Philosopher[5];
			//I also want 5 forks
			Fork[] forks = new Fork[5];
			// Make each fork with their fork number
			for(int i = 0; i< philosophers.length;i++) {
				forks[i]=new Fork(i);	
			}
			//create a pool of threads that I can reuse a fixed number of times.
			executorService = Executors.newFixedThreadPool(philosophers.length);
			//For loop for creating a philosopher and setting which fork which is numbered,
			//to their left and to their right
			// keeping track of which fork will be picked up by a single philospher
			for(int i = 0; i< philosophers.length;i++) {
				philosophers[i] = new Philosopher(i, forks[i], forks[(i+1)%forks.length]);
				//run it
				executorService.execute(philosophers[i]);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	   }
}
//Tony Tran and Linh Nguyen
// the phlisophhers will think and eat infinitely unless I stop it manually
//there were no specifications on how long they should eat and think for
// if they can pick up the left fork then they will, if they can also pick up the right fork they shall and if they have both forks they will eat
// if they can pick up the left but not the right then they will put it back down.