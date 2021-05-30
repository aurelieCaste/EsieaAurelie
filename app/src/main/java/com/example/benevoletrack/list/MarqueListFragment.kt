package com.example.benevoletrack.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.benevoletrack.R
import com.example.benevoletrack.api.MarqueApi
import com.example.benevoletrack.api.MarqueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MarqueListFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView

    /** Création de l'adapter */
    private val adapter = MarqueAdapter(listOf(), ::onClickedMarque)


    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_destination_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /** Recuperer un élément de ma vue */
        recyclerview= view.findViewById(R.id.destination_recyclerview)

        val apply = recyclerview.apply {
            adapter = this@MarqueListFragment.adapter
            layoutManager = this@MarqueListFragment.layoutManager
        }

        /** La retrofit class génère une implementation de l'API*/
        val retrofit = Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val marqueApi: MarqueApi = retrofit.create(MarqueApi::class.java)


        /** Appel du serveur Web distant : Lancer une requête de manière asynchrone */
        marqueApi.getMarqueList("json").enqueue(object: Callback<MarqueResponse>{

            override fun onFailure(call: Call<MarqueResponse>, t: Throwable) {
                TODO("Not yet implemented")

            }

            override fun onResponse(call: Call<MarqueResponse>, response: Response<MarqueResponse>) {
               if(response.isSuccessful && response.body() != null){
                   val marqueResponse = response.body()!!
                   adapter.updateList(marqueResponse.Results)
               }
            }


        })


        /** première partie TD2 : ViewHolder :

        val destinationList = arrayListOf<Destination>().apply {
            add(Destination("Afrique du Sud"))
            add(Destination("Botswana"))
            add(Destination("Ouganda"))
            add(Destination("Madagascar"))
            add(Destination("Sénégal"))
        }
*/

    }



    private fun onClickedMarque(marque: Marque) {
        findNavController().navigate(R.id.navigateToMarqueDetailFragment)
    }
}