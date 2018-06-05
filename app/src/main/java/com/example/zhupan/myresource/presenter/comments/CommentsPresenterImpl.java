package com.example.zhupan.myresource.presenter.comments;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.zhupan.myresource.base.BaseResultCallBackListener;
import com.example.zhupan.myresource.base.IBaseView;
import com.example.zhupan.myresource.config.RequestCodeConstants;
import com.example.zhupan.myresource.entity.CommentModel;
import com.example.zhupan.myresource.entity.CommentsModel;
import com.example.zhupan.myresource.model.ICommentsModel;
import com.example.zhupan.myresource.model.comments.CommentsModelImpl;
import com.example.zhupan.myresource.presenter.ICommentsPresenter;
import com.example.zhupan.myresource.view.comments.impl.CommentsActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentsPresenterImpl implements ICommentsPresenter,BaseResultCallBackListener{
    ICommentsModel iCommentsModel;
    IBaseView iBaseView;
    Context context;
    private static final String TAG = "CommentsPresenterImpl";
    @Override
    public void getData() {
        iCommentsModel.getData(this);
    }

    public CommentsPresenterImpl(Context context, IBaseView iBaseView){
        iCommentsModel = new CommentsModelImpl();
        this.iBaseView = iBaseView;
        this.context = context;
    }

    @Override
    public void onSuccess(String response, int requestCode){
        switch (requestCode){
            case RequestCodeConstants.COMMENTS_LIST:
                if(response.equals("123")){
                    String commentsStr = "{\"comments\": [{\"speakName\": \"皮贰万\",\"content\":\"Come Baby!!!\",\"childComments\": [{\"speakName\": \"贾小绿\",\"toName\":\"\",\"content\":\"yo,yo,yo~\"}, {\"speakName\":\"李小兰\",\"toName\":\"贾小绿\",\"content\": \"靓仔，我去洗剪吹啦，吹吹吹吹吹~\"}, {\"speakName\":\"马小骚\",\"toName\": \"李小兰\",\"content\": \"来呀， 快活呀， 反正有大把欲望~\"}]}]}";
                    ((CommentsActivity)iBaseView).showList(commentsStr);
                }

                break;
        }
    }

    @Override
    public void onEorror(String msg, String requestCode) {

    }
}
