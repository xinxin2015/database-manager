package cn.upc.database.utils;

import java.io.Serializable;

public class ResultData<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6079119599458665028L;

	private T data;
	
	private String message;
	
	private boolean success;
	
	public Object getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResultData(T data, String message, boolean success) {
		this.data = data;
		this.message = message;
		this.success = success;
	}

	public ResultData(T data, String message) {
		this.data = data;
		this.message = message;
		this.success = true;
	}
	
	public ResultData(T data) {
		this.data = data;
		this.success = true;
	}
	
	public static <T> ResultData<T> ok (T data) {
		return new ResultData<T>(data);
	}
	
	public static <T> ResultData<T> error(String msg) {
		return new ResultData<T>(null, msg, false);
	}
}
