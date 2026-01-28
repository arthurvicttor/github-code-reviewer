package com.github.codereviewer.model;

public class Issue {
    private final String file;
    private final String element;
    private final String description;

    public Issue(String file, String element, String description) {
        this.file = file;
        this.element = element;
        this.description = description;
    }

    public String getFile() {
        return file;
    }

    public String getElement() {
        return element;
    }

    public String getDescription() {
        return description;
    }
}
