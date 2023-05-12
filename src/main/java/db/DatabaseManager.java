package db;


public class DatabaseManager {


    private static final String CREATE_SCHEMA = "CREATE SCHEMA `java159`";
    private static final String DROP_SCHEMA = "DROP SCHEMA `java159`";

    private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `java159`.`coupons` (\n" +
            "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `company_id` INT NOT NULL,\n" +
            "  `category` VARCHAR(10) NOT NULL,\n" +
            "  `title` VARCHAR(30) NOT NULL,\n" +
            "  `description` VARCHAR(45) NOT NULL,\n" +
            "  `start_date` DATE NOT NULL,\n" +
            "  `end_date` DATE NOT NULL,\n" +
            "  `amount` INT NOT NULL,\n" +
            "  `price` DOUBLE NOT NULL,\n" +
            "  `image` VARCHAR(150) NOT NULL,\n" +
            "  PRIMARY KEY (`id`));\n";

    public static void letsGo() {
        DBUtils.runQuery(DROP_SCHEMA);
        DBUtils.runQuery(CREATE_SCHEMA);
        DBUtils.runQuery(CREATE_TABLE_COUPONS);

    }

}
