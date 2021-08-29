import {Component, OnInit} from '@angular/core';
import {Book} from "../../model/book.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {BooksService} from "../../services/books.service";
import {AuthorsService} from "../../services/authors.service";
import {Author} from "../../model/author.model";

@Component({
    selector: 'app-books-authors',
    templateUrl: './books-authors.component.html'
})
export class BooksAuthorsComponent implements OnInit {
    title = 'Книжная полка';
    authorTitle = 'Авторы';
    authors: any | Author[] = [];
    bookId?: any;
    book?: Book;


    constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, private bookService: BooksService, private authorsService: AuthorsService) {
    }

    addAuthorForm: FormGroup = this.formBuilder.group({
        fio: ['', Validators.required],
        dateOfBirth: ['', Validators.required],
        country: ['', Validators.required]
    });

    onSubmit() {
        this.bookService.addAuthor(this.bookId, this.addAuthorForm?.value)
            .subscribe(value => {
                this.getBookById();
            });
    }

    onBackClick() {
        this.router.navigate(['']);
    }

    getBookById() {
        this.bookService.getBookById(this.bookId)
            .subscribe(value => {
                this.book = value;
                this.authors = this.book?.authors;
            });
    }

    ngOnInit(): void {
        this.bookId = this.route.snapshot.paramMap.get('id');
        this.getBookById();
    }
}
