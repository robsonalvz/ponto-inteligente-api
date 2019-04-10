package com.eagle.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.eagle.pontointeligente.api.entities.Lancamento;
import com.eagle.pontointeligente.api.repositories.LancamentoRepository;
import com.eagle.pontointeligente.api.services.LancamentoService;

@Service
public class LancamentoSerciveImpl implements LancamentoService {
	private static final Logger log = LoggerFactory.getLogger(LancamentoSerciveImpl.class);

	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	
	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando funcionario com id {}",funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando funcionario com id {}",id);
		return Optional.ofNullable(this.lancamentoRepository.findOne(id));
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo o lancamento {}",lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo o lancamento do banco de dados.");
		this.lancamentoRepository.delete(id);

	}

}
