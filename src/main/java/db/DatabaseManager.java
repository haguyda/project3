package db;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.*;
import utils.Art;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


public class DatabaseManager {


    private static final String CREATE_SCHEMA = "CREATE SCHEMA `java159`";
    private static final String DROP_SCHEMA = "DROP SCHEMA `java159`";

    private static final CategoriesDAO categoryDAO = new CategoriesDAOImpl();
    private static final CustomersDAO customerDAO = new CustomersDAOImpl();
    private static final CompaniesDAO companyDAO = new CompaniesDAOImpl();
    private static final CouponsDAO couponDAO = new CouponsDAOImpl();


    private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `java159`.`companies` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  `email` VARCHAR(45) NOT NULL,\n" +
            "  `password` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));\n";

    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `java159`.`customers` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `first_name` VARCHAR(45) NOT NULL,\n" +
            "  `last_name` VARCHAR(45) NOT NULL,\n" +
            "  `email` VARCHAR(45) NOT NULL,\n" +
            "  `password` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));\n";

    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `java159`.`categories` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));\n";

    private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `java159`.`coupons` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `company_id` INT NOT NULL,\n" +
            "  `category_id` INT NOT NULL,\n" +
            "  `title` VARCHAR(45) NOT NULL,\n" +
            "  `description` VARCHAR(45) NOT NULL,\n" +
            "  `start_date` DATE NOT NULL,\n" +
            "  `end_date` DATE NOT NULL,\n" +
            "  `amount` INT NOT NULL,\n" +
            "  `price` DOUBLE NOT NULL,\n" +
            "  `image` VARCHAR(150) NOT NULL,\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\n" +
            "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\n" +
            "  CONSTRAINT `company_id`\n" +
            "    FOREIGN KEY (`company_id`)\n" +
            "    REFERENCES `coupon_system`.`companies` (`id`)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `category_id`\n" +
            "    FOREIGN KEY (`category_id`)\n" +
            "    REFERENCES `coupon_system`.`categories` (`id`)\n" +
            "    ON DELETE NO ACTION\n" +
            "    ON UPDATE NO ACTION);\n";

    private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE `java159`.`customers_vs_coupons` (\n" +
            "  `customer_id` INT NOT NULL,\n" +
            "  `coupon_id` INT NOT NULL,\n" +
            "  PRIMARY KEY (`customer_id`, `coupon_id`),\n" +
            "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\n" +
            "  CONSTRAINT `customer_id`\n" +
            "    FOREIGN KEY (`customer_id`)\n" +
            "    REFERENCES `coupon_system`.`customers` (`id`)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE NO ACTION,\n" +
            "  CONSTRAINT `coupon_id`\n" +
            "    FOREIGN KEY (`coupon_id`)\n" +
            "    REFERENCES `coupon_system`.`coupons` (`id`)\n" +
            "    ON DELETE CASCADE\n" +
            "    ON UPDATE NO ACTION);\n";

    public static void startDatabase() {
        db.DBUtils.runQuery(DROP_SCHEMA);
        db.DBUtils.runQuery(CREATE_SCHEMA);
        db.DBUtils.runQuery(CREATE_TABLE_COMPANIES);
        db.DBUtils.runQuery(CREATE_TABLE_CUSTOMERS);
        db.DBUtils.runQuery(CREATE_TABLE_CATEGORIES);
        db.DBUtils.runQuery(CREATE_TABLE_COUPONS);
        db.DBUtils.runQuery(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
        initDatabase();
        db.ConnectionPool.getConnectionPool();

    }

    public static void endDatabase() {
        db.ConnectionPool.getConnectionPool().closeAllConnections();
    }

    private static void initDatabase() {
        updateCategoriesTable();
        loadCustomers();
        loadCompanies();
        loadCoupons();
    }

    private static void updateCategoriesTable() {
        Arrays.stream(Category.values()).forEach(categoryDAO::add);
    }

    private static void loadCustomers() {
        System.out.println(Art.CUSTOMERS);
        Customer c1 = Customer.builder()
                .firstName("yakov")
                .lastName("levy")
                .email("yakov@email.com")
                .password("1234")
                .build();

        Customer c2 = Customer.builder()
                .firstName("itizk")
                .lastName("levy")
                .email("itzik@email.com")
                .password("1234")
                .build();

        Customer c3 = Customer.builder()
                .firstName("shlomi")
                .lastName("ben tzion")
                .email("shlomi@email.com")
                .password("1234")
                .build();

        Customer c4 = Customer.builder()
                .firstName("roee")
                .lastName("libiker")
                .email("roee@email.com")
                .password("1234")
                .build();


        List<Customer> customers = List.of(c1, c2, c3, c4);
        customerDAO.addAll(customers);

        customerDAO.getAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");

    }

    private static void loadCompanies() {
        System.out.println(Art.COMPANIES);
        Company c1 = Company.builder()
                .name("johny")
                .email("johny@gmail.com")
                .password("1234")
                .build();
        Company c2 = Company.builder()
                .name("adidas")
                .email("adidas@adidas.com")
                .password("1234")
                .build();

        Company c3 = Company.builder()
                .name("pelephone ")
                .email("pele@pelephone.com")
                .password("1234")
                .build();



        List<Company> companies = List.of(c1, c2, c3);
        companyDAO.addAll(companies);

        companyDAO.getAll().forEach(System.out::println);
        System.out.println("---------------------------------------------------------------");
    }

    private static void loadCoupons() {
        System.out.println(Art.COUPONS);
        Coupon c1 = Coupon.builder()
                .companyID(2)
                .category(Category.RESTAURANT)
                .title("50%")
                .description("get 50% of everything!")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(300)
                .price(100)
                .build();

        Coupon c2 = Coupon.builder()
                .companyID(5)
                .category(Category.ELECTRICITY)
                .title("30% of outlet")
                .description("get 30% off all outlet products")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(3)))
                .amount(1000)
                .price(50)
                .build();

        Coupon c3 = Coupon.builder()
                .companyID(3)
                .category(Category.VACATION)
                .title("free airpods")
                .description("buy new iphone recieve free airpods")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusWeeks(2)))
                .amount(50)
                .price(4000)
                .build();

        List<Coupon> coupons = List.of(c1, c2, c3);
        couponDAO.addAll(coupons);

        couponDAO.getAll().forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
        couponDAO.addCouponPurchase(1, 1);
        couponDAO.addCouponPurchase(2, 2);
        couponDAO.addCouponPurchase(3, 3);
    }
}
