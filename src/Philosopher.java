import java.util.Random;

public class Philosopher implements Runnable{
//Making a Philosopher Object
	private int philNumber;
	private Fork leftFork;
	private Fork rightFork;
	private Random random;
	// These are the Philosopher's properties where each one has a number where I can keep track of 
	//each one
	// they also can hold a right fork or left fork
	public Philosopher(int philNumber, Fork leftFork, Fork rightFork) {
		this.philNumber = philNumber+1;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
		this.random=new Random();
		}
	
	// A philospher can only do to things either eat and sleep.
	//Put the thread to sleep so they can do the action for a random amount of time
	//before performing the next action
	private void think() throws InterruptedException{
		System.out.println(this + " is thinking");
		Thread.sleep(random.nextInt(3000-1000)+1000);
	}
	//If they arent eating then they are thinking 
	//If they aren't thinking then they are eating
	private void eat() throws InterruptedException{
		System.out.println(this + " is eating");
		Thread.sleep(random.nextInt(3000-1000)+1000);
	}

	//Create the function that will allow a philosopher to act.
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//try catch block
		try {
			while(true) {
				// by default they will all think
				think();
				//if they can pick up the left fork they will
				if(leftFork.takeForks(this,ForkLR.left)) {
					//if they can pick up the right fork as well after picking up the left fork
					// they will also pick up the right fork
					if(rightFork.takeForks(this,ForkLR.right)) {	
						// since they were able to pick up the right fork and left fork
						// the philosopher will eat and then place back down their right fork
						eat();
						rightFork.returnForks(this,ForkLR.right);				
						}
					// the philosopher will put down the left fork if there is no right fork or
					//they just finished eating.
					leftFork.returnForks(this,ForkLR.left);
				}
				
			}
		}
		//cathing exception e	
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Override String for my purposes.
	@Override
	public String toString() {
		return "Philosopher " + philNumber;
	}
	
	
}
	
