package com.mystery.funclothes.Bean;


import com.mystery.funclothes.R;

import java.util.ArrayList;

/**
 * Created by Vindicated-Rt
 * 2019/5/13 8:10 PM
 * 场景数据类
 * 包括场景标题 图片 描述
 */

public class StyleInfo {

    private static StyleInfo instance = new StyleInfo();
    private ArrayList<Integer> imageIds = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();

    /*构造方法*/
    public StyleInfo() {
        setImageId();
        setTitle();
        setDescription();
    }

    /*单例模式唯一对象*/
    public static StyleInfo getInstance() {
        return instance;
    }

    public int getSize() {
        return imageIds.size();
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
        imageIds.add(R.mipmap.style_1);
        imageIds.add(R.mipmap.style_2);
        imageIds.add(R.mipmap.style_3);
        imageIds.add(R.mipmap.style_4);
        imageIds.add(R.mipmap.style_5);
        imageIds.add(R.mipmap.style_6);
        imageIds.add(R.mipmap.style_7);
        imageIds.add(R.mipmap.style_8);
        imageIds.add(R.mipmap.style_9);
        imageIds.add(R.mipmap.style_10);
        imageIds.add(R.mipmap.style_11);
        imageIds.add(R.mipmap.style_12);
        imageIds.add(R.mipmap.style_13);
    }

    private void setTitle() {
        titles.add("短袖");
        titles.add("长袖");
        titles.add("短外套");
        titles.add("长外套");
        titles.add("背心");
        titles.add("吊带");
        titles.add("短裤");
        titles.add("长裤");
        titles.add("裙子");
        titles.add("短连衣裙");
        titles.add("长连衣裙");
        titles.add("背心连衣裙");
        titles.add("吊带连衣裙");
    }

    private void setDescription() {
        descriptions.add("\t\t\t\t夏季炎热天气,经典的夏日单品");
        descriptions.add("\t\t\t\t温度始终的多种场合,出行必备");
        descriptions.add("\t\t\t\t又称为大衣,是穿在最外的服装");
        descriptions.add("\t\t\t\t一般用作保暖或抵挡雨水的用途");
        descriptions.add("\t\t\t\t适合运动场所,平常生活,清新透气");
        descriptions.add("\t\t\t\t流畅线条,轻柔质地,性感而不失时髦品味");
        descriptions.add("\t\t\t\t遮盖下体至大腿的衣服,有热裤、三分裤、五分裤等");
        descriptions.add("\t\t\t\t指由腰及踝,包覆全腿的裤子.一般有运动裤,牛仔裤,哈伦裤等");
        descriptions.add("\t\t\t\t围在腰部以下的服装,通风散热性能好,穿着方便,行动自如,美观");
        descriptions.add("\t\t\t\t上身合体，下摆稍展宽，无腰节缝,迷你裙起初长及膝盖,后逐渐缩到大腿，适合年轻人的口味。");
        descriptions.add("\t\t\t\t通常肩,领设计较低,裙摆宽大,裙长及踝。多用华贵的材料。");
        descriptions.add("\t\t\t\t后背裸露至腰。形式多样。宜选用柔软、悬垂效果好的面料裁制。");
        descriptions.add("\t\t\t\t吊带裙较窄短。吊带裙一般在腰节以上部位都有一段护胸和护背的衣料");
    }

}
