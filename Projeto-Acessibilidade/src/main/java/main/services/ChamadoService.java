package main.services;
import main.dtos.ChamadoDto;
import main.enums.Prioridade;
import main.enums.Status;
import main.models.Chamado;
import main.models.Cliente;
import main.models.Tecnico;
import main.repositories.ChamadoRepository;
import main.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(@Valid ChamadoDto objDto) {
        return  repository.save(newChamado(objDto));
    }

    public Chamado update(Integer id, @Valid ChamadoDto objDto){
        objDto.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDto);
        return  repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDto obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado(null, Status.ANDAMENTO,Prioridade.MEDIA,"Chamado 1","Teste chamado 1",cliente,tecnico);

        if (obj.getId() != null){
            chamado.setId(obj.getId());
        }

        if (obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnumeration(obj.getPrioridade()));
        chamado.setStatus(Status.toEnumeration(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacao());
        return chamado;

    }

}
