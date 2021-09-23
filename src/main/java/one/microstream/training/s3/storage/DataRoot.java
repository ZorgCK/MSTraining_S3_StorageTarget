package one.microstream.training.s3.storage;

import java.util.ArrayList;
import java.util.List;

import one.microstream.training.s3.domain.Book;


public class DataRoot
{
	private List<Book> books = new ArrayList<Book>();
	
	public List<Book> getBooks()
	{
		return books;
	}

	public void setBookList(List<Book> books)
	{
		this.books = books;
	}
}
