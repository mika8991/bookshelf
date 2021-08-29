import {Author} from "./author.model";

export class Book {
    id?: string;
    isbn?: string;
    date?: string;
    title?: string;
    authors?: Author[];
}
