package com.github.codereviewer;

import com.github.codereviewer.analysis.CodeAnalyzer;
import com.github.codereviewer.analysis.detectors.CodeSmellDetector;
import com.github.codereviewer.analysis.detectors.CyclomaticComplexityDetector;
import com.github.codereviewer.analysis.detectors.LargeClassDetector;
import com.github.codereviewer.analysis.detectors.LongMethodDetector;
import com.github.codereviewer.model.Issue;
import com.github.codereviewer.parser.JavaFileParser;
import com.github.codereviewer.report.TerminalReportPrinter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java -jar github-code-reviewer.jar <caminho-do-projeto>");
            return;
        }

        Path projectPath = Paths.get(args[0]);

        if (!Files.exists(projectPath)) {
            System.out.println("Caminho não encontrado: " + projectPath);
            return;
        }

        JavaFileParser parser = new JavaFileParser();

        List<CodeSmellDetector> detectors = new ArrayList<>();

        detectors.add(new LongMethodDetector());
        detectors.add(new CyclomaticComplexityDetector());
        detectors.add(new LargeClassDetector());

        CodeAnalyzer analyzer = new CodeAnalyzer(detectors);
        List<Issue> allIssues = new ArrayList<>();

        try {
            Files.walk(projectPath)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(path ->
                            parser.parse(path).ifPresent(cu -> {
                                List<Issue> issues = analyzer.analyze(
                                        cu, path.getFileName().toString()
                                );
                                allIssues.addAll(issues);
                            })
                    );
        } catch (Exception e) {
            System.err.println("Erro ao percorrer arquivos do projeto");
            e.printStackTrace();
        }

        new TerminalReportPrinter().print(allIssues);
    }
}
