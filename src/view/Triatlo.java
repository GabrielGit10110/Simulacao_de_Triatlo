package view;

import java.util.concurrent.Semaphore;

import controller.TriatloCtrl;

public class Triatlo {
	public static void main(String[] args) {
		Semaphore fiveStops = new Semaphore(5);
		
		for (int i = 0; i < 25; i++) {
			TriatloCtrl newAthlete = new TriatloCtrl(i, fiveStops);
			newAthlete.start();
			
		}
		
	}
}
