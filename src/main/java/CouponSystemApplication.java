import beans.Category;
import beans.Coupon;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import db.DatabaseManager;
import utils.Art;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class CouponSystemApplication {

    public static void main(String[] args) {
        CouponDAO couponDAO = new CouponDAOImpl();
        start();
        DatabaseManager.letsGo();

        Coupon c1 = new Coupon(123, Category.FOOD, "one plus one", "all burgers",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(4)),
                100, 49.99, "https://giphy.com/clips/itkkey9wCMcfmIchQ9");

        Coupon c2 = new Coupon(456, Category.COMPUTER, "Buy PC GET MOUSE FOR FREE", "DELL Computers Only",
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(21)),
                25, 4449.99, "https://media.giphy.com/media/l378bFG2RkMIAKDW8/giphy.gif");


        couponDAO.addCoupon(c1);
        couponDAO.addCoupon(c2);


        List<Coupon> coupons = null;

        coupons = couponDAO.getAllCoupons();
        for (Coupon coupon:coupons) {
            System.out.println(coupon);
        }

        end();
    }

    public static void start() {
        System.out.println(Art.LOGO);
    }

    public static void end() {
        System.out.println(Art.END);
    }
}