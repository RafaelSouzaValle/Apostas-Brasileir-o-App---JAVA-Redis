package app;

import jedis.JedisManager;
import model.Campeonato;

public class BrasileiraoApp {

	public static void main(String[] args) {

		//Cria um campeonato
		Campeonato campeonato = new Campeonato();

		//Executa o campeonato
		campeonato.executaCampeonato();
		
		//imprime o campeonato
		campeonato.imprimeCampeonato();
		
		//Salva o campeonato no banco de dados
		JedisManager.salvaCampeonato(campeonato);
		
		//Recupera o campeonato salvo no Banco e o imprime
		JedisManager.carregaCampeonatoSalvo();
		
	}

}
