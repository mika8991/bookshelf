import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {BooksComponent} from './components/books/books.component';
import {AuthorsComponent} from './components/authors/authors.component';
import {BooksAuthorsComponent} from "./components/books-authors/books-authors.component";
import {AuthorsBooksComponent} from "./components/author-books/author-books.component";

@NgModule({
    declarations: [
        AppComponent,
        AuthorsComponent,
        BooksComponent,
        BooksAuthorsComponent,
        AuthorsBooksComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
