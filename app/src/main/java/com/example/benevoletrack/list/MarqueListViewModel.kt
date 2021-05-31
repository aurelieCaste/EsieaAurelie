package com.example.benevoletrack.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.benevoletrack.Singletons
import com.example.benevoletrack.api.MarqueListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** MarqueListViewModel : Récupération des données (logique de l'écran)*/
class MarqueListViewModel : ViewModel(){

     val marqueList : MutableLiveData<MarqueModel> = MutableLiveData()

    /** Appel du serveur Web distant : Lancer une requête de manière asynchrone */
    init{
        callApi()
    }

    private fun callApi() {
        marqueList.value = MarqueLoader

        Singletons.marqueApi.getMarqueList("json").enqueue(object: Callback<MarqueListResponse> {
            override fun onFailure(call: Call<MarqueListResponse>, t: Throwable) {
                marqueList.value = MarqueError
            }

            override fun onResponse(call: Call<MarqueListResponse>, response: Response<MarqueListResponse>) {
                if(response.isSuccessful && response.body() != null){
                    val marqueResponse = response.body()!!
                    marqueList.value = MarqueSuccess(marqueResponse.Results)
                } else{
                    marqueList.value = MarqueError
                }
            }

        })
    }


}