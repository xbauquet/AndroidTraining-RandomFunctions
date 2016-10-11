package com.ramdomfunctions;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class MyService extends IntentService {
    // extends IntentService rather than Service because dataBases work are needed so we cannot
    // work into the main thread, IntentService starts automatically a worker thread
    public MyService() {
        super("MyService");
    }

    // 10.0.0.2 = from USB
    private static final String HOST_URL = "http://10.0.3.2:8080";
    private static final String TAG = MyService.class.getSimpleName();

    public interface Users {
        @GET("/zoo/rest/user/list")
        Call<List<ZooUserModel>> users();
    }

    public class UserFromWebService{
        String email;
        String firstName;
        String lastName;
        String pass;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

        String authority;
    }

    @Override
    protected void onHandleIntent(Intent intent){

        // call the web service for user data
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Users users = retrofit.create(Users.class);


        Call<List<ZooUserModel>> call = users.users();

        // Fetch a list of the users.
        try{
            List<ZooUserModel> zooUsers = call.execute().body();

        }catch (IOException e){
            Log.w(TAG, "Error when calling call.execute().body() in MyService.java, network call failure", e);
            e.printStackTrace();
        }

    }
}
