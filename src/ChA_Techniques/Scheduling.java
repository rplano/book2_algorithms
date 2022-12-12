import java.util.PriorityQueue;

import acm.program.ConsoleProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Scheduling
 * 
 * To organize your week, you break up your calendar in time slots, for instance
 * one hour time slots. Then you generate a list with tasks, that have a
 * duration, a priority and may have a time. For instance: <br/>
 * - name duration priority time <br/>
 * - breakfast, 1h, high, EveryDay, 7:00 am <br/>
 * - prog2, 2h, medium, Friday, 8:00 am <br/>
 * - party, 4h, low, AnyDay, AnyTime <br/>
 * 
 * Device a greedy algorithm, that would turn your task list for the week into
 * calendar entries.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Scheduling extends ConsoleProgram {
	private final String[] weekdays = { "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday" };

	private PriorityQueue<Task> tasks = new PriorityQueue<Task>();
	private String[][] calendar = new String[5][24]; // week has 5 days, 24h
														// each

	public void run() {
		setSize(600, 300);
		setFont("Courier new-bold-15");

		// create task list:
		tasks.add(new Task("Buy Food", 4, 2, "AnyDay", -1));
		tasks.add(new Task("Breakfast", 1, 0, "EveryDay", 7));
		tasks.add(new Task("Prog 2", 2, 1, "Friday", 8));
		// ... add some more tasks

		// basically go through the priority queue and
		// place one item after the other in your calendar
		Task task;
		while ((task = tasks.poll()) != null) {

			if (task.day.contains("EveryDay")) {
				for (int i = 0; i < weekdays.length; i++) {
					addToCalendar(i, task);
				}

			} else if (task.day.contains("AnyDay")) {
				addToCalendar(task);

			} else {
				for (int i = 0; i < weekdays.length; i++) {
					if (task.day.contains(weekdays[i])) {
						addToCalendar(i, task);
					}
				}
			}
		}

		printCalendar();
	}

	/**
	 * Tries to add task at an arbitrary day and time:
	 * 
	 * @param task
	 */
	private void addToCalendar(Task task) {
		int day = 0;
		int hour = 7;
		while (!insertTaskIntoCal(day, hour, task)) {
			hour++;
			if (hour > 23) {
				day++;
				hour = 7;
				if (day > 4) {
					System.out.println("Conflict: " + task);
					break;
				}
			}
		}
	}

	/**
	 * Tries to add task at a given day and time:
	 * 
	 * @param day
	 * @param task
	 */
	private void addToCalendar(int day, Task task) {
		if (!insertTaskIntoCal(day, task.time, task)) {
			println("Conflict: " + task);
		}
	}

	/**
	 * Tries to insert a given task at a given day and given time.
	 * 
	 * @param day
	 * @param hour
	 * @param task
	 * @return true if the insertion was successful, otherwise false
	 */
	private boolean insertTaskIntoCal(int day, int hour, Task task) {
		boolean success = true;
		for (int i = 0; i < task.duration; i++) {
			if (calendar[day][hour + i] != null) {
				success = false;
			}
		}
		if (success) {
			for (int i = 0; i < task.duration; i++) {
				calendar[day][hour + i] = task.name;
			}
		}
		return success;
	}

	/**
	 * Print the calendar to the console.
	 */
	private void printCalendar() {
		final int PAD_WIDTH = 10;
		// print header:
		String[] dayOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };
		print(padRight("", PAD_WIDTH));
		for (int i = 0; i < calendar.length; i++) {
			print(padRight(dayOfWeek[i], PAD_WIDTH));
		}
		println();

		// print actual calendar:
		for (int i = 6; i < calendar[0].length; i++) {
			print(padRight(padLeft("" + i, 2) + ":00", PAD_WIDTH));
			for (int j = 0; j < calendar.length; j++) {
				print(padRight(calendar[j][i], PAD_WIDTH));
			}
			println();
		}

	}

	private String padLeft(String str, int size) {
		if (str == null)
			str = "";
		StringBuffer padded = new StringBuffer(str);
		while (padded.length() < size) {
			padded.insert(0, " ");
		}
		return padded.toString();
	}

	private String padRight(String str, int size) {
		if (str == null)
			str = "";
		StringBuffer padded = new StringBuffer(str);
		while (padded.length() < size) {
			padded.append(" ");
		}
		return padded.toString();
	}

	/**
	 * A simple data container that holds all relevant data for a given task.
	 */
	private class Task implements Comparable {
		protected String name; // name of task, eg "breakfast", "prog2", ...
		protected int duration; // in hours
		protected int priority; // 0 is high, 1 is medium, 2 is low
		protected String day; // "EveryDay", "Friday", "AnyDay"
		protected int time; // 7 for 7:00 am or 13 for 1pm or -1 for anytime

		public Task(String name, int duration, int priority, String day,
				int time) {
			super();
			this.name = name;
			this.duration = duration;
			this.priority = priority;
			this.day = day;
			this.time = time;
		}

		/**
		 * Needed so that tasks can be inserted into a priority queue sorted by
		 * their priorities.
		 */
		public int compareTo(Object o) {
			if (o instanceof Task) {
				return this.priority - ((Task) o).priority;
			}
			return 0;
		}
	}
}