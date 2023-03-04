package com.abdulkerim.retrofitkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdulkerim.retrofitkotlin.R
import com.abdulkerim.retrofitkotlin.adapter.CryptoAdapter
import com.abdulkerim.retrofitkotlin.databinding.ActivityMainBinding
import com.abdulkerim.retrofitkotlin.databinding.RowLayoutBinding
import com.abdulkerim.retrofitkotlin.model.CryptoModel
import com.abdulkerim.retrofitkotlin.service.CryptoApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
   private val BASE_URL="https://raw.githubusercontent.com/"
    private lateinit var cryptoModels: ArrayList<CryptoModel>
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        compositeDisposable= CompositeDisposable()

        binding.recyclerView.layoutManager=LinearLayoutManager(this)

        loadData()




    }

    private fun loadData(){
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoApi::class.java)

        compositeDisposable.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))


        /*

        val call=service.getDAta()
        val service=retrofit.create(CryptoApi::class.java)
        call.enqueue(object :Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {//response null değilse
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels= ArrayList(it)
                       cryptoAdapter=CryptoAdapter(cryptoModels)
                        binding.recyclerView.adapter=cryptoAdapter

                    }
                }
            }


            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })*/
    }
    private fun handleResponse(cryptoList: List<CryptoModel>){
        cryptoModels= ArrayList(cryptoList)

        cryptoAdapter=CryptoAdapter(cryptoModels)
        binding.recyclerView.adapter=cryptoAdapter


    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}