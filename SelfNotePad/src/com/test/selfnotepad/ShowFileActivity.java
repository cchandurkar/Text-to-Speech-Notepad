package com.test.selfnotepad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowFileActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        Bundle extras2 = getIntent().getExtras();
        if(extras2!=null)
        {
        	String file_name = extras2.getString("file_name");
    		String file_content = extras2.getString("file_content");
	    	String task = extras2.getString("task");
	        if(task.equals("edit_file"))
	        {	
	        	setContentView(R.layout.newfile);
	        	final EditText file_name_editor = (EditText)findViewById(R.id.file_name);
	        	final EditText file_content_editor = (EditText)findViewById(R.id.file_text);
	            Button btn_save=(Button) findViewById(R.id.btn_save);
	            Button btn_cancel=(Button) findViewById(R.id.btn_cancel);
	    		file_name_editor.setText(file_name);
	    		file_content_editor.setText(file_content);
	    		
	    		
	    		 btn_save.setOnClickListener(new View.OnClickListener() {
	    				public void onClick(View v) {
	    					// TODO Auto-generated method stub
	    					//Toast.makeText(getBaseContext(),file_name_editor.getText().toString(),Toast.LENGTH_LONG).show();
	    					String save_file_name=file_name_editor.getText().toString();
							String save_file_text=file_content_editor.getText().toString();
	    					
	    					try
	    					{
	    								FileOutputStream fOut =
	    						        openFileOutput(save_file_name+".txt", MODE_WORLD_READABLE);
	    						        OutputStreamWriter osw = new OutputStreamWriter(fOut);
	    					        
	    						        //---write the string to the file---
	    						        osw.write(save_file_text);
	    						        osw.flush();
	    						        osw.close();
	    					        
	    						        //---display file saved message---
	    						        Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
	    					        
	    						        //---clears the EditText---
	    						        file_name_editor.setText("");
	    						        file_content_editor.setText("");
	    						        
	    						        Intent data = new Intent();
	    								//	---get the EditText view---
	    						        data.setData(Uri.parse("Done"));
	    								setResult(RESULT_OK, data);
	    								//	---closes the activity---
	    								finish();	        
	    					}
	    					catch (IOException ioe) 
	    			        {
	    						Toast.makeText(getBaseContext(),"Some Error Occured",Toast.LENGTH_LONG).show();
	    			        	ioe.printStackTrace();
	    			        }
	    					
	    				}
	    			});
	    	        
	    	        //Add Lister to open button
	    	        
	    	        btn_cancel.setOnClickListener(new View.OnClickListener() {
	    				public void onClick(View v) {
	    					// TODO Auto-generated method stub
	    					Toast.makeText(getBaseContext(),"Cancel Clicked",Toast.LENGTH_LONG).show();
	    				}
	    			});
	        }
	        else
        	if(task.equals("create_new_file"))
        	{
        		setContentView(R.layout.newfile);
        		final EditText file_name_editor = (EditText)findViewById(R.id.file_name);
	        	final EditText file_content_editor = (EditText)findViewById(R.id.file_text);
        		Button btn_save=(Button) findViewById(R.id.btn_save);
	            Button btn_cancel=(Button) findViewById(R.id.btn_cancel);
        		btn_save.setOnClickListener(new View.OnClickListener() {
	    				public void onClick(View v) {
	    					// TODO Auto-generated method stub
	    					//Toast.makeText(getBaseContext(),file_name_editor.getText().toString(),Toast.LENGTH_LONG).show();
	    					String save_file_name=file_name_editor.getText().toString();
							String save_file_text=file_content_editor.getText().toString();
	    					
							if(save_file_name.equals("") || save_file_name.equals(" "))
							{
								Toast.makeText(getBaseContext(),"Please Enter a file name",Toast.LENGTH_LONG).show();
							}
							else
							{
			    					try
			    					{
			    								FileOutputStream fOut =
			    						        openFileOutput(save_file_name+".txt", MODE_WORLD_READABLE);
			    						        OutputStreamWriter osw = new OutputStreamWriter(fOut);
			    					        
			    						        //---write the string to the file---
			    						        osw.write(save_file_text);
			    						        osw.flush();
			    						        osw.close();
			    					        
			    						        //---display file saved message---
			    						        Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
			    					        
			    						        //---clears the EditText---
			    						        file_name_editor.setText("");
			    						        file_content_editor.setText("");
			    						        
			    						        Intent data = new Intent();
			    								//	---get the EditText view---
			    						        data.setData(Uri.parse("Done"));
			    								setResult(RESULT_OK, data);
			    								//	---closes the activity---
			    								finish();	        
			    					}
			    					catch (IOException ioe) 
			    			        {
			    						Toast.makeText(getBaseContext(),"Some Error Occured",Toast.LENGTH_LONG).show();
			    			        	ioe.printStackTrace();
			    			        }	
							}
	    					
	    				}
	    			});
	    	        
	    	        //Add Lister to open button
	    	        
	    	        btn_cancel.setOnClickListener(new View.OnClickListener() {
	    				public void onClick(View v) {
	    					// TODO Auto-generated method stub
	    					finish();
	    				}
	    			});
        		
        		
        	}
	        else
	        	if(task.equals("read_file"))
	        	{
	        		setContentView(R.layout.readfile);
		        	TextView file_name_txt = (TextView)findViewById(R.id.file_name);
		        	TextView file_content_txt = (TextView)findViewById(R.id.file_content);
		            
		        	file_name_txt.setText(file_name);
		        	file_content_txt.setText(file_content);
		        	
		        	 Button back=(Button) findViewById(R.id.back);
		        	 back.setOnClickListener(new View.OnClickListener() {
		    				public void onClick(View v) {
		    					// TODO Auto-generated method stub
		    					finish();
		    				}
		    			});
	        	}
        }
   
    }
}