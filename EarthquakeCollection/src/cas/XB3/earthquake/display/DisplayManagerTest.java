package cas.XB3.earthquake.display;

import java.util.ArrayList;
import java.util.Scanner;

import cas.XB3.earthquake.ADT.PointT;

public class DisplayManagerTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter the latidude");
		double lat = input.nextDouble();
		System.out.println("Please enter the longitude:");
		double longi = input.nextDouble();
		System.out.println("Please enter the radius:");
		int radius = input.nextInt();
		System.out.println("Please choose the display option:");
		int choice;

		PointT location = new PointT(lat, longi);
		DisplayManager displaymanager = new DisplayManager(location, radius);
		do {
			System.out.println("Display by Magnitude, choose 1; Display by time, choose 2, exist, choose 0");
			choice = input.nextInt();
			if (choice == 1) {
				displaymanager.display(new DispalyByMagnitude());
			} else if (choice == 2) {
				displaymanager.display(new DisplayByDistance());
			}
			
		}while(choice != 0);			

		input.close();
	}

}
