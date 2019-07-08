package com.me.leaveproject.ui.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListAdapter;

import com.me.leaveproject.R;
import com.me.leaveproject.adapter.BaseAdapter;
import com.me.leaveproject.base.BaseActivity;

public class ListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        init();
    }

    private void init() {
        initRecyclerView();

    }

    private void initRecyclerView() {
        mAdapter = new BaseAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

    }
}
