package facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;

import java.util.List;

public interface CompanyFacade {
    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int id, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int id) throws CouponSystemException;

    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(Category category);

    List<Coupon> getCompanyCoupons(double maxPrice);

    Company getCompanyDetails();
}
