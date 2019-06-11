package cn.xmkeshe.utils.validate;
/**
 * 
 * @author 欣茂科技软件学院（xmkeshe.cn）
 * <li>数据验证</li>
 */
public class ValidateUtils {
	/**
	 * 验证字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean EmptyValidate(String str) {
		if(str != null && str.length() >  0) {
			return true;
		}
		return false;
	}
	/**
	 * 验证数据是否为空，并且判断数据是否是数字
	 * @param str
	 * @return
	 */
	public static boolean IntegerValidate(String str) {
		if(EmptyValidate(str)) {
			return str.matches("\\d+");
		}
		return false;
	}
	/**
	 * 判断两次参数内容是否一致
	 * @param strA 参数a
	 * @param strB 参数b
	 * @return
	 */
	public static boolean sameValidate(String strA,String strB) {
		if(EmptyValidate(strA) && EmptyValidate(strB)) {
			return strA.equalsIgnoreCase(strB);
		}
		return false;
	}
}
