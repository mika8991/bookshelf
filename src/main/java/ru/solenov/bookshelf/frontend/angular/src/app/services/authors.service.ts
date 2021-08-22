import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {Author} from "../model/author.model";

const baseUrl = 'http://localhost:8080/authors';

@Injectable({
    providedIn: 'root'
})
export class AuthorsService {

    constructor(private http: HttpClient) {
    }

    getAllAuthors(): Observable<any> {
        return this.http.get(`${baseUrl}`);
    }

    getAuthorById(id: string): Observable<any> {
        return this.http.get(`${baseUrl}/${id}`);
    }

    createAuthor(author: Author): Observable<any> {
        return this.http.post(`${baseUrl}`, author);
    }

    updateAuthor(id: string, author: Author): Observable<any> {
        return this.http.put(`${baseUrl}/${id}`, author);
    }

    deleteAuthor(id: string | undefined): Observable<any> {
        return this.http.delete(`${baseUrl}/${id}`);
    }
}


