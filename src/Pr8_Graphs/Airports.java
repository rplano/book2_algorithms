import graph.AbstractEdge;
import graph.Dijkstra;
import graph.GraphEdgeList;
import graph.Vertex;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import tree.GThickLine;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

/**
 * MIT License (http://choosealicense.com/licenses/mit/)
 * 
 * Airports
 * 
 * A graphics program, demonstrating some features of a graph.
 * 
 * @see http://www.VariationenZumThema.de/
 * @author Ralph P. Lano
 */
public class Airports extends GraphicsProgram {
	private static final int RADIUS = 5;

	private double latMin = 0;
	private double latMax = 0;
	private double lonMin = 0;
	private double lonMax = 0;

	private Map<String, Location> airportLocations = new HashMap<String, Location>();
	private Map<GObject, String> airportNames = new HashMap<GObject, String>();

	private GraphEdgeList<Integer, String> graph;
	private Map<String, Vertex<String>> vertices;

	private GLabel lbl;

	private int clickCount = 0;
	private String departure;
	private String destination;


	@Override
	public void run() {
		graph = new GraphEdgeList<Integer, String>();
		vertices = new HashMap<String, Vertex<String>>();

		loadMap("MapEurope.png");
		loadAirports("airport_codes.txt");

		displayConnections();
		displayAirports();

		lbl = new GLabel("");
		lbl.setFont("Arial-bold-18");
		lbl.setVisible(false);
		add(lbl);
	}
	
	private void displayConnections() {
		Collection<AbstractEdge<Integer>> edges = graph.edges();
		for (AbstractEdge<Integer> edge : edges) {
			int distance = edge.getElement();
			Vertex<?>[] vtcs = edge.getVertices();
			Vertex<String> from = (Vertex<String>) vtcs[0];
			Vertex<String> to = (Vertex<String>) vtcs[1];
			// System.out.println(from.getElement()+"-"+to.getElement());

			Location locFrom = airportLocations.get(from.getElement());
			Location locTo = airportLocations.get(to.getElement());
			drawLine(locFrom, locTo, Color.GRAY);
		}
	}

	private void drawLine(Location locFrom, Location locTo, Color col) {
		Point from = scalePoint(locFrom);
		Point to = scalePoint(locTo);
		// GLine line = new GLine(from.getX(), from.getY(), to.getX(),
		// to.getY());
		GThickLine line = new GThickLine(from.getX(), from.getY(), to.getX(),
				to.getY(), 2);
		line.setColor(col);
		add(line);
	}

	private void displayAirports() {
		for (String city : airportLocations.keySet()) {
			Location loc = airportLocations.get(city);
			Point p = scalePoint(loc);
			GOval airport = new GOval(p.x - RADIUS, p.y - RADIUS, RADIUS * 2,
					RADIUS * 2);
			airport.setFilled(true);
			airport.setFillColor(Color.GREEN);
			airport.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((GOval) e.getSource()).setFillColor(Color.RED);
					if (clickCount % 2 == 0) { // first
						departure = airportNames.get(((GOval) e.getSource()));
					} else { // second
						destination = airportNames.get(((GOval) e.getSource()));
						findBestRoute(departure, destination);
					}
					clickCount++;
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					lbl.setLabel(airportNames.get(((GOval) e.getSource())));
					lbl.setLocation(e.getX(), e.getY());
					lbl.setVisible(true);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lbl.setVisible(false);
				}
			});
			add(airport);
			airportNames.put(airport, city);
		}

	}

	private void findBestRoute(String departure, String destination) {
		Vertex<String> sourceVertex = graph.findVertex(departure);
		Vertex<String> destinationVertex = graph.findVertex(destination);

		try {
			Dijkstra<Integer, String> dijk = new Dijkstra<Integer, String>(
					graph);
			println("Shortest distance from "+departure+" to "+destination+": "
					+ dijk.shortestPath(sourceVertex, destinationVertex));
			
			// fastest route from B to A:
			//println("Fastest route from "+sourceVertex.getElement()+" to "+destinationVertex.getElement()+": ");
			Map<Vertex<String>, Vertex<String>> predcrs = dijk
					.getAllPredecessors(sourceVertex);
			Vertex<String> vTmp = destinationVertex;
			while (vTmp != sourceVertex) {
				print(vTmp.getElement() + " -> ");
				Vertex<String> vTmpTo = predcrs.get(vTmp);

				Location locFrom = airportLocations.get(vTmp.getElement());
				Location locTo = airportLocations.get(vTmpTo.getElement());
				drawLine(locFrom, locTo, Color.RED);
				
				vTmp = vTmpTo;
			}
			println(vTmp.getElement());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Point scalePoint(Location loc) {
		double scaleY = getHeight() / (latMax - latMin);
		double scaleX = getWidth() / (lonMax - lonMin);
		int y = (int) (getHeight() - (loc.latitude - latMin) * scaleY + 40);
		int x = (int) ((loc.longitude - lonMin) * scaleX + 5);
		Point p = new Point(x, y);
		return p;
	}

	private void loadAirports(String fileName) {
		try {
			int state = -1;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				if (!line.startsWith("#")) { // ignore comments
					switch (state) {
					case 0:
						// size of map
						getBoundaries(line);
						break;
					case 1:
						// location
						addToCities(line);
						break;
					case 2:
						// distances
						addToDistances(line);
						break;
					case 3:
						// ignore
						break;

					default:
						System.out.println("we should never get here... "
								+ state);
						break;
					}
				} else {
					state++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean firstDistanceLine = true;
	private String[] airportKeys = null;

	private void addToDistances(String line) {
		if (firstDistanceLine) {
			airportKeys = line.split(",");
			for (int i = 0; i < airportKeys.length; i++) {
				airportKeys[i] = airportKeys[i].trim();
			}
			firstDistanceLine = false;
		} else {
			String[] data = line.split(",");
			String from = data[0].trim();
			for (int i = 1; i < data.length; i++) {
				String to = airportKeys[i];
				if (data[i].trim().length() > 0) {
					int distance = Integer.parseInt(data[i].trim());
					if (distance > 0) {
						// System.out.println(from + "-" + to + ": " +
						// distance);
						if (!vertices.containsKey(from)) {
							Vertex<String> vtx = graph
									.insertVertex(new Vertex<String>(from));
							vertices.put(from, vtx);
						}
						if (!vertices.containsKey(to)) {
							Vertex<String> vtx = graph
									.insertVertex(new Vertex<String>(to));
							vertices.put(to, vtx);
						}
						graph.insertEdge(vertices.get(from), vertices.get(to),
								distance);
					}
				}
			}
		}
	}

	private void getBoundaries(String line) {
		String[] data = line.split(",");
		latMax = Double.parseDouble(data[0].trim());
		latMin = Double.parseDouble(data[1].trim());
		lonMin = Double.parseDouble(data[2].trim());
		lonMax = Double.parseDouble(data[3].trim());
	}

	private void addToCities(String line) {
		String[] data = line.split(",");
		Location loc = new Location(Double.parseDouble(data[1].trim()),
				Double.parseDouble(data[2].trim()));
		airportLocations.put(data[0].trim(), loc);
	}

	private void loadMap(String fileName) {
		GImage map = new GImage(fileName);
		this.setSize((int) map.getWidth(), (int) map.getHeight());
		add(map);
	}

	private class Location {
		public double latitude;
		public double longitude;

		public Location(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}

	}
}
