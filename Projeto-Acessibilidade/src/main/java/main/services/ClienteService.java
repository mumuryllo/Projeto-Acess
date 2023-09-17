package main.services;

import main.dtos.ClienteDto;
import main.models.Cliente;
import main.models.Pessoa;
import main.repositories.ClienteRepository;
import main.repositories.PessoaRepository;
import main.services.exceptions.DataViolationException;
import main.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;


    public Cliente findById(Integer id){
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException("Objeto não encontrado! Id: " + id));
    }
    public Cliente update(Integer id, ClienteDto objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaCpfEmail(objDto);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDto objDTO){
        objDTO.setId(null);
        validaCpfEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return  repository.save(newObj);
    }

    private void  validaCpfEmail(ClienteDto objDto){
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataViolationException("E-mail já cadastrado no sistema!");
        }
    }



    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() >0){
            throw  new DataViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }else {
            repository.deleteById(id);
        }
    }
}
