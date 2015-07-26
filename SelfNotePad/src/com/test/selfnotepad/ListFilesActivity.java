package com.test.selfnotepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;





public class ListFilesActivity extends ListActivity {
	
	  
	String[] savedFiles;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.openfile);
       ShowSavedFiles();
       
    }
    
    void ShowSavedFiles(){
        savedFiles = getApplicationContext().fileList();
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,savedFiles);

        ListView listSavedFiles = (ListView)findViewById(android.R.id.list);
		listSavedFiles.setAdapter(adapter);
		listSavedFiles.setOnItemClickListener(listSavedFilesOnItemClickListener);
       }
    
    OnItemClickListener listSavedFilesOnItemClickListener = new OnItemClickListener(){
    	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		    // TODO Auto-generated method stub
		    String clickedFile = (String) parent.getItemAtPosition(position);
		    Toast.makeText(getBaseContext(),clickedFile,Toast.LENGTH_LONG).show();
		    
		    getFileContent(clickedFile);   
    	}
    	
    	public void getFileContent(String file)
    	{
    		//Read file in Internal Storage
    	    FileInputStream fis;
    	    String content = "";
    	    try{
    	     fis = openFileInput(file);
    	     byte[] input = new byte[fis.available()];
    	     while (fis.read(input) != -1){}
    	     content += new String(input);
    	    }
    	    catch (IOException e){
    	     e.printStackTrace(); 
    	    }
    	    // Send file name and content of the file to Text to Speech Converter
    	    //Toast.makeText(getBaseContext(),content,Toast.LENGTH_LONG).show();
    	    
    		Bundle extras = getIntent().getExtras();
			String task = extras.getString("task");
			Toast.makeText(getBaseContext(),"Task is "+task,Toast.LENGTH_LONG).show();
    	   
			Bundle extras2=new Bundle();
			extras2.putString("file_name", file);
			extras2.putString("file_content", content);
			if(task.equals("edit_file"))
			{
    		   
    	    	//Toast.makeText(getBaseContext(),"Inside task "+task,Toast.LENGTH_LONG).show();
    	    	Intent i =new Intent("com.test.selfnotepad.ShowFileActivity");
    	    	extras2.putString("task", "edit_file");
    	    	i.putExtras(extras2);
    	    	startActivity(i);
			}
			else
			if(task.equals("read_file"))
				{
					Intent i =new Intent("com.test.selfnotepad.ShowFileActivity");
	    	    	extras2.putString("task", "read_file");
	    	    	i.putExtras(extras2);
	    	    	startActivity(i);
				}
			else	
			{
	    	    Intent i2 =new Intent("com.test.selfnotepad.Text2SpeechActivity");
	    	    i2.putExtras(extras2);
				startActivity(i2);
			}
    }
  
};
}
