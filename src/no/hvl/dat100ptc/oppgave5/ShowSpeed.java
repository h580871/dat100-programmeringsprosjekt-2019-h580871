package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
				
		// TODO - START
		
		double[] speeds = gpscomputer.speeds(); // make a table with avgspeed between two points
		
		setSpeed(timescaling);
		
		int xdiff = 2; // space between each bar
		setColor(0,0,255); // blue
		int avgStartX = MARGIN+xdiff; 
		
		for (int i = 0; i < N; i++) { // N - number of data points
			
			int spd = (int) speeds[i]; // get speed, change to integer
			
			drawLine(MARGIN+xdiff, ybase, MARGIN+xdiff, ybase-spd);
			// ybase is bottom, speed = pixel
			
			xdiff += 2; // moves x-position
		}
		
		int avgspeed = (int) gpscomputer.averageSpeed(); // uses method from previous task
		
		setColor(255,0,0); // red line
		drawLine(avgStartX, ybase-avgspeed, avgStartX+xdiff, ybase-avgspeed);
		
	
		// TODO - SLUTT
	}
}
