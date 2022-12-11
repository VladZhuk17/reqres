package adapters;

import io.restassured.response.Response;
import static utils.StringConstant.RESOURCE_API_ENDPOINT;
import static utils.StringConstant.SINGLE_RESOURCE_API_ENDPOINT;

public class ResourceAdapter extends BaseAdapter {
    public Response getResourceList() {
        return get(RESOURCE_API_ENDPOINT);
    }

    public Response getResourceSingle(String resourceID) {
        return get(String.format(SINGLE_RESOURCE_API_ENDPOINT, resourceID));
    }
}
