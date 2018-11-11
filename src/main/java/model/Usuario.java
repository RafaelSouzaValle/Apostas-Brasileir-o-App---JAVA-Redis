package model;

import java.time.LocalDate;

public class Usuario {

	private String apelido;
	private String nome;
	private LocalDate nascimento;
	private Genero genero;
	private Endereco endereco;
	private Integer pontuacao;
	
	
	
	/**
	 * @param apelido
	 * @param nome
	 */
	public Usuario(String apelido, String nome) {
		this.apelido = apelido;
		this.nome = nome;
	}
	
	public Usuario() {
		
	}
	
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	
	@Override
	public String toString() {
		
		return "Jogador: " + apelido + " \n"
				+ "Nome: " + nome
				+ "\nNascimento: " + nascimento.getDayOfMonth() + "/" + nascimento.getDayOfMonth() + "/" + nascimento.getYear() 
				+ "\nGênero: " + genero.toString()
				+ "\nEndereço: " + endereco.toString()
				+ "\nPontuação: " + pontuacao;
	}
}
