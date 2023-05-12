package db;


import beans.Category;
import beans.Coupon;

import java.sql.Date;
import java.util.Map;


public class ConvertUtils {

    public static Coupon couponFromPairs(Map<String, Object> map) {
        long id = (int) map.get("id");
        int companyId = (int) map.get("company_id");
        String category = (String) map.get("category");
        String title = (String) map.get("title");
        String description = (String) map.get("description");
        Date startDate = (Date) map.get("start_date");
        Date endDate = (Date) map.get("end_date");
        int amount = (int) map.get("amount");
        double price = (double) map.get("price");
        String image = (String) map.get("image");

        return new Coupon(id, companyId, Category.valueOf(category), title, description, startDate, endDate, amount, price, image);
    }


    public static boolean booleanFromPairs(Map<String, Object> map) {
        boolean res = ((long) map.get("res") == 1);
        return res;
    }
}
