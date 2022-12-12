package adapters;

import io.restassured.response.Response;
import objects.User;
import static utils.StringConstant.LOGIN_API_ENDPOINT;

public class LoginAdapter extends BaseAdapter{
    public Response postLoginUser(User user) {
        return post(LOGIN_API_ENDPOINT, convector.toJson(user));
    }
}
