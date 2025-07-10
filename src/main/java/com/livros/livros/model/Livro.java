package com.livros.livros.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer totalDownloads;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Autor> autores = new ArrayList<>();

    public Livro() {
    }

    public Livro(Long id, String titulo, String idioma, Integer totalDownloads) {
        this.id = id;
        this.titulo = titulo;
        this.idioma = idioma;
        this.totalDownloads = totalDownloads;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getTotalDownloads() {
        return totalDownloads;
    }

    public void setTotalDownloads(Integer totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
    	StringBuilder autoresNomes = new StringBuilder();
        for (Autor autor : autores) {
            autoresNomes.append(autor.getNome()).append(", ");
        }
        if (autoresNomes.length() > 0) {
            autoresNomes.setLength(autoresNomes.length() - 2);
        }

        return "Livro [id=" + id 
                + ", titulo=" + titulo 
                + ", idioma=" + idioma 
                + ", totalDownloads=" + totalDownloads 
                + ", autores=[" + autoresNomes + "]]";
    }
}
