package com.example.benevoletrack.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.benevoletrack.R
import com.example.benevoletrack.api.MarqueDetailResponse
import com.example.benevoletrack.Singletons
import com.example.benevoletrack.list.MarqueListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarqueDetailFragment : Fragment() {

    private lateinit var textViewName: TextView


    /** Création du viewModel (MVVM) */
    private val viewModel: MarqueListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_marque_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.marque_detail_name)


        CallApi()

    }

    private fun CallApi() {
        val id = arguments?.getString("marqueName") ?: -1
        Singletons.marqueApi.getMarqueDetail(id as String).enqueue(object : Callback<MarqueDetailResponse>{
            override fun onFailure(call: Call<MarqueDetailResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MarqueDetailResponse>, response: Response<MarqueDetailResponse>) {
                if(response.isSuccessful && response.body() != null) {
                    textViewName.text = response.body()!!.Results.toString()
                }
            }

        })


    }        
}


