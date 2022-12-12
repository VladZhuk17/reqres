package tests;

import adapters.LoginAdapter;
import adapters.RegisterAdapter;
import adapters.ResourceAdapter;
import adapters.UserAdapter;
import com.google.gson.Gson;
import io.restassured.response.Response;
import objects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.*;

public class ReqresTest {

    private String name = "morpheus";
    private String createJob = "leader";
    private String updateJob = "zion resident";
    private String registerSuccessfulEmail = "eve.holt@reqres.in";
    private String registerSuccessfulPassword = "pistol";
    private String registerUnsuccessfulEmail = "sydney@fife";
    private String loginSuccessfulEmail = "eve.holt@reqres.in";
    private String loginSuccessfulPassword = "cityslicka";
    private String loginUnsuccessfulEmail = "peter@klaven";
    private String errorLoginMessage = "Missing password";

    @Test
    public void getListUsersTest() {
        UserAdapter userAdapter = new UserAdapter();
        String pageNumber = "2";
        int actualStatusCode = userAdapter.getListUsers(pageNumber).statusCode();
        Assert.assertEquals(actualStatusCode, HTTP_OK);
    }

    @Test
    public void getSingleUserTest() {
        UserAdapter userAdapter = new UserAdapter();
        String userID = "2";
        int actualStatusCode = userAdapter.getSingleUser(userID).statusCode();
        Assert.assertEquals(actualStatusCode, HTTP_OK);
    }

    @Test
    public void getSingleUserNotFoundTest() {
        String userID = "23";
        Response response = new UserAdapter().getSingleUser(userID);
        Assert.assertEquals(response.statusCode(), HTTP_NOT_FOUND);
    }

    @Test
    public void getListOfResourceTest() {
        ResourceAdapter resourceAdapter = new ResourceAdapter();
        resourceAdapter.getResourceList().body().asString();
        ResourceList resourceList = new Gson().fromJson(resourceAdapter.getResourceList().body().asString(), ResourceList.class);
        int expectedTotal = 12;
        Assert.assertEquals(resourceList.getTotal(), expectedTotal);
    }

    @Test
    public void getSingleOfResourceTest() {
        String resourceID = "2";
        Response response = new ResourceAdapter().getResourceSingle(resourceID);
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void getListOfResourceNotFoundTest() {
        String resourceID = "23";
        Response response = new ResourceAdapter().getResourceSingle(resourceID);
        Assert.assertEquals(response.statusCode(), HTTP_NOT_FOUND);
    }

    @Test
    public void postCreateUsersTest() {
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();
        Response response = new UserAdapter().postCreateUser(user);
        Assert.assertEquals(response.statusCode(), HTTP_CREATED);
    }

    @Test
    public void putUpdateUserTest() {
        User user = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        String userID = "2";
        Response response = new UserAdapter().putUpdateUser(userID, user);
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void patchUpdateUserTest() {
        User user = User.builder()
                .name("morpheus")
                .job("zion resident")
                .build();
        String userID = "2";
        Response response = new UserAdapter().patchUpdateUser(userID, user);
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void deleteUserTest() {
        String userID = "2";
        Response response = new UserAdapter().deleteUser(userID);
        Assert.assertEquals(response.statusCode(), HTTP_NO_CONTENT);
    }

    @Test
    public void postRegisterSuccessfulTest() {
        User user = User.builder().email("eve.holt@reqres.in").password("pistol").build();
        Response response = new RegisterAdapter().postRegisterUser(user);
        Assert.assertEquals(response.statusCode(),HTTP_OK);
    }

    @Test
    public void postRegisterUnSuccessfulTest() {
        User user = User.builder().email("sydney@fife").build();
        Response response = new RegisterAdapter().postRegisterUser(user);
        Assert.assertEquals(response.statusCode(), HTTP_BAD_REQUEST);
    }

    @Test
    public void postLoginSuccessfulTest() {
        User user = User.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        Response response = new LoginAdapter().postLoginUser(user);
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }

    @Test
    public void postLoginUnSuccessfulTest() {
        User user = User.builder()
                .email("sydney@fife")
                .build();
        Response response = new LoginAdapter().postLoginUser(user);
        Assert.assertEquals(response.statusCode(), HTTP_BAD_REQUEST);
    }

    @Test
    public void getDelayedResponseTest() {
        String pageNumber = "3";
        Response response = new UserAdapter().getUserDelayedResponse(pageNumber);
        Assert.assertEquals(response.statusCode(), HTTP_OK);
    }
}
