package resources;

public enum  LibrarySource {
	
	addBook("Library/Addbook.php"),
	getBook("/Library/GetBook.php"),
	deleteBook("/Library/DeleteBook.php");
	
	private String resource;
	
	LibrarySource(String resource)
	{
		this.resource=resource;
	}
	
	public String resourceReturn()
	{
		return resource;
	}


}
