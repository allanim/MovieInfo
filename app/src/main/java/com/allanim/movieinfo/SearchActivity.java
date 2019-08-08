package com.allanim.movieinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.allanim.movieinfo.api.OmdbRepository;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText mSearch = findViewById(R.id.search_name);
        Button btnSearch = findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(v -> {
            String keyword = mSearch.getText().toString();

            // check valid
            if (keyword.isEmpty()) {
                mSearch.setError(getResources().getString(R.string.error_field_required));
                mSearch.requestFocus();
                return;
            }

            // search
            OmdbRepository.getInstance().searchMovie(this, keyword, result -> {
                if (result.getTotalResults() > 0) {
                    Intent intent = new Intent(this, SearchResultActivity.class);
                    intent.putExtra("searchResult", result);
                    startActivity(intent);
                } else {
                    // no data
                    mSearch.setError(getResources().getString(R.string.no_result));
                    mSearch.requestFocus();
                }
            });

        });


    }

}
