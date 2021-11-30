package br.com.nutriCenter.services;

import br.com.nutriCenter.exception.ObjectNotFoundException;
import br.com.nutriCenter.model.Consulta;
import br.com.nutriCenter.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private NutricionistaService nutricionistaService;
    @Autowired
    private PacienteService pacienteService;

    public Consulta createConsulta(Consulta consulta, long idDoPaciente, long idDoNutricionista)throws Exception{
        if(this.nutricionistaService.findById(idDoNutricionista).isEmpty() ||
        this.pacienteService.findById(idDoPaciente).isEmpty()){
            throw new ObjectNotFoundException();
        }
        var nutricionista = this.nutricionistaService.findById(idDoNutricionista).get();
        nutricionista.getConsultaList().add(consulta);
        consulta.setPaciente(this.pacienteService.findById(idDoPaciente).get());
        this.nutricionistaService.update(nutricionista);


        return this.consultaRepository.save(consulta);
    }

    public List<Consulta> findAll(){
        return this.consultaRepository.findAll();
    }
}
