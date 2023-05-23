package tests;


import beans.Category;
import beans.Coupon;
import exceptions.CouponSystemException;
import facade.CompanyFacade;
import login.ClientType;
import login.LoginManager;
import utils.Art;

import java.sql.Date;
import java.time.LocalDate;

public class CompanyFacadeTest {
    private static CompanyFacade companyFacade;
    private static LoginManager loginManager = LoginManager.getInstance();

    public void testAsCompany() {
        System.out.println(Art.COMPANY_FACADE);

        Test.test("Company Facade - bad login - wrong email");
        try {
            companyFacade = (CompanyFacade) loginManager.login("stam@stam.com", "1234", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Facade - bad login - wrong password");
        try {
            companyFacade = (CompanyFacade) loginManager.login("KSP@KSP.com", "stam", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Company Facade - bad login - wrong email and password");
        try {
            companyFacade = (CompanyFacade) loginManager.login("stam@stam.com", "stam", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - good login");
        try {
            companyFacade = (CompanyFacade) loginManager.login("KSP@KSP.com", "1234", ClientType.COMPANY);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - good add coupon");
        Coupon coupon = Coupon.builder()
                .title("20% off Logitech products")
                .image("https://shorturl.at/epxRT")
                .price(500)
                .category(Category.ELECTRICITY)
                .companyID(1)
                .description("get 20% off any Logitech product")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(100)
                .build();
        try {
            companyFacade.addCoupon(coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - bad add coupon - same title");
        try {
            companyFacade.addCoupon(coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - bad update coupon - changed id");
        coupon.setId(2);
        try {
            companyFacade.updateCoupon(6, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - bad update coupon - changed company id");
        coupon.setId(6);
        coupon.setCompanyID(2);
        try {
            companyFacade.updateCoupon(6, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - good update coupon");
        coupon.setCompanyID(1);
        coupon.setAmount(50);
        coupon.setTitle("30% OFF Everything!!!");
        coupon.setDescription("price 30% off all products");
        try {
            companyFacade.updateCoupon(6, coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - show all company coupons");
        companyFacade.getCompanyCoupons().forEach(System.out::println);

        Test.test("Company Facade - show all company coupons - electricity");
        companyFacade.getCompanyCoupons(Category.ELECTRICITY).forEach(System.out::println);

        Test.test("Company Facade - show all company coupons - max price 500");
        companyFacade.getCompanyCoupons(500.0).forEach(System.out::println);

        Test.test("Company Facade - delete coupon id=5");
        try {
            companyFacade.deleteCoupon(6);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Company Facade - show all company coupons");
        companyFacade.getCompanyCoupons().forEach(System.out::println);

        Test.test("Company Facade - get company details");
        System.out.println(companyFacade.getCompanyDetails());


    }
}
