package dao;

import beans.Coupon;
import db.ConvertUtils;
import db.DBUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CouponDAOImpl implements CouponDAO{

    private static final String SQL_INSERT = "INSERT INTO `java159`.`coupons` (`company_id`, `category`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "";
    private static final String SQL_DELETE = "DELETE FROM `java159`.`coupons` WHERE (`id` = ?);";
    private static final String SQL_GET_ALL = "SELECT * FROM `java159`.`coupons`";
    private static final String SQL_GET_ONE = "";
    private static final String SQL_EXIST = "";
    @Override
    public void addCoupon(Coupon coupon) {

        Map<Integer,Object> params = new HashMap<>();
        params.put(1,coupon.getCompanyId());
        params.put(2,coupon.getCategory().name());
        params.put(3,coupon.getTitle());
        params.put(4,coupon.getDescription());
        params.put(5,coupon.getStartDate());
        params.put(6,coupon.getEndDate());
        params.put(7,coupon.getAmount());
        params.put(8,coupon.getPrice());
        params.put(9,coupon.getImage());
        DBUtils.runQuery(SQL_INSERT,params);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int couponId) {

        Map<Integer,Object> params = new HashMap<>();
        params.put(1,couponId);
        DBUtils.runQuery(SQL_DELETE,params);
    }



    @Override
    public List<Coupon> getAllCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        List<?> results = DBUtils.runQueryWithResultSet(SQL_GET_ALL);
        for (Object obj: results) {
            Map<String,Object> pairs = (Map<String, Object>) obj;
            Coupon coupon = ConvertUtils.couponFromPairs(pairs);
            coupons.add(coupon);
        }
        return coupons;
    }










    @Override
    public Coupon getSingleCoupon(int CouponId) {
        return null;
    }

    @Override
    public boolean isCouponExist(int couponId) {
        return false;
    }
}
