package com.github.eulerlcs.study.sb.mail;

import com.github.eulerlcs.study.sb.mail.config.TelworkProperties;
import org.apache.commons.text.StringSubstitutor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SbMailApplicationTests {

    @Autowired
    JavaMailSender sender;

    @Autowired
    TelworkProperties telwork;

//    @Autowired
//    ExternalProp externalProp;


    @Test
    void testStartMail() {
        // System.out.println(externalProp);
    }

    @Test
    void testProperty() {
        System.out.println(telwork);
    }

    @Test
    void testPlaceholder() {
        Map<String, String> values = new HashMap<String, String>();
        values.put("value", "x");
        values.put("column", "y");
        StringSubstitutor sub = new StringSubstitutor(values);
        String result = sub.replace("There's an incorrect value '${value}' in column # %(column)");
        System.out.println(result);
    }
}
