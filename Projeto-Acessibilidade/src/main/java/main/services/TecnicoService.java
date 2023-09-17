package main.services;

import main.dtos.TecnicoDto;
import main.models.Pessoa;
import main.models.Tecnico;
import main.repositories.PessoaRepository;
import main.repositories.TecnicoRepository;
import main.services.exceptions.DataViolationException;
import main.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;



    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDto objDTO){
        objDTO.setId(null);
        validaCpfEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return  repository.save(newObj);
    }

    private void  validaCpfEmail(TecnicoDto objDto){
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public Tecnico update(Integer id, TecnicoDto objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaCpfEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() >0){
            throw  new DataViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }else {
            repository.deleteById(id);
        }
    }
}
