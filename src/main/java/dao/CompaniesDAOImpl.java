package dao;

import beans.Company;
import db.ConvertUtils;
import db.DBUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDAOImpl implements CompaniesDAO {
    private static final String INSERT_COMPANY = "INSERT INTO `java159`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);\n";
    private static final String UPDATE_COMPANY = "UPDATE `java159`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);\n";
    private static final String DELETE_COMPANY = "DELETE FROM java159.companies WHERE id = ?";
    private static final String GET_ALL_COMPANIES = "SELECT * FROM java159.companies";
    private static final String GET_ONE_COMPANY = "SELECT * FROM java159.companies WHERE id = ?";
    private static final String GET_COMPANY_LOGIN = "SELECT * FROM java159.companies WHERE email = ? AND password = ?";
    private static final String EXISTS_COMPANY = "SELECT EXISTS (SELECT * FROM java159.companies WHERE email = ? AND password = ?) as res";
    private static final String EXISTS_ID = "SELECT EXISTS (SELECT * FROM java159.companies WHERE id = ?) as res";
    private static final String EXISTS_NAME = "SELECT EXISTS (SELECT * FROM java159.companies WHERE name = ?) as res";
    private static final String EXISTS_EMAIL = "SELECT EXISTS (SELECT * FROM java159.companies WHERE email = ?) as res";
    private static final String GET_ID_BY_EMAIL = "SELECT id FROM java159.companies WHERE email=?";

    @Override
    public void add(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        DBUtils.runQuery(INSERT_COMPANY, params);
    }

    @Override
    public void update(Integer id, Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, id);
        DBUtils.runQuery(UPDATE_COMPANY, params);
    }

    @Override
    public void delete(Integer id) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        DBUtils.runQuery(DELETE_COMPANY, params);
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        List<?> results = DBUtils.runQueryWithResultSet(GET_ALL_COMPANIES);
        for (Object obj : results) {
            Company company = ConvertUtils.companyFromPairs((Map<String, Object>) obj);
            companies.add(company);
        }
        return companies;
    }

    @Override
    public Company getOne(Integer id) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<?> results = DBUtils.runQueryWithResultSet(GET_ONE_COMPANY, params);
        Company company = ConvertUtils.companyFromPairs((Map<String, Object>) results.get(0));
        return company;
    }

    @Override
    public boolean isExistsById(Integer id) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        List<?> results = DBUtils.runQueryWithResultSet(EXISTS_ID, params);
        boolean result = ConvertUtils.booleanFromPairs((Map<String, Object>) results.get(0));
        return result;
    }

    @Override
    public boolean isCompanyExists(String email, String password) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        List<?> results = DBUtils.runQueryWithResultSet(EXISTS_COMPANY, params);
        boolean result = ConvertUtils.booleanFromPairs((Map<String, Object>) results.get(0));
        return result;
    }

    @Override
    public int getIdByEmail(String email) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        List<?> results = DBUtils.runQueryWithResultSet(GET_ID_BY_EMAIL, params);
        int id = ConvertUtils.idFromPairs((Map<String, Object>) results.get(0));
        return id;
    }

    @Override
    public boolean isExistsByName(String name) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);
        List<?> results = DBUtils.runQueryWithResultSet(EXISTS_NAME, params);
        boolean result = ConvertUtils.booleanFromPairs((Map<String, Object>) results.get(0));
        return result;
    }

    @Override
    public boolean isExistsByEmail(String email) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        List<?> results = DBUtils.runQueryWithResultSet(EXISTS_EMAIL, params);
        boolean result = ConvertUtils.booleanFromPairs((Map<String, Object>) results.get(0));
        return result;
    }

}
