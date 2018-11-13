package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import campeonato.Campeonato;
import exception.BrasileiraoException;
import jedis.JedisManager;
import usuario.Aposta;
import usuario.Endereco;
import usuario.Genero;
import usuario.Usuario;
import util.Console;

public class Aplicacao {
	
	private static Menu menu = new Menu();

	public static void iniciar() {

		int opcao = 0;

		while (opcao != menu.SAIR) {
			try {
				opcao = menu.exibeOpcoes();

				switch (opcao) {
				case Menu.CADASTRO_USUARIO:
					System.out.println("Inserir usuário");
					inserirUsuario();
					break;
				case Menu.APOSTA:
					fazerAposta();
					break;
				case Menu.RANKING:
					exibirRanking();
					break;
				}

			} catch (BrasileiraoException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println();
			}
		}
	}

	private static void inserirUsuario() throws BrasileiraoException {
		Usuario usuario = new Usuario();

		System.out.print("Digite o apelido: ");
		usuario.setApelido(Console.readString());

		System.out.print("Digite o nome: ");
		usuario.setNome(Console.readString());

		System.out.print("Digite o nascimento: ");
		String dataNascimento = Console.readString();
		LocalDate d = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		usuario.setNascimento(d);

		System.out.print("Digite o gênero: M ou F");
		String inputGenero = Console.readString().toUpperCase();

		if (inputGenero.equals("M")) {
			usuario.setGenero(Genero.Masculino);
		}

		if (inputGenero.equals("F")) {
			usuario.setGenero(Genero.Feminino);
		}

		Endereco endereco = new Endereco();
		System.out.print("DIGITE ENDEREÇO: ");
		System.out.print("Insira o país: ");
		endereco.setPais(Console.readString());
		System.out.print("Insira o estado: ");
		endereco.setEstado(Console.readString());
		System.out.print("Insira a cidade: ");
		endereco.setCidade(Console.readString());
		System.out.print("Insira a rua: ");
		endereco.setRua(Console.readString());
		System.out.print("Insira o complemento: ");
		endereco.setComplemento(Console.readString());
		System.out.print("Insira o Código Postal: ");
		endereco.setCodigoPostal(Console.readString());

		usuario.setEndereco(endereco);

		usuario.setPontuacao(0);
		
		usuario.setAposta(null);
		
		JedisManager.salvaUsuario(usuario);

	}

	private static void fazerAposta() throws BrasileiraoException {
		//Buscar usuário do bando de dados pelo apelido e retorna o usuário como objeto
		System.out.println("Digite o apelido do usuário que fará a aposta: ");
		String apelido = Console.readString();
		Usuario usuario = JedisManager.carregaUsuarioSalvo(apelido);
		
		//Cria o campeonato principal e salva no Redis
		Campeonato campeonato = new Campeonato();
		campeonato.executaCampeonato();
		JedisManager.salvaCampeonato(campeonato);
		
		//Executa a aposta do usuário
		Aposta aposta = new Aposta();
		aposta.criaCampeonatoDaAposta(apelido, campeonato);
		usuario.setPontuacao(aposta.getPontuacao());
	}
	
	private static void exibirRanking() {
		JedisManager.carregarListaUsuarios();
	}
}
