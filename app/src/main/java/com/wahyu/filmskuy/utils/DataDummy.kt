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

    fun generateDummyTvShow(): List<FilmModel> {
        val films = ArrayList<FilmModel>()

        films.add(
            FilmModel(
                "t01",
                R.drawable.poster_arrow,
                "Arrow",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "6.5",
                "10/10/2012",
                "(2012)"
            )
        )

        films.add(
            FilmModel(
                "t02",
                R.drawable.poster_doom_patrol,
                "Doom Patrol",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "7.5",
                "02/15/2019",
                "(2019)"
            )
        )

        films.add(
            FilmModel(
                "t03",
                R.drawable.poster_flash,
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "7.5",
                "10/07/2014",
                "(2014)"
            )
        )

        films.add(
            FilmModel(
                "t04",
                R.drawable.poster_gotham,
                "Gotham",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "7.4",
                "09/22/2014",
                "(2014)"
            )
        )

        films.add(
            FilmModel(
                "t05",
                R.drawable.poster_grey_anatomy,
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "8.0",
                "03/27/2005",
                "(2005)"
            )
        )

        films.add(
            FilmModel(
                "t06",
                R.drawable.poster_iron_fist,
                "Marvel's Iron Fist",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "6.4",
                "03/17/2017",
                "(2017)"
            )
        )

        films.add(
            FilmModel(
                "t07",
                R.drawable.poster_ncis,
                "NCIS",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "7.1",
                "09/23/2003",
                "(2003)"
            )
        )

        films.add(
            FilmModel(
                "t08",
                R.drawable.poster_riverdale,
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "8.6",
                "01/26/2017",
                "(2017)"
            )
        )

        films.add(
            FilmModel(
                "t09",
                R.drawable.poster_supernatural,
                "Supernatural ",
                "When they were boys, Sam and Dean Winchester lost their mother to a mysterious and demonic supernatural force. Subsequently, their father raised them to be soldiers. He taught them about the paranormal evil that lives in the dark corners and on the back roads of America ... and he taught them how to kill it. Now, the Winchester brothers crisscross the country in their '67 Chevy Impala, battling every kind of supernatural threat they encounter along the way.",
                "8.1",
                "09/13/2005",
                "(2005)"
            )
        )

        films.add(
            FilmModel(
                "t10",
                R.drawable.poster_the_walking_dead,
                "The Walking Dead: World Beyond",
                "A heroic group of teens sheltered from the dangers of the post-apocalyptic world leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.",
                "7.8",
                "10/04/2020",
                "(2020)"
            )
        )
        return films
    }
}