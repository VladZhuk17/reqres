package objects;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import java.util.ArrayList;

@Data
@Builder
public class ResourceList {
    private int page;
    @SerializedName("per_page")
    private int perPage;
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    private ArrayList<ResourceData> data;

}