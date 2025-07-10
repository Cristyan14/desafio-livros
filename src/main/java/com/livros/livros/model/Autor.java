package com.livros.livros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String nome;
	    private Integer anoNascimento;
	    private Integer anoFalecimento;

	    @ManyToOne
	    @JoinColumn(name = "livro_id")
	    private Livro livro;

	    public Autor() {
	    }

	    public Autor(Long id, String nome, Integer anoNascimento, Integer anoFalecimento, Livro livro) {
	        this.id = id;
	        this.nome = nome;
	        this.anoNascimento = anoNascimento;
	        this.anoFalecimento = anoFalecimento;
	        this.livro = livro;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public Integer getAnoNascimento() {
	        return anoNascimento;
	    }

	    public void setAnoNascimento(Integer anoNascimento) {
	        this.anoNascimento = anoNascimento;
	    }

	    public Integer getAnoFalecimento() {
	        return anoFalecimento;
	    }

	    public void setAnoFalecimento(Integer anoFalecimento) {
	        this.anoFalecimento = anoFalecimento;
	    }

	    public Livro getLivro() {
	        return livro;
	    }

	    public void setLivro(Livro livro) {
	        this.livro = livro;
	    }

	    @Override
	    public String toString() {
	        String anoNascStr = (anoNascimento != null) ? anoNascimento.toString() : "desconhecido";
	        String anoFalecStr = (anoFalecimento != null) ? anoFalecimento.toString() : "vivo";

	        String livroTitulo = (livro != null) ? livro.getTitulo() : "Sem livro";

	        return "Autor [id=" + id + ", nome=" + nome + ", anoNascimento=" + anoNascStr 
	            + ", anoFalecimento=" + anoFalecStr + ", livro=" + livroTitulo + "]";
	    }
	    
}
