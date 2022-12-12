package adapters;

import io.restassured.response.Response;
import objects.User;

import static utils.StringConstant.REGISTER_API_ENDPOINT;

public class RegisterAdapter extends BaseAdapter{
    public Response postRegisterUser(User user){
        return post(REGISTER_API_ENDPOINT, convector.toJson(user));
    }
}
