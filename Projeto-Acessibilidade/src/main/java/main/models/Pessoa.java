package main.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import main.enums.Perfil;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public abstract class Pessoa implements Serializable {

    private static final Long serialUID=1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;
    private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCriacao = LocalDate.now();




    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public Pessoa(Integer id, String nome, String email, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        addPerfis(Perfil.CLIENTE);
    }

    public Pessoa(){
      addPerfis(Perfil.CLIENTE);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnumeration(x)).collect(Collectors.toSet());
    }



    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
