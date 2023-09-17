package main.services;

import main.enums.Perfil;
import main.enums.Prioridade;
import main.enums.Status;
import main.models.Chamado;
import main.models.Cliente;
import main.models.Tecnico;
import main.repositories.ChamadoRepository;
import main.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;


    @Autowired
    private PasswordEncoder encoder;

    public void instanciaDb(){


        Tecnico tec1 = new Tecnico(null, "Muryllo Soares", "mury@mail.com", "550.482.150-95", encoder.encode("123"));
        tec1.addPerfis(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "João Lobato", "joao@mail.com", "250.452.120-95",  encoder.encode("123"));
        Tecnico tec3 = new Tecnico(null, "Everton Carlos Nunes", "everton@mail.com", "570.462.350-75",  encoder.encode("123"));

        Cliente cli1 = new Cliente(null, "Albert Einstein", "einstein@mail.com", "111.661.890-74",  encoder.encode("123"));
        Cliente cli2 = new Cliente(null, "Nidalee", "nida@mail.com", "161.641.790-84",  encoder.encode("123"));
        Cliente cli3 = new Cliente(null, "Amy Rose", "amy@mail.com", "311.361.790-24",  encoder.encode("123"));

        Chamado c1 = new Chamado(null, Status.ANDAMENTO,Prioridade.MEDIA,"Chamado 1","Teste chamado 1",cli1,tec1);
        Chamado c2 = new Chamado(null, Status.ENCERRADO,Prioridade.BAIXA,"Chamado 2","Teste chamado 2",cli2,tec2);
        Chamado c3 = new Chamado(null, Status.ABERTO,Prioridade.ALTA,"Chamado 3","Teste chamado 3",cli3,tec3);

        pessoaRepository.saveAll(Arrays.asList(tec1,tec2,tec3,cli1,cli2,cli3));
        chamadoRepository.saveAll(Arrays.asList(c1,c2,c3));

    }

}
