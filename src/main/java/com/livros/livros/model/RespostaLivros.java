package com.livros.livros.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaLivros(
    List<DadosLivros> results
) {}