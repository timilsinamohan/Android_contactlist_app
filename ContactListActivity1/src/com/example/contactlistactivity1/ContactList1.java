package com.example.contactlistactivity1;

import java.util.ArrayList;
import java.util.List;

public class ContactList1 {
	private List<Contact1> _contacts=new ArrayList<Contact1>();
	public List<Contact1> getContacts(){return _contacts;}
	
	public void addContact(Contact1 contact){ this._contacts.add(contact);}
	
}
