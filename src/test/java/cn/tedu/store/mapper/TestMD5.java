package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMD5 {

    String salt = UUID.randomUUID().toString();

    @Test
    public void MD5() {

        String password = "123456";

        String md5Password = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        System.err.println(salt);
        System.err.println(md5Password);

    }

    @Test
    public void commonsMd5() {
        System.err.println();
        System.out.println();

    }
}
