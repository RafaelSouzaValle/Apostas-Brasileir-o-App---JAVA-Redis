package model;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Random rand = new Random();
		
		int[] rodadas = new int[38];
		int[] partidas = new int[10];
		int[] times = new int[2];
		int[] timesQueJogaram = new int[20];
		int timeIndice = 0;
		
		int randTime = 0;
		int time1 = 0;
		int time2 = 0;
		
		for (int i = 0; i < rodadas.length; i++) {
			System.out.println("Rodada " + i + "===================");
			for (int j = 0; j < partidas.length; j++) {
				System.out.print("Partida " + j + "---> ");

				
				timesQueJogaram[timeIndice] = randTime;
				if(timeIndice < 19) {
					timeIndice++;
				}
				
				
				for (int k = 0; k < timesQueJogaram.length; k++) {
					randTime = rand.nextInt(20);
					if(randTime == timesQueJogaram[k] || randTime == time1 || randTime == time2) {
						break;
					} else {
						time1 = randTime;
					}
					
					
				}
				
				for (int k = 0; k < timesQueJogaram.length; k++) {
					randTime = rand.nextInt(20);
					if(randTime == timesQueJogaram[k] || randTime == time1 || randTime == time2) {
						break;
					} else {
						time2 = randTime;
					}
				}
				
				System.out.println("Time " + time1 + " X " + "Time " + time2);
			}
		}
	}
}