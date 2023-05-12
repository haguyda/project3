package dao;

import beans.Coupon;

import java.util.List;

/**
 * Created by kobis on 15 Mar, 2023
 */
public interface CouponDAO {

    void addCoupon(Coupon coupon);
    void updateCoupon(int couponId,Coupon coupon);
    void deleteCoupon(int couponId);
    List<Coupon> getAllCoupons();
    Coupon getSingleCoupon(int CouponId);
    boolean isCouponExist(int couponId);

}