package com.example.contactlistactivity1;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.Data;
import java.util.*; 

public class ContactListActivity1 extends ListActivity {
	static ArrayList<String> ids = new ArrayList<String>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContactList1 contactList=this.getContacts();
        ArrayAdapter<Contact1> adapter=new ContactAdapter1(this,contactList.getContacts());
        setListAdapter(adapter);
        
        
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	super.onListItemClick(l, v, position, id);
    	Object o=this.getListAdapter().getItem(position);
    	Contact1 c=(Contact1)o;
    	Toast.makeText(this, c.getDisplayName(), Toast.LENGTH_SHORT).show();
    	Toast.makeText(this, c.getId(), Toast.LENGTH_SHORT).show();
    	ids.add(c.getId());
    	System.out.println("Testing id"+ids);
    }
    
   
    
   /* public void just_print()
    {
    	 
    	//System.out.println("Am I getting the signal");
    	for(int i=0; i<ids.size(); i++){
          //  System.out.println(ids.get(i));
        }
         

    }
    */
    
    private ContactList1 getContacts()
    {
    	ContactList1 contactList=new ContactList1();
    	Uri uri=ContactsContract.Contacts.CONTENT_URI;
    	ContentResolver cr=getContentResolver();
    	String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
    	Cursor cur=cr.query(uri, null, null, null, sortOrder);
    	
    	if(cur.getCount() >0)
    	{
    		String id;
    		String img;
    		String name;
    		while(cur.moveToNext())
    		{
    			Contact1 c =new Contact1();
    			id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
    		    img= cur.getString(cur.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
    			name=cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
    			
    			final Bitmap photo;
    			if(img != null) {
    				photo = queryContactBitmap(img);
    				
    				
    			} else {
    				photo = null;
    			}
    			
    			
    			    			
    			c.setId(id);
    			//c.setId(img);
    		
    			c.setImage(photo);
    			c.setDisplayName(name);
    			contactList.addContact(c);
    			
    			
    		}
    	}
    //	cur.close();
    	return contactList;
    }
    private Bitmap queryContactBitmap(String photoId) {
		final Cursor photo = managedQuery(
				Data.CONTENT_URI,
				new String[] {Photo.PHOTO},		// column where the blob is stored
				Data._ID + "=?",				// select row by id
				new String[]{photoId},			// filter by the given photoId
				null);
		
		
		final Bitmap photoBitmap;
		if(photo.moveToFirst()) {
			byte[] photoBlob = photo.getBlob(
					photo.getColumnIndex(Photo.PHOTO));
			photoBitmap = BitmapFactory.decodeByteArray(
					photoBlob, 0, photoBlob.length);
		} else {
			photoBitmap = null;
		}
		photo.close();
		
		return photoBitmap;
		
		
	}
}
