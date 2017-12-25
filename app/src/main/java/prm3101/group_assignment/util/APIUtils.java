package prm3101.group_assignment.util;

/**
 * Created by chuonghv on 12/25/17.
 */

public class APIUtils {
    public static final String BASE_URL = "https://api.mymemory.translated.net/";

    public static APIService getAPIService() {
        return RestService.getClient(BASE_URL).create(APIService.class);
    }
}
