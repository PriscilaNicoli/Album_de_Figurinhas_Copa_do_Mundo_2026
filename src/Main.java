import exception.FiguraNaoEncontradaException;
import model.*;
import service.AlbumService;
import repository.AlbumRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Proprietario proprietario = new Proprietario("Priscila", "email@email.com");

        AlbumRepository repository = new AlbumRepository();
        Album album = repository.carregarAlbum(proprietario);

        AlbumService service = new AlbumService(album);

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE ALBUM DE FIGURINHAS =====");
            System.out.println("1 - Registrar figura colada");
            System.out.println("2 - Registrar figura repetida");
            System.out.println("3 - Ver total de figuras coladas");
            System.out.println("4 - Ver total de figuras repetidas");
            System.out.println("5 - Listar figuras faltantes");
            System.out.println("6 - Listar faltantes por selecao");
            System.out.println("7 - Listar figuras por tipo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Numero da figura colada: ");
                        int numeroColada = scanner.nextInt();
                        scanner.nextLine();

                        Figura figuraColada = service.registrarFiguraColada(numeroColada);
                        System.out.println("Figura colada com sucesso: " + figuraColada.getDescricao());
                        repository.salvarColecaoUsuario(album);
                        break;

                    case 2:
                        System.out.print("Numero da figura repetida: ");
                        int numeroRepetida = scanner.nextInt();
                        scanner.nextLine();

                        service.registrarFiguraRepetida(numeroRepetida);
                        System.out.println("Figura repetida registrada com sucesso!");
                        repository.salvarColecaoUsuario(album);
                        break;

                    case 3:
                        List<Figura> coladas = service.listarFigurasColadas();

                        System.out.println("\n===== FIGURAS COLADAS =====");

                        if (coladas.isEmpty()) {
                            System.out.println("Nenhuma figurinha foi colada.");
                        } else {
                            for (Figura figura : coladas) {
                                System.out.println(figura.getDescricao());
                            }
                        }

                        System.out.println("\nTotal de figuras coladas: " + coladas.size());
                        break;

                    case 4:
                        List<Figura> repetidas = service.listarFigurasRepetidas();

                        System.out.println("\n===== FIGURAS REPETIDAS =====");

                        if (repetidas.isEmpty()) {
                            System.out.println("Nenhuma figurinha repetida.");
                        } else {

                            for (Figura figura : repetidas) {

                                System.out.println(
                                    figura.getDescricao()
                                    + " | Quantidade: "
                                    + figura.getQuantidadeRepetida()
                                );

                            }

                        }
                        System.out.println("\nTotal de figuras diferentes repetidas: " + repetidas.size());
                        System.out.println("Total de unidades repetidas: " + service.contarFigurasRepetidas()); 
                        break;

                    case 5:
                        List<Figura> faltantes = service.listarFigurasFaltantes();

                        System.out.println("\nFiguras faltantes:");
                        for (Figura figura : faltantes) {
                            System.out.println(figura.getDescricao());
                        }
                        break;

                    case 6:
                        System.out.print("Digite o nome da selecao: ");
                        String nomeSelecao = scanner.nextLine();

                        List<Figura> faltantesSelecao = service.listarFigurasFaltantesPorSelecao(nomeSelecao);

                        System.out.println("\nFiguras faltantes da selecao " + nomeSelecao + ":");
                        for (Figura figura : faltantesSelecao) {
                            System.out.println(figura.getDescricao());
                        }
                        break;

                    case 7:
                        System.out.println("Tipos disponiveis:");
                        System.out.println("JOGADOR, TECNICO, BRASAO, BANDEIRA, ESTADIO, LENDARIA, ESPECIAL");
                        System.out.print("Digite o tipo: ");
                        String tipoDigitado = scanner.nextLine().toUpperCase();

                        TipoFigura tipo = TipoFigura.valueOf(tipoDigitado);
                        List<Figura> figurasPorTipo = service.listarFigurasPorTipo(tipo);

                        System.out.println("\nFiguras do tipo " + tipo + ":");
                        for (Figura figura : figurasPorTipo) {
                            System.out.println(figura.getDescricao());
                        }
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        repository.salvarColecaoUsuario(album);
                        break;

                    default:
                        System.out.println("Opção invalida.");
                }
            } catch (FiguraNaoEncontradaException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: tipo de figura invalido.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}