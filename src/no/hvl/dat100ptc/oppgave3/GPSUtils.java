package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];

		for (double d : da) {

			if (d < min) {
				min = d;
			}
		}
		return min;

		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double latitudes[] = new double[gpspoints.length];

		for (int i = 0; i < latitudes.length; i++) {

			latitudes[i] = gpspoints[i].getLatitude();

		}
		return latitudes;

		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double longitudes[] = new double[gpspoints.length];

		for (int i = 0; i < longitudes.length; i++) {

			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		// TODO - START

		double latitude1 = toRadians(gpspoint1.getLatitude());
		double latitude2 = toRadians(gpspoint2.getLatitude());
		double longitude1 = toRadians(gpspoint1.getLongitude());
		double longitude2 = toRadians(gpspoint2.getLongitude());

		double n = latitude2 - latitude1;
		double m = longitude2 - longitude1;

		double a = (pow((sin(n / 2)), 2) + (cos(latitude1) * (cos(latitude2)) * (pow(sin(m / 2), 2))));
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		double d = R * c;

		return d;

		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		// TODO - START
		int secPoint1 = gpspoint1.getTime();
		int secPoint2 = gpspoint2.getTime();

		int secs = secPoint2 - secPoint1;

		double distance = GPSUtils.distance(gpspoint1, gpspoint2);

		double speed = (distance / secs) * 3.6;

		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START

		String hh = String.format("%02d", (int) secs / 3600);
		String mm = String.format("%02d", (int) secs % 3600 / 60);
		String ss = String.format("%02d", (int) secs % 60);
		
		timestr = String.format("%10s", hh+TIMESEP+mm+TIMESEP+ss);

		return timestr;

		// TODO - SLUTT

	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		
		// TODO - START
		DecimalFormat df2 = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));
		
		str = df2.format(d);
		str = String.format("%" + TEXTWIDTH + "s", str);
		
		return str;
		

		// TODO - SLUTT

	}
}
