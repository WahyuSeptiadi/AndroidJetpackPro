package com.wahyu.filmskuy.utils

import com.wahyu.filmskuy.R
import com.wahyu.filmskuy.models.FilmModel

/**
 * Created by wahyu_septiadi on 25, October 2020.
 * Visit My GitHub --> https://github.com/WahyuSeptiadi
 */

object DataDummy {
    fun generateDummyMovies(): List<FilmModel> {
        val films = ArrayList<FilmModel>()

        films.add(
            FilmModel(
                "m01",
                R.drawable.mposter_alita,
                "Alita: Battle Angel",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "7.1",
                "02/14/2019",
                "(2019)"
            )
        )

        films.add(
            FilmModel(
                "m02",
                R.drawable.mposter_aquaman,
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "6.9",
                "12/21/2018",
                "(2018)"
            )
        )

        films.add(
            FilmModel(
                "m03",
                R.drawable.mposter_cold_persuit,
                "Cold Pursuit",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "5.6",
                "02/08/2019",
                "(2019)"
            )
        )

        films.add(
            FilmModel(
                "m04",
                R.drawable.mposter_creed,
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "6.9",
                "11/21/2018",
                "(2018)"
            )
        )

        films.add(
            FilmModel(
                "m05",
                R.drawable.mposter_glass,
                "Glass",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "6.6",
                "01/18/2019",
                "(2019)"
            )
        )

        films.add(
            FilmModel(
                "m06",
                R.drawable.mposter_infinity_war,
                "Avengers: Infinity War",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "8.3",
                "04/27/2018",
                "(2018)"
            )
        )

        films.add(
            FilmModel(
                "m07",
                R.drawable.mposter_crimes,
                "Fantastic Beasts: The Crimes of Grindelwald",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "6.9",
                "11/16/2018",
                "(2018)"
            )
        )

        films.add(
            FilmModel(
                "m08",
                R.drawable.mposter_master_z,
                "Master Z:\nIp Man Legacy",
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "5.6",
                "12/26/2018",
                "(2018)"
            )
        )

        films.add(
            FilmModel(
                "m09",
                R.drawable.mposter_overlord,
                "Overlord",
                "France, June 1944. On the eve of D-Day, some American paratroopers fall behind enemy lines after their aircraft crashes while on a mission to destroy a radio tower in a small village near the beaches of Normandy. After reaching their target, the surviving paratroopers realise that, in addition to fighting the Nazi troops that patrol the village, they also must fight against something else.",
                "6.7",
                "11/09/2018",
                "(2018)"
            )
        )

        films.add(
            FilmModel(
                "m10",
                R.drawable.mposter_mortal_engines,
                "Mortal Engines",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "6.1",
                "12/14/2018",
                "(2018)"
            )
        )
        return films
    }
}