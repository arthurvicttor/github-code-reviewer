package com.github.codereviewer.analysis;

import com.github.codereviewer.analysis.detectors.CodeSmellDetector;
import com.github.codereviewer.model.Issue;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

public class CodeAnalyzer {
    private final List<CodeSmellDetector> detectors;

    public CodeAnalyzer(List<CodeSmellDetector> detectors) {
        this.detectors = detectors;
    }

    public List<Issue> analyze(CompilationUnit cu, String fileName) {
        List<Issue> issues = new ArrayList<>();

        for (CodeSmellDetector detector : detectors) {
            issues.addAll(detector.analyze(cu, fileName));
        }

        return issues;
    }
}
