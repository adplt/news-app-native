package com.example.newsapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.List;
import adapter.SourceAdapter;
import apis.SourceListApi;
import model.SourceModel;
import utils.Storage;
import utils.Transformer;

public class SearchSourceList extends AppCompatActivity {

    private ListView listView;
    private Storage storage;
    EditText searchKeyword;
    SourceAdapter sourceAdapter;
    Transformer transformer = new Transformer();
    private HashMap<String,String> source;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_source_list);
        listView = findViewById(R.id.source_list);

        TextView tv = findViewById(R.id.back_button);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        searchKeyword = findViewById(R.id.search_input);
        storage = new Storage(SearchSourceList.this);
        source = storage.getSource();
        getView();
    }

    public void getView() {
        SourceListApi sourceApi = new SourceListApi();
        List<SourceModel> sourceList = sourceApi.getSourceList("/sources", "");

        sourceList = transformer.filterByCategory(sourceList,source.get(Storage.SOURCE__CATEGORY));

        if(sourceList != null){
            sourceAdapter = new SourceAdapter(SearchSourceList.this, R.layout.interface_source_list, sourceList);
            listView.setAdapter(sourceAdapter);

            List<SourceModel> finalSourceList = sourceList;
            searchKeyword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().length() == 0 || s.toString().length() == 3) {
                        List<SourceModel> newSource = transformer.filterByKeyword(finalSourceList, s.toString());
                        sourceAdapter.filterList(newSource);
                    }
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SourceModel source = (SourceModel) listView.getAdapter().getItem(position);
                    storage.setSource(source);
                    Intent intent = new Intent(SearchSourceList.this, NewsList.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return true;
                }
            });
        }
    }
}
