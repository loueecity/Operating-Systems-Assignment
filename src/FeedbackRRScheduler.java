import java.util.Properties;
import java.util.Queue;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {

  // TODO
private Queue<Process> readyQueue;
private int timeQuantum;


  public FeedbackRRScheduler(){
    //Comparator compares the priority of the processes when ordered into the queue
      Comparator<Process> priorComparator = new Comparator<Process>(){
            @Override
            public int compare(Process p1, Process p2){
            return p1.getPriority() - p2.getPriority();
            };
            };
        
        readyQueue = new PriorityQueue<>(priorComparator);
  }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  @Override
 public void initialize(Properties parameters) {
     timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
 }
 
 //Returns the time quantum by overriding the method in AbstractScheduler
  @Override
  public int getTimeQuantum(){
      return timeQuantum;
  }
  
  
  @Override
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    if (process.getRecentBurst()> timeQuantum){
        usedFullTimeQuantum = true;
    }
    if(usedFullTimeQuantum){
        process.setPriority((process.getPriority() +1));
    }
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
    if (!readyQueue.isEmpty()){
        System.out.print("The next process to be run from the ready queue is " + readyQueue.peek());
        return readyQueue.remove();
        
    } 
    //if ready queue is empty then it returns null
    System.out.print("The ready queue is empty");
    return null;
  }
  //FeedbackRRScheduler is preemptive
  @Override
  public boolean isPreemptive(){
      return true;
  }

  
}
