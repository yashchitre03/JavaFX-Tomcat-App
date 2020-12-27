package com.yashchitre;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

public class QueryItem {

    public CheckBox checkBox = new CheckBox();
    public long pageId;

    public Text text = new Text();
    public String content;

    public QueryItem(long pageId, String content) {
        this.pageId = pageId;
        this.content = content;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public Text getText() {
        return text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }
}
