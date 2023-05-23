package login;

import exceptions.CouponSystemException;
import exceptions.ErrMsg;
import facade.*;

public class LoginManager {
    private static final LoginManager instance = new LoginManager();

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case ADMINSTRATOR:
                AdminFacade adminFacade = new AdminFacadeImpl();
                if (((ClientFacade) adminFacade).login(email, password)) {
                    return (ClientFacade) adminFacade;
                }
                break;
            case COMPANY:
                CompanyFacade companyFacade = new CompanyFacadeImpl();
                if (((ClientFacade) companyFacade).login(email, password)) {
                    return (ClientFacade) companyFacade;
                }
                break;
            case CUSTOMER:
                CustomerFacade customerFacade = new CustomerFacadeImpl();
                if (((ClientFacade) customerFacade).login(email, password)) {
                    return (ClientFacade) customerFacade;
                }
                break;
        }
        throw new CouponSystemException(ErrMsg.LOGIN_FAILED);
    }

}