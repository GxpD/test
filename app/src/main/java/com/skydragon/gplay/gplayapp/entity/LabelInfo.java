package com.skydragon.gplay.gplayapp.entity;

/**
 * Created by Administrator on 2016/7/16.
 */
public class LabelInfo {
    private  String labelImagePath;
    private  String labelName;

    public LabelInfo(String labelImagePath, String labelName) {
        this.labelImagePath = labelImagePath;
        this.labelName = labelName;
    }

    public LabelInfo() {
        super();
    }

    public String getLabelImagePath() {
        return labelImagePath;
    }

    public void setLabelImagePath(String labelImagePath) {
        this.labelImagePath = labelImagePath;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "LabelInfo{" +
                "labelImagePath='" + labelImagePath + '\'' +
                ", labelName='" + labelName + '\'' +
                '}';
    }
}
