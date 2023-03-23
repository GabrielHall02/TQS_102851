package tqs.java.book;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tqs.book.Book;
import tqs.book.Library;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType(".*")
    public Date dateFormat(String entry) {
        // Date can be of the form yyyy or yyyy MMMMM dd
        // Example of yyyy: 2019
        // Example of yyyy MMMMM dd: 2019 January 01
        if (entry.length() == 4) {
            return Date.from(LocalDate.of(Integer.parseInt(entry), 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        return Date.from(LocalDate.parse(entry, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    @Given("a book with the title {string}, written by {string}, published in {dateFormat}")
    public void addNewBook(final String title, final String author, final Date published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @Given("another book with the title {string}, written by {string}, published in {dateFormat}")
    public void another_book_with_the_title_written_by_published_in_august(String title, String author, Date published) {
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {dateFormat} and {dateFormat}")
    public void setSearchParameters(final Date from,  final Date to) {
        result = library.findBooks(from, to);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertEquals(result.size(), booksFound);
        //assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(final int position, final String title) {
        assertEquals(result.get(position - 1).getTitle(), title);
        //assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }
}
