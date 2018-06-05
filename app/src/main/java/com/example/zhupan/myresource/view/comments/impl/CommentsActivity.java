package com.example.zhupan.myresource.view.comments.impl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.zhupan.myresource.R;
import com.example.zhupan.myresource.adapter.CommentsOutAdapter;
import com.example.zhupan.myresource.base.BaseActivity;
import com.example.zhupan.myresource.entity.CommentsModel;
import com.example.zhupan.myresource.presenter.ICommentsPresenter;
import com.example.zhupan.myresource.presenter.comments.CommentsPresenterImpl;
import com.example.zhupan.myresource.view.comments.ICommentsView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;

public class CommentsActivity extends BaseActivity implements ICommentsView {
    ICommentsPresenter iCommentsPresenter;
    private CommentsOutAdapter commentsOutAdapter;
    private XRecyclerView xRecyclerView;
    private CommentsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);
        xRecyclerView = findViewById(R.id.xrec_out);
        setView();
        iCommentsPresenter = new CommentsPresenterImpl(this, this);
        iCommentsPresenter.getData();
    }

    private void setView() {
        model = new CommentsModel();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    }

    @Override
    public void showEorror(String msg) {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showList(String comments) {
        Gson gson = new Gson();
        model = gson.fromJson(comments, CommentsModel.class);
        Toast.makeText(CommentsActivity.this, model.getComments().get(0).getContent(), Toast.LENGTH_SHORT).show();
        commentsOutAdapter = new CommentsOutAdapter(this,model);
        xRecyclerView.setAdapter(commentsOutAdapter);
//        commentsOutAdapter.notifyDataSetChanged();
    }
}
