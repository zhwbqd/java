package com.roywmiller.contacts.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import com.roywmiller.contacts.model.ContactsUser;

public class UserDatabase {

	protected static UserDatabase singleton;
	protected File usersFile;
	protected Map users = new HashMap();
	protected EncoderDecoder encoderDecoder = new EncoderDecoder();
	protected String databaseFilePathname;
	
	public static UserDatabase getSingleton() {
		if (singleton == null)
			singleton = new UserDatabase();
		return singleton;
	}
	
	public void setDatabaseFilePathname(String pathRoot) {
		databaseFilePathname = pathRoot + File.separator + "userDatabase.txt";
	}

	public ContactsUser get(String name, String password) {
		String userKey = name + "!" + password;
		ContactsUser user = (ContactsUser) users.get(userKey);
		return user;
	}
	
	public void put(ContactsUser user) {
		String userKey = user.getUsername() + "!" + user.getPassword();
		users.put(userKey, user);
	}

	public void initialize() {
		usersFile = new File(databaseFilePathname);
		
		String allUsers = retrieveText();
		StringTokenizer tokenizer = new StringTokenizer(allUsers, "\n");
		while (tokenizer.hasMoreTokens()) {
			String userEntry = tokenizer.nextToken();
			UserRecord record = new UserRecord(userEntry);
			put(new ContactsUser(record.getName(), record.getPassword(), record.getContactList()));
		}
	}
	
	public void shutDown() {
		writeUsers();
	}
	
	protected void writeUsers() {
		StringBuffer buffer = new StringBuffer();
		Collection allUsers = users.values();
		Iterator iterator = allUsers.iterator();
		while (iterator.hasNext()) {
			ContactsUser each = (ContactsUser) iterator.next();
			UserRecord record = new UserRecord(each);
			buffer.append(record.getFullRecord());
		}
		writeText(buffer.toString());
	}
	
	protected synchronized String retrieveText() {
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(usersFile.getAbsolutePath()));
			char charBuff[] = new char[(int) new File(usersFile.getAbsolutePath()).length()];

			bufferedReader.read(charBuff);
			return new String(charBuff);
		} catch (Exception e) {
			throw new RuntimeException("Unable to read in the file.", e);
		} finally {
			closeReader(bufferedReader);
		}
	}

	protected void closeReader(BufferedReader bufferedReader) {
		try {
			if (bufferedReader != null)
				bufferedReader.close();
		} catch (Exception ex) {}
	}
	
	protected synchronized void writeText(String text) {
		Writer writer = null;
		
		try {
			writer = new FileWriter(usersFile.getAbsolutePath());
			writer.write(text);
		} catch (Exception e) {
			throw new RuntimeException("Unable to append to file.", e);
		} finally {
			closeWriter(writer);
		}
	}

	protected void closeWriter(Writer writer) {
		if (writer != null) {
			try {
				writer.flush();
				writer.close();
			} catch (Exception ex) {}
		}
	}

}