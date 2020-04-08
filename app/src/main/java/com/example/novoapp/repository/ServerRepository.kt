package com.example.novoapp.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.novoapp.di.component.ApiComponent
import com.example.novoapp.di.component.DaggerApiComponent
import com.example.novoapp.model.Comments
import com.example.novoapp.model.Posts
import com.example.novoapp.model.ResponseData
import com.example.novoapp.model.UsersList
import com.example.novoapp.service.ApiService
import com.example.novoapp.service.RetrofitClient
import com.example.novoapp.view.NovoApplication

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ServerRepository {


    private val mutableData:MutableLiveData<List<UsersList>> = MutableLiveData()

    private val mutablePostData:MutableLiveData<List<Posts>> = MutableLiveData()

    private val mutableCommentsData:MutableLiveData<List<Comments>> = MutableLiveData()

    @Inject
    lateinit var apiService: ApiService
    init {

        var apiComponent :ApiComponent =  NovoApplication.apiComponent
        apiComponent.inject(this)
    }



    fun getUsersData() : LiveData<List<UsersList>> {


      //  val call: Call<List<ResponseData>> = RetrofitClient.service.callApiForUsers()
        val call: Call<List<ResponseData>> = apiService.callApiForUsers()
        call.enqueue(object : Callback<List<ResponseData>> {
            override fun onFailure(call: Call<List<ResponseData>>, t: Throwable) {
                Log.d(">>> onFailure",t.message!!)
            }

            override fun onResponse(call: Call<List<ResponseData>>, response: Response<List<ResponseData>>) {
                if(response.isSuccessful){
                    Log.d(">>>>",response.body().toString())
                    var usersList = mutableListOf<UsersList>()
                    for(user in response.body()!!)
                    {
                        var u = UsersList(
                            id = user.id,
                            name = user.name  ,
                            email = user.email,
                            username = user.username,
                            error = null
                        )
                        usersList.add(u)
                    }
                    mutableData.postValue(usersList)
                }else{
                   Log.d(">>> onResponse",response.message())
                }
            }

        })


        return mutableData
    }

    fun getUsersPost(id:Int):LiveData<List<Posts>>{
        val call: Call<List<Posts>> = RetrofitClient.service.getAllPostByUsers(id)
        call.enqueue(object :Callback<List<Posts>>{
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if(response.isSuccessful)
                {
                    mutablePostData.postValue(response.body()!!)
                }


            }

        })
        return  mutablePostData
    }
    fun getPostComment(id:Int):LiveData<List<Comments>>{
        val call: Call<List<Comments>> = RetrofitClient.service.getAllCommentsOfPost(id)
        call.enqueue(object :Callback<List<Comments>>{
            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if(response.isSuccessful)
                {
                    mutableCommentsData.postValue(response.body()!!)
                }


            }

        })
        return  mutableCommentsData
    }

    /*fun getWeatherInfo(cityName: String, callback: RequestCompleteListener<WeatherInfoResponse>) {

       var call: Call<WeatherInfoResponse> =
           RetrofitClient.service.callApiForWeatherInfo(cityName)

           call.enqueue(object : Callback<WeatherInfoResponse> {

           // if retrofit network call success, this method will be triggered
           override fun onResponse(call: Call<WeatherInfoResponse>, response: Response<WeatherInfoResponse>) {

            //   Log.d(">>>URL",call.request().url().toString())
               if (response.body() != null)
                   callback.onRequestSuccess(response.body()!!) //let viewModel know the weather information data
               else
                   callback.onRequestFailed(response.message()) //let viewModel know about failure
           }

           // this method will be triggered if network call failed
           override fun onFailure(call: Call<WeatherInfoResponse>, t: Throwable) {
               callback.onRequestFailed(t.localizedMessage!!) //let viewModel know about failure
           }
       })
   }*/

}