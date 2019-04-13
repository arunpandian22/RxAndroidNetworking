# RxAndroidNetworking

RxAndroidNetworking:
A repo about the do network call using RxJava and Retrofit within 10minutes for any type of API Call.

**Description:**
Android Rx Networking Library is a powerful library for doing any type of networking in Android applications which is made on top of Retrofit Using RxJava.
No other library provides or supports Network calling using RxJava.
* Provides a simple interface for doing all types of things in networking like setting params, its type and response model like that
* You can check Network availability using inbuilt function 

**Why Using RxJava:**
* RxJava Provides more operators to manipulating API response.
* Chaining API Calls
* we can choose thread whether the main thread or worker thread to get response. (Thread Handlings)


** Dependency **

Add the following dependency in your app level build.gradle

```
implementation'me.arun.androidrxnetworking:androidrxnetworking:1.0.1

```


**Implementing Network Call:**

``` java
 // initialization of Publish subject with Response Model class
   PublishSubject<SlashdrLogin> loginPublishSubject;
    loginPublishSubject = PublishSubject.create();

//initialization of Publishprocessor 
PublishProcessor<SearchStatusResponse> ppSerchRes = PublishProcessor.create();

```

* In Above code  Publish subject which is one type of observable  used to receive the API response its appropriate observer which is subscribed to
* We can use  PublishProcessor instead of for Flowable type of API call to handle back pressure while pagination

``` java
 // initialization of Publish subject with Response Model class
 /**
     * API call Response Handler for Login
     */
    public void subscribeForLogin() {
        loginPublishSubject.subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new Observer<SlashdrLogin>() {
            @Override
            public void onSubscribe(Disposable d) {
         
            }

            @Override
            public void onNext(SlashdrLogin slashdrLogin) {
// handle response
            }
            @Override
            public void onError(Throwable e) {
               // handle error
            }
            @Override
            public void onComplete() { }
        });
    }


```

* Above code snippets describes the subscription for the Publish subject which receives the API response.

```

//**/
/ * Making API call by passing needed login header parameters/
 **//
public void makeLoginRequest(String HeaderToken) {
    Log.d(TAG, "makeLoginRequest: "+HeaderToken);
    Map<String, String> hmHearders = new HashMap<>();
    hmHearders.put("Authorization", HeaderToken);
    RxNetworkRequest<SlashdrLogin> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder(this, UrlHandler.login, ObservableType.SINGLE, RequestType.POST, SlashdrLogin.class).setHeaderParams(hmHearders).build();
    if (Network_check.isNetworkAvailable(this))
        rxNetworkRequest.makeRequest(loginPublishSubject, null, "");
    else {
        Toast.makeText(this, ""+getString(R.string.poor_network_con), Toast.LENGTH_SHORT).show();
    }
}
```
 
* Above code describes the  making API call request.
* Builder class required the following paramos to define API Call type:

```
RxNetworkRequest<SlashdrLogin> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder(this, UrlHandler.login, ObservableType.SINGLE, RequestType.POST, SlashdrLogin.class)
```

* Here RxNetworkRequest is a needed class for making an API request.
* Here Context, Endpoint, ObservableType, RequestType, and Custom class type is essential to make one API call.

**Observable types:**

	* **FLOWABLE** - to make continuous api call for example pagination type of API calls
	* **SINGLE** - to get response like success or failure we can use this login type of place
	* **MAYBE** - It will return response or may not return if error any
	* **COMPLETABLE** - It will call completable callback method if request is success. We can use this response in file creation

 **Request Types:**

	*  **GET** -  Use GET requests**to retrieve resource representation/information only**.
	*  **POST** - Use POST APIs**to create new subordinate resources**
	*  **PUT** - Use PUT APIs primarily**to update existing resource**
	* **DELETE** - DELETE APIs are used**to delete resources**

**Header Adding In Request:**

```

RxNetworkRequest<SlashdrLogin> rxNetworkRequest = new RxNetworkRequest.RxNetworkRequestBuilder(this, UrlHandler.login, ObservableType.SINGLE, RequestType.POST, SlashdrLogin.class);
//headers in hashmap
Map<String, String> hmHearders = new HashMap<>();
hmHearders.put("token","24242nsidnvisskl89"));

rxNetworkRequest.setHeaderParams(hmHearders).build();

```

* setHeaderParams(hmHearders) method used to add headers
* you have to construct header params using HashMap as a (key, value) pair

**Query Params Adding in Request:**

```
Map<String, String> hmParams = new HashMap<>();
hmParams.put("search", "ab" );
hmParams.put("page_no", "1" );
hmParams.put("queue_page", "1");
rxNetworkRequest.setQueryParams(hmParams);
```
	
* setQueryParams(hmParams) method used to add headers
*  you have to construct query params using HashMap as a (key, value) pair

**Filed Params Adding in Request:**

```
Map<String, String> fieldParams = new HashMap<>();
fieldParams.put("search", "ab" );
fieldParams.put("page_no", "1" );
fieldParams.put("queue_page", "1");
rxNetworkRequest.setFieldsParams(fieldParams);
```

* fieldParams  are used to  send the Formurlencoded params
* setQueryParams(hmParams) method used to add headers
*  you have to construct query params using HashMap as a (key, value) pair

**Adding Image in Request:**

```
MultipartBody.Part
 file = ParamsUtils.getImageMultiPart(File file)
rxNetworkRequest.setFile(file);

```

* setBody() method used to upload a file in API request
* Using getImageMultiPart(File file ), you have to get the multipart object, then has to set in NetworkRequest object using a setFile method

**Adding RequestBody in Request:***

```
RequestBody body= ParamsUtils.getRequestBodyFromCustomObject(Object object);
rxNetworkRequest.setBody(file);

```
* setBody() method used to send RequestBody in API request
* Using ParamsUtils.getRequestBodyFromCustomObject(Object object), you have to get the RequestBody object, then has to set in NetworkRequest object using setBody method


**Known Issue:**

* If once API failed to get a response then Subscription will be dead, so you have to again initialise the PublishSubject object and resubscribe to get the response

