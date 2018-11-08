package model;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * 
 * Classe apenas para testar
 * a recuperação de dados
 * 
 * @author Rafael.Valle
 *
 */
public class Teste {

	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("localhost");
		
		List<String> list = jedis.lrange("01", 0, 100);
		
		for (String string : list) {
			System.out.print(string);
		}
	}
}
