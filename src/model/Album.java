package model;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String nome;
    private Proprietario proprietario;
    private List<Selecao> selecoes;

    public Album(String nome, Proprietario proprietario) {
        this.nome = nome;
        this.proprietario = proprietario;
        this.selecoes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public List<Selecao> getSelecoes() {
        return new ArrayList<>(selecoes);
    }

    public void adicionarSelecao(Selecao selecao) {
        selecoes.add(selecao);
    }

    public int contarTotalFiguras() {
        int total = 0;

        for (Selecao selecao : selecoes) {
            total += selecao.getFiguras().size();
        }

        return total;
    }

    public int contarFigurasColadas() {
        int total = 0;

        for (Selecao selecao : selecoes) {
            total += selecao.contarFigurasColadas();
        }

        return total;
    }

    public int contarFigurasRepetidas() {
        int total = 0;

        for (Selecao selecao : selecoes) {
            total += selecao.contarFigurasRepetidas();
        }

        return total;
    }

    public List<Figura> listarFigurasColadas() {
        List<Figura> coladas = new ArrayList<>();

        for (Selecao selecao : selecoes) {
            for (Figura figura : selecao.getFiguras()) {
                if (figura.isColada()) {
                    coladas.add(figura);
                }
            }
        }

        return coladas;
    }

    public List<Figura> listarFigurasRepetidas() {
        List<Figura> repetidas = new ArrayList<>();

        for (Selecao selecao : selecoes) {
            for (Figura figura : selecao.getFiguras()) {
                if (figura.getQuantidadeRepetida() > 0) {
                    repetidas.add(figura);
                }
            }
        }

        return repetidas;
    }

    public List<Figura> listarFigurasFaltantes() {
        List<Figura> faltantes = new ArrayList<>();

        for (Selecao selecao : selecoes) {
            faltantes.addAll(selecao.listarFigurasFaltantes());
        }

        return faltantes;
    }

    public List<Figura> listarFigurasFaltantesPorSelecao(String nomeSelecao) {
        for (Selecao selecao : selecoes) {
            if (selecao.getNome().equalsIgnoreCase(nomeSelecao)) {
                return selecao.listarFigurasFaltantes();
            }
        }

        return new ArrayList<>();
    }

    public List<Figura> listarFigurasPorTipo(TipoFigura tipo) {
        List<Figura> figuras = new ArrayList<>();

        for (Selecao selecao : selecoes) {
            figuras.addAll(selecao.listarFigurasPorTipo(tipo));
        }

        return figuras;
    }

    public Figura buscarFiguraPorNumero(int numero) {
        for (Selecao selecao : selecoes) {
            for (Figura figura : selecao.getFiguras()) {
                if (figura.getNumero() == numero) {
                    return figura;
                }
            }
        }

        return null;
    }
}