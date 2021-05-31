package com.example.benevoletrack.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.benevoletrack.R
import com.example.benevoletrack.api.MarqueDetailResponse
import com.example.benevoletrack.Singletons
import com.example.benevoletrack.list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MarqueDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView


    /** Création de l'adapter */
    private val adapter = MarqueAdapter(listOf(), ::onClickedMarque)



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

        /** Recuperer un élément de ma vue */
        textViewName = view.findViewById(R.id.marque_detail_name)
        loader= view.findViewById(R.id.marque_Loader)
        textViewError= view.findViewById(R.id.marque_Error)


        CallApi()

        viewModel.marqueList.observe(viewLifecycleOwner, Observer { marqueModel ->
            loader.isVisible = marqueModel is MarqueLoader
            textViewError.isVisible = marqueModel is MarqueError

            if( marqueModel is MarqueSuccess) {
                adapter.updateList(marqueModel.marqueList)
            }


        })

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

    private fun onClickedMarque(id: String) {
        findNavController().navigate(R.id.navigateToMarqueDetailFragment, bundleOf(
                "marqueName" to id

        ))
    }
}


