package com.livros.livros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.livros.livros.principal.Principal;
import com.livros.livros.repository.LivroRepository;

@SpringBootApplication
public class LivrosApplication implements CommandLineRunner {
    private final LivroRepository livroRepository;

    @Autowired
    public LivrosApplication(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }
	public static void main(String[] args) {
		SpringApplication.run(LivrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
	    Principal app = new Principal(livroRepository);
        app.exibeMenu();
	}

}
