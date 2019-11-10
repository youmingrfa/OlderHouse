package com.example.olderhouse.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.olderhouse.R;
import com.example.olderhouse.ui.adapter.InstitutionSelectAdapter;
import com.example.olderhouse.ui.adapter.ServiceAdapter;
import com.example.olderhouse.ui.provided.OnItemClickListener;
import com.example.olderhouse.ui.widgets.RecyclerItemDecoration;

public class FindServiceActivity extends AppCompatActivity {

    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_service);

        recycler = findViewById(R.id.all_service);

        //RecyclerView的设置
        final StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.addItemDecoration(new RecyclerItemDecoration());
        ServiceAdapter adapter = new ServiceAdapter(this);
        adapter.notifyDataSetChanged();
        recycler.setAdapter(adapter);
    }
}
