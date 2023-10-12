package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class Controlador {
    @PostMapping("/register_books")
    public Books register_books(@RequestBody Books books) throws SQLException, ClassNotFoundException {

        String code = books.getCode();
        String name = books.getName();
        String editorial = books.getEditorial();
        String code_author = books.getCode_author();
        String reference = books.getReference();

        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                editorial == null || editorial.equals("") || editorial.length() < 0 || code_author == null || code_author.equals("") ||
                code_author.length() < 0 || reference == null || reference.equals("") || reference.length() < 0) {

            return new Books(null, null, null, null, null);
        } else {
            BD bd = new BD();
            String worked_editorial = bd.Select_editorial(editorial);
            String nit = bd.Select_nit(code_author);

            if (worked_editorial.equals("") || nit.equals("")) {
                return new Books(null, null, Errors.error_editorial, Errors.error_nit, null);
            } else {
                books = bd.Register(code, name, editorial, code_author, reference);
            }
        }
        return books;
    }

    @PostMapping("/edit_book")
    public Books edit_book(@RequestBody Books books) throws SQLException, ClassNotFoundException {

        String code = books.getCode();
        String name = books.getName();
        String editorial = books.getEditorial();
        String code_author = books.getCode_author();
        String reference = books.getReference();

        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                editorial == null || editorial.equals("") || editorial.length() < 0 || code_author == null || code_author.equals("") ||
                code_author.length() < 0 || reference == null || reference.equals("") || reference.length() < 0) {

            return new Books(null, null, null, null, null);
        } else {
            BD bd = new BD();
            String worked_editorial = bd.Select_editorial(editorial);
            String nit = bd.Select_nit(code_author);

            if (worked_editorial.equals("") || nit.equals("")) {
                return new Books(null, null, Errors.error_editorial, Errors.error_nit, null);
            } else {
                books = bd.Edit(code, name, editorial, code_author, reference);
            }
        }
        return books;
    }

    @GetMapping("/search")
    public List<Books> search() throws SQLException, ClassNotFoundException {
        BD bd = new BD();
        List<Books> list = bd.Search_all();

        return list;
    }

    @GetMapping("/search_book/{code}")
    public Books search_user(@PathVariable String code) throws SQLException, ClassNotFoundException {

        Books books;

        if (code == null || code.equals("") || code.length() < 0) {

            return new Books(Errors.error_search, null, null, null, null);

        } else {
            books = BD.Select_book(code);
        }
        return books;
    }

    @DeleteMapping("/delete_book")
    public Books delete_user(@RequestBody Books books) throws SQLException, ClassNotFoundException {
        String code = books.getCode();
        if (books.getCode() == null || books.getCode().equals("") || books.getCode().length() < 0) {
            return new Books(null, null, null, null, null);
        } else {

            int f = BD.Delete(code);
            if (f == 0) {
                return new Books(Errors.error_delete, null, null, null, null);
            } else {

            }
        }

        return books;
    }

    @DeleteMapping("/delete_books_all")
    public String delete_books_all() throws SQLException, ClassNotFoundException {

        BD.Delete_all();

        String message = "Se han eliminado todos los registros";

        return message;
    }
}



