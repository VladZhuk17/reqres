package adapters;

import io.restassured.response.Response;
import objects.Register;
import objects.User;
import static utils.StringConstant.*;

public class UserAdapter extends BaseAdapter {

    public Response getListUsers(String pageNumber) {
        return get(String.format(USERS_LIST_API_ENDPOINT, pageNumber));
    }

    public Response getSingleUser(String userId) {
        return get(String.format(SINGLE_USER_API_ENDPOINT, userId));
    }

    public Response postCreateUser(User user) {
        return post(USERS_API_ENDPOINT, convector.toJson(user));
    }

    public Response putUpdateUser(String usersID, User user) {
        return put(String.format(SINGLE_USER_API_ENDPOINT, usersID), convector.toJson(user));
    }

    public Response patchUpdateUser (String usersID, User user) {
        return patch(String.format(SINGLE_USER_API_ENDPOINT, usersID), convector.toJson(user));
    }

    public Response deleteUser(String userId) {
        return delete(String.format(SINGLE_USER_API_ENDPOINT, userId));
    }

    public Response registerUser(Register register) {
        return post(REGISTER_API_ENDPOINT, convector.toJson(register));
    }

    public Response loginUser(User user) {
        return post(LOGIN_API_ENDPOINT, convector.toJson(user));
    }

    public Response getUserDelayedResponse (String page) {
        return get(String.format(USERS_DELAY_API_ENDPOINT, page));
    }
}
