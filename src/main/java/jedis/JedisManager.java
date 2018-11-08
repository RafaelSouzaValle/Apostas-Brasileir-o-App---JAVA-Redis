package jedis;

import java.util.ArrayList;
import java.util.List;

import model.Campeonato;
import model.Partida;
import model.Rodada;
import redis.clients.jedis.Jedis;

public class JedisManager {

	// Conecta no Redis do localhost
	public static Jedis jedis = new Jedis("localhost");

	//
	public static void gravarString(String chave, String valor) {
		jedis.set(chave, valor);
	}

	public static void salvaCampeonato(Campeonato campeonato) {

		// Remove os registros do campeonato anterior do Banco de Dados
		jedis.flushDB();

		// Recebe a lista de rodadas do campeonato passado como argumento
		List<Rodada> rodadas = campeonato.getRodadas();

		int numRodada = 0;
		// Lê as rodadas do campeonato
		for (Rodada rodada : rodadas) {

			// Recebe a lista de partidas da rodada atual
			List<Partida> partidas = rodada.getPartidas();

			int numPartida = 0;
			// Lê as partidas da rodada atual
			for (Partida partida : partidas) {

				// Recebe resulrado das partidas atuais
				String[] valores = { partida.getNomeTime1(), partida.getNomeTime2(), partida.getResultado() };

				String chave = numRodada + "" + numPartida;
				// Salva os times e o resultado no Banco de dados
				jedis.lpush(chave, valores[2]);
				jedis.lpush(chave, valores[1]);
				jedis.lpush(chave, valores[0]);

				numPartida++;
			}
			numRodada++;
		}
	}

	// Carrega o campeonato armazenado no Banco de Dados
	public static Campeonato carregaCampeonatoSalvo() {
		//Cria uma instância de Campeonato para receber
		//as informações extraídas do BD
		Campeonato campeonato = new Campeonato();
		
		// Lê as rodadas do campeonato
		for (int i = 0; i < Campeonato.getQuantidadeRodadas(); i++) {

			// Lê as partidas da rodada atual
			for (int j = 0; j < Rodada.getQuantidadePartidas(); j++) {
				String chave = i + "" + j;

				List<String> rodadas = new ArrayList<String>();

				//
				rodadas.add(jedis.lrange(chave, 0, 2).toString());

				for (String rodada : rodadas) {
					System.out.print("Rodada " + (i + 1) + " | Partida " + (j + 1) + rodada);
				}

				System.out.println();
			}
		}
		return null;
	}
	
}
