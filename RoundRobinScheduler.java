
import java.util.Scanner;

public class RoundRobinScheduler {

		//Declaration & initialization of time variables and input
		static int numprocess, qTime, currentTime, sum_turnaroundTime, avg_turnaroundTime = 0;	
		static Scanner input = new Scanner(System.in);
		
		//Method to accept user-defined "# of process" variable 
		public static int numprocess() {
			System.out.print("The number of processes: ");
			numprocess = input.nextInt();
			return numprocess;
		}
		
		//Method to run the Application
		public static void main(String[] args) {
			//Asking for the number of processes
			numprocess = numprocess();
			int[] arrival = new int[numprocess];
			int[] waitingTime = new int[numprocess];
			int[] turnaroundTime = new int[numprocess];
			int[] cpuBurst = new int[numprocess];
			int[] rem_cpuBurst = new int[numprocess];
			//Asking for arrival and CPU time of each process
			System.out.print("Arrival time (for each process): ");
			for (int i = 0; i < arrival.length; i++) {
				arrival[i] = input.nextInt();
			}
			System.out.print("CPU burst time (for each process): ");
			for (int i = 0; i < numprocess; i++) {
				cpuBurst[i] = input.nextInt();
				rem_cpuBurst[i] = cpuBurst[i];
			}
			System.out.print("Time quantum: ");
			qTime = input.nextInt();			
	        findingTime(rem_cpuBurst, waitingTime, cpuBurst, turnaroundTime, arrival, currentTime, qTime);
		}
		
		//Method to find the Wait and Turnaround Time
		public static void findingTime(int[] rem_cpuBurst, int[] waitingTime, int[] cpuBurst, int[] turnaroundTime, int[] arrival, int currentTime, int qTime) {
			//Round Robin Traversal begins
			while (true) {
				boolean terminated = true;
				for (int i = 0; i < numprocess; i++) {
					//If burst time is greater than 0 then, processing continues
					if (rem_cpuBurst[i] > 0) { 
	                    terminated = false;        
	                    if (rem_cpuBurst[i] > qTime) { 
	                        currentTime += qTime;
	                        rem_cpuBurst[i] -= qTime; 
	                    } 
	                    //If burst time is smaller than or equal to quantum time.
	                    else {
	                    	currentTime = currentTime + rem_cpuBurst[i];
	                    	//Finds the Waiting time
	                        waitingTime[i] = currentTime - cpuBurst[i] - arrival[i]; 
	                        rem_cpuBurst[i] = 0;
	                    }
					}	
				}
				if (terminated == true) {
					break;
				}
			}
			findingTurnaroundTime(turnaroundTime, cpuBurst, waitingTime, arrival);
		}
		
		//Method to find the Turnaround Time and the average Turnaround time
		public static void findingTurnaroundTime(int[] turnaroundTime, int[] cpuBurst, int[] waitingTime, int[] arrival) {
			for (int i = 0; i < numprocess ; i++) {
				//Find the turnaround time
	            turnaroundTime[i] = cpuBurst[i] + waitingTime[i];
				sum_turnaroundTime += turnaroundTime[i];
			}
			//Outputs the Processes, Arrival time, CPU Burst time and turnaround time in a table
			System.out.println("|Processes " + "\t|" + "Arrival time " + "\t|" + "CPU Burst time |" + "Turnaround time|"); 
	        for (int i = 0; i < numprocess; i++) { 
	            System.out.println("|" + (i + 1) + "\t\t|" + arrival[i] + "\t\t|" + cpuBurst[i] +"\t\t|" + turnaroundTime[i] +"\t\t|"); 
	        } 
	        System.out.println("Average turn around time = " + (float)sum_turnaroundTime / (float)numprocess);
		}
}

	
