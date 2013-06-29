package com.jhadley.tempconvertermk2;

/*---------------------------------
 * 	Author: Josiah Hadley
 * 	Class: CS211D
 * 	Assignment: HW 4
 * 	File: TempConverterActivity.java
 * 	Purpose: The temperature conversion class
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class TempConverterActivity extends Activity 
{
	//define the GUI elements
	//Radio group and radio buttons
	RadioGroup converterGroup;
	RadioButton cToF;
	RadioButton fToC;
	
	//Text input and output
	EditText tempInput;
	EditText tempOutput;
	
	//Labels for the input and output
	TextView inputLabel;
	TextView outputLabel;
	
	//calculate button
	Button calcButton;
	Button quitConverterButton;
	
	//switch to differentiate between conversion direction
	Boolean convertSwitch;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_converter_layout);
        
        //instantiate gui elements
        converterGroup = (RadioGroup) this.findViewById(R.id.converterGroup);
        cToF = (RadioButton) this.findViewById(R.id.ctof);
        fToC = (RadioButton) this.findViewById(R.id.ftoc);
        
        tempInput = (EditText) this.findViewById(R.id.tempInput);
        tempOutput = (EditText) this.findViewById(R.id.tempOutput);
        tempOutput.setEnabled(false);
        
        inputLabel = (TextView) this.findViewById(R.id.inputLabel);
        outputLabel = (TextView) this.findViewById(R.id.outputLabel);
        
        calcButton = (Button) this.findViewById(R.id.calcButton);
        quitConverterButton = (Button) this.findViewById(R.id.quitConverterButton);

    }
    
    @Override
    public void onStart()
    {
    	super.onStart();
    	
        //call setConverter to ensure the conversion flag
        //and labels are set properly
        setConverter();
        
        //what to do when the user selects a different radio button
        converterGroup.setOnCheckedChangeListener(new OnCheckedChangeListener()
        						{
									@Override
									public void onCheckedChanged(
											RadioGroup group,
											int checkedId)
									{
										//call this method to see which 
										//button is selected
										setConverter();
										
									}
     	
								});
        
        //What do do when the user clicks the Calculate! button
        calcButton.setOnClickListener(new OnClickListener()
        	{
        		@Override
				public void onClick(View v)
				{
					//ensure that the textview isn't empty
					if(tempInput.getText().toString().trim().length() > 0)
					{
						//get the value of the input EditText
						Double tempIn = Double.parseDouble(tempInput
																.getText()
																.toString());
						//check the switch value to see
						//what kind of conversion we're doing
						if(convertSwitch)
						{			
							tempOutput.setText(String.format("%.2f", getF(tempIn)));
						}
						else if (!convertSwitch)
						{
							tempOutput.setText(String.format("%.2f",  getC(tempIn)));
						}
					}
				}
        	
       		});
        
        quitConverterButton.setOnClickListener(new OnClickListener()
        	{
        		@Override
				public void onClick(View v)
				{
        			finish();
				}
        	});
    }
    
    //Checks to see which radio button is selected
    //and adjusts the switch and labels accordingly
    public void setConverter()
    {
    	if(cToF.isChecked())
    	{	
    		convertSwitch = true;
    		
    		inputLabel.setText("C");
    		outputLabel.setText("F");
    	}
    	if(fToC.isChecked())
    	{
    		convertSwitch = false;
    		
    		inputLabel.setText("F");
    		outputLabel.setText("C");	
    	}
    	
    	tempInput.setText("");
    	tempOutput.setText("");
    		
    	
    }
    
    private Double getC(Double tempIn)
    {
    	return (5.0/9.0)*(tempIn-32);
    }
    
    private Double getF(Double tempIn)
    {
    	return (9.0/5.0)*tempIn+32;
    }
    
    @Override
    public void finish()
    {
    	setResult(RESULT_OK);
    	super.finish();
    }
    
}