package com.atguigu.srb.sms;

import com.atguigu.common.util.RegexValidateUtils;
import com.atguigu.srb.sms.utils.SmsProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTests {

    @Test
    public void testProperties(){
        System.out.println(SmsProperties.KEY_ID);
        System.out.println(SmsProperties.KEY_SECRET);
        System.out.println(SmsProperties.REGION_Id);
    }

    @Test
    public void testPhoneNumber(){
        String phoneNumber = "17788358052";
        boolean b = RegexValidateUtils.checkCellphone(phoneNumber);
        System.out.println(b);
    }
}