package tests;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.*;
import db.DatabaseManager;
import utils.Art;

import java.sql.Date;
import java.time.LocalDate;

public class DAOTests {
    private static final CustomersDAO customersDAO = new CustomersDAOImpl();
    private static final CompaniesDAO companyDAO = new CompaniesDAOImpl();
    private static final CouponsDAO couponDAO = new CouponsDAOImpl();

    public static void main(String[] args) {
        System.out.println(Art.LOGO);
        System.out.println("-----------------------------------------------------");
        DatabaseManager.startDatabase();

        //CustomersDAO tests
        System.out.println("---------------------------------------- CustomersDAO tests ----------------------------------------");
        Customer c1 = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@email.com")
                .password("1234")
                .build();
        c1.setFirstName("Ahmad");
        c1.setLastName("Jaber");
        customersDAO.update(1, c1);
        System.out.println(customersDAO.getOne(1));
        System.out.println(customersDAO.isCustomerExists(c1.getEmail(), c1.getPassword()));
        System.out.println(customersDAO.isCustomerExists("test@test.com", "1234"));
        System.out.println("deleting customer with id='1' and printing all");
        customersDAO.delete(1);
        customersDAO.getAll().forEach(System.out::println);

        //CompaniesDAO tests
        System.out.println("---------------------------------------- CompaniesDAO tests ----------------------------------------");
        Company co1 = Company.builder()
                .name("KSP")
                .email("KSP@KSP.com")
                .password("1234")
                .build();
        co1.setName("test");
        companyDAO.update(1, co1);
        System.out.println(companyDAO.getOne(1));
        System.out.println(companyDAO.isCompanyExists(co1.getEmail(), co1.getPassword()));
        System.out.println(companyDAO.isCompanyExists("test@test.com", "1234"));
        System.out.println("deleting company with id='3' and printing all");
        companyDAO.delete(3);
        companyDAO.getAll().forEach(System.out::println);

        //CouponsDAO tests
        System.out.println("---------------------------------------- CouponsDAO tests ----------------------------------------");
        Coupon coupon1 = Coupon.builder()
                .companyID(2)
                .category(Category.RESTAURANT)
                .title("1+1 Burger")
                .description("buy burger 200g and get second for free")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(100)
                .price(70.0)
                .image("https://media3.giphy.com/media/IgOEWPOgK6uVa/giphy.gif?cid=ecf05e47dtsmbz3xkfjphy7ul1fj4iluf7uke5ww1kmf7dao&rid=giphy.gif&ct=g")
                .build();
        coupon1.setPrice(80);
        coupon1.setAmount(20);
        couponDAO.update(1, coupon1);
        System.out.println(couponDAO.getOne(1));
        System.out.println("deleting coupon with id='1' and printing all");
        couponDAO.delete(1);
        couponDAO.getAll().forEach(System.out::println);
        DatabaseManager.endDatabase();
        System.out.println(Art.END);
    }


}
