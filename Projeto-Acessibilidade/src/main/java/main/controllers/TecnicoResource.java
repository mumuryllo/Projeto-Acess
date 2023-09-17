package main.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.dtos.TecnicoDto;
import main.models.Tecnico;
import main.services.TecnicoService;
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
@RequestMapping(value = "/tecnicos")
@Tag(name = "Feature - Tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    @Operation(summary = " : Lista todos os técnicos pelo ID", method = "GET")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }
    @GetMapping
    @Operation(summary = " : Lista todos os técnicos ", method = "GET")
    public ResponseEntity<List<TecnicoDto>> findAll(){
        List<Tecnico> obj = service.findAll();
        List<TecnicoDto> listDto =obj.stream().map(list-> new TecnicoDto(list)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    @Operation(summary = " : Insere os dados dos técnicos", method = "POST")
    public  ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto objDto){
        Tecnico newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = " : Atualiza os dados do  técnico pelo ID", method = "UPDATE")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id ,@Valid @RequestBody TecnicoDto objDto){
        Tecnico obj = service.update(id,objDto);
        return  ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = " : Exclui um técnico pelo id", method = "DELETE")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id){
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
