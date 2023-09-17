package main.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.dtos.ClienteDto;
import main.models.Cliente;
import main.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
@Tag(name = "Feature - Clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @PreAuthorize("hasRole('CLIENTE')")
    @GetMapping(value = "/{id}")
    @Operation(summary = " : Lista todos os clientes pelo ID", method = "GET")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id){
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDto(obj));
    }
    @GetMapping
    @Operation(summary = " : Lista todos os clientes ", method = "GET")
    @PreAuthorize("hasRole('ROLE_CLIENTE')")
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> obj = service.findAll();
        List<ClienteDto> listDto =obj.stream().map(list-> new ClienteDto(list)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    @Operation(summary = " : Insere os dados dos clientes", method = "POST")
    @PreAuthorize("hasAnyRole('CLIENTE', 'ROLE_ADMIN')")
    public  ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDto){
        Cliente newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = " : Atualiza os dados do  cliente pelo ID", method = "UPDATE")
    @PreAuthorize("hasAnyRole('ROLE_CLIENTE', 'ROLE_ADMIN')")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id ,@Valid @RequestBody ClienteDto objDto){
        Cliente obj = service.update(id,objDto);
        return  ResponseEntity.ok().body(new ClienteDto(obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = " : Exclui um cliente pelo id", method = "DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }



}
