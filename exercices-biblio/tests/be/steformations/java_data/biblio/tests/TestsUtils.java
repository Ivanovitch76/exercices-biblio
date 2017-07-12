package be.steformations.java_data.biblio.tests;

public class TestsUtils {
	
	static class TestBook {
		public final String code;
		public final String title;
		public final int collection;
		public final Integer[] authors;
		public final short edition;
		public final short pages;
		public final java.util.Date date;
		
		public TestBook(String code, String title, int collection, Integer[] authors, short edition, short pages, String sdate) {
			super();
			this.code = code;
			this.title = title;
			this.collection = collection;
			this.authors = authors;
			this.edition = edition;
			this.pages = pages;
			this.date = string2Date(sdate);
		}
	}
	
	static class SearchCriteria {
		public final Integer author;
		public final Integer editor;
		public final String word;
		
		public SearchCriteria(Integer author, Integer editor, String word) {
			super();
			this.author = author;
			this.editor = editor;
			this.word = word;
		}
	}
	
	static class TestBorrower {
		public final String firstname;
		public final String lastname;
		public final String email;
		public final String phone;
		public final java.util.Date ddn;
		
		public TestBorrower(String firstname, String lastname, String email, String phone, String sdate) {
			super();
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.phone = phone;
			this.ddn = string2Date(sdate);
		}
	}
	
	public static java.util.List<TestBook> getAllBooks() {
		java.util.List<TestBook> books
			= new java.util.ArrayList<TestBook>();
		books.add(new TestBook("A108", "Python précis et concis", 2, new Integer[] {1}, (short) 2, (short) 80, "2000-01-01"));
		books.add(new TestBook("A123", "Programmation Python", 2, new Integer[] {1}, (short) 2, (short) 536, "2006-01-01"));
		books.add(new TestBook("A124", "Introduction à Python", 2, new Integer[] {1, 4}, (short) 4, (short) 1216, "2009-09-01"));
		books.add(new TestBook("A256", "Python en concentré", 2, new Integer[] {3}, (short) 3, (short) 654, "2003-01-01"));
		books.add(new TestBook("B150", "Python: Le livre de Recettes", 2, new Integer[] {3, 4}, (short) 3,(short)  554, "2006-07-01"));
		books.add(new TestBook("B155", "Plongez au coeur de Python", 1, new Integer[] {2}, (short) 1, (short) 413, "2004-07-19"));
		
		return books;
	}
	
	static java.util.Date string2Date(String sdate) {
		String[] dateParts = sdate.split("[-/]");
		java.util.Calendar calendar = java.util.GregorianCalendar.getInstance();
		calendar.set(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]) - 1, Integer.parseInt(dateParts[2]), 0, 0, 0);
		calendar.set(java.util.Calendar.MILLISECOND, 0);
		return new java.util.Date(calendar.getTimeInMillis());
	}
}
