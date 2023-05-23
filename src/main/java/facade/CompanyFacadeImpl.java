package facade;

import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CouponSystemException;
import exceptions.ErrMsg;

import java.util.List;

public class CompanyFacadeImpl extends ClientFacade implements CompanyFacade {

    private int companyId;

    @Override
    public boolean login(String email, String password) {
        if (companiesDAO.isCompanyExists(email, password)) {
            companyId = companiesDAO.getIdByEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (couponsDAO.isExistsByTitle(companyId, coupon.getTitle())) {
            throw new CouponSystemException(ErrMsg.COUPON_TITLE_EXISTS);
        }
        if (coupon.getCompanyID() != companyId) {
            throw new CouponSystemException(ErrMsg.COUPON_WRONG_COMPANY);
        }
        couponsDAO.add(coupon);
    }

    @Override
    public void updateCoupon(int id, Coupon coupon) throws CouponSystemException {
        if (!couponsDAO.isExistsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        Coupon original = couponsDAO.getOne(id);
        if (original.getId() != coupon.getId()) {
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_MATCH);
        }
        if (original.getCompanyID() != coupon.getCompanyID()) {
            throw new CouponSystemException(ErrMsg.COUPON_COMPANY_NOT_MATCH);
        }
        couponsDAO.update(id, coupon);
    }

    @Override
    public void deleteCoupon(int id) throws CouponSystemException {
        if (!couponsDAO.isExistsById(id)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXISTS);
        }
        couponsDAO.delete(id);
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        List<Coupon> coupons = couponsDAO.getCompanyCoupons(companyId);
        return coupons;
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        List<Coupon> coupons = couponsDAO.getCompanyCoupons(companyId, category);
        return coupons;
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        List<Coupon> coupons = couponsDAO.getCompanyCoupons(companyId, maxPrice);
        return coupons;
    }

    @Override
    public Company getCompanyDetails() {
        Company company = companiesDAO.getOne(companyId);
        company.setCoupons(getCompanyCoupons());
        return company;
    }
}