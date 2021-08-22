import {Book} from "./book.model";

export class Author {
    id?: string;
    fio?: string;
    dateOfBirth?: string;
    country?: string;
    books?: Book[];
}