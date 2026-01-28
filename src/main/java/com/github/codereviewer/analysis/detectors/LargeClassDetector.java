package com.github.codereviewer.analysis.detectors;

import com.github.codereviewer.model.Issue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.ArrayList;
import java.util.List;

public class LargeClassDetector implements CodeSmellDetector {

    private static final int MAX_METHODS = 10;

    @Override
    public List<Issue> analyze(CompilationUnit cu, String fileName) {
        List<Issue> issues = new ArrayList<>();

        // Percorre todas as classes do arquivo
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(clazz -> {

            // Conta apenas métodos (ignora construtores, atributos etc.)
            long methodCount = clazz.getMethods().size();

            if (methodCount > MAX_METHODS) {
                issues.add(new Issue(
                        fileName,
                        clazz.getNameAsString(),
                        "Classe muito grande (" + methodCount + " métodos)"
                ));
            }
        });

        return issues;
    }
}
