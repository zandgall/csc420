package com.zandgall.csc420.fin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MapGraph {
	
	private ArrayList<String> locations;
	private ArrayList<ArrayList<Route>> routes;

	public MapGraph() {
		locations = new ArrayList<>();
		routes = new ArrayList<>();
	}

	public int addLocation(String location) {
		int index = locations.indexOf(location);
		// Only add locations not already included
		if(index == -1) {
			index = locations.size();
			locations.add(location);
		}
		return index;
	}

	public void addRoute(String from, String to, int distance) {
		int fromIndex = addLocation(from);
		int toIndex = addLocation(to);
		while(routes.size() <= fromIndex)
			routes.add(new ArrayList<>());
		routes.get(fromIndex).add(new Route(fromIndex, toIndex, distance));
	}

	private ArrayList<Node> getAvailablePaths(int from, int to) {
		ArrayList<Node> openNodes = new ArrayList<Node>(routes.get(from).stream().map(x -> new Node(x)).collect(Collectors.toList()));
		HashSet<Route> checkedRoutes = new HashSet<>();
		ArrayList<Node> endNodes = new ArrayList<>();
		while(!openNodes.isEmpty()) {
			Node n = openNodes.removeFirst();
			for(Route r : routes.get(n.route.to)) {
				if(!checkedRoutes.contains(r)) {
					Node nextNode = new Node(r, n);
					openNodes.add(nextNode);
					if(r.to == to)
						endNodes.add(nextNode);
				}
				checkedRoutes.add(n.route);
			}
		}
		return endNodes;
	}

	public Path getPath(int from, int to) {
		ArrayList<Node> paths = getAvailablePaths(from, to);
		if(paths.isEmpty())
			return new Path();
		paths.sort((a, b) -> a.accumulation - b.accumulation);

		return new Path(paths.getFirst());
	}

	public ArrayList<Route> getRoutesFrom(String from) {
		return routes.get(addLocation(from));
	}

	public String getLocationFromIndex(int index) {
		return locations.get(index);
	}

	public Path getPath(String from, String to) {
		return getPath(addLocation(from), addLocation(to));
	}

	public static class Route {
		private int from, to, distance;
		public Route(int from, int to, int distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}

		public int getFrom() { return from; }
		public int getTo() { return to; }
		public int getDistance() { return distance; }
	}

	public static class Path {
		public ArrayList<Route> stops;
		private int totalDistance;

		public Path() {
			stops = new ArrayList<>();
			totalDistance = 0;
		}

		protected Path(Node endNode) {
			stops = new ArrayList<>();
			if(endNode == null) {
				totalDistance = 0;
				return;
			}
			totalDistance = endNode.accumulation;
			for(Node n = endNode; n != null; n = n.previous)
				stops.addFirst(n.route);
		}

		public int getTotalDistance() { return totalDistance; }
	}

	private static class Node {
		Route route;
		int accumulation;
		Node previous;
		public Node(Route route) {
			this.route = route;
			this.accumulation = route.distance;
			this.previous = null;
		}

		public Node(Route route, Node previous) {
			this.route = route;
			if(previous == null)
				this.accumulation = route.distance;
			else
				this.accumulation = previous.accumulation + route.distance;
			this.previous = previous;
		}
	}
}
