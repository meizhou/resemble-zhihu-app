package com.noworkday.bean;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.R.integer;
import android.util.Log;


public class PostBean {
	private int id;
	private String text;
	private String imageUrl;
	private int image;
	
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public static LinkedList<PostBean> parse(String json) {
		JSONTokener jsonTokener  = new JSONTokener(json);
		LinkedList<PostBean> postBeans = new LinkedList<PostBean>();
		JSONArray jsonArray;
		try {
			jsonArray = (JSONArray) jsonTokener.nextValue();
			for (int i = 0; i < jsonArray.length(); i++) {
				PostBean postBean = new PostBean();
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				postBean.setText((String)jsonObject.get("title"));
				postBean.setImageUrl(jsonObject.getString("image"));
				postBeans.add(postBean);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return postBeans;
	}
}
