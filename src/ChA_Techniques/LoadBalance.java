import java.util.LinkedList;
import java.util.Queue;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * LoadBalance
 * 
 * Simulates the load of four servers. Every server has a queue of tasks to work
 * on. In the simulation tasks are removed randomly. When new tasks are being
 * added, they are added to the server with the least amount of work, i.e. that
 * one whose task queue is the shortest.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class LoadBalance extends ConsoleProgram {
	private final int NR_OF_SERVERS = 4;
	private Queue<String>[] serverQueues;
	private int currentTaskNr = 0;

	public void init() {
		serverQueues = new Queue[NR_OF_SERVERS];
		for (int i = 0; i < serverQueues.length; i++) {
			serverQueues[i] = new LinkedList<String>();
		}
	}

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-20");

		while (true) {
			addNewServerTask();
			addNewServerTask();
			removeSomeRandomTask();
			printServerLoad();
			pause(1000);
		}
	}

	private void addNewServerTask() {
		addNewServerTaskRandom();
	}

	private void addNewServerTaskRandom() {
		int serverNr = (int) (Math.random() * NR_OF_SERVERS);
		currentTaskNr++;
		serverQueues[serverNr].add("Task Nr." + currentTaskNr);
	}

	private void addNewServerTaskRoundRobin() {
		int serverNr = currentTaskNr % NR_OF_SERVERS;
		currentTaskNr++;
		serverQueues[serverNr].add("Task Nr." + currentTaskNr);
	}

	private void addNewServerTaskLoad() {
		int serverNr = findServerWithLowestLoad();
		currentTaskNr++;
		serverQueues[serverNr].add("Task Nr." + currentTaskNr);
	}

	private int findServerWithLowestLoad() {
		int serverNr = 0;
		int min = serverQueues[serverNr].size();
		for (int i = 1; i < serverQueues.length; i++) {
			if (min > serverQueues[i].size()) {
				min = serverQueues[i].size();
				serverNr = i;
			}
		}
		return serverNr;
	}

	private void removeSomeRandomTask() {
		int serverNr = (int) (NR_OF_SERVERS * Math.random());
		// System.out.println(serverNr);
		if (serverQueues[serverNr].size() > 0) {
			serverQueues[serverNr].remove();
		}
	}

	private void printServerLoad() {
		for (int i = 0; i < serverQueues.length; i++) {
			print(serverQueues[i].size() + ", ");
		}
		println();
	}

}
