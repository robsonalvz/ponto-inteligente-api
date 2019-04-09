package com.eagle.pontointeligente.api.repositories;




import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.eagle.pontointeligente.api.entities.Lancamento;
@Transactional(readOnly=true)
@NamedQueries({
	@NamedQuery(name = "LancamentosRepository.findByFuncionarioId",
			query = "SELECT lanc FROM Lancamento lanc WHERE lanc.funcionario.id = :funcionarioId")
})
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByFuncionarioId(Long funcionarioId);
	
	
	List<Lancamento> findByFuncionarioId(Long funcionarioId, Pageable pageable);
	
}
