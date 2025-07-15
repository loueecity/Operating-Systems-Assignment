import java.util.Comparator;
import java.util.Properties;
import java.util.Queue;
import java.util.PriorityQueue;
/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  // TODO
//initialises the ready queue
    private Queue<Process> readyQueue;
    private double initialBurstEstimate;
    private double alphaBurstEstimate;
    private double burstEstimate;
    
    public SJFScheduler(){
     Comparator<Process> burstEstimComparator = new Comparator<Process>(){
            @Override
            public int compare(Process p1, Process p2){
            return (int)Math.ceil(burstEstim(p1) - burstEstim(p2));
            };
            };
     readyQueue = new PriorityQueue<>(burstEstimComparator);
 }
    public double burstEstim(Process process){
        burstEstimate = (alphaBurstEstimate*process.getRecentBurst() +(1-alphaBurstEstimate)*process.getRecentBurst());
        return burstEstimate;
    }
    
 @Override
 public void initialize(Properties parameters) {
 initialBurstEstimate = Double.parseDouble(parameters.getProperty("initialBurstEstimate"));
 alphaBurstEstimate = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
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
