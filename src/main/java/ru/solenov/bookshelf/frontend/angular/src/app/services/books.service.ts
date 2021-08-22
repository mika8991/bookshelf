import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Book} from "../model/book.model";

const baseUrl = 'http://localhost:8080/authors';

@Injectable({
    providedIn: 'root'
})
export class BooksService {

    constructor(private http: HttpClient) {
    }

    getBooksByAuthorId(id: string | undefined): Observable<any> {
        return this.http.get(`${baseUrl}/${id}/books`);
    }

    addBook(id: string | undefined, book: Book): Observable<any> {
        return this.http.post(`${baseUrl}/${id}/books`, book);
    }

    updateBook(authorId: string, bookId: string, book: Book) {
        return this.http.put(`${baseUrl}/${authorId}/books/${bookId}`, book)
    }

    deleteBook(authorId: string | undefined, bookId: string | undefined) {
        return this.http.delete(`${baseUrl}/${authorId}/books/${bookId}`);
    }
}


