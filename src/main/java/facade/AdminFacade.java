package facade;

import beans.Company;
import beans.Customer;
import exceptions.CouponSystemException;

import java.util.List;
import java.util.Optional;

public interface AdminFacade {
    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int id, Company company) throws CouponSystemException;

    void deleteCompany(int id) throws CouponSystemException;

    List<Company> getAllCompanies();

    Optional<Company> getOneCompany(int id) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(int id, Customer customer) throws CouponSystemException;

    void deleteCustomer(int id) throws CouponSystemException;

    List<Customer> getAllCustomers();

    Optional<Customer> getOneCustomer(int id) throws CouponSystemException;

}