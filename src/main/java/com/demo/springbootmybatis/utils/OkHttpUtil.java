package com.cnct.fwt.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okhttp3.FormBody.Builder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



/**
 * OkHttp请求工具
 * @author helei
 *
 */
public class OkHttpUtil {
	
	static OkHttpClient client = new OkHttpClient();
	/**
	 * OKHttp Post请求
	 * @param url
	 * @param json json字符串
	 * @return
	 * @throws IOException
	 */
	public static String post(String url,String json) throws IOException{
		Builder builder = new FormBody.Builder();
		//把json字符串转换为键值对的形式
		JSONObject jsonObject = JSONObject.parseObject(json);
		Set<String> keys = jsonObject.keySet();
		for (String key : keys) {
			builder.add(key, jsonObject.getString(key));
		}
		RequestBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if(response.isSuccessful()){
				return response.body().string();
			}else{
				throw new IOException("Unexpected code: "+response);
			}
		} finally {
			response.body().close();
		}
	}
	
	/**
	 * OKHttp Post请求
	 * @param url
	 * @param map 
	 * @return
	 * @throws IOException
	 */
	public static String post(String url,Map<String,String> map) throws IOException{
		Builder builder = new FormBody.Builder();
		//把json字符串转换为键值对的形式
		Set<String> keys = map.keySet();
		for (String key : keys) {
			builder.add(key,map.get(key));
		}
		RequestBody body = builder.build();
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if(response.isSuccessful()){
				return response.body().string();
			}else{
				throw new IOException("Unexpected code: "+response);
			}
		} finally {
			response.body().close();
		}
	}

	/**
	 * OkHttp Get 请求
	 * @param url 请求地址
	 * @return
	 * @throws IOException
	 */
	public static String get(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if(response.isSuccessful()){
				return response.body().string();
			}else{
				throw new IOException("Unexpected code: "+response);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			response.body().close();
		}
	}

	/**
	 * OkHttp Get 请求
	 * @param url 请求地址
	 * @param params 请求参数
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, Object> params) throws Exception {
		StringBuilder sb = new StringBuilder();
		if (params != null) {
			for (String key : params.keySet()) {
				String value = params.get(key) == null ? "" : params.get(key).toString();
				sb.append("&").append(key).append("=").append(URLEncoder.encode(value, "utf-8"));
			}
		}
		if (StringUtil.isNotEmpty(url) && url.indexOf("?") < 0) {
			sb.replace(0, 1, "?");
		}
		return get(url + sb.toString());
	}

}
