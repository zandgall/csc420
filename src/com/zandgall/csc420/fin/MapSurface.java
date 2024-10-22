package com.zandgall.csc420.fin;

import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

import com.zandgall.csc420.fin.MapGraph.Route;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapSurface extends JPanel {

	private static final float SCALE = (int)(Main.WIDTH / 7.25);

	private static final Font FONT_SMALL = new Font("san-serif", Font.PLAIN, 18), FONT_LARGE = new Font("san-serif", Font.BOLD, 24);
	
	private HashMap<String, Point> locations;

	private MapGraph graph;

	private MapGraph.Path highlighted = null;

	public MapSurface(MapGraph graph) throws FileNotFoundException {
		super();	
		locations = new HashMap<>();
		this.graph = graph;
		
		Scanner s = new Scanner(new File("map.txt"));
		while(s.hasNextLine()) {
			String[] args = s.nextLine().split("\\|");
			if(args.length != 3)
				continue;
			locations.put(args[0], new Point((int)(Float.parseFloat(args[1]) * SCALE), (int)(Float.parseFloat(args[2]) * SCALE)));
		}
		s.close();
	}

	public void highlightPath(String from, String to) {
		highlighted = graph.getPath(from, to);
	}

	@Override
	public void paintComponent(Graphics graphics) {
		Graphics2D g = (Graphics2D)graphics;
		g.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		for(Map.Entry<String, Point> e : locations.entrySet()) {
			int x = e.getValue().x, y = e.getValue().y;
			ArrayList<MapGraph.Route> routesOut = graph.getRoutesFrom(e.getKey());
			g.setColor(Color.BLACK);
			g.setFont(FONT_SMALL);
			for(MapGraph.Route r : routesOut) {
				Point to = locations.get(graph.getLocationFromIndex(r.getTo()));

				double angle = Math.atan2(to.y - y, to.x - x), sideAngle = angle + Math.PI*0.5;

				double xDir = Math.cos(angle), yDir = Math.sin(angle);
				double xOff = Math.cos(sideAngle), yOff = Math.sin(sideAngle);

				g.drawLine(x+(int)(xOff*5+xDir*50), y+(int)(yOff*5+yDir*50), to.x+(int)(xOff*5-xDir*50), to.y+(int)(yOff*5-yDir*50));
				g.drawLine(to.x+(int)(xOff*5-xDir*50), to.y+(int)(yOff*5-yDir*50), to.x + (int)(xOff*10 - xDir*60), to.y + (int)(yOff*10 - yDir*60));

				Point textPos = new Point((int)(15*xOff + (to.x + x)*0.5), (int)(15*yOff + (to.y + y)*0.5));
				g.drawString(""+r.getDistance(), textPos.x-5, textPos.y+5);
			}
			
			g.setFont(FONT_LARGE);
			g.setColor(Color.BLUE);
			g.fillOval((int)(x - 10), (int)(y - 10), 20, 20);
			Rectangle2D textBounds = g.getFontMetrics().getStringBounds(e.getKey(), g);
			int textX = (int)Math.min(Math.max(x - textBounds.getWidth() / 2, 0), Main.WIDTH - textBounds.getWidth());
			int textY = (int)Math.min(Math.max(y - 20 - textBounds.getHeight() / 2, 0), Main.HEIGHT - textBounds.getHeight());
			g.drawString(e.getKey(), textX, textY);
		}
		if(highlighted != null) {
			g.setStroke(new BasicStroke(10.f));
			g.setColor(Color.GREEN);
			for(Route r : highlighted.stops) {
				Point from = locations.get(graph.getLocationFromIndex(r.getFrom()));
				Point to = locations.get(graph.getLocationFromIndex(r.getTo()));
				g.drawLine(from.x, from.y, to.x, to.y);
				g.fillOval(from.x - 10, from.y - 10, 20, 20);
			}
			Point end = locations.get(graph.getLocationFromIndex(highlighted.stops.getLast().getTo()));
			g.fillOval(end.x - 10, end.y - 10, 20, 20);
		}
	}
}
