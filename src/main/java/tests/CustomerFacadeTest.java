package tests;

import beans.Category;
import beans.Coupon;
import dao.CouponsDAO;
import dao.CouponsDAOImpl;
import exceptions.CouponSystemException;
import facade.CustomerFacade;
import login.ClientType;
import login.LoginManager;
import utils.Art;

public class CustomerFacadeTest {

    private static CustomerFacade customerFacade;
    private static LoginManager loginManager = LoginManager.getInstance();

    public void testAsCustomer() {
        System.out.println(Art.CUSTOMER_FACADE);

        Test.test("Customer Facade - bad login");
        try {
            customerFacade = (CustomerFacade) loginManager.login("stam@email.com", "1234", ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Facade - good login");
        try {
            customerFacade = (CustomerFacade) loginManager.login("johndoe@email.com", "1234", ClientType.CUSTOMER);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Facade - good purchase");
        CouponsDAO couponsDAO = new CouponsDAOImpl();
        Coupon toPurchase = couponsDAO.getOne(1);
        try {
            customerFacade.purchaseCoupon(toPurchase);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Facade - bad purchase - already purchased");
        try {
            customerFacade.purchaseCoupon(toPurchase);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Customer Facade - get customer coupons");
        customerFacade.getCustomerCoupons().forEach(System.out::println);

        Test.test("Customer Facade - get customer coupons category=Electricity");
        customerFacade.getCustomerCoupons(Category.ELECTRICITY).forEach(System.out::println);

        Test.test("Customer Facade - get customer coupons max_price=3000");
        customerFacade.getCustomerCoupons(3000).forEach(System.out::println);

        Test.test("Customer Facade - get customer details");
        System.out.println(customerFacade.getCustomerDetails());

    }
}