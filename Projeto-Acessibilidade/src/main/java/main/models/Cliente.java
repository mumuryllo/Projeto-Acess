package main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.dtos.ClienteDto;
import main.enums.Perfil;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cliente extends Pessoa  {


    private static final Long serialUID=1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(){
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String email, String cpf, String senha) {
        super(id, nome, email, cpf, senha);
        addPerfis(Perfil.CLIENTE);
    }

    public Cliente(ClienteDto obj) {
        super();
        this.setId(obj.getId());
        this.setNome(obj.getNome());
        this.setEmail(obj.getEmail());
        this.setCpf(obj.getCpf());
        this.setSenha(obj.getSenha());
        this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.setDataCriacao(obj.getDataCriacao());
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
