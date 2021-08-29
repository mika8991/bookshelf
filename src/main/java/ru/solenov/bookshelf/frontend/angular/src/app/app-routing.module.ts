import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BooksComponent} from "./components/books/books.component";
import {AuthorsComponent} from "./components/authors/authors.component";
import {BooksAuthorsComponent} from "./components/books-authors/books-authors.component";
import {AuthorsBooksComponent} from "./components/author-books/author-books.component";

const routes: Routes = [
    {path: '', redirectTo: 'authors', pathMatch: 'full'},
    {path: 'authors', component: AuthorsComponent},
    {path: 'books/:id', component: BooksComponent},
    {path: 'book-authors/:id', component: BooksAuthorsComponent},
    {path: 'books', component: AuthorsBooksComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes),
        FormsModule,
        ReactiveFormsModule],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
