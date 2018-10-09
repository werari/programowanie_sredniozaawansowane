package pl.sda.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
@Data
@Builder
@AllArgsConstructor
public class Borrow {
    private Book book;
    private String title;
    private String user;
    private Instant borrowDate;
    private Instant returnDate;
    private BorrowStatus borrowStatus;

    public Borrow(Book book, String user) {
        this.book = book;
        this.user = user;
        this.borrowDate=Instant.now();
        this.returnDate= null;
        this.borrowStatus= BorrowStatus.BORROWED;
    }
}
