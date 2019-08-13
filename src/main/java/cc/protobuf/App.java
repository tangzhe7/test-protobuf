package cc.protobuf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cc.protobuf.model.Person;
import cc.protobuf.model.Person.AddressBook;

public class App
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Person person = Person.newBuilder().setEmial("test").build();
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		AddressBook addressBook = AddressBook.newBuilder().addAllPeople(persons).build();
		System.out.println(addressBook.getPeople(0).getEmial());
		writeToFile(addressBook);
		AddressBook addressBook2 = readFromFile();
		System.out.println(addressBook2.getPeople(0).getEmial());
	}

	static void writeToFile(AddressBook addressBook) throws FileNotFoundException, IOException
	{
		OutputStream out = new FileOutputStream("/Users/caicai/addressBook");
		addressBook.writeTo(out);
		out.close();
	}

	static AddressBook readFromFile() throws IOException
	{
		InputStream out = new FileInputStream("/Users/caicai/addressBook");
		AddressBook book = AddressBook.parseFrom(out);
		out.close();
		return book;
	}
}
