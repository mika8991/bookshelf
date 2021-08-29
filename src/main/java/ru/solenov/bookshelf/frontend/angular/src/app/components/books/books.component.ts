import {Component, OnInit} from '@angular/core';
import {Book} from "../../model/book.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {BooksService} from "../../services/books.service";
import {AuthorsService} from "../../services/authors.service";
import {Author} from "../../model/author.model";

@Component({
    selector: 'app-books',
    templateUrl: './books.component.html'
})
export class BooksComponent implements OnInit {
    title = 'Книжная полка';
    bookTitle = 'Книги';
    books?: Book[];
    authorId?: any;
    author?: Author;

    constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, private bookService: BooksService, private authorsService: AuthorsService) {
    }

    addBookForm: FormGroup = this.formBuilder.group({
        isbn: ['', Validators.required],
        date: ['', Validators.required],
        title: ['', Validators.required]
    });

    onSubmitBook() {
        this.bookService.addBook(this.authorId, this.addBookForm?.value)
            .subscribe(value => this.getBooksByAuthorId());
    }

    onBackClick() {
        this.router.navigate(['']);
    }

    getBooksByAuthorId() {
        this.bookService.getBooksByAuthorId(this.authorId)
            .subscribe(value => {
                this.books = value
            });
    }

    getAuthorById(id: any) {
        this.authorsService.getAuthorById(id)
            .subscribe(value => this.author = value);
    }

    ngOnInit(): void {
        this.authorId = this.route.snapshot.paramMap.get('id');
        this.getBooksByAuthorId();
        this.getAuthorById(this.authorId);
    }
}
