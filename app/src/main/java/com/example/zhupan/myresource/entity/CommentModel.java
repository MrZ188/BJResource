package com.example.zhupan.myresource.entity;

import java.util.ArrayList;

public class CommentModel {
    private String speakName;
    private String content;
    private ArrayList<ChildCommentBean> childComments;

    public String getSpeakName() {
        return speakName;
    }

    public void setSpeakName(String speakName) {
        this.speakName = speakName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<ChildCommentBean> getChildComments() {
        return childComments;
    }

    public void setChildComments(ArrayList<ChildCommentBean> childComments) {
        this.childComments = childComments;
    }

    public class ChildCommentBean{
        private String speakName;
        private String toName;
        private String content;

        public String getSpeakName() {
            return speakName;
        }

        public void setSpeakName(String speakName) {
            this.speakName = speakName;
        }

        public String getToName() {
            return toName;
        }

        public void setToName(String toName) {
            this.toName = toName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
