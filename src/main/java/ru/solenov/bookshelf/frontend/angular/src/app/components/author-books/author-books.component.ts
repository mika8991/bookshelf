import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthorsService} from "../../services/authors.service";
import {Book} from "../../model/book.model";
import {BooksService} from "../../services/books.service";

@Component({
    selector: 'app-author-books',
    templateUrl: './author-books.component.html',
})
export class AuthorsBooksComponent implements OnInit {
    title = 'Книжная полка';
    booksTitle = 'Книги';
    books?: Book[];

    constructor(private formBuilder: FormBuilder, private router: Router, private authorsService: AuthorsService, private booksService: BooksService) {
    }

    getAllBooks() {
        this.booksService.getAllBooks().subscribe(data => {
            this.books = data;
        });
    }

    onBackClick() {
        this.router.navigate(['']);
    }

    onTransitionAuthors(book: Book) {
        this.router.navigate(['book-authors', book.id]);
    }

    ngOnInit(): void {
        this.getAllBooks();
    }
}
