package com.eagle.pontointeligente.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroPFDto {
	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	private Optional<String> qtdHorasAlmoco = Optional.empty();
	private String cnpj;
	
	public Long getId() {
		return id;
	}
	
	@NotEmpty(message= "Nome não pode ser vazio")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 a 200 caracteres")
	public String getNome() {
		return nome;
	}
	@Email(message = "Email inválido")
	@NotEmpty(message = "Email não pode ser vazio")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres")
	public String getEmail() {
		return email;
	}
	@NotEmpty(message = "Senha não pode ser vazia")
	public String getSenha() {
		return senha;
	}
	@NotEmpty(message = "CPF não pode ser vazia")
	@CPF(message = "CPF inválido")
	public String getCpf() {
		return cpf;
	}
	public Optional<String> getValorHora() {
		return valorHora;
	}
	public Optional<String> getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}
	public Optional<String> getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}
	
	@NotEmpty(message = "Senha não pode ser vazia")
	@CNPJ(message = "CNPJ inválido")
	public String getCnpj() {
		return cnpj;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}
	public void setQtdHorasTrabalhoDia(Optional<String> qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}
	public void setQtdHorasAlmoco(Optional<String> qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "CadastroPFDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco="
				+ qtdHorasAlmoco + ", cnpj=" + cnpj + "]";
	}
	
	
	
}
