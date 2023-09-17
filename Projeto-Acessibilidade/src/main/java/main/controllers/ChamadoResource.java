package main.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import main.dtos.ChamadoDto;
import main.models.Chamado;
import main.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/chamados")
@Tag(name = "Feature - Chamados")
public class ChamadoResource {
    @Autowired
    private ChamadoService service;
    @GetMapping(value = "/{id}")
    @Operation(summary = " : Lista todos os chamados pelo ID", method = "GET")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(obj));
    }
    @GetMapping
    @Operation(summary = " : Lista todos os chamados ", method = "GET")
    public ResponseEntity<List<ChamadoDto>> findAll(){
        List<Chamado> obj = service.findAll();
        List<ChamadoDto> listDto =obj.stream().map(list-> new ChamadoDto(list)).collect(Collectors.toList());
        return  ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    @Operation(summary = " : Insere os dados dos chamados", method = "POST")
    public  ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto objDto){
        Chamado newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = " : Atualiza os dados do  chamados pelo ID", method = "UPDATE")
    public ResponseEntity<ChamadoDto> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDto objDto){
     Chamado newObj = service.update(id,objDto);
     return ResponseEntity.ok().body(new ChamadoDto(newObj));
    }

}
