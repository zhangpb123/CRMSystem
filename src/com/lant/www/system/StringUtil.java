package com.lant.www.system;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 工具类的使用
 */
public class StringUtil {

    /**
     * UUID:全宇宙唯一的一个字符串;
     * 作为主键;不可重复;
     * 作为图片的名称
     * 缺点:不可排序(无法建立有效索引)
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    //编码: 将一串字符串,按照特定的方式去进行改造;得到对应的结果(可逆编码 不可逆编码)
    //加密: 将一些内容,按照自己主观的方式去变化;

    /**
     *  对字符串进行MD5加密
     * @param str 需要加密的字符串
     */
    public static String md5Str(String str){
        byte[] digest = null;

        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //我们自己在做其他的操作
        for(int i = 0 ; i < digest.length ; i++){

            digest[i] += 10;//不暴露给别人;猜不到;  加盐的过程

        }

        //代表我们现在把字节数组 转成一个Integer得到一个
        //16: 转换为16进制的数字;
        String s = new BigInteger(1, digest).toString(16);
        return s;

    }
}
