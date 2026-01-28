package com.github.codereviewer.report;

import com.github.codereviewer.model.Issue;

import java.util.List;

public class TerminalReportPrinter {
    public void print(List<Issue> issues) {
        System.out.println("==== RELATÓRIO DE ANÁLISE ====");

        if (issues.isEmpty()) {
            System.out.println("Nenhum problema encontrado 🎉");
            return;
        }

        issues.forEach(issue -> {
            System.out.println("- Arquivo: " + issue.getFile());
            System.out.println("  Elemento: " + issue.getElement());
            System.out.println("  Problema: " + issue.getDescription());
            System.out.println();
        });

        System.out.println("Total de problemas: " + issues.size());
    }
}
