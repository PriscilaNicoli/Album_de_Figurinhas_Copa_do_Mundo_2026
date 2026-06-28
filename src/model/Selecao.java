package model;

import java.util.ArrayList;
import java.util.List;

public class Selecao {
    private String nome;
    private List<Figura> figuras;

    public Selecao(String nome) {
        this.nome = nome;
        this.figuras = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Figura> getFiguras() {
        return figuras;
    }

    public void adicionarFigura(Figura figura) {
        figuras.add(figura);
    }

    public int contarFigurasColadas() {
        int total = 0;

        for (Figura figura : figuras) {
            if (figura.isColada()) {
                total++;
            }
        }

        return total;
    }

    public int contarFigurasRepetidas() {
        int total = 0;

        for (Figura figura : figuras) {
            total += figura.getQuantidadeRepetida();
        }

        return total;
    }

    public List<Figura> listarFigurasFaltantes() {
        List<Figura> faltantes = new ArrayList<>();

        for (Figura figura : figuras) {
            if (!figura.isColada()) {
                faltantes.add(figura);
            }
        }

        return faltantes;
    }

    public List<Figura> listarFigurasPorTipo(TipoFigura tipo) {
        List<Figura> encontradas = new ArrayList<>();

        for (Figura figura : figuras) {
            if (figura.getTipo() == tipo) {
                encontradas.add(figura);
            }
        }

        return encontradas;
    }
}