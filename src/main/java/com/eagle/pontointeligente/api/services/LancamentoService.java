package com.eagle.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.eagle.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {
	/**
	 * Retorna uma lista paginada de lancamentos de um determinado funcionario.
	 * 
	 * @param funcionarioId
	 * @param pageRequest
	 * @return
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

	/**
	 * Retorna um lancamento pelo seu id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Lancamento> buscarPorId(Long id);

	/**
	 * Persistir um lancamento no banco de dados.
	 * 
	 * @param lancamento
	 * @return
	 */
	Lancamento persistir(Lancamento lancamento);

	/**
	 * Remove um lancamento do banco de dados.
	 * 
	 * @param id
	 */
	void remover(Long id);
}
