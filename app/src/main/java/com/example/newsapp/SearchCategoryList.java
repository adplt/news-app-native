package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import adapter.CategoryAdapter;
import apis.SourceListApi;
import model.SourceModel;
import utils.Storage;
import utils.Transformer;

public class SearchCategoryList extends AppCompatActivity {

    private ListView listView;
    private Storage storage;
    Transformer transformer = new Transformer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category_list);
        listView = findViewById(R.id.category_list);

        storage = new Storage(SearchCategoryList.this);

        TextView tv = findViewById(R.id.back_button);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getView();
    }

    public void getView() {
        SourceListApi sourceApi = new SourceListApi();
        List<SourceModel> sourceList = sourceApi.getSourceList("/sources", "");

        sourceList = transformer.filterCategory(sourceList);

        if(sourceList != null){
            CategoryAdapter categoryAdapter = new CategoryAdapter(SearchCategoryList.this, R.layout.interface_category_list, sourceList);
            listView.setAdapter(categoryAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    SourceModel source = (SourceModel) listView.getAdapter().getItem(position);
                    storage.setSource(source);
                    startActivity(new Intent(SearchCategoryList.this, SearchSourceList.class));
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
