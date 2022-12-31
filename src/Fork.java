import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
	//make the fork it's own object with it's own properties
	private int forkNum;
	private Lock lock;
	
	//give the Fork it's own property such as a number so I can keep trackof which fork is on the left
	// or right of a philopsher
	// and i put a lock on it so once someone picks it up then no one else can also pick up that same fork until i "unlock" it 
	// or place down the fork again
	public Fork(int forkNum) {
		this.forkNum = forkNum;
		//Using a Reentrant lock for convinence of locking a thread
		this.lock = new ReentrantLock();
	}
	//create a function for the philosopher picking up the fork
	// passing in is the philospher and if it's either the left or right fork being passed
	public boolean takeForks(Philosopher philospher, ForkLR state) throws InterruptedException {
		//when a philosopher tries to pick up a lock it "tryLock" to see if the lock is locked or unlock
		// if it is unlocked then return true so philospher can pick it up if not "wait" for a bit of time and do something else
		if(lock.tryLock(10,TimeUnit.MILLISECONDS)) {
			System.out.println(philospher +" picked up the "+ state.toString()+ this);
			return true;
		}
		// if the lock is locked then can't pick it up
		return false;
	}
	//create a function for the philosopher putting down the fork

	public void returnForks(Philosopher philospher, ForkLR state) {
		//takes a certain lock on a specific fork and then unlocks it
		lock.unlock();
		System.out.println(philospher +" putdown the "+  state.toString()+ this);
	}
	//Override on toString
	@Override
	public String toString() {
		return " Fork " ;
	}

}
