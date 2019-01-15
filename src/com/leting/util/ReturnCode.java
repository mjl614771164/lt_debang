package com.leting.util;

public enum ReturnCode {
 
	SUCCESS("200"),    //请求成功
	ERROR_SERVICE("500"),    //服务器异常，记录日志

	ERROR_UNLOGIN("612"),//未登录
	ERROR_LOGIN_FAILED("501"),//用户登陆失败
	ERROR_REGISTER_ALREADY("502"),//用户已经注册
	ERROR_PHONE_MESS_OFENT("609"),//发送手机验证码过于频繁
	ERROR_PARAM_INCOMPLETE("600"),//参数不全
	ERROR_PARAM_TYPE("602"),//参数类型错误(校验失败)
	ERROR_PARAM_CHECKCODE("603"),//验证码错误
	ERROR_PARAM_VAL("604"),//参数值错误
	ERROR_ORDER_CANOT_CANCEL("700"),//订单不能取消
	ERROR_PRO_NOT_EXSIST("701"),//此id的产品不存在
	ERROR_PAY_TIME_NOTREACHED("702"),//支付时间未到
	ERROR_ORDER_INVALID("703"),//无效订单
	ERROR_ORDER_NOPAY_TOMANEY("704"),//未支付的订单过多
	ERROR_ORDER_PRO_NOTENOUGH("705"),//商品库存不足，无法下单
	ERROR_YINGMI_UNABLETOPARSE("802");//报文无法解析
	
	private final String value;

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ReturnCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
