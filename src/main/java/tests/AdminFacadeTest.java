package tests;

import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;
import facade.AdminFacade;
import facade.AdminFacadeImpl;
import login.ClientType;
import login.LoginManager;
import utils.Art;

public class AdminFacadeTest {
    private static AdminFacade adminFacade;
    private static LoginManager loginManager = LoginManager.getInstance();

    public void testAsAdmin() {
        System.out.println(Art.ADMIN_FACADE);

        Test.test("Admin Facade - bad login - wrong email");
        try {
            adminFacade = (AdminFacadeImpl) loginManager.login("stam@email.com", "admin", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Facade - bad login - wrong password");
        try {
            adminFacade = (AdminFacadeImpl) loginManager.login("admin@admin.com", "stam", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        Test.test("Admin Facade - bad login - wrong email and password");
        try {
            adminFacade = (AdminFacadeImpl) loginManager.login("stam@email.com", "stam", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - good login");
        try {
            adminFacade = (AdminFacadeImpl) loginManager.login("admin@admin.com", "admin", ClientType.ADMINSTRATOR);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad add company - same name");
        Company c1 = Company.builder()
                .name("KSP")
                .email("stam@stam.com")
                .password("1234")
                .build();
        try {
            adminFacade.addCompany(c1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad add company - same id");
        c1.setName("KFC");
        c1.setId(1);
        try {
            adminFacade.addCompany(c1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad add company - same email");
        c1.setName("stam");
        c1.setId(0);
        c1.setEmail("KSP@KSP.com");
        try {
            adminFacade.addCompany(c1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - good add company");
        Company c2 = Company.builder()
                .name("EFC")
                .email("efc@efc.com")
                .password("1234")
                .build();
        try {
            adminFacade.addCompany(c2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad update company - changed id");
        c2.setId(2);
        try {
            adminFacade.updateCompany(11, c2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad update company - changed name");
        c2.setId(11);
        c2.setName("EEE");
        try {
            adminFacade.updateCompany(11, c2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - good update company - changed name");
        c2.setName("EFC");
        c2.setEmail("info@efc.com");
        try {
            adminFacade.updateCompany(11, c2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - get one company id=11");
        try {
            System.out.println(adminFacade.getOneCompany(11).orElseThrow(() -> new Exception("Company doesn't exist")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - get one company id=15 - doesn't exist");
        try {
            System.out.println(adminFacade.getOneCompany(11).orElseThrow(() -> new Exception("Company doesn't exist")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - delete company id=11");
        try {
            adminFacade.deleteCompany(11);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - get all companies");
        adminFacade.getAllCompanies().forEach(System.out::println);

        Test.test("Admin Facade - bad add customer - same email");
        Customer customer1 = Customer.builder()
                .firstName("stam")
                .lastName("stam")
                .email("johndoe@email.com")
                .password("1234")
                .build();
        try {
            adminFacade.addCustomer(customer1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad add customer - same id");
        customer1.setEmail("stam@stam.com");
        customer1.setId(1);
        try {
            adminFacade.addCustomer(customer1);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - good add customer");
        Customer customer2 = Customer.builder()
                .firstName("Ahmad")
                .lastName("Jbara")
                .email("ahmadjbara@email.com")
                .password("1234")
                .build();
        try {
            adminFacade.addCustomer(customer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - bad update customer - changed id");
        customer2.setId(2);
        try {
            adminFacade.updateCustomer(1, customer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - good update customer");
        customer2.setId(11);
        customer2.setFirstName("Jaber");
        try {
            adminFacade.updateCustomer(11, customer2);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - get one customer - id=11");
        try {
            System.out.println(adminFacade.getOneCustomer(11).get());
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        Test.test("Admin Facade - delete customer - id=11");
        try {
            adminFacade.deleteCustomer(11);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }


        Test.test("Admin Facade - get all customers");
        adminFacade.getAllCustomers().forEach(System.out::println);

    }
}
