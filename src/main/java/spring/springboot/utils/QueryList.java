package spring.springboot.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spring.springboot.constants.Constants;

public class QueryList {
    public Map<String, Long> calPagination(long total, String limit, String page) {
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

    public Map<String, Object> resList(List<?> results, long total) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put(Constants.resResults, results);
        data.put(Constants.resTotal, total);
        return data;
    }
}
