package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;
import java.lang.Math;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 600;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

//		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		ystep = MAPYSIZE / (Math.abs(maxlat - minlat));
		
		return ystep;

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		
		// latitude -> y-axis, longitude -> x-axis
		
		double[] latitudes = GPSUtils.getLatitudes(gpspoints);
		double[] longitudes = GPSUtils.getLongitudes(gpspoints);
		
		int x = 0, y = 0;
		
		double minLat = GPSUtils.findMin(latitudes);
		double minLong = GPSUtils.findMin(longitudes);
		
		double ystep = ystep();
		double xstep = xstep();
		
		int prevX = (int) Math.round(((longitudes[0] - minLong) * xstep));
		int prevY = (int) Math.round(((latitudes[0] - minLat) * ystep));
		
		setColor(0,255,0);
		int r = 3; // radius for points
		
		for (int i = 0; i < gpspoints.length; i++) {
			x = (int) Math.round(((longitudes[i] - minLong) * xstep));
			y = (int) Math.round(((latitudes[i] - minLat) * ystep));
			
			fillCircle(x + MARGIN, ybase - y,r);
			
			drawLine(prevX + MARGIN, ybase - prevY, x + MARGIN, ybase - y);
			
			prevX = x;
			prevY = y;	
		}
		
		setColor(0,0,255);
		
		fillCircle(x + MARGIN, ybase - y, r*2);
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int MARGIN = 10;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		int seconds = gpscomputer.totalTime();
		String time = GPSUtils.formatTime(seconds);
		
		double dis = gpscomputer.totalDistance() / 1000.0;
		String distance = GPSUtils.formatDouble(dis) + " km";
		
		double ele = gpscomputer.totalElevation();
		String elevation = GPSUtils.formatDouble(ele) + " m";
		
		double maxSpd = gpscomputer.maxSpeed();
		String maxSpeed = GPSUtils.formatDouble(maxSpd) + " m/s";
		
		double avgSpd = gpscomputer.averageSpeed();
		String avgSpeed = GPSUtils.formatDouble(avgSpd) + " m/s";
		
		double calories = gpscomputer.totalKcal(gpscomputer.getWeight());
		String energy = GPSUtils.formatDouble(calories) + " kcal";
		
		drawString("Total time     :" + time, MARGIN, TEXTDISTANCE);
		drawString("Total distance :" + distance, MARGIN, TEXTDISTANCE*2);
		drawString("Total elevation:" + elevation, MARGIN, TEXTDISTANCE*3);
		drawString("Max speed      :" + maxSpeed, MARGIN, TEXTDISTANCE*4);
		drawString("Average speed  :" + avgSpeed, MARGIN, TEXTDISTANCE*5);
		drawString("Energy         :" + energy, MARGIN, TEXTDISTANCE*6);
		
		
		
		
		
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

}
