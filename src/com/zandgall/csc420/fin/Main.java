package com.zandgall.csc420.fin;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main extends JFrame {

	public static final int WIDTH = 1450, HEIGHT = 1000;
	
	public Main() throws FileNotFoundException {
		MapGraph graph = new MapGraph();
		Scanner s = new Scanner(new File("routes.txt"));
		while(s.hasNextLine()) {
			String[] params = s.nextLine().split("\\|");
			if(params.length != 3)
				continue;
			graph.addRoute(params[0], params[1], Integer.parseInt(params[2]));
		}
		s.close();

		MapSurface surface = new MapSurface(graph);
		Panel container = new Panel(new BorderLayout());
		Panel p = new Panel();
		TextField fromField = new TextField("From...", 50), toField = new TextField("To...", 50);
		Button findRoute = new Button("Find Route");
		Label label = new Label("Route path will show here");
		findRoute.addActionListener((l) -> {
			surface.highlightPath(fromField.getText(), toField.getText());
			surface.repaint();
			MapGraph.Path path = graph.getPath(fromField.getText(), toField.getText());
			if(path.stops.isEmpty())
				return;
			String newLabel = graph.getLocationFromIndex(path.stops.getFirst().getFrom());
			for(MapGraph.Route r : path.stops)
				newLabel += " >> " + graph.getLocationFromIndex(r.getTo());
			newLabel += " (" + path.getTotalDistance() + ")";
			
			label.setText(newLabel);
		});
		container.add(surface, BorderLayout.CENTER);
		p.add(fromField);
		p.add(toField);
		p.add(findRoute);
		// p.add(label);
		container.add(p, BorderLayout.NORTH);
		container.add(label, BorderLayout.SOUTH);
		add(container);
		// add(surface);

        setTitle("Routing Map");
        setSize(1450, 1050);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws FileNotFoundException {	
		Main main = new Main();
		main.setVisible(true);
	}
}
