package prm3101.group_assignment.util;

import java.util.Map;
import prm3101.group_assignment.data.translate.TranslateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by chuonghv on 12/25/17.
 */

public interface APIService {

    // example request -- https://api.mymemory.translated.net/get?q=Hello%20World&langpair=en|ja

    @GET("get")
    Call<TranslateResponse> getTranslateResponse(@QueryMap Map<String, String> keyWord);

}
