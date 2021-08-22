import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BooksComponent} from "./components/books/books.component";
import {AuthorsComponent} from "./components/authors/authors.component";

const routes: Routes = [
    {path: '', redirectTo: 'authors', pathMatch: 'full'},
    {path: 'authors', component: AuthorsComponent},
    {path: 'books/:id', component: BooksComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes),
        FormsModule,
        ReactiveFormsModule],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
