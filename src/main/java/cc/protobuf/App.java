package cc.protobuf;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import cc.protobuf.model.AddressBookProtos;
import cc.protobuf.model.AddressBookProtos.Person;
import cc.protobuf.model.AddressBookProtos.Person.AddressBook;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.HtmlFormat;
import com.googlecode.protobuf.format.JsonFormat;

public class App
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
//		Person.PhoneNumber.Builder phone = Person.PhoneNumber.newBuilder();
//		phone.setRtpeValue(0);
//		phone.setNumber("13391961678");
//
//		Person person = Person.newBuilder()
//				.setId(123456789)
//				.setEmial("hi@lvhejin.cn")
//				.addPhone(0, Person.PhoneNumber.newBuilder().setRtpeValue(1).setNumber("13391961678").build())
//				.build();
//		Person person1 = Person.newBuilder()
//				.setId(8888)
//				.setEmial("abc.io")
//				.addPhone(phone)
//				.build();
//		List<Person> persons = new ArrayList<>();
//		persons.add(person);
//		persons.add(person1);
//		AddressBook addressBook = AddressBook.newBuilder().addAllPeople(persons).build();
//		System.out.println(addressBook);
//		writeToFile(addressBook);
//		AddressBook addressBook2 = readFromFile();
//		System.out.println(addressBook2);

//		protobuf对象转换成json：
		Person person = Person.newBuilder()
				.setId(123456789)
				.setEmial("hi@lvhejin.cn")
				.addPhone(0,Person.PhoneNumber.newBuilder().setRtpeValue(1).setNumber("13391961678").build())
				.build();
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		AddressBook addressBook = AddressBook.newBuilder().addAllPeople(persons).build();
//		String jsonFormat = new JsonFormat().printToString(addressBook);
//		System.out.println(jsonFormat);

//		json转成protobuf对象：
//		Message.Builder builder = AddressBook.newBuilder();
//		JsonFormat.merge(jsonFormat.getBytes(StandardCharsets.UTF_8), builder);
//------------------------------
		JsonFormat jsonFormat = new JsonFormat();
		String asJson = jsonFormat.printToString(addressBook);
		System.out.println(asJson);

		Message.Builder builder = AddressBook.newBuilder();
		ByteArrayInputStream stream= new ByteArrayInputStream(asJson.getBytes());
		jsonFormat.merge(stream, builder);
		System.out.println(builder);

		HtmlFormat htmlFormat = new HtmlFormat();
		String asHtml = htmlFormat.printToString(addressBook);
		System.out.println(asHtml);

	}

	static void writeToFile(AddressBook addressBook) throws FileNotFoundException, IOException
	{
		OutputStream out = new FileOutputStream("./addressBook");
		addressBook.writeTo(out);
		out.close();
	}

	static AddressBook readFromFile() throws IOException
	{
		InputStream out = new FileInputStream("./addressBook");
		AddressBook book = AddressBook.parseFrom(out);
		out.close();
		return book;
	}
}
