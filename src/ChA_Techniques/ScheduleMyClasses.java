import java.util.HashMap;
import java.util.HashSet;

/**
 * Legal Notice and License
 * 
 * Copyright (c) Ralph Lano. Permission is granted to copy, distribute and/or
 * modify this document under the terms of the GNU Free Documentation License,
 * Version 1.3 or any later version published by the Free Software Foundation;
 * with no Invariant Sections, no Front-Cover Texts, and no Back-Cover Texts. A
 * copy of the license is included in the section entitled
 * "GNU Free Documentation License" (http://www.gnu.org/copyleft/fdl.html).
 * 
 * Program
 * 
 * The idea is to schedule lectures given a set of constraints. Given are a
 * number of rooms and a number of hours, as well as a list of classes.
 * 
 * The rooms and hours are represented by a 2d array of Strings. - the first
 * dimension is the number of rooms - the second dimension is the number of
 * hours per week, so if Monday has 6 time slots, the 7th time slot is Tuesday
 * first hour and so on - the value of that 2d array is the class name to be
 * held in that room at that hour
 * 
 * For the constraints, we have also a 2d array of type HashSet. The dimensions
 * are the same as for hours, but now it lists the classes which are not allowed
 * to be held at that room at that time.
 * 
 * @author rlano
 * 
 * @Hist: 0.03: added student groups, i.e., one class is associated with one or
 *        more student groups, then a schedule for each group must be provided,
 *        and no collisions may occur<br>
 *        0.02: added new view. Also added lots of comments. prof is not allowed
 *        in two different rooms at the same time. <br>
 *        0.01: you can assign rooms and hours to classes and give constraints
 *        like class2 is not allowed in this room or is not allowed at this
 *        hour. it uses simulated annealing
 * 
 * @ToDo: - run several of these processes in parallel with different initial
 *        sets<br>
 *        - add additional constraints, like max number of students in room, or
 *        software requirements (can be done easily)<br>
 *        - we do need soft constraints, i.e., those could be violated but they
 *        should not<br>
 */
public class ScheduleMyClasses {
	/** 2d array, first entry is room, second is class */
	static private String[][] hours = new String[2][4];
	/** contains classes that are not allowed at these hours/rooms */
	static private HashSet[][] hoursNotAllowed = new HashSet[hours.length][hours[0].length]; //
	/** contains class names and associated prof */
	static private HashMap<String, String> classes;
	/** contains just a list of profs */
	static private HashSet<String> profs = new HashSet<String>();
	/** contains just a list of student groups */
	static private HashSet<String> groups = new HashSet<String>();

	/**
	 * The main method, simply shows an example on how to run this code.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("hi");
		initArray(hours, "");

		// simply add all profs, is needed to check so that no prof
		// is teaching in two different rooms at the same time
		profs.add("prof1");
		profs.add("prof2");

		// student groups
		groups.add("group1");
		groups.add("group2");
		groups.add("group3");

		// init classes with class names and profs who teach that class
		classes = new HashMap<String, String>();
		// list of classes and associated prof
		// i.e. prof1 is teaching class1,
		// plus group1 and group3 have to take class1
		classes.put("class1", "prof1,group1,group3");
		classes.put("class2", "prof1,group1");
		classes.put("class3", "prof2,group1,group2,group3");
		classes.put("class4", "prof2,group2");
		classes.put("class5", "prof2,group2");
		classes.put("class6", "prof2,group2");

		// init constraints:
		initConstraints(hoursNotAllowed);
		// in room1 (no class2 and no class 4) is allowed:
		for (int i = 0; i < hours[0].length; i++) {
			hoursNotAllowed[1][i].add("class2");
			hoursNotAllowed[1][i].add("class4");
		}
		// class3 is not allowed in hours 0 and 1
		for (int j = 0; j < hours.length; j++) {
			hoursNotAllowed[j][0].add("class3");
			hoursNotAllowed[j][1].add("class3");
		}
		// prof1 cannot teach at hours 3
		for (int j = 0; j < hours.length; j++) {
			hoursNotAllowed[j][3].add("class1");
			hoursNotAllowed[j][3].add("class2");
		}

		// now find a distribution that fulfills all constraint:
		setInitialRandomDistribution();
		int f = 0;
		if (calculateFitness() > 0) {
			// make sure the fitness function goes to zero, we do this by
			// exchanging two classes and then see if the fitness has improved.
			// TODO: this could be an infinite loop or run for a very long time,
			// hence there should be some criterion when to stop
			while ((f = doSimulatedAnnealing()) > 0) {
				System.out.print(" fitness=" + f);
				// listClassesByRoom();
			}
		}

		// when we get here, we found a solution to our problem, hence show the
		// results:
		listClassesByRoom();
		listClassesByProf();
		listClassesByGroup();
	}

	/**
	 * Basically checks all constraints. For each constraint that is violates,
	 * it adds one to the fitness function.
	 * 
	 * @return so the higher the return value is the less fit the solution is.
	 *         All constraints are fulfilled if it returns zero.
	 */
	private static int calculateFitness() {
		int numberOfFailedConstraints = 0;

		// check for room, hour and day violations:
		for (int j = 0; j < hours.length; j++) {
			for (int i = 0; i < hours[0].length; i++) {
				// if ( hours[j][i].equals(hoursNotAllowed[j][i])) {
				if (hoursNotAllowed[j][i].contains(hours[j][i])) {
					numberOfFailedConstraints++;
				}
			}
		}

		// need to check if a prof is in two different rooms at the same time.
		for (String prof : profs) {
			for (int i = 0; i < hours[0].length; i++) {
				boolean bProfHadClass = false;
				for (int j = 0; j < hours.length; j++) {
					// System.out.println(((classes.get(hours[j][i]).indexOf(prof))>-1));
					if ((classes.get(hours[j][i]) != null)
							&& ((classes.get(hours[j][i]).indexOf(prof)) > -1)) {
						if (bProfHadClass) {
							// we come here the second time, hence prof already
							// had a class at this time
							numberOfFailedConstraints++;
						}
						// System.out.print("room" + j + ", ");
						bProfHadClass = true;
						// System.out.print(hours[j][i] + ", ");
					}
				}
			}

		}

		// need to check if a group is in two different rooms at the same time.
		for (String group : groups) {
			for (int i = 0; i < hours[0].length; i++) {
				boolean bGroupHadClass = false;
				for (int j = 0; j < hours.length; j++) {
					// System.out.println(((classes.get(hours[j][i]).indexOf(prof))>-1));
					if ((classes.get(hours[j][i]) != null)
							&& ((classes.get(hours[j][i]).indexOf(group)) > -1)) {
						if (bGroupHadClass) {
							// we come here the second time, hence group already
							// had a class at this time
							numberOfFailedConstraints++;
						}
						// System.out.print("room" + j + ", ");
						bGroupHadClass = true;
						// System.out.print(hours[j][i] + ", ");
					}
				}
			}

		}

		return numberOfFailedConstraints;
	}

	/**
	 * This simply switches two elements in hour[][].
	 */
	private static int doSimulatedAnnealing() {
		int initialFitness = calculateFitness();
		int rooma = (int) ((double) (hours[0].length) * Math.random());
		int roomb = (int) ((double) (hours[0].length) * Math.random());
		int houra = (int) ((double) (hours.length) * Math.random());
		int hourb = (int) ((double) (hours.length) * Math.random());
		String temp = hours[houra][rooma];
		hours[houra][rooma] = hours[hourb][roomb];
		hours[hourb][roomb] = temp;
		int finalFitness = calculateFitness();
		if (finalFitness > initialFitness) {
			temp = hours[hourb][roomb];
			hours[hourb][roomb] = hours[houra][rooma];
			hours[houra][rooma] = temp;
		}
		return finalFitness;
	}

	/**
	 * In the beginning the classes are randomly distributed amongst the
	 * available rooms and hours (i.e. hours[][])
	 */
	private static void setInitialRandomDistribution() {
		for (String lect : classes.keySet()) {
			// System.out.println(lect);
			boolean bIsConstraintSatisfied = false;
			while (!bIsConstraintSatisfied) {
				int hour = (int) ((double) (hours[0].length) * Math.random());
				int room = (int) ((double) (hours.length) * Math.random());
				System.out.print("." + room + "," + hour);
				if (hours[room][hour].equals("")) {
					hours[room][hour] = lect;
					bIsConstraintSatisfied = true;
				}
			}

		}

	}

	/**
	 * Initializes all elements of the array arr with the string initString
	 * (prob could also be done with Java's Array class)
	 * 
	 * @param arr
	 * @param initString
	 */
	private static void initArray(String[][] arr, String initString) {
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i < arr[0].length; i++) {
				arr[j][i] = initString;
			}
		}
	}

	/**
	 * The array arr of HashSets needs to be initialized, so it is initialized
	 * with all empty HashSets.
	 * 
	 * @param arr
	 */
	private static void initConstraints(HashSet[][] arr) {
		for (int j = 0; j < arr.length; j++) {
			for (int i = 0; i < arr[0].length; i++) {
				arr[j][i] = new HashSet<String>();
			}
		}
	}

	/**
	 * This simply lists all classes (rooms) that one group of students is
	 * supposed to take (basically the student groups class schedule).
	 */
	private static void listClassesByGroup() {
		System.out.println("-------------------------------------------------");
		for (String group : groups) {
			System.out.print(group + ": ");

			for (int i = 0; i < hours[0].length; i++) {
				boolean bGroupHadClass = false;
				for (int j = 0; j < hours.length; j++) {
					if ((classes.get(hours[j][i]) != null)
							&& ((classes.get(hours[j][i]).indexOf(group)) > -1)) {
						System.out.print("room" + j + ", ");
						bGroupHadClass = true;
						// System.out.print(hours[j][i] + ", ");
					}
				}
				if (!bGroupHadClass) {
					System.out.print("----- , ");
				}
			}
			System.out.println("");

		}
	}

	/**
	 * This simply lists all classes (rooms) that one professor is supposed to
	 * teach (basically the profs class schedule).
	 */
	private static void listClassesByProf() {
		System.out.println("-------------------------------------------------");
		for (String prof : profs) {
			System.out.print(prof + ": ");

			for (int i = 0; i < hours[0].length; i++) {
				boolean bProfHadClass = false;
				for (int j = 0; j < hours.length; j++) {
					if ((classes.get(hours[j][i]) != null)
							&& ((classes.get(hours[j][i]).indexOf(prof)) > -1)) {
						System.out.print("room" + j + ", ");
						bProfHadClass = true;
						// System.out.print(hours[j][i] + ", ");
					}
				}
				if (!bProfHadClass) {
					System.out.print("----- , ");
				}
			}
			System.out.println("");

		}
	}

	/**
	 * This lists which classes are in which room.
	 */
	private static void listClassesByRoom() {
		System.out.println("-------------------------------------------------");
		for (int j = 0; j < hours.length; j++) {
			System.out.print("room" + j + ": ");
			for (int i = 0; i < hours[0].length; i++) {
				if (!hours[j][i].equals("")) {
					System.out.print(hours[j][i] + ", ");
				} else {
					System.out.print("----- , ");
				}
			}
			System.out.println("");
		}
	}

}
