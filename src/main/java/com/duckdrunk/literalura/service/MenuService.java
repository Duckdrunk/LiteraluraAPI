package com.duckdrunk.literalura.service;

import com.duckdrunk.literalura.dto.AuthorWithBooksDTO;
import com.duckdrunk.literalura.dto.BookDTO;
import com.duckdrunk.literalura.dto.PersonDTO;
import com.duckdrunk.literalura.model.Language;
import com.duckdrunk.literalura.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService {
    private final BookService bookService;
    private final PersonService personService;

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int option = -1;

        while (option != 0) {
            System.out.println("""
                    
                    📚 LITERALURA
                    -----------------------
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Importar libros de Gutendex
                    0 - Salir
                    -----------------------
                    Elija una opción:
                    """);

            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1 -> buscarPorTitulo();
                    case 2 -> listarLibros();
                    case 3 -> listarAutores();
                    case 4 -> autoresVivosPorAnio();
                    case 5 -> buscarPorIdioma();
                    case 6 -> importarLibros();
                    case 0 -> System.out.println("👋 Hasta luego");
                    default -> System.out.println("❌ Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido");
            }
        }
    }

    private void buscarPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        try {
            String title = scanner.nextLine();
            bookService.findByTitle(title)
                    .ifPresentOrElse(
                            this::formatearLibro,
                            () -> System.out.println("📕 Libro no encontrado")
                    );

        } catch (Exception e) {
            System.out.println("❌ Error al buscar libro");
        }
    }

    private void importarLibros() {
        System.out.print("Ingrese el número de página de Gutendex: ");
        try {
            int page = Integer.parseInt(scanner.nextLine());
            bookService.importBooks(page);
            System.out.println("✅ Libros importados correctamente");
        } catch (Exception e) {
            System.out.println("❌ Error al importar libros");
        }
    }

    private void listarLibros() {
        bookService.findAll().forEach(this::formatearLibro);
    }

    private void buscarPorIdioma() {
        System.out.print("Ingrese código de idioma (ej: en, es): ");
        String code = scanner.nextLine();

        var libros = bookService.findByLanguage(code);

        if (libros.isEmpty()) {
            System.out.println("❌ No se encontraron libros");
        } else {
            libros.forEach(b -> System.out.println("📖 " + b.title()));
        }
    }

    private void autoresVivosPorAnio() {
        System.out.print("Ingrese el año: ");
        try {
            int year = Integer.parseInt(scanner.nextLine());
            var autores = personService.findAuthorsAliveInYear(year);

            if (autores.isEmpty()) {
                System.out.println("❌ No se encontraron autores");
            } else {
                autores.forEach(this::formatearAutor);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Año inválido");
        }
    }

    private void listarAutores() {
        var autores = personService.findAllAuthors();
        if (autores.isEmpty()) {
            System.out.println("❌ No se encontraron autores");
        } else {
            autores.forEach(this::formatearAutor);
        }
    }

    private void formatearLibro(BookDTO book){
        System.out.printf(
                """
                        --------------------
                        Título: %s
                        Autores: %s
                        Idioma: %s
                        Descargas: %d
                        %n""", book.title(),
                book.authors()
                        .stream().map(PersonDTO::name)
                        .collect(Collectors.joining(", ")),
                book.languages(),
                book.download_count()
        );
    }

    private void formatearAutor(AuthorWithBooksDTO dto){
        String libros = dto.books()
                .stream()
                .map(b -> " - " + b)
                .collect(Collectors.joining("\n"));

        System.out.printf(
                """
                --------------------
                Autor: %s
                Fecha de Nacimiento: %s
                Fecha de Fallecimiento: %s
                Libros:
                %s
                %n""",
                dto.author(),
                dto.birthYear(),
                dto.deathYear(),
                libros
        );

    }
}
