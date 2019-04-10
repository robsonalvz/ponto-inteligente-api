package com.eagle.pontointeligente.api.services;

import java.util.Optional;

import com.eagle.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {

	/**
	 * Persiste um funcionario no banco de dados.
	 * @param funcionario
	 * @return
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * Buscar um funcionario pelo seu CPF.
	 * @param cpf
	 * @return
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * Buscar um funcionario pelo seu email.
	 * @param email
	 * @return
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * Buscar um funcionário pelo seu id.
	 * @param id
	 * @return
	 */
	Optional<Funcionario> buscarPorId(Long id);
}
