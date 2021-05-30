package com.example.benevoletrack.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.benevoletrack.R
import com.example.benevoletrack.Singletons
import com.example.benevoletrack.api.MarqueListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarqueListFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView

    /** Création de l'adapter */
    private val adapter = MarqueAdapter(listOf(), ::onClickedMarque)




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marque_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /** Recuperer un élément de ma vue */
        recyclerview= view.findViewById(R.id.destination_recyclerview)

        val apply = recyclerview.apply {
            adapter = this@MarqueListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }






        /** Appel du serveur Web distant : Lancer une requête de manière asynchrone */
        Singletons.marqueApi.getMarqueList("json").enqueue(object: Callback<MarqueListResponse>{

            override fun onFailure(call: Call<MarqueListResponse>, t: Throwable) {
                TODO("Not yet implemented")

            }

            override fun onResponse(call: Call<MarqueListResponse>, response: Response<MarqueListResponse>) {
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



    private fun onClickedMarque(id: Int) {
        findNavController().navigate(R.id.navigateToMarqueDetailFragment, bundleOf(
                "marqueId" to (id + 1)

        ))
    }
}