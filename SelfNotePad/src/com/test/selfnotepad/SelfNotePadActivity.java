package com.test.selfnotepad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelfNotePadActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn_new=(Button) findViewById(R.id.btn_new);
        Button btn_open=(Button) findViewById(R.id.btn_open);
        Button btn_edit=(Button) findViewById(R.id.btn_edit);
        Button btn_read=(Button) findViewById(R.id.btn_read);
       
        btn_new.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =new Intent("com.test.selfnotepad.ShowFileActivity");
			
				Bundle extras =new Bundle();
				extras.putString("task", "create_new_file");
				i.putExtras(extras);
				startActivityForResult(i,1);
				//Toast.makeText(getBaseContext(),"New Clicked",Toast.LENGTH_LONG).show();
			}
		});
        
        //Add Lister to open button
        
        btn_open.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(),"Open Clicked",Toast.LENGTH_LONG).show();
			
				Intent i =new Intent("com.test.selfnotepad.ListFilesActivity");
				Bundle extras =new Bundle();
				extras.putString("task", "open_file");
				i.putExtras(extras);
				startActivity(i);
			}
		});
        
        
        btn_read.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(),"Read Clicked",Toast.LENGTH_LONG).show();
			
				Intent i =new Intent("com.test.selfnotepad.ListFilesActivity");
				Bundle extras =new Bundle();
				extras.putString("task","read_file");
				i.putExtras(extras);
				startActivity(i);
			}
		});
        
        
        btn_edit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(),"Edit  Clicked",Toast.LENGTH_LONG).show();
				Intent i =new Intent("com.test.selfnotepad.ListFilesActivity");
				Bundle extras =new Bundle();
				extras.putString("task", "edit_file");
				i.putExtras(extras);
				startActivity(i);
			}
		});
        
        
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == 1) 
		{
			if (resultCode == RESULT_OK) 
			{
				Toast.makeText(this,data.getData().toString(),
						Toast.LENGTH_SHORT).show();
			}
		}
	}
}