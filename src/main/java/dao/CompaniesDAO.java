package dao;

import beans.Company;

public interface CompaniesDAO extends DAO<Company, Integer> {
    boolean isCompanyExists(String email, String password);

    int getIdByEmail(String email);

    boolean isExistsByName(String name);

    boolean isExistsByEmail(String email);


}