import java.util.Properties;
import java.util.Queue;
import java.util.PriorityQueue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
    //initialises the ready queue
    private Queue<Process> readyQueue;
    private int timeQuantum;
    
//Creates the ready queue when the rrscheduler is made    
 public RRScheduler(){
     readyQueue = new PriorityQueue<>();
 }
  
/**
   * Initialises the scheduler from the given parameters
   */
 @Override
 public void initialize(Properties parameters) {
     timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
 }
    
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
 @Override
 public void ready(Process process, boolean usedFullTimeQuantum) {
       readyQueue.offer(process);
       //usedFullTimeQuantum = true;
  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  @Override
  public Process schedule() {
    // TODO
    //if the ready queue isn't empty it will use .remove to return + remove the next process
    if (!readyQueue.isEmpty()){
        System.out.print("The next process to be run from the ready queue is " + readyQueue.peek());
        return readyQueue.remove();
        
    } 
    //if ready queue is empty then it returns null
    System.out.print("The ready queue is empty");
    return null;
  }
  
  //Returns the time quantum by overriding the method in AbstractScheduler
  @Override
  public int getTimeQuantum(){
      return timeQuantum;
  }
}
  