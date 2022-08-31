package com.amazingbooks.bookms.model;

public class BookDetails {

    private long bookId;
    private String isbn;
    private int availableCopies;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
