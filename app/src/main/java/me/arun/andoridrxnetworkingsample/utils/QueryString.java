package me.arun.andoridrxnetworkingsample.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class QueryString
{

    public String getCollectionQuery()
    {
        return collectionQuery;
    }

    public void setCollectionQuery(String collectionQuery)
    {
        this.collectionQuery = collectionQuery;
    }

    public static String collectionQuery="list(collections_id:1){id name fb instagram pintrest g_plus updatedAt images{ url } twitter }";

    public static String countryQuery()
    {

        HashMap params = new HashMap();
        String param;
        param="{\n" +
                "  country(code: \"BR\") {\n" +
                "    name\n" +
                "    native\n" +
                "    emoji\n" +
                "    currency\n" +
                "    languages {\n" +
                "      code\n" +
                "      name\n" +
                "    }\n" +
                "  }\n" +
                "} ";
        params.put("query",param);
        Gson gson=new GsonBuilder().create();
        return gson.toJson(params);

    }

    public static String tweetUserQuery(String userName)
    {


        HashMap params = new HashMap();
        String param;


        param="{\n" +
                "  twitter {\n" +
                "    user (identifier: name, identity:"+ ""+userName+ ") {\n" +
                "      created_at\n" +
                "      description\n" +
                "      id\n" +
                "      screen_name\n" +
                "      name\n" +
                "      profile_image_url\n" +
                "      url\n" +
                "      tweets_count\n" +
                "      followers_count\n" +
                "      tweets(limit: 1) {\n" +
                "        text\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        params.put("query",param);
        Gson gson=new GsonBuilder().create();
        return gson.toJson(params);
    }

    public static String collectionQuery(String collections_id)
    {
        HashMap params = new HashMap();
        String param;
        param="{\n" +
                "  listCollections\n" +
                "  {\n" +
                "         id\n" +
                "         name\n" +
                "    thumb_image\n" +
                "         playstore_id\n" +
                "    appstore_id\n" +
//                "    description\n" +
                "    allow_preview \n" +
                "        is_free\n" +
                "    is_active\n" +
                "    list_count\n" +
                "    is_purchase\n" +
                "    createdAt\n" +
                "    updatedAt\n" +
                "  }\n" +
                "}";
        params.put("query",param);
        Gson gson=new GsonBuilder().create();
        return gson.toJson(params);
    }

    public static String listQuery(String collections_id)
    {
        HashMap params = new HashMap();
        String param;
        param="{\n" +
                "  list(collections_id:"+collections_id+")\n" +
                "  {\n" +
                "    id\n" +
                "    collections_id\n" +
                "    draft_lists_id\n" +
                "    name\n" +
                "    byline\n" +
                "    description\n" +
                "    thumb_image\n" +
                "    cost_categorization\n" +
                "    cost_two\n" +
                "    display_name\n" +
                "    address_line1\n" +
                "    address_line2\n" +
                "    zip_code\n" +
                "    lat\n" +
                "    lang\n" +
                "    phone_no\n" +
                "    web_timing\n" +
                "    timing\n" +
                "    timing_note\n" +
                "    fb\n" +
                "    g_plus\n" +
                "    twitter\n" +
                "    pintrest\n" +
                "    instagram\n" +
                "    youtube\n" +
                "    website\n" +
                "    recommended\n" +
                "    rating_product\n" +
                "    rating_ambience\n" +
                "    rating_service\n" +
                "    is_preview\n" +
                "    is_changed\n" +
                "    createdAt\n" +
                "    updatedAt\n" +
                "    cuisines{\n" +
                "      id\n" +
                "      name\n" +
                "    }\n" +
                "    ambiences{\n" +
                "      id\n" +
                "      name\n" +
                "    }\n" +
                "    \n" +
                "    category_ideal_fors{\n" +
                "      id\n" +
                "      name\n" +
                "    }\n" +
                "    images{\n" +
                "      url\n" +
                "      id\n" +
                "      is_archive\n" +
                "      lists_id\n" +
                "    }\n" +
                "  \n" +
                "    }\n" +
                "    \n" +
                "  }\n";
        params.put("query",param);
        Gson gson=new GsonBuilder().create();
        return gson.toJson(params);
    }







}