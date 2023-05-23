package facade;

import dao.*;

public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO = new CompaniesDAOImpl();
    protected CustomersDAO customersDAO = new CustomersDAOImpl();
    protected CouponsDAO couponsDAO = new CouponsDAOImpl();

    public abstract boolean login(String email, String password);


}