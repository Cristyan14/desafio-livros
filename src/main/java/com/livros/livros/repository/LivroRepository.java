package com.livros.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.livros.livros.model.Autor;
import com.livros.livros.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    List<Livro> findByTituloContainingIgnoreCase(String nomeLivro);
    
    @EntityGraph(attributePaths = "autores")
    List<Livro> findByIdiomaIgnoreCase(String idioma);

    @EntityGraph(attributePaths = "autores")
    List<Livro> findAll();  // <-- ISSO AQUI resolve o erro do toString()

    @Query("SELECT a FROM Livro l JOIN l.autores a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :nomeAutor, '%'))")
    List<Autor> findAutoresByNomeContainingIgnoreCase(@Param("nomeAutor") String nomeAutor);

    @Query("SELECT a FROM Livro l JOIN l.autores a WHERE a.anoFalecimento IS NULL OR a.anoFalecimento > :ano")
    List<Autor> findAutoresVivosEmAno(@Param("ano") int ano);
}
