package com.eagle.pontointeligente.api.controllers;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eagle.pontointeligente.api.dtos.CadastroPFDto;
import com.eagle.pontointeligente.api.entities.Empresa;
import com.eagle.pontointeligente.api.entities.Funcionario;
import com.eagle.pontointeligente.api.enums.PerfilEnum;
import com.eagle.pontointeligente.api.response.Response;
import com.eagle.pontointeligente.api.services.EmpresaService;
import com.eagle.pontointeligente.api.services.FuncionarioService;
import com.eagle.pontointeligente.api.utils.PasswordUtils;

@RestController
@RequestMapping("api/cadastrar-pf")
@CrossOrigin(origins = "*")
public class CadastroPFController {
	private static final Logger log = LoggerFactory.getLogger(CadastroPFController.class);
	
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public CadastroPFController() {
		
	}
	/**
	 * 
	 * @param cadastroPFDto
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public ResponseEntity<Response<CadastroPFDto>> cadastrar(@Valid @RequestBody CadastroPFDto cadastroPFDto,
			BindingResult result) throws NoSuchAlgorithmException{
		Response<CadastroPFDto> response = new Response<CadastroPFDto>();
		
		validarDadosExistentes(cadastroPFDto,result);
		Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPFDto,result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PF:{}",result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.persistir(funcionario);
		response.setData(this.converterCadastroFPDto(funcionario));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Popula o DTO de cadastro com os dados do funcioario e empresa.
	 * @param funcionario
	 * @return
	 */
	private CadastroPFDto converterCadastroFPDto(Funcionario funcionario) {
		CadastroPFDto cadastroPFDto = new CadastroPFDto();
		cadastroPFDto.setId(funcionario.getId());
		cadastroPFDto.setNome(funcionario.getNome());
		cadastroPFDto.setEmail(funcionario.getEmail());
		cadastroPFDto.setCpf(funcionario.getCpf());
		cadastroPFDto.setCnpj(funcionario.getEmpresa().getCnpj());
		funcionario.getQtdHorasAlmocoOpt().ifPresent(qtdHorasAlmoco -> cadastroPFDto.setQtdHorasAlmoco(Optional.of(Float.toString(qtdHorasAlmoco))));
		funcionario.getQtdHorasTrabalhoDiaOpt().ifPresent(qtdHorasDia -> cadastroPFDto.setQtdHorasTrabalhoDia(Optional.of(Float.toString(qtdHorasDia))));
		funcionario.getValorHoraOpt().ifPresent(valorHora -> cadastroPFDto.setValorHora(Optional.of(valorHora.toString())));
		return cadastroPFDto;
	}
	/**
	 * Converte DTo para funcionario.
	 * 
	 * @param cadastroPFDto
	 * @param result
	 * @return
	 */
	private Funcionario converterDtoParaFuncionario(CadastroPFDto cadastroPFDto, BindingResult result) throws NoSuchAlgorithmException{
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPFDto.getNome());
		funcionario.setEmail(cadastroPFDto.getEmail());
		funcionario.setCpf(cadastroPFDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(cadastroPFDto.getSenha()));
		cadastroPFDto.getQtdHorasAlmoco().ifPresent(qtdHorasAlmoco -> funcionario.setQtdHorasAlmoco(Float.valueOf(qtdHorasAlmoco)));
		cadastroPFDto.getQtdHorasTrabalhoDia().ifPresent(qtdHorasTrabalhoDia -> funcionario.setQtdHorasTrabalhoDia(Float.valueOf(qtdHorasTrabalhoDia)));
		cadastroPFDto.getValorHora().ifPresent(valorHora -> funcionario.setValorHora(new BigDecimal(valorHora)));
		return funcionario;
	}

	/**
	 * Verifica se a empresa está cadastrada e se  funcionario não existe na base de dados.
	 * @param cadastroPFDto
	 * @param result
	 */
	private void validarDadosExistentes(CadastroPFDto cadastroPFDto, BindingResult result) {
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cadastroPFDto.getCnpj());
		if (!empresa.isPresent()) {
			result.addError(new ObjectError("empresa", "Empresa não cadastrada."));
		}
		this.funcionarioService.buscarPorCpf(cadastroPFDto.getCpf()).ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente")));
		this.funcionarioService.buscarPorEmail(cadastroPFDto.getEmail()).ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente")));
		
	}
	
}
