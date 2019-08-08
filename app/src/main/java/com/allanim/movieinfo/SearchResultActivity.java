package com.allanim.movieinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.allanim.movieinfo.adapter.SearchAdapter;
import com.allanim.movieinfo.models.Search;
import com.allanim.movieinfo.models.SearchResult;

import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private ListView listView;
    private SearchResult searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        searchResult = (SearchResult) intent.getSerializableExtra("searchResult");

        // List
        listView = findViewById(R.id.search_result);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Search search = (Search) listView.getItemAtPosition(position);
            Intent goToDetail = new Intent(this, SearchResultActivity.class);
            goToDetail.putExtra("id", search.getImdbID());
            startActivity(goToDetail);
        });
    }

    private void loadData() {
        List<Search> searchList = searchResult.getSearch();
        SearchAdapter adapter = new SearchAdapter(this, searchList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
