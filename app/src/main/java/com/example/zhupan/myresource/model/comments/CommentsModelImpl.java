package com.example.zhupan.myresource.model.comments;

import com.example.zhupan.myresource.base.BaseResultCallBackListener;
import com.example.zhupan.myresource.config.RequestCodeConstants;
import com.example.zhupan.myresource.model.ICommentsModel;

public class CommentsModelImpl implements ICommentsModel{

    @Override
    public void getData(BaseResultCallBackListener callBackListener) {
        callBackListener.onSuccess("123", RequestCodeConstants.COMMENTS_LIST);
    }
}
