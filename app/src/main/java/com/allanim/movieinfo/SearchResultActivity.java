package com.allanim.movieinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.allanim.movieinfo.adapter.SearchAdapter;
import com.allanim.movieinfo.api.OmdbRepository;
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

        // action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        
        Intent intent = getIntent();
        searchResult = (SearchResult) intent.getSerializableExtra("searchResult");

        // List
        listView = findViewById(R.id.search_result);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Search search = (Search) listView.getItemAtPosition(position);

            // get detail then move
            OmdbRepository.getInstance().getMovie(this, search.getImdbID(), result -> {
                Intent goToDetail = new Intent(this, MovieDetailActivity.class);
                goToDetail.putExtra("movieDetail", result);
                startActivity(goToDetail);
            });
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
