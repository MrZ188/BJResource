package com.example.zhupan.myresource.utils;


public class Contact {

    /**
     * 联系人姓名
     */
    private String name;
    /**
     * 排序字母
     */
    private String sortKey;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
        int code = CodeToEn.ChineseToCode(name.charAt(0));
        this.sortKey = String.valueOf(CodeToEn.CodeToEng(code));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

}