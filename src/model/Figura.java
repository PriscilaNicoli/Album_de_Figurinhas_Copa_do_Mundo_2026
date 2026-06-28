package model;

public abstract class Figura {
    private int numero;
    private String nome;
    private TipoFigura tipo;
    private boolean colada;
    private int quantidadeRepetida;

    public Figura(int numero, String nome, TipoFigura tipo) {
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
        this.colada = false;
        this.quantidadeRepetida = 0;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public TipoFigura getTipo() {
        return tipo;
    }

    public boolean isColada() {
        return colada;
    }

    public int getQuantidadeRepetida() {
        return quantidadeRepetida;
    }

    public void colar() {
        this.colada = true;
    }

    public void adicionarRepetida() {
        this.quantidadeRepetida++;
    }

    public abstract String getDescricao();

    @Override
    public String toString() {
        return numero + " - " + nome + " (" + tipo + ")";
    }
}