package com.example.zhupan.myresource.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhupan.myresource.R;
import com.example.zhupan.myresource.entity.CommentsModel;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class CommentsOutAdapter extends RecyclerView.Adapter<CommentsOutAdapter.ViewHolder>{
    private CommentsModel model;
    private Context context;
    public CommentsOutAdapter(Context context,CommentsModel model){
        this.model = model;
        this.context = context;
    }
    @Override
    public CommentsOutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comments_out,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsOutAdapter.ViewHolder holder, int position) {
        holder.txtSpeaker.setText(model.getComments().get(position).getSpeakName());
        holder.txtContent.setText(model.getComments().get(position).getContent());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.xRecyclerView.setLayoutManager(layoutManager);
        holder.xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        holder.xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        CommentsInAdapter commentsOutAdapter = new CommentsInAdapter(model.getComments().get(position).getChildComments());
        holder.xRecyclerView.setAdapter(commentsOutAdapter);
    }

    @Override
    public int getItemCount() {
        return model.getComments().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtSpeaker,txtContent;
        private XRecyclerView xRecyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            txtSpeaker = itemView.findViewById(R.id.txt_speak_name);
            txtContent = itemView.findViewById(R.id.txt_content);
            xRecyclerView = itemView.findViewById(R.id.xrec_child);

        }
    }
}
