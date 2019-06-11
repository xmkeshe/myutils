package cn.xmkeshe.utils.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	/**
	 * 打印输出到页面
	 * @param str 要打印到内容
	 */
	public void print(String str) {
		try {
			this.response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 接收参数
	 * @param str 要接收到参数
	 */
	public String getParameter(String str) {
		return this.request.getParameter(str);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 多业务处理
		 */
		this.request = request;
		this.response = response;
		String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
		if (status != null && status.length() > 0) {
			try {
				Method method = this.getClass().getMethod(status);
				method.invoke(this);
			} catch (Exception e) {} 
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	/**
	 * 创建文件名称
	 * @return 返回新的文件名称
	 */
	public String createName() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
