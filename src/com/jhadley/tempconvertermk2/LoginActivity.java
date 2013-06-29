package com.jhadley.tempconvertermk2;

/*---------------------------------
 * 	Author: Josiah Hadley
 * 	Class: CS211D
 * 	Assignment: HW 4
 * 	File: LoginActivity.java
 * 	Purpose: The login class
 */

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{
	private static final String SET_PASSWORD = "foobar";
	private static final int ATTEMPTS = 5;
	private static final int CONVERT_REQUEST_KEY = 4325;
	private static final int EXIT_REQUEST_KEY = 3245;
	
	private EditText unInput;
	private EditText pwInput;
	
	private Button login;
	private Button quitLogin;
	
	private String userName;
	private String password;
	
	private int loginAttempts;
	
	private Intent converter;
	private Intent userActivity;
	
	private ArrayList<String> userList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		unInput = (EditText) this.findViewById(R.id.unInput);
		pwInput = (EditText) this.findViewById(R.id.pwInput);
		login = (Button) this.findViewById(R.id.login);
		quitLogin = (Button) this.findViewById(R.id.quitLogin);
		
		loginAttempts = 0;
		
		converter = new Intent(getApplicationContext()
				, TempConverterActivity.class);
		userActivity = new Intent(getApplicationContext()
				, UserListActivity.class);
		
		userList = new ArrayList<String>();
	}

	@Override
	public void onStart()
	{	
		super.onStart();
		
		login.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						//capture login and password
						userName = unInput.getText().toString();
						password = pwInput.getText().toString();
						//verify that the password is
						//correct
						loginVerification();
						
					}
					
				});
		
		quitLogin.setOnClickListener(new OnClickListener()
				{

					@Override
					public void onClick(View v)
					{
						userActivity.putStringArrayListExtra("UserList"
														, userList);
						startActivityForResult(userActivity
								, EXIT_REQUEST_KEY);
						
					}
			
				});
	}
	
	@Override
	public void onActivityResult(int requestCode
								, int result
								, Intent returnIntent)
	{
		super.onActivityResult(requestCode, result, returnIntent);
		if(requestCode == CONVERT_REQUEST_KEY)
		{
			unInput.setText("");
			pwInput.setText("");
			
			unInput.requestFocus();
		}
		else if(requestCode == EXIT_REQUEST_KEY)
		{
			finish();
		}
	}
	
	
	private void loginVerification()
	{
		if(password.equals(SET_PASSWORD))
		{
			userList.add(userName);
			startActivityForResult(converter
					, CONVERT_REQUEST_KEY);
		}
		else
		{
			//pop some toast
			Toast.makeText(getApplicationContext()
					, "Password is incorrect"
					, Toast.LENGTH_SHORT).show();
			
			//clear password field
			pwInput.setText("");
			//increment the number of attempts
			loginAttempts++;
			if(loginAttempts >= ATTEMPTS)
			{
				Toast.makeText(getApplicationContext()
						, "Too many login attempts,  closing application"
						, Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}


}
