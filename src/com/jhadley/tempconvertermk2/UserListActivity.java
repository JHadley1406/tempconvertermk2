package com.jhadley.tempconvertermk2;

/*---------------------------------
 * 	Author: Josiah Hadley
 * 	Class: CS211D
 * 	Assignment: HW 4
 * 	File: UserListActivity.java
 * 	Purpose: The class to display the list of users
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserListActivity extends Activity
{

	EditText userText;
	Button quitNameList;
	
	Intent users;
	
	String userString;
	
	ArrayList<String> userList;
	
	@Override
	protected void onCreate(Bundle b)
	{
		super.onCreate(b);
		setContentView(R.layout.user_list_layout);
		
		userText = (EditText) this.findViewById(R.id.userText);
		quitNameList = (Button) this.findViewById(R.id.quitNameList);
		
		userText.setEnabled(false);
		
		
		userList = new ArrayList<String>();
		userString = "";
		
		users = getIntent();
		userList = users.getStringArrayListExtra("UserList");
		
		this.stringBuilder();
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		
		userText.setText(userString);
		
		quitNameList.setOnClickListener(new OnClickListener()
								{

									@Override
									public void onClick(View v)
									{
										finish();
										
									}
									
								});
	}
	
	private void stringBuilder()
	{
		for(String s : userList)
		{
			userString += "\n"+s;
		}
	}
	

	@Override
	public void finish()
	{
		setResult(RESULT_OK);
		super.finish();
	}
	
}
