package com.livros.livros.service;

public interface IConverteDados {
	<T> T obterDados(String json, Class<T> classe);
}
