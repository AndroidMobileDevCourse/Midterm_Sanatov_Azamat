package com.ozaki.asanatov_backendless;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LogoutActivity extends AppCompatActivity {
    private TextView congratzTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        congratzTextView = findViewById(R.id.congratz_TextView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = findViewById(R.id.header);
        switch (id) {
            case R.id.user_settings:
                BackendlessUser user = Backendless.UserService.CurrentUser();
                headerView.setText("Your email: "+user.getEmail()+"\nYour Name: "+user.getProperty("name")+"\nAccount created:" + user.getProperty("created"));
                return true;
            case R.id.signout_settings:
                Logout();
                SignedOutIntent();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void SignedOutIntent(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void Logout() {
        final AsyncCallback<Void> logoutResponder = new AsyncCallback<Void>()
        {
            @Override
            public void handleResponse( Void aVoid )
            {
                Toast.makeText(getContext(), "Logout proceeded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void handleFault( BackendlessFault backendlessFault )
            {
                System.out.println( "Server reported an error " + backendlessFault.getMessage() );
            }
        };
        Backendless.UserService.logout( logoutResponder );
    }
    public Context getContext() {
        return this;
    }
}
