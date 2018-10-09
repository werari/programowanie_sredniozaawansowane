package pl.sda.library.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.model.Borrow;
import pl.sda.library.domain.model.BorrowStatus;
import pl.sda.library.domain.port.BorrowRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BorrowServiceTest {

    private BorrowService borrowService;
    private BooksService booksService;
    private BorrowRepository borrowRepository;

    @Before
    public void init(){
        this.booksService= Mockito.mock(BooksService.class);
        this.borrowRepository= Mockito.mock(BorrowRepository.class);
        this.borrowService = new BorrowService(booksService, borrowRepository);
    }

    @Test
    public void borrowShouldBorrow(){
        //given
        Mockito.when(booksService.findById(Mockito.anyString())).thenReturn(Optional.of(
                Book.builder().title("Dziady III").author("Adam Mickiewicz").year(1952).language("Polish").pages(150).id("70").build()
        ));
        String id = "70";
        String userName = "test-user";
        //when
        Borrow borrow = borrowService.borrow(id, userName);
        //then
        Assert.assertTrue(borrow.getBook() !=null);
        Assert.assertEquals(userName, borrow.getUser());
        Assert.assertEquals(BorrowStatus.BORROWED, borrow.getBorrowStatus());
        Mockito.verify(borrowRepository, Mockito.times(1)).save(Mockito.any());
    }
    @Test
    public void findByUserAndStatusShouldReturnExistingUserAndBorrowedStatus(){
        //given
        String userName= "test-user";
        BorrowStatus borrowStatus= BorrowStatus.BORROWED;
        Mockito.when(borrowRepository.findAll()).thenReturn(
                Arrays.asList(
                        Borrow.builder().user("test-user").borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("1").title("Dzieci z Bulerbyn").build()).build(),
                        Borrow.builder().user("test-user").borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("2").title("Dziady IV").build()).build(),
                        Borrow.builder().user("admin-user").borrowStatus(BorrowStatus.BORROWED)
                                .book(Book.builder().id("3").title("Folwark zwierzecy").build()).build(),
                        Borrow.builder().user("test-user").borrowStatus(BorrowStatus.RETURNED)
                                .book(Book.builder().id("4").title("W pustyni i w puszczy").build()).build()
                )
        );
        //when
        List<Borrow> borrows = borrowService.findByUserAndStatus(userName, borrowStatus);
        //then
        Assert.assertEquals(2, borrows.size());
        borrows.forEach(e-> Assert.assertTrue(userName.equals(e.getUser()) && borrowStatus.equals(e.getBorrowStatus())));
    }

}