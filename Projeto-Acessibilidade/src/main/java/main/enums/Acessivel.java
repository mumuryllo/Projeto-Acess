package main.enums;

public enum Acessivel {

    NORMAL(0,"ROLE_NORMAL"),ACESSIVEL(1,"ROLE_ACESSIVEL");

    private Integer codigo;
    private String descricao;

    private Acessivel(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Acessivel toEnumeration(Integer cod){
        if (cod == null){
            return  null;
        }
        for (Acessivel a:Acessivel.values()
        ) if (cod.equals(a.getCodigo())){
            return a;
        }
        throw  new IllegalArgumentException("Opção Inválida!");
    }

}
