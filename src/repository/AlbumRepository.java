package repository;

import model.*;

import java.io.*;
import java.util.List;

public class AlbumRepository {

    private static final String CAMINHO_CATALOGO = "../data/CatalogoFigurinhas.txt";
    private static final String CAMINHO_COLECAO = "../data/ColecaoUsuario.txt"; 

    public Album carregarAlbum(Proprietario proprietario) {
        Album album = new Album("Álbum Copa do Mundo 2026", proprietario);

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_CATALOGO))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(";");

                int numero = Integer.parseInt(partes[0]);
                String nomeSelecao = partes[1];
                TipoFigura tipo = TipoFigura.valueOf(partes[2]);
                String nomeFigura = partes[3];
                String descricao = partes[4];

                Selecao selecao = buscarOuCriarSelecao(album, nomeSelecao);

                Figura figura;

                if (tipo == TipoFigura.JOGADOR) {
                    figura = new FiguraJogador(numero, nomeFigura, descricao);
                } else {
                    figura = new FiguraEspecial(numero, nomeFigura, tipo, descricao);
                }

                selecao.adicionarFigura(figura);
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar catálogo: " + e.getMessage());
        }

        carregarColecaoUsuario(album);

        return album;
    }

    public void salvarColecaoUsuario(Album album) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_COLECAO))) {

            for (Selecao selecao : album.getSelecoes()) {
                for (Figura figura : selecao.getFiguras()) {
                    if (figura.isColada() || figura.getQuantidadeRepetida() > 0) {
                        bw.write(figura.getNumero() + ";" + figura.isColada() + ";" + figura.getQuantidadeRepetida());
                        bw.newLine();
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar coleção: " + e.getMessage());
        }
    }

    private void carregarColecaoUsuario(Album album) {
        File arquivo = new File(CAMINHO_COLECAO);

        if (!arquivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_COLECAO))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(";");

                int numero = Integer.parseInt(partes[0]);
                boolean colada = Boolean.parseBoolean(partes[1]);
                int repetidas = Integer.parseInt(partes[2]);

                Figura figura = buscarFiguraPorNumero(album, numero);

                if (figura != null) {
                    if (colada) {
                        figura.colar();
                    }

                    for (int i = 0; i < repetidas; i++) {
                        figura.adicionarRepetida();
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar coleção do usuário: " + e.getMessage());
        }
    }

    private Selecao buscarOuCriarSelecao(Album album, String nomeSelecao) {
        for (Selecao selecao : album.getSelecoes()) {
            if (selecao.getNome().equalsIgnoreCase(nomeSelecao)) {
                return selecao;
            }
        }

        Selecao novaSelecao = new Selecao(nomeSelecao);
        album.adicionarSelecao(novaSelecao);

        return novaSelecao;
    }

    private Figura buscarFiguraPorNumero(Album album, int numero) {
        for (Selecao selecao : album.getSelecoes()) {
            for (Figura figura : selecao.getFiguras()) {
                if (figura.getNumero() == numero) {
                    return figura;
                }
            }
        }

        return null;
    }
}