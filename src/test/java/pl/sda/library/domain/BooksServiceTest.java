package pl.sda.library.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.sda.library.domain.exceptions.InvalidPagesValueException;
import pl.sda.library.domain.model.Book;
import pl.sda.library.domain.port.BooksRepository;

import java.util.Arrays;
import java.util.List;


public class BooksServiceTest {

    private BooksRepository booksRepository;
    private BooksService booksService;

    @Before
    public void before() {
        this.booksRepository = Mockito.mock(BooksRepository.class);
        Mockito.when(booksRepository.findAll()).thenReturn(
                Arrays.asList(
                        Book.builder().title("Dziady III").author(" Adam Mickiewicz").year(1952).language("Polish").pages(150).build(),
                        Book.builder().title("Dziady IV").author("Adam Mickiewicz").year(1953).language("Polish").pages(151).build(),
                        Book.builder().title("W pustyni i w puszczy").author("Sienkiewicz").year(1946).language("Polish").pages(130).build()));


        this.booksService = new BooksService(booksRepository);
    }

    @Test
    public void findByTitleShouldReturnEmptyListForNullTitle() {
        //given
        String title = null;
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());

    }

    @Test
    public void findByTitleShouldReturnEmptyListForEmptyTitle() {
        //given
        String title = "";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByTitleShouldReturnItemsForExistingTitle() {
        //given
        String title = "Dziady";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getTitle().contains(title)));
    }

    @Test
    public void findByTitleShouldReturnEmptyListForNonExistingTitle() {
        //given
        String title = "non-existing";
        //when
        List<Book> books = booksService.findByTitle(title);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForNonExistingAuthor() {
        //given
        String author = "non-existing";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForEmptyAuthor() {
        //given
        String author = "";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());

    }

    @Test
    public void findByAuthorShouldReturnItemsForExistingAuthor() {
        //given
        String author = "Adam Mickiewicz";
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().contains(author)));
    }

    @Test
    public void findByAuthorShouldReturnEmptyListForNullAuthor() {
        //given
        String author = null;
        //when
        List<Book> books = booksService.findByAuthor(author);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByAuthorShouldReturnItemsForExistingAuthorLastName() {
        //given
        String authorLastName = "Mickiewicz";
        //when
        List<Book> books = booksService.findByAuthor(authorLastName);
        //then
        Assert.assertEquals(2, books.size());
        books.forEach(book -> Assert.assertTrue(book.getAuthor().contains(authorLastName)));
    }

    @Test
    public void findByDateShouldReturnEmptyListForNonExistingDate() {
        //given
        Integer year = -1;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(0, books.size());
    }


    @Test
    public void findByDateShouldReturnItemsForExistingDate() {
        //given
        Integer year = 1952;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(1, books.size());
        books.forEach(book -> Assert.assertEquals(book.getYear(), (year)));
    }

    @Test
    public void findByDateShouldReturnEmptyListForNullDate() {
        //given
        Integer year = null;
        //when
        List<Book> books = booksService.findByYear(year);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByLanguageShouldReturnEmptyListForNullLanguage() {
        //given
        String language = null;
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(0, books.size());

    }

    @Test
    public void findByLanguageShouldReturnEmptyListForEmptyLanguage() {
        //given
        String language = "";
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByLanguageShouldReturnItemsForExistingLanguage() {
        //given
        String language = "Polish";
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(3, books.size());
        books.forEach(book -> Assert.assertTrue(book.getLanguage().contains(language)));
    }

    @Test
    public void findByLanguageShouldReturnEmptyListForNonExistingLanguage() {
        //given
        String language = "non-existing";
        //when
        List<Book> books = booksService.findByLanguage(language);
        //then
        Assert.assertEquals(0, books.size());
    }

    @Test
    public void findByPagesRangeShouldReturnBooksWhenFromIsEqualsToTo() throws InvalidPagesValueException {
        //given
        Integer to=150;
        Integer from= to;
        //when
        List<Book> books= booksService.findByPagesRange(from, to);
        //then
        Assert.assertEquals(1, books.size());
        Assert.assertEquals(from, books.get(0).getPages());
    }
    @Test
    public void findByPagesRangeShouldReturnBooksFromValidPagesRange() throws InvalidPagesValueException {
        //given
        Integer from= 100;
        Integer to= 200;
        //when
        List<Book> books= booksService.findByPagesRange(from, to);
        //then
        Assert.assertEquals(3,books.size());
        books.forEach(e-> Assert.assertTrue(e.getPages()>= from && e.getPages()<= to));
    }
    @Test (expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenToIsNegative() throws InvalidPagesValueException {
        //given
        Integer from = 100;
        Integer to= -100;
        //when
        List<Book> books = booksService.findByPagesRange(from, to);
    }
    @Test (expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenFromIsNegative() throws InvalidPagesValueException {
        //given
        Integer from = - 100;
        Integer to= 100;
        //when
        List<Book> books = booksService.findByPagesRange(from, to);
    }
    @Test (expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenFromIsBiggerThanTo() throws InvalidPagesValueException {
        //given
        Integer from= 150;
        Integer to= 50;
        //when
        List <Book> books=booksService.findByPagesRange(from, to);
    }
    @Test (expected = InvalidPagesValueException.class)
    public void findByPagesRangeShouldThrowExceptionWhenBothFromAndToAreNegative() throws InvalidPagesValueException {
        //given
        Integer from= -150;
        Integer to= -50;
        //when
        List <Book> books=booksService.findByPagesRange(from, to);
    }
}
