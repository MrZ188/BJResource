package com.example.zhupan.myresource.view;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AlphabetIndexer;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhupan.myresource.R;
import com.example.zhupan.myresource.adapter.ContactAdapter;
import com.example.zhupan.myresource.utils.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TongXunLuActivity extends AppCompatActivity {
    private static final String TAG = "TongXunLuActivity";
    private String[] names = {"朱晓明", "李白", "李信", "李世民",
            "朱元璋", "猪悟能", "赵云", "赵飞燕",
            "王昭君", "王明飞", "汪淼"};
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tong_xun_lu);

//        findViewById(R.id.contacts_list_view);
//    }

    /**
     * 分组的布局
     */
    private LinearLayout titleLayout;

    /**
     * 分组上显示的字母
     */
    private TextView title;

    /**
     * 联系人ListView
     */
    private ListView contactsListView;

    /**
     * 联系人列表适配器
     */
    private ContactAdapter adapter;

    /**
     * 用于进行字母表分组
     */
    private AlphabetIndexer indexer;

    /**
     * 存储所有手机中的联系人
     */
    private List<Contact> contacts = new ArrayList<Contact>();

    /**
     * 定义字母表的排序规则
     */
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_xun_lu);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        title = (TextView) findViewById(R.id.title);
        contactsListView = (ListView) findViewById(R.id.contacts_list_view);
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Arrays.sort(names);
        Log.i(TAG, "onCreate: "+names.toString());
        Collection<String> nameArr = Arrays.asList(names);
        for (String name : nameArr) {
            contacts.add(new Contact(name));
//            getContentResolver().insert(uri,contentValues);
        }
//        Cursor cursor = getContentResolver().query(uri,
//                null, null, null, "sort_key");
//        if (cursor.moveToFirst()) {
//            do {
//                Log.i(TAG, "onCreate: moveToFirst");
//                String name = cursor.getString(0);
//                String sortKey = getSortKey(cursor.getString(1));
//                Contact contact = new Contact();
//                contact.setName(name);
//                contact.setSortKey(sortKey);
//                contacts.add(contact);
//            } while (cursor.moveToNext());
//        }
//        startManagingCursor(cursor);
//        indexer = new AlphabetIndexer(cursor, 1, alphabet);
        adapter = new ContactAdapter(this, R.layout.list_item_txl, contacts);
        adapter.setIndexer(indexer);
        if (contacts.size() > 0) {
            setupContactsListView();
        }
    }

    /**
     * 为联系人ListView设置监听事件，根据当前的滑动状态来改变分组的显示位置，从而实现挤压动画的效果。
     */
    private void setupContactsListView() {
        if (null == contactsListView) Log.i(TAG, "setupContactsListView: 1");
        if (adapter == null) Log.i(TAG, "setupContactsListView: 2");
        contactsListView.setAdapter(adapter);
        contactsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
//                int section = indexer.getSectionForPosition(firstVisibleItem);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf(contacts.get(firstVisibleItem).getSortKey()));
                }
//                if (nextSecPosition == firstVisibleItem + 1) {
                View childView = view.getChildAt(0);
                if (childView != null) {
                    int titleHeight = titleLayout.getHeight();
                    int bottom = childView.getBottom();
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                            .getLayoutParams();
                    if (bottom < titleHeight) {
                        float pushedDistance = bottom - titleHeight;
                        params.topMargin = (int) pushedDistance;
                        titleLayout.setLayoutParams(params);
                    } else {
                        if (params.topMargin != 0) {
                            params.topMargin = 0;
                            titleLayout.setLayoutParams(params);
                        }
                    }
                }
//                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

    }

    /**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
     *
     * @param sortKeyString 数据库中读取出的sort key
     * @return 英文字母或者#
     */
    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }
}
