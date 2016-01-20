package com.loopeer.fastandroid.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopeer.fastandroid.R;
import com.loopeer.fastandroid.ui.adapter.TestRecyclerViewAdapter;
import com.loopeer.fastandroid.ui.fragment.dummy.DummyContent;

public class TestListFragment extends Fragment {

    public TestListFragment() {
    }

    public static TestListFragment newInstance() {
        TestListFragment fragment = new TestListFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new TestRecyclerViewAdapter(DummyContent.ITEMS));
        }
        return view;
    }

}
