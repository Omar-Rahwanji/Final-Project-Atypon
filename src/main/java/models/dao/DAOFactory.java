package models.dao;

public class DAOFactory {
    private DAOFactory(){}

    public static DAO getInstanceByType(String type){
        if(type.equalsIgnoreCase("customer"))
            return new CustomerDAO();
        else
            if(type.equalsIgnoreCase("account"))
                return new AccountDAO();
            return new NullDAO();
    }
}
