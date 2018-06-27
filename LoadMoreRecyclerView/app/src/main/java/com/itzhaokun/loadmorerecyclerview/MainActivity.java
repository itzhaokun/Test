package com.itzhaokun.loadmorerecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.itzhaokun.loadmorerecyclerview.View.LoadMoreRecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SampleAdapter mSampleAdapter;
    private LoadMoreRecyclerView mRvSample;
    private ArrayList<String> mData = new ArrayList<>();
    private int mCurrentPage = 1;
    private static Handler mHandler = new Handler();
    private boolean isRetry = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData(1);
    }

    private void initView() {
        mRvSample = (LoadMoreRecyclerView) findViewById(R.id.mRecy);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRvSample.setLayoutManager(new GridLayoutManager(this, 5));
        mSampleAdapter = new SampleAdapter(mData);
        mRvSample.setAdapter(mSampleAdapter);
        mRvSample.setOnLoadMore(new LoadMoreRecyclerView.OnLoadMore() {
            @Override
            public void onLoad() {
                getData(++mCurrentPage);
            }
        });
    }

    private void getData(final int page) {
        if (page == 1) {
            for (int i = (page - 1) * 30; i < page * 30; i++) {
                mData.add("RecyclerViewItem" + i);
            }
            mSampleAdapter.notifyDataSetChanged();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        if (page == 4 && !isRetry) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mRvSample.setLoadMoreStatus(LoadMoreRecyclerView.STATUS_ERROR);
                                }
                            });
                        } else {
                            for (int i = (page - 1) * 30; i < page * 30; i++) {
                                mData.add("RecyclerViewItem" + i);
                            }
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mRvSample.setLoadMoreStatus(LoadMoreRecyclerView.STATUS_PREPARE);
                                    mSampleAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mRvSample.setLoadMoreStatus(LoadMoreRecyclerView.STATUS_ERROR);
                            }
                        });
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
