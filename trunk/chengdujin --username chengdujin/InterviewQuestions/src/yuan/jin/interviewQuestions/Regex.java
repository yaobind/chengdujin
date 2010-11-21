package yuan.jin.interviewQuestions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 写出正则表达式，从一个字符串中提取链接地址。比如下面字符串中
 * "IT面试题博客中包含很多  <a href=http://hi.baidu.com/mianshiti/blog/category/微软面试题> 微软面试题 </a> "
 * 则需要提取的地址为 " http://hi.baidu.com/mianshiti/blog/category/微软面试题 "
 * 
 * http://hi.baidu.com/mianshiti/blog/item/5b4d97366601d585a71e12e4.html
 * 
 * @author Yuan
 * 
 */
public class Regex {

	// http://www.wellho.net/solutions/java-regular-expressions-in-java.html
	static void email() {
		String input = "Prime   minister’s   office     "
				+ "Food   Safety   Task   Force       Kwak,No   "
				+ "seong  Add:905   LeeMa   Building,146-1,Susong-dong, "
				+ "Jongno-gu,Seoul,110-755  Tel:82   2   3703-2286-9         "
				+ "fax:82   2   725-5238       e-mail:forsome@chol.com  1、（"
				+ "EUCOMED）European   Medical   Technologies   Industry   Association  "
				+ "B   1150   Woluwe   St   Pierre   -   Belgium   -   Tel.:(32   2)  "
				+ " 772.22.12   -   Fax:(32   2)   771.39.09   -   eucomed@eucomed.be ";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("([a-z])*@([a-z])*.([a-z])*");
		matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	static void url() {
		String input = "IT面试题博客中包含很多  <a href=http://hi.baidu.com/mianshiti/blog/category/微软面试题> 微软面试题 </a> ";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile("<a(?: [^>]*)+href=([^ >]*)(?: [^>]*)*>");
		matcher = pattern.matcher(input);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
	}

	public static void main(String[] args) {
		email();
	}

}
