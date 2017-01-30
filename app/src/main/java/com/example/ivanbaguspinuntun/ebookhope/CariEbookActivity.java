package com.example.ivanbaguspinuntun.ebookhope;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CariEbookActivity extends AppCompatActivity {

    private static final String TAG = "cariebook";
    private RecyclerView list_ebook;
    private ProgressBar progressBar;
    private Button btn_refresh;
    private LinearLayoutManager layoutManager;

    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> array_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_ebook);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_ebook = (RecyclerView) findViewById(R.id.list_ebook);
        layoutManager = new GridLayoutManager(CariEbookActivity.this, 2);
        list_ebook.setHasFixedSize(true);
        list_ebook.setLayoutManager(layoutManager);
        list_ebook.setItemAnimator(new DefaultItemAnimator());

        array_list = new ArrayList<>();

        progressBar = (ProgressBar) findViewById(R.id.ebook_progress);
        btn_refresh = (Button) findViewById(R.id.btn_refresh);

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEbook();
            }

        });

        getEbook();
    }

    private void getEbook(){
        list_ebook.setVisibility(View.INVISIBLE);
        btn_refresh.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        String url = "http://cloudofoasis.com/api/Ivan/getEbook.php";
        Log.i(TAG,url);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("DEBUG_", "On Response getEbook");
                        try {
                            int banyakEbook = response.length();
                            JSONObject jsonObject;
                            Log.d("DEBUG_", "Parse JSON");
                            for (int i = 0; i < banyakEbook; i++) {
                                jsonObject = response.getJSONObject(i);
                                map = new HashMap<>();
                                map.put("id_ebook", jsonObject.getString("id_ebook"));
                                map.put("gambar", jsonObject.getString("gambar"));
                                map.put("judul", jsonObject.getString("judul"));
                                map.put("isi", jsonObject.getString("isi"));
                                map.put("kelas", jsonObject.getString("kelas"));
                                map.put("penerbit", jsonObject.getString("penerbit"));
                                array_list.add(map);
                            }
                            Log.d("DEBUG_", "Set data to Adapter");

                            //Pasang Data
                            progressBar.setVisibility(View.INVISIBLE);
                            list_ebook.setVisibility(View.VISIBLE);
                            ListCariEbookAdapter adapter = new ListCariEbookAdapter(CariEbookActivity.this, array_list);
                            list_ebook.setAdapter(adapter);
                        }
                        catch (JSONException je) {
                            Toast.makeText(CariEbookActivity.this, "JSON Error", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            btn_refresh.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.INVISIBLE);
                        btn_refresh.setVisibility(View.VISIBLE);
                        Toast.makeText(CariEbookActivity.this, "ERROR : " + error.toString(), Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "Error : " + error.toString());
                    }
                });
        request.setRetryPolicy(new DefaultRetryPolicy(100000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
