package org.everit.wolverine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String url = "https://gateway.marvel.com:443/v1/public/characters?name=wolverine&apikey=3330bc13f67c141b74dc36f454556461";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final OkHttpClient client = new OkHttpClient();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listComics = findViewById(R.id.activity_main_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listComics.setAdapter(adapter);

        adapter.add("blah 1");
        adapter.add("blah 2");
        adapter.add("blah 3");
        adapter.add("blah 4");
        adapter.add("blah 5");

        Button buttonSearch = findViewById(R.id.activity_main_button);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textHeroName = findViewById(R.id.activity_main_text);
                String heroName = textHeroName.getText().toString();
                Toast.makeText(MainActivity.this, heroName, Toast.LENGTH_SHORT).show();
                Request request = new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String body = response.body() != null ? response.body().string() : "";
                        Toast.makeText(MainActivity.this, body, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
