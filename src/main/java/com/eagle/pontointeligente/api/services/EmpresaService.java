package com.eagle.pontointeligente.api.services;

import java.util.Optional;

import com.eagle.pontointeligente.api.entities.Empresa;

public interface EmpresaService {
	
	/**
	 * Retorna uma empresa dado um CNPJ 
	 * 
	 * @param cnpj
	 * @return
	 */
	
	Optional<Empresa> buscarPorCnpj(String cnpj);

	/**
	 * 
	 *  Cadastra uma nova empresa no banco de dados 
	 *  @param empresa
	 *  @return empresa
	 *  
	 *  */
	Empresa persistir(Empresa empresa);
}
