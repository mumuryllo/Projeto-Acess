package main.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import main.dtos.TecnicoDto;
import main.enums.Perfil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Tecnico extends Pessoa{
    private static final Long serialUID=1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico(){
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String email, String cpf, String senha) {
        super(id, nome, email, cpf, senha);
        addPerfis(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDto obj) {
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
