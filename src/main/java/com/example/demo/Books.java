package com.example.demo;

public class Books {

    public String code;
    public String name;
    public String editorial;
    public String code_author;
    public String reference;

    public Books(String code, String name, String editorial, String code_author, String reference) {
        this.code = code;
        this.name = name;
        this.editorial = editorial;
        this.code_author = code_author;
        this.reference = reference;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCode_author() {
        return code_author;
    }

    public void setCode_author(String code_author) {
        this.code_author = code_author;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
