import java.util.Comparator;
import java.util.Properties;
import java.util.Queue;
import java.util.PriorityQueue;

/**
 * Ideal Shortest Job First Scheduler
 * 
 * @version 2017
 */

public class IdealSJFScheduler extends AbstractScheduler {
  // TODO
    private Queue<Process> readyQueue;
 
    public IdealSJFScheduler(){
        //compares the burst time of the process for the priorityqueue
        Comparator<Process> btComparator = new Comparator<Process>(){
            @Override
            public int compare(Process p1, Process p2){
            return p1.getNextBurst() - p2.getNextBurst();
            };
            };
        
        readyQueue = new PriorityQueue<>(btComparator);
    }
    

   
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  @Override
  public void ready(Process process, boolean usedFullTimeQuantum) {
    // TODO
    readyQueue.offer(process);

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
}

