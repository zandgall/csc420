package	com.zandgall.csc420.s5.a5;

public class ProcessInfo implements Comparable<ProcessInfo> {
	private String name;
	private int id, priority, remainingRuntime;
	private long startTime = 0, endTime = 0, elapsedTime = 0;
	
	public ProcessInfo(String name, int id, int priority, int runtime) {
		this.name = name;
		this.id = id;
		this.priority = priority;
		remainingRuntime = runtime;
		startTime = System.currentTimeMillis();
	}

	public boolean executeProcess(int millis) {
		// Mark starting time
		long start = System.currentTimeMillis();
	
		// --- Process ---
		// In this program we will only take *as much time needed* to finish the program
		// if the time given is greater. This might not reflect how an operating system
		// actually prioritizes processes.
		int actualMillis = Math.min(millis, remainingRuntime);
		remainingRuntime -= actualMillis;

		try {
			Thread.sleep(actualMillis);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		// --- Process ---

		// Update elapsed time using start
		elapsedTime += System.currentTimeMillis() - start;

		// If we're done, return true after setting endTime
		if(remainingRuntime <= 0) {
			endTime = System.currentTimeMillis();
			return true;
		}
		return false;
	}

	public int getPriority() {
		return priority;
	}

	public int compareTo(ProcessInfo other) {
		if(priority == other.priority)
			return this.id - other.id;
		return this.priority - other.priority;
	}

	public String toString() {
		return String.format("Process Name: %8s Process ID: %2d Process Priority %2d Process Remaining Runtime: %d", name, id, priority, remainingRuntime);
	}

	public String displayCompletedInfo() {
		return String.format("Process Name: %8s Process Priority: %2d CPU Time: %d Real Time: %d", name, priority, elapsedTime, endTime - startTime);
	}

	private void endProcess() {
		// ??? What is this for?
	}
}
