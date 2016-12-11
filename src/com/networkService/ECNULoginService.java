package com.networkService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ECNULoginService {
	private String id;
	private String name;
	private String dept;
	private int deptId;
	private String cls;
	private int clsId;
	private String phone;
	private String email;
	private String errorMsg;

	public boolean login(String id, String password) throws IOException {
		this.id = id;
		NetworkConnection connection = new NetworkConnection("http://202.120.82.2:8081/ClientWeb/pro/ajax/login.aspx");
		String response = connection.doPost("id=" + id + "&pwd=" + password + "&act=login");
		JSONObject jsonObject = new JSONObject(response);
		try {
			JSONObject data = jsonObject.getJSONObject("data");
			name = data.getString("name");
			dept = data.getString("dept");
			deptId = data.getInt("deptid");
			cls = data.getString("cls");
			clsId = data.getInt("clsid");
			phone = data.getString("phone");
			email = data.getString("email");
			return true;
		} catch (JSONException e) {
			errorMsg = jsonObject.getString("msg");
			return false;
		}finally {
			connection.close();
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDept() {
		return dept;
	}

	public int getDeptId() {
		return deptId;
	}

	public String getCls() {
		return cls;
	}

	public int getClsId() {
		return clsId;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

}
