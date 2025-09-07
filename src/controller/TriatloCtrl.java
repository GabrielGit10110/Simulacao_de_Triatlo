package controller;

import java.util.concurrent.Semaphore;

import model.Atleta;

public class TriatloCtrl extends Thread {
    private final int athlete;
    private final Semaphore fiveStops;
    private int shootingPoints;
    
    private static int position = 0;
    private static int[] finalScores = new int[25];
    private static int[] athleteIds = new int[25];
    private static Semaphore staticMutex = new Semaphore(1);
    
    public TriatloCtrl(int athlete, Semaphore fiveStops) {
        this.athlete = athlete;
        this.fiveStops = fiveStops;
    }
    
    @Override
    public void run() {
        startRace(3000, 20, 25, 30, "corrida");
        highPriorityTasks();
        startRace(5000, 30, 40, 40, "bicicleta");
    }
    
    private void finished() {
        try {
            staticMutex.acquire();
            calculateFinalScore();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            staticMutex.release();
        }
    }
    
    private void startRace(int finishingDistance, int minDistance,
                         int maxDistance, int velocity, String raceType) {
        int currentDistance = 0;
        
        System.out.println("Atleta #"+athlete+" começou a "+raceType+"!");
        
        while (currentDistance < finishingDistance) {
            int distanceIncrement = (int)(minDistance + 
                (maxDistance - minDistance) * Math.random());
            currentDistance += distanceIncrement;
            
            try {
                sleep(velocity);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        System.out.println("Atleta #"+athlete+" finalizou a "+raceType+"!");
        
        if (raceType.equals("bicicleta")) {
            finished();
        }
    }
    
    private void highPriorityTasks() {
        try {
            fiveStops.acquire();
            shootingRange();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            fiveStops.release();
        }
    }
    
    private void shootingRange() {
        int targets = 3;
        this.shootingPoints = 0;
        
        System.out.println("Atleta #"+athlete+" começou a prova de tiro!");
        
        for (int i = 1; i <= targets; i++) {
            int shotAccuracy = (int)(Math.random() * 11); // 0 a 10
            int timeTaken = (int)(500 + (2500) * Math.random());
            
            this.shootingPoints += shotAccuracy;
            
            if (shotAccuracy == 0) {
                System.out.println("#"+athlete+" errou o "+i+"° tiro!");
            } else if (shotAccuracy == 10) {
                System.out.println("#"+athlete+" tiro perfeito no "+i+"° alvo!");
            } else {
                System.out.println("#"+athlete+" acertou "+shotAccuracy+" no "+i+"° alvo");
            }
            
            try {
                sleep(timeTaken);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
        System.out.println("Atleta #"+athlete+" finalizou a prova de tiro com "+shootingPoints+" pontos!");
    }
    
    private void calculateFinalScore() {
        // do the calculations based on the arrival order
        int basePoints = 250 - (position * 10);
        int totalScore = basePoints + this.shootingPoints;
        
        // set the total score and the athlete Id of the current thread
        finalScores[position] = totalScore;
        athleteIds[position] = athlete;
        
        System.out.println("Atleta #"+athlete+" finalizou em "+(position+1)+"° lugar " +
                         "com "+totalScore+" pontos ("+basePoints+" base + "+shootingPoints+" tiro)");
        
        position++;
        
        // when all operations are done, show the final ranking 
        if (position == 25) {
            displayFinalRanking();
        }
    }
    
    private void sortArray(Atleta[] results) {
    	for (int i = 0; i < results.length; i++) {
    		for (int j = (i + 1); j < results.length - 1; j++) {
    			if (results[i].getScore() < results[j].getScore()) {
    				Atleta aux = results[i];
    				results[i] = results[j];
    				results[j] = aux;
    				
    			}
    			
    		}
    		
    	}
    }
    
    private void displayFinalRanking() {
        try {
            System.out.println("\nFazendo contagem final dos pontos...");
            sleep(2000);
            
            // creates 25 instances of the Atleta object 
            Atleta[] results = new Atleta[25];
            for (int i = 0; i < 25; i++) {
                results[i] = new Atleta(athleteIds[i], finalScores[i]);
            }
            
            // sort the results array 
            sortArray(results);

            
            System.out.println("\n RANKING FINAL ");
            for (int i = 0; i < 25; i++) {
                System.out.println((i+1) + "° lugar: Atleta #" + results[i].getId() + 
                                 " com " + results[i].getScore() + " pontos");
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}