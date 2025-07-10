package com.livros.livros.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivros(

		  @JsonAlias("id")
		    int id,

		    @JsonAlias("title")
		    String titulo,

		    @JsonAlias("authors")
		    List<DadosAutor> autores,

		    @JsonAlias("subjects")
		    List<String> assuntos,

		    @JsonAlias("bookshelves")
		    List<String> categorias,

		    @JsonAlias("languages")
		    List<String> idiomas,

		    @JsonAlias("formats")
		    Map<String, String> formatos,

		    @JsonAlias("download_count")
		    int totalDownloads
		) {

}
