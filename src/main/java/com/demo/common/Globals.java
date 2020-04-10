package com.demo.common;

import com.demo.util.ConfigUtils;

import java.math.BigDecimal;



/**
 * @author JG 
 * @version 1.0 2014-9-9
 * 
 */
public class Globals {
	/** 域名 */
	static ConfigUtils configUtils= new ConfigUtils();
	public static final String WWW_HOST=configUtils.getProperties("WWW_HOST");
	public static final String WWW_PC_HOST=configUtils.getProperties("WWW_PC_HOST");
	/** 域名 */
	public static final String MANAGER_HOST=configUtils.getProperties("MANAGER_HOST");
	/** 图片 */
	public static final String IMAGE_HOST=configUtils.getProperties("IMAGE_HOST");
	/** 短信发送 */	
	public static final String SMS_URI = "http://222.73.117.158:80/msg/HttpBatchSendSM";
	/** 图片上传*/
	///var/www/Uploads/xinjue
	//D://image//
	public static final String IMAGE_URL="/var/www/Uploads/xinjue/";
	
	/** 附件上传 **/
	public static final String Enclosure = "/var/www/Uploads/enclosure/";
	
	/** 字符集      */
	public static final String CHARACTER_SET = "UTF-8";
	/** 投注重试次数	 */
	public static final int BET_RETRY_COUNT = 3;
	/** 消息分隔符    */
	public static final String SPLIT ="<<::>>";
	/** 失败	 */
	public static final String ERROR = "error";
	/** 成功	 */
	public static final String SUCCESS = "success";
	/** 系统异常*/
	public static final String SYSTEM_EXCEPTION = "9999";
	/** 管理key */
	public static final String SYS_MANAGE_KEY = "3IU6019kayK87KS";
	/** 回车换行  */
	public static final String CRLF = "\r\n";
	/**缓存用户KEY**/
	public static final String USERS="users";
	/**登录状态**/
	public static final String NOT_LOGIN="notlogin";
	public static final String COOKIE_USERNAME="cookie_userName";
	
	/** 昵称敏感词语  **/
	public static final String SENSITIVEWORDS = configUtils.getProperties("SENSITIVEWORDS");
	
	//心觉支付参数信息----start
	
	/** 小程序ID **/
	public static String appID="wx3a83f5a6c941cb5f";
	/** API密钥**/
	public static String WX_KEY="4296425466a14c0b93646f054a7fdc8b";
	/** 商户号**/
	public static String MCH_ID="1572755411";
	//D:/key/apiclient_cert.p12
	///var/www/key/apiclient_cert.p12
	/** API证书路径**/
	public static String CERT_LOCAL_PATH= System.getProperty("os.name").indexOf("Windows")!=-1?"D:/wxkey/apiclient_cert.p12":"/var/www/key2/apiclient_cert.p12";
	/** 支付成功回调地址**/
	public static String WX_PAY_NOTIFY_URL_ZXFK="http://m.xinjuenet.com/wx_zx_pay_back";
	
	//心觉支付参数信息----end
	
	/*public static String appID="wxa0d7fe4c2645a9aa";
	
	public static String WX_KEY="f1f2774e68d60e5b6448980011be5e11";
	public static String MCH_ID="1415377802";
	//D:/key/apiclient_cert.p12
	///var/www/key/apiclient_cert.p12
	public static String CERT_LOCAL_PATH= System.getProperty("os.name").indexOf("Windows")!=-1?"D:/key1/apiclient_cert.p12":"/var/www/key2/apiclient_cert.p12";
	
	public static String WX_PAY_NOTIFY_URL_ZXFK="http://www.xinshuidashi.com/wx_zx_pay_back";*/
	
	//微信支付参数信息----------
	//金果树:wx6c059e606bd93874
	//一起众筹:wxb594de914719d0b2
	/*public static String appID="wxb594de914719d0b2";
	
	public static String WX_KEY="mZrhxMNPVd2XIu4RJQF9W1YYRUpRmzRF";*/
	//微信扫码登录
	public static String sm_appID="wx547b3033fc647c47";
	//微信扫码登录
	public static String SM_WX_KEY="548fc99bb81c9de9b75075c0e580b261";
	
	//金果树:1300331001
	//一起众筹:1319209501
	/*public static String MCH_ID="1319209501";
	//D:/key1/apiclient_cert.p12
	///var/www/key/apiclient_cert.p12
	public static String CERT_LOCAL_PATH="D:/key1/apiclient_cert.p12";*/
	
	public static String WX_PAY_NOTIFY_URL="http://www.firstspring.com/wx_pay_back";
	//微信支付参数信息----------end
	
	//慈善总会-----------------
	
	public static String CS_KEY="blqIUGqOCI3LrrwLBOqzxvMFz";
	
	//慈善总会-----------------end
	
	
	/**
	 * user ssesion
	 */
	public static final String SSESION_USER = "currUser";
	/**
	 * user ssesion
	 */
	public static final String SSESION_MANAGER_USER = "currMangerUser";
	/**
	 * MessageSms ssesion
	 */
	public static final String SMS_USER = "currUserSms";
	/**
	 * 用户短信-渠道商
	 */
	public static final String SMS_USER_CHANNEL = "亿美软";
	/**
	 * 用户邮箱-渠道商Tuanpubao123456
	 */
	public static final String EMAIL_USER_CHANNEL = "admin@tuanpubao.org";
	
	/**
	 * 手机验证短信内容
	 */
	public static final String SMS_COMMENT = "【一起众筹】验证码:";
	
	/**
	 * 邮件类型
	 */
	public static final String EMAIL_DRAW_INFORM = "drawInform";
	
	/** email	 */
	public static final String EMAIL = "email";
	/** mobile	 */
	public static final String MOBILE = "mobile";
	
	
	/**
	 * 用户不存在
	 */
	public static final String SYSTEM_NO_EXIST = "用户不存在";
	/**
	 * 用户已存在
	 */
	public static final String SYSTEM__EXIST = "用户已存在";
	/**
	 * 系统忙，请稍候再试
	 */
	public static final String SYSTEM_BUSY = "系统忙，请稍候再试";
	/**
	 * 对不起，您的帐号已被锁定，请联系客服
	 */
	public static final String SYSTEM_ACCOUNT_LOCK = "对不起，您的帐号已被锁定，请联系客服";	
	/**
	 * 用户密码错误
	 */
	public static final String SYSTEM_ACCOUNT_PWD_ERROR = "用户密码错误";
	/**
	 * 验证码错误
	 */
	public static final String SYSTEM_IDENT_CODE_ERROR = "验证码错误";
	/**
	 * 手机/邮箱验证码错误
	 */
	public static final String MOBILE_EMAIL_CODE_ERROR = "手机或邮箱验证码错误";
	/**
	 * 手机已存在
	 */
	public static final String SYSTEM_MOBILE_CZ_ERROR = "手机已存在";
	/**
	 * 邮箱已存在
	 */
	public static final String SYSTEM_EMAIL_CZ_ERROR = "邮箱已存在";
	/**
	 * 旧邮箱账号错误
	 */
	public static final String OLD_EMAIL_ACOUNT__ERROR = "旧邮箱账号错误";
	/**
	 * 旧手机账号错误
	 */
	public static final String OLD_MOBILE_ACOUNT__ERROR = "旧手机账号错误";
	/**
	 * 旧密码有误
	 */
	public static final String SYSTEM_ACCOUNT_CHNANGE_PWD_ERROR = "旧密码有误";
	/**
	 * 生日错误
	 */
	public static final String SYSTEM_ACCOUNT_CHNANGE_BIRTHDAY_ERROR = "日期超过了今天";
	/**
	 * 我的帐号-实名认证
	 */
	public static final String MY_TUANPUBAO_ACCOUNT_ID_NUMBER_ERROR = "身份证已经被他人注册";
	
	/**
	 * 交易密码错误
	 */
	public static final String USERS_TRADE_PWD_ERROR = "交易密码错误";
	/**
	 * 请先设置交易密码
	 */
	public static final String USERS_TRADE_SZ_PWD_ERROR = "请先设置交易密码";
	
	/**
	 * 提款金额+手续费已经超过可用余额
	 */
	public static final String USERS_DAWAMOUNT_ERROR = "提款金额已经超过可用余额";
	
	/**
	 * 提款金额无效
	 */
	public static final String USERS_DAWAMOUNT_NULLITY = "提款金额无效";
	/**
	 * 已绑定
	 */
	public static final String USERS_BANKCARD_ISBANGDING_YES = "已绑定";
	/**
	 * 未绑定
	 */
	public static final String USERS_BANKCARD_ISBANGDING_NO = "未绑定";
	/**
	 * 提现只能使用默认银行卡
	 */
	public static final String USERS_BANKCARD_DEFAULT = "只能使用默认银行卡";
	/**
	 * 请先登录
	 */
	public static final String SYSTEM_LOGIN = "请先登录";
	/**
	 * 请先实名认证
	 */
	public static final String SYSTEM_REAL = "请先实名认证";
	/**
	 * 请先绑定手机号码
	 */
	public static final String SYSTEM_BD_MOBILE = "请先绑定手机号码";
	/**
	 * 请先绑定手机号码
	 */
	public static final String SYSTEM_BD_REALNAME_AND_MOBILE = "请先实名认证、绑定手机号码";
	/**
	 * 余额不足
	 */
	public static final String SYSTEM_USER_ACCOUNT = "余额不足";
	/**
	 * 用户名已存在
	 */
	public static final String SYSTEM_USER_NAME_YCZ = "用户名已存在";
	/**
	 * 不能与登录密码一样
	 */
	public static final String SYSTEM_USER_PWD_JYPWD = "不能与登录密码一样";
	/**
	 * 个人昵称不能为空
	 */
	public static final String SYSTEM_USER_NICKNAME = "个人昵称不能为空";
	/**
	 * 个人密码-旧密码
	 */
	public static final String SYSTEM_USER_OLD_PASSWORD = "旧密码不能为空";
	/**
	 * 个人密码-新密码
	 */
	public static final String SYSTEM_USER_NEW_PASSWORD = "新密码不能为空";
	/**
	 * 个人密码-确认新密码
	 */
	public static final String SYSTEM_USER_SURE_NEW_PASSWORD = "确认新密码不能为空";
	/**
	 * 个人密码-新密码-确认新密码
	 */
	public static final String SYSTEM_USER_NEW_PASSWORD_TWO = "两次输入的密码不一致";
	/**
	 * 请选择上传文件
	 */
	public static final String SYSTEM_UPLOAD_FILE = "请选择上传文件";
	/**
	 * 文件格式错误
	 */
	public static final String SYSTEM_FILE_FORMAT_ERROR = "格式错误";
	/**
	 * 金果币兑换个数
	 */
	public static final int SYSTEM_USER_GOLD_GS = 10;
	/**
	 * 收取手续费 2
	 */
	public static final BigDecimal SYSTEM_POUNDAGE=BigDecimal.valueOf(2.0);
	/**
	 * 手续费收取条件值 10
	 */
	public static final BigDecimal SYSTEM_POUNDAGE_CONDITIONS=BigDecimal.valueOf(10.0);
	/**
	 * 系统默认图片
	 */
	public static final String SYSTEM_DEFAULT_IMAGE = "images/nopic.jpg";
	/**
	 * 信息不完整
	 */
	public static final String INFORMATION_INCOMPLETE = "信息不完整!";
}
