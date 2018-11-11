package app;

import jedis.JedisManager;
import model.Aposta;
import model.Campeonato;

public class BrasileiraoApp {

	public static void main(String[] args) {

		//Cria uma aposta num campeonato
		Campeonato c1 = new Campeonato();
		
		//Executa aposta
		c1.executaCampeonato();
		
		//Cria um campeonato
		Campeonato campeonato = new Campeonato();

		//Executa o campeonato
		campeonato.executaCampeonato();
		
		//imprime pontuação obtida na aposta
		System.out.println(Aposta.calculaPontuacao(campeonato, c1));
		
		//imprime o campeonato
		campeonato.imprimeCampeonato();
		
		//Salva o campeonato no banco de dados
		JedisManager.salvaCampeonato(campeonato);
		
		//Recupera o campeonato salvo no Banco e o imprime
		JedisManager.carregaCampeonatoSalvo().imprimeCampeonato();
		
	}

}
