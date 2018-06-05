package com.example.zhupan.myresource.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhupan.myresource.R;
import com.example.zhupan.myresource.entity.CommentModel;

import java.util.List;

public class CommentsInAdapter extends RecyclerView.Adapter<CommentsInAdapter.ViewHolder> {
    private List<CommentModel.ChildCommentBean> data;

    public CommentsInAdapter(List<CommentModel.ChildCommentBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_comments_in, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtSpeaker.setText(data.get(position).getSpeakName());
        Log.i("commentsInAdapter", "onBindViewHolder: " + data.get(position).getSpeakName() + "," + data.get(position).getToName() + "," + data.get(position).getContent());
        holder.txtToName.setText(data.get(position).getToName());
        holder.txtContent.setText(data.get(position).getContent());
        if(data.get(position).getToName().isEmpty()){
            holder.txtTo.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtSpeaker;
        private TextView txtToName;
        private TextView txtTo;
        private TextView txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSpeaker = itemView.findViewById(R.id.txt_speak_name);
            txtToName = itemView.findViewById(R.id.txt_to_name);
            txtContent = itemView.findViewById(R.id.txt_content);
            txtTo = itemView.findViewById(R.id.txt_to);
        }
    }
}
