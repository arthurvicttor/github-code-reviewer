package com.github.codereviewer.analysis.detectors;

import com.github.codereviewer.model.Issue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.WhileStmt;
import java.util.ArrayList;
import java.util.List;

public class CyclomaticComplexityDetector implements CodeSmellDetector{
    private static final int MAX_COMPLEXITY = 10;

    @Override
    public List<Issue> analyze(CompilationUnit cu, String fileName) {
        List<Issue> issues = new ArrayList<>();

        cu.findAll(MethodDeclaration.class).forEach(method -> {
            int complexity = 1;

            complexity += method.findAll(IfStmt.class).size();
            complexity += method.findAll(ForStmt.class).size();
            complexity += method.findAll(WhileStmt.class).size();
            complexity += method.findAll(SwitchEntry.class).size();

            if (complexity > MAX_COMPLEXITY) {
                issues.add(new Issue(
                        fileName,
                        method.getNameAsString(),
                        "Alta complexidade ciclomática: " + complexity
                ));
            }
        });

        return issues;
    }
}
