package main.controllers.exceptions;

import java.io.Serializable;

public class CamposMsg  implements Serializable{

    private static  final long serialVersionUID=1L;

    private String campoNome,msg;



    public CamposMsg(String campoNome, String msg) {
        this.campoNome = campoNome;
        this.msg = msg;
    }

    public String getCampoNome() {
        return campoNome;
    }

    public void setCampoNome(String campoNome) {
        this.campoNome = campoNome;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
