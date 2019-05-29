package com.mystery.funclothes.Bean;


import com.mystery.funclothes.R;

import java.util.ArrayList;

/**
 * Created by Vindicated-Rt
 * 2019/5/13 8:10 PM
 * 场景数据类
 * 包括场景标题 图片 描述
 */

public class ScenesInfo {

    private static ScenesInfo instance = new ScenesInfo();
    private ArrayList<Integer> imageIds = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();

    /*构造方法*/
    public ScenesInfo() {
        setImageId();
        setTitle();
        setDescription();
    }

    /*单例模式唯一对象*/
    public static ScenesInfo getInstance() {
        return instance;
    }

    public int getSize() {
        return 8;
    }

    public int getImageId(int postion) {
        return imageIds.get(postion);
    }

    public String getDescription(int postion) {
        return descriptions.get(postion);
    }

    public String getTitle(int postion) {
        return titles.get(postion);
    }

    private void setImageId() {
        imageIds.add(R.mipmap.scenes_1);
        imageIds.add(R.mipmap.scenes_2);
        imageIds.add(R.mipmap.scenes_3);
        imageIds.add(R.mipmap.scenes_4);
        imageIds.add(R.mipmap.scenes_5);
        imageIds.add(R.mipmap.scenes_6);
        imageIds.add(R.mipmap.scenes_7);
        imageIds.add(R.mipmap.scenes_8);
    }

    private void setTitle() {
        titles.add("教室");
        titles.add("办公室");
        titles.add("旅行");
        titles.add("登山");
        titles.add("约会");
        titles.add("海边");
        titles.add("学校");
        titles.add("公园");
    }

    private void setDescription() {
        descriptions.add("\t\t\t\t适合日常不夸张，积极向上、朝气蓬勃能体现精神面貌的衣服");
        descriptions.add("\t\t\t\t适合知性、职业、整洁大方能体现专业素养的衣服");
        descriptions.add("\t\t\t\t结合自身特色，舒适且适合拍照，符合当地风土人情避开特殊宗教禁忌");
        descriptions.add("\t\t\t\t舒适，比较宽松，吸汗透气性强的衣服");
        descriptions.add("\t\t\t\t展现自身优点以及魅力，整洁大方的衣服");
        descriptions.add("\t\t\t\t凉爽、飘逸，具有海岛风格的衣服");
        descriptions.add("\t\t\t\t整洁大方，质朴自然，合乎时宜衣着得体");
        descriptions.add("\t\t\t\t休闲，舒适自然，清新随性能展现活泼阳光气质的衣服");
    }

}
