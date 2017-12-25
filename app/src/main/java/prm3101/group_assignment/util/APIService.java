package prm3101.group_assignment.util;

import java.util.List;
import java.util.Map;

import prm3101.group_assignment.data.translate.TranslateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by chuonghv on 12/25/17.
 */

public interface APIService {

    // URL_Test = https://api.mymemory.translated.net/get?q=Hello World&langpair=en|ja

    @GET("get")
    Call<TranslateResponse> getTranslateResponse(@QueryMap Map<String, String> keyWord);

}
