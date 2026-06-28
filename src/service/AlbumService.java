package service;

import exception.FiguraNaoEncontradaException;
import model.Album;
import model.Figura;
import model.TipoFigura;

import java.util.List;

public class AlbumService {
    private Album album;

    public AlbumService(Album album) {
        this.album = album;
    }

    public Figura registrarFiguraColada(int numero) throws FiguraNaoEncontradaException {
        Figura figura = album.buscarFiguraPorNumero(numero);

        if (figura == null) {
            throw new FiguraNaoEncontradaException("Figura número " + numero + " não encontrada.");
        }

        figura.colar();
        return figura;
    }

    public void registrarFiguraRepetida(int numero) throws FiguraNaoEncontradaException {
        Figura figura = album.buscarFiguraPorNumero(numero);

        if (figura == null) {
            throw new FiguraNaoEncontradaException("Figura número " + numero + " não encontrada.");
        }

        figura.adicionarRepetida();
    }

    public int contarFigurasColadas() {
        return album.contarFigurasColadas();
    }

    public int contarFigurasRepetidas() {
        return album.contarFigurasRepetidas();
    }

    public int contarTotalFiguras() {
        return album.contarTotalFiguras();
    }

    public List<Figura> listarFigurasFaltantes() {
        return album.listarFigurasFaltantes();
    }

    public List<Figura> listarFigurasFaltantesPorSelecao(String nomeSelecao) {
        return album.listarFigurasFaltantesPorSelecao(nomeSelecao);
    }

    public List<Figura> listarFigurasPorTipo(TipoFigura tipo) {
        return album.listarFigurasPorTipo(tipo);
    }

    public List<Figura> listarFigurasColadas() {
    return album.listarFigurasColadas();
    }

    public List<Figura> listarFigurasRepetidas() {
        return album.listarFigurasRepetidas();
    }
}