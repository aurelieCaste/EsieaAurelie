package com.example.benevoletrack.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.benevoletrack.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DestinationListFragment : Fragment() {

    private lateinit var recyclerview: RecyclerView

    /** Création de l'adapter */
    private val adapter = DestinationAdapter(listOf())

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

        recyclerview.apply {
            adapter = this@DestinationListFragment.adapter
            layoutManager = this@DestinationListFragment.layoutManager
        }

        val destinationList : ArrayList<Destination> = arrayListOf<Destination>().apply {
            add(Destination("Afrique du Sud"))
            add(Destination("Botswana"))
            add(Destination("Ouganda"))
            add(Destination("Madagascar"))
            add(Destination("Sénégal"))
        }

        adapter.updateList(destinationList)

    }
}