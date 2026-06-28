package model;

public class FiguraEspecial extends Figura {
    private String descricaoEspecial;

    public FiguraEspecial(int numero, String nome, TipoFigura tipo, String descricaoEspecial) {
        super(numero, nome, tipo);
        this.descricaoEspecial = descricaoEspecial;
    }

    public String getDescricaoEspecial() {
        return descricaoEspecial;
    }

    @Override
    public String getDescricao() {
        return getNumero() + " - " + getNome() + " - " + getTipo() + " - " + descricaoEspecial;
    }
}