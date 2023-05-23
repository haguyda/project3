package dao;

import beans.Category;
import db.DBUtils;

import java.util.HashMap;
import java.util.Map;

public class CategoriesDAOImpl implements CategoriesDAO {
    private static final String INSERT_CATEGORY = "INSERT INTO `coupon_system`.`categories` (`name`) VALUES (?);\n";

    @Override
    public void add(Category category) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, category.name());
        DBUtils.runQuery(INSERT_CATEGORY, params);
    }
}