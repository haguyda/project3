package jobs;

import dao.CouponsDAO;
import dao.CouponsDAOImpl;

public class CouponExpirationDailyJob implements Runnable {

    private static final int SLEEP_TIME = 1000 * 60 * 60 * 24;

    private CouponsDAO couponsDAO;
    private boolean quit;

    public CouponExpirationDailyJob() {
        this.couponsDAO = new CouponsDAOImpl();
        quit = false;
    }

    @Override
    public void run() {
        while (!quit) {
            couponsDAO.deleteExpiredCoupons();
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void stop() {
        quit = true;
    }
}