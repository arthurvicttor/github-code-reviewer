package com.github.codereviewer.analysis.detectors;

import com.github.codereviewer.model.Issue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class LongMethodDetector implements CodeSmellDetector{
    private static final int MAX_LINES = 30;

    @Override
    public List<Issue> analyze(CompilationUnit cu, String fileName) {
        List<Issue> issues = new ArrayList<>();

        cu.findAll(MethodDeclaration.class).forEach(method -> {
            method.getBody().ifPresent(body -> {
                int lines = body.toString().split("\n").length;

                if (lines > MAX_LINES) {
                    issues.add(new Issue(
                            fileName,
                            method.getNameAsString(),
                            "Método muito longo (" + lines + " linhas)"
                    ));
                }
            });
        });

        return issues;
    }
}
