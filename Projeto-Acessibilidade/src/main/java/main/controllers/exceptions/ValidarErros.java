package main.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidarErros extends StandardError{
    private static  final long serialVersionUID=1L;

    private List<CamposMsg> erros = new ArrayList<>();
    public ValidarErros(){

    }
    public ValidarErros(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<CamposMsg> getErros() {
        return erros;
    }

    public void addErros(String camposNome, String msg) {
        this.erros.add(new CamposMsg(camposNome,msg));
    }
}
