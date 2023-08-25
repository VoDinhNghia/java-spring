package spring.springboot.utils;

import java.util.Random;

public class GenerateUserCode {
    GetCurrentDate currentDate = new GetCurrentDate();
    Random random = new Random();
    public String createCode() {
        int code = random.nextInt(10000);
        String generateCode = currentDate.getYear() + "" + currentDate.getMonth() + "" + currentDate.getDate() + "" + code;
        return generateCode;
    }
}
