package model;

public class FiguraJogador extends Figura {
    private String posicao;

    public FiguraJogador(int numero, String nome, String posicao) {
        super(numero, nome, TipoFigura.JOGADOR);
        this.posicao = posicao;
    }

    public String getPosicao() {
        return posicao;
    }

    @Override
    public String getDescricao() {
        return getNumero() + " - " + getNome() + " - Jogador - Posição: " + posicao;
    }
}