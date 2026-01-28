package com.github.codereviewer.parser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class JavaFileParser {

    private final JavaParser javaParser;

    public JavaFileParser() {
        this.javaParser = new JavaParser();
    }

    /**
     * Converte um arquivo .java em uma CompilationUnit (AST)
     */
    public Optional<CompilationUnit> parse(Path filePath) {
        try {
            ParseResult<CompilationUnit> result = javaParser.parse(filePath);

            // Se o JavaParser conseguiu montar a AST
            if (result.isSuccessful() && result.getResult().isPresent()) {
                return result.getResult();
            }

            // Se houve erros de parsing (sintaxe inválida, etc)
            System.err.println("Erro ao parsear: " + filePath);
            result.getProblems().forEach(problem ->
                    System.err.println(" - " + problem.getMessage())
            );

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + filePath);
        }

        // Em qualquer falha, retorna vazio
        return Optional.empty();
    }
}
