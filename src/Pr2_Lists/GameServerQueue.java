import java.util.LinkedList;
import java.util.Queue;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * GameServerQueue
 * 
 * In this simulation we use a queue to build teams of always two players. The
 * teams are assigned via a first-come first-serve policy using a queue.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class GameServerQueue extends ConsoleProgram {
	private final int NR_OF_PLAYERS_PER_TEAM = 2;
	private Queue<String> playerQueue = new LinkedList<String>();

	public void run() {
		setSize(400, 300);
		setFont("Times New Roman-bold-20");

		fillQueueWithNames();

		while (playerQueue.size() > 0) {
			removePlayers();
			pause(1000);
		}
	}

	private void removePlayers() {
		if (playerQueue.size() >= NR_OF_PLAYERS_PER_TEAM) {
			for (int i = 0; i < NR_OF_PLAYERS_PER_TEAM; i++) {
				print(playerQueue.remove() + ", ");
			}
			println();
		}
	}

	private void fillQueueWithNames() {
		while (true) {
			String name = readLine("Enter player name: ");
			if (name.length() == 0)
				break;
			playerQueue.add(name);
		}
	}

}
