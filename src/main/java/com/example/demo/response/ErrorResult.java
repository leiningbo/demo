package com.example.demo.response;


import com.example.demo.constants.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常基类
 * @author leiningbo
 * @version 1.0
 * 注：如有任何疑问欢迎加入QQ群977438372 进行讨论
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResult {

	/**
	 * 异常状态码
	 */
	private Integer status;

	/**
	 * 用户看得见的异常，例如 用户名重复！！,
	 */
	private String message;

	/**
	 * 异常的名字
	 */
	private String exception;

	/**
	 * 异常堆栈信息
	 */
	//private String errors;

	/**
	 * 对异常提示语进行封装
	 */
	public static ErrorResult fail(ResultCode resultCode, Throwable e, String message) {
		ErrorResult result = ErrorResult.fail(resultCode, e);
		result.setMessage(message);
		return result;
	}

	/**
	 * 对异常枚举进行封装
	 */
	public static ErrorResult fail(ResultCode resultCode, Throwable e) {

		ErrorResult result = new ErrorResult();
		result.setMessage(resultCode.message());
		result.setStatus(resultCode.code());
		result.setException(e.getClass().getName());
		//result.setErrors(Throwables.getStackTraceAsString(e));
		return result;
	}
}
