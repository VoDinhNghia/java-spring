package spring.springboot.utils;

import java.util.HashMap;
import java.util.Map;

import spring.springboot.constants.Constants;

public class CalQuery {
    public Map<String, Long> pagQuery(long total, String limit, String page) {
        long skip = 0;
        long numberLimit = total;
        if (limit != null && page != null) {
            numberLimit = Integer.parseInt(limit);
            skip = (Integer.parseInt(page) - 1) * numberLimit;
        }
        HashMap<String, Long> data = new HashMap<String, Long>();
        data.put(Constants.querySkip, skip);
        data.put(Constants.numberLimit, numberLimit);
        return data;
    }
}
