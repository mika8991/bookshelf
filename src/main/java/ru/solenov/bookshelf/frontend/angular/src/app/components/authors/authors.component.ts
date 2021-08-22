import {Component, OnInit} from '@angular/core';
import {Author} from "../../model/author.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthorsService} from "../../services/authors.service";

@Component({
    selector: 'app-authors',
    templateUrl: './authors.component.html',
})
export class AuthorsComponent implements OnInit {
    title = 'Книжная полка';
    authorTitle = 'Авторы';
    authors?: Author[];

    constructor(private formBuilder: FormBuilder, private router: Router, private authorsService: AuthorsService) {
    }

    addAuthorForm: FormGroup = this.formBuilder.group({
        fio: ['', Validators.required],
        dateOfBirth: ['', Validators.required],
        country: ['', Validators.required]
    });

    getAllAuthors() {
        this.authorsService.getAllAuthors().subscribe(data => {
            this.authors = data;
        })
    }

    onSubmit() {
        this.authorsService.createAuthor(this.addAuthorForm?.value)
            .subscribe(data => {
                this.getAllAuthors();
            })
    }

    onAuthorDelete(author: Author) {
        this.authorsService.deleteAuthor(author.id)
            .subscribe(data =>
                this.getAllAuthors());
    }

    onTransitionBooks(author: Author) {
        this.router.navigate(['books', author.id]);
    }

    ngOnInit(): void {
        this.getAllAuthors();
    }
}
