/**
 * 
 */
package br.com.nutriCenter.converter;

import br.com.nutriCenter.DTO.PacienteDTO;
import br.com.nutriCenter.model.Paciente;

/**
 * @author José Caio
 *
 */
public class PacienteConverter extends Converter<PacienteDTO, Paciente> {

	  public PacienteConverter() {
	    super(PacienteConverter::convertToEntity, PacienteConverter::convertToDto);
	  }

	  private static PacienteDTO convertToDto(Paciente user) {
	    return new PacienteDTO(user.getId(),user.getNome(),user.getSobreNome(),
	    		user.getDataNasc(),user.getCell(),user.getNivelDeAcesso(),
	    		user.getGenero(), user.getCpf(), user.getCidade(), user.getRua(),
	    		user.getBairro(),user.getEstado(),user.getDataCadastro(),
	    		user.getDataUltimaConsulta(),user.getAvaliacoesDoPaciente());
	  }

	  private static Paciente convertToEntity(PacienteDTO dto) {
	    return new Paciente(dto.getId(),dto.getNome(),dto.getSobreNome(),
	    		dto.getDataNasc(),dto.getCell(),dto.getNivelDeAcesso(),
	    		dto.getGenero(), dto.getCpf(), dto.getCidade(), dto.getRua(),
	    		dto.getBairro(),dto.getEstado(),dto.getDataCadastro(),
	    		dto.getDataUltimaConsulta(),dto.getAvaliacoesDoPaciente());
	  }

	}
