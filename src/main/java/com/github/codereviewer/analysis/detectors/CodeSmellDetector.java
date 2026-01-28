package com.github.codereviewer.analysis.detectors;

import com.github.codereviewer.model.Issue;
import com.github.javaparser.ast.CompilationUnit;
import java.util.List;

public interface CodeSmellDetector {
    List<Issue> analyze(CompilationUnit cu, String fileName);
}
