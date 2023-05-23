package facade;

import beans.Category;
import beans.Coupon;
import beans.Customer;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data

public class CustomerFacadeImpl extends ClientFacade implements CustomerFacade {

    private int customerId;

    @Override
    public boolean login(String email, String password) {
        if (customersDAO.isCustomerExists(email, password)) {
            customerId = customersDAO.getIdByEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        if (!couponsDAO.isExistsById(coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        if (couponsDAO.isExistsPurchase(customerId, coupon.getId())) {
            throw new CouponSystemException(ErrMsg.COUPON_ALREADY_PURCHASED);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrMsg.COUPON_AMOUNT_ZERO);
        }
        if (coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        couponsDAO.addCouponPurchase(customerId, coupon.getId());
        coupon.setAmount(coupon.getAmount() - 1);
        couponsDAO.update(coupon.getId(), coupon);
    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        return couponsDAO.getCouponsByCustomer(customerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        return couponsDAO.getCouponsByCustomer(customerId, category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return couponsDAO.getCouponsByCustomer(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails() {
        Customer customer = customersDAO.getOne(customerId);
        customer.setCoupons(getCustomerCoupons());
        return customer;
    }
}