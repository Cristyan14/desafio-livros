package com.livros.livros.principal;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.livros.livros.model.Autor;
import com.livros.livros.model.DadosAutor;
import com.livros.livros.model.DadosLivros;
import com.livros.livros.model.Livro;
import com.livros.livros.model.RespostaLivros;
import com.livros.livros.repository.LivroRepository;
import com.livros.livros.service.ConsumoApi;
import com.livros.livros.service.ConverteDados;





public class Principal {
	private ConsumoApi consumo = new ConsumoApi();
	private Scanner leitura = new Scanner(System.in);
	private ConverteDados conversor = new ConverteDados();
	private  final String ENDERECO = "https://gutendex.com/books/?search=";
	private LivroRepository repositorio;
	private Optional<Livro> livroBusca;

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }
		public void exibeMenu() {
			int opcao = -1;

	        while (opcao != 6) {
	            System.out.println("\nMenu:");
	            System.out.println("1 - Buscar livro pelo título");
	            System.out.println("2 - Listar livros registrados");
	            System.out.println("3 - Listar autores registrados");
	            System.out.println("4 - Listar autores vivos em um determinado ano");
	            System.out.println("5 - Listar livros em um determinado idioma");
	            System.out.println("6 - Sair");
	            System.out.print("Escolha uma opção: ");
	            
	            try {
	                opcao = Integer.parseInt(leitura.nextLine());
	            } catch (NumberFormatException e) {
	                System.out.println("Opção inválida.");
	                continue;
	            }

	            switch (opcao) {
	                case 1:
	                    buscarLivroPeloTitulo();
	                    break;
	                case 2:
	                    listarLivrosRegistrados();
	                    break;
	                case 3:
	                    listarAutoresRegistrados();
	                    break;
	                case 4:
	                    listarAutoresVivosEmAno();
	                    break;
	                case 5:
	                    listarLivrosPorIdioma();
	                    break;
	                case 6:
	                    System.out.println("Saindo...");
	                    break;
	                default:
	                    System.out.println("Opção inválida.");
	            }
		       
			 	}
		    }


		private void listarLivrosPorIdioma() {
			// TODO Auto-generated method stub
			   System.out.print("Digite o idioma para busca: ");
			    String idioma = leitura.nextLine();

			    List<Livro> livros = repositorio.findByIdiomaIgnoreCase(idioma);

			    if (livros.isEmpty()) {
			        System.out.println("Nenhum livro encontrado para o idioma informado.");
			    } else {
			        System.out.println("Livros encontrados:");
			        livros.forEach(System.out::println);
			    }
		}


		private void listarAutoresVivosEmAno() {
			// TODO Auto-generated method stub
			 System.out.print("Digite o ano para busca: ");
			    int ano;
			    try {
			        ano = Integer.parseInt(leitura.nextLine());
			    } catch (NumberFormatException e) {
			        System.out.println("Ano inválido.");
			        return;
			    }

			    List<Autor> autores = repositorio.findAutoresVivosEmAno(ano);

			    if (autores.isEmpty()) {
			        System.out.println("Nenhum autor vivo encontrado para o ano informado.");
			    } else {
			        System.out.println("Autores vivos no ano " + ano + ":");
			        autores.forEach(System.out::println);
			    }
			
		}


		private void listarAutoresRegistrados() {
			// TODO Auto-generated method stub
		    
		    List<Autor> autoresBuscados = repositorio.findAutoresByNomeContainingIgnoreCase("");

		    if (!autoresBuscados.isEmpty()) {
		        autoresBuscados.forEach(System.out::println);
		    } else {
		        System.out.println("Livro não encontrado!");
		    }
			
		}


		private void listarLivrosRegistrados() {
			// TODO Auto-generated method stub
		    List<Livro> livros = repositorio.findAll();

		    if (!livros.isEmpty()) {
		        livros.forEach(System.out::println);
		    } else {
		        System.out.println("Nenhum livro encontrado!");
		    }
		}


		private void buscarLivroPeloTitulo() {
			// TODO Auto-generated method stub
			 System.out.print("Digite o título para busca: ");
		        String titulo = leitura.nextLine();

		        List<Livro> livrosNoBanco = repositorio.findByTituloContainingIgnoreCase(titulo);
		        if (!livrosNoBanco.isEmpty()) {
		            System.out.println("Livro(s) já cadastrado(s) no banco:");
		            livrosNoBanco.forEach(System.out::println);
		            return;
		        }
		        String json = consumo.obterDados(ENDERECO + titulo.replace(" ", "+"));
		        RespostaLivros resposta = conversor.obterDados(json, RespostaLivros.class);
		        List<DadosLivros> livrosApi = resposta.results();

		        if (livrosApi.isEmpty()) {
		            System.out.println("Nenhum livro encontrado na API.");
		            return;
		        }
		        DadosLivros dados = livrosApi.get(0);
		        Livro livro = new Livro();
		        livro.setTitulo(dados.titulo());
		        livro.setIdioma(dados.idiomas().isEmpty() ? "desconhecido" : dados.idiomas().get(0));
		        livro.setTotalDownloads(dados.totalDownloads());

	
		        for (DadosAutor autorApi : dados.autores()) {
		            Autor autor = new Autor();
		            autor.setNome(autorApi.name());
		            autor.setLivro(livro);
		            livro.getAutores().add(autor);
		        }

		 
		        repositorio.save(livro);

		 
		        System.out.println(livro);
		}
}
