package com.example.benevoletrack.list


/** MarqueModel (Sealed class) : permet de maîtriser l'héritage  */
sealed class MarqueModel

data class MarqueSuccess(val marqueList: List<Marque>) : MarqueModel()
object MarqueLoader : MarqueModel()
object MarqueError : MarqueModel()