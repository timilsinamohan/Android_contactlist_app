package com.example.contactlistactivity1;

import java.util.ArrayList;

import android.R.string;
import android.graphics.Bitmap;

public class Contact1 {
	private String _id;
	private String _displayName;
	private Bitmap _img;
	
	public String getId(){return _id;}
	public String getDisplayName(){return _displayName;}
	public Bitmap getImage(){return _img;}
	public void setId(String id){_id=id;}
	public void setDisplayName(String displayName){_displayName=displayName;}
	public void setImage(Bitmap img){_img=img;}
}

