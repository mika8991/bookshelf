import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Book} from "../model/book.model";
import {Author} from "../model/author.model";

const baseUrl = 'http://localhost:8080/authors';

@Injectable({
    providedIn: 'root'
})
export class BooksService {

    constructor(private http: HttpClient) {
    }

    getAllBooks(): Observable<any> {
        return this.http.get('http://localhost:8080/books')
    }

    getBooksByAuthorId(id: string | undefined): Observable<any> {
        return this.http.get(`${baseUrl}/${id}/books`);
    }

    addBook(id: string | undefined, book: Book): Observable<any> {
        return this.http.post(`${baseUrl}/${id}/books`, book);
    }

    addAuthor(bookId: any, author: Author): Observable<any> {
        return this.http.post(`http://localhost:8080/books/${bookId}/author`, author)
    }

    updateBook(authorId: string, bookId: string, book: Book) {
        return this.http.put(`${baseUrl}/${authorId}/books/${bookId}`, book)
    }

    deleteBook(authorId: string | undefined, bookId: string | undefined) {
        return this.http.delete(`${baseUrl}/${authorId}/books/${bookId}`);
    }

    getAuthorsByBookId(bookId: string | undefined): Observable<any> {
        return this.http.get(`${baseUrl}/${bookId}`);
    }

    getBookById(bookId: string | undefined): Observable<any> {
        return this.http.get(`http://localhost:8080/book/${bookId}`)
    }
}


