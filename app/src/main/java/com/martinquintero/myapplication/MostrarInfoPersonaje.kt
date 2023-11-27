package com.martinquintero.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.martinquintero.myapplication.ui.rutas.Rutas

@Composable
fun MostrarInfoPersonaje(navController : NavHostController, nombre : String) {

    data class PersonajeInfo(val nombre: String, val imagen: Int, val descripcion: String)

    val listaPersonajes = remember { mutableStateListOf<PersonajeInfo>(
        PersonajeInfo("Hu Tao",
            R.drawable.hutao_046_gacha_splash,"La joven Hu Tao es la 77.ª directora de la Funeraria El Camino y la principal encargada de todos los asuntos relacionados con ella. Sin embargo, a pesar de su posición, no siempre sabe comportarse como es debido.\n" +
                    "\n" +
                    "Tiene más ideas malvadas que granos de arena hay en la Costa Yaoguang, y sus ocurrencias siempre pillan desprevenida a cualquier persona.\n" +
                    "\n" +
                    "Normalmente, Hu Tao es una chica a la que le encanta gastar bromas. Tan pronto como tiene algo de tiempo libre, se dispone a corretear por todas partes, por lo que quienes la conocen la ven como alguien que manda mucho y hace poco.\n" +
                    "\n" +
                    "Los únicos momentos en los que muestra su faceta más seria y solemne es cuando lidera personalmente una marcha fúnebre a través de una calle iluminada con tenues luces."),
        PersonajeInfo("Yelan",
            R.drawable.yelan_060_gacha_splash,"El Ministerio de Asuntos Civiles de Liyue se caracteriza por su transparencia. Por eso, hay información sobre todos los trabajadores en los registros oficiales, desde los altos cargos de la Terraza Yujing hasta los muchos funcionarios de menor rango.\n" +
                    "La única excepción es Yelan, quien aun así afirma trabajar en el Ministerio. La mayoría de sus compañeros ni siquiera han oído su nombre o no lo encuentran en los registros.\n" +
                    "Sin embargo, esto va muy acorde con su personalidad, dado que es impredecible y tiene una sorprendente capacidad para desaparecer sin dejar rastro.\n" +
                    "Como si de un fantasma se tratara, aparece a menudo con distintas apariencias cuando hay un problema y, cuando te das cuenta de que se trata ella, ya se ha esfumado sin dejar rastro.\n" +
                    "A veces, durante un conflicto, ofrece su ayuda a una de las partes y, justo cuando dicha parte se está regocijando, sus oponentes también han recibido la misma ayuda.\n" +
                    "Aun habiéndole complicado la vida y enfurecido a tanta gente, no solo fueron incapaces de prever sus verdaderas intenciones, sino que tampoco lograron adivinar cuál era su posición real en el asunto.\n" +
                    "Hay quien piensa que Yelan ofrece sus servicios a algún tipo de agencia secreta y que es experta en llevar las cosas al límite y sacar provecho de ello.\n" +
                    "También hay quien afirma que no es más que una persona desorganizada, que no pertenece a ninguna organización y que no tiene ningún propósito específico.\n" +
                    "Y si de verdad tuviera uno, sería el de agitar aún más las aguas que ya están turbias, echar leña a un fuego que ya está muy vivo y arrastrar a los demás al caos que ella misma causa.\n" +
                    "Con tantas teorías y elucubraciones, todo sobre ella es un misterio. En cuanto a cuál es la verdad, puede que solo la propia Yelan lo sepa.\n" +
                    "Desafortunadamente, sonsacarle la verdad no será tarea fácil, ya que es ella quien te encuentra primero, no al revés."),
        PersonajeInfo("Furina",
            R.drawable.furina_089_gacha_splash,"Un viajero de fuera jamás podrá entender por qué la diosa de Fontaine es considerada una celebridad a menos que haga una visita a la majestuosa Ópera de la Epíclesis.\n" +
                    "\n" +
                    "Ya sea en los espectáculos o los juicios, esta diosa siempre estará sentada en su asiento exclusivo a la hora de la función. A veces, sus reacciones y su euforia son incluso más impactantes que la propia obra del escenario en sí.\n" +
                    "\n" +
                    "La gente compra entradas para vivir nuevas experiencias sensoriales, y en ese aspecto Furina es mucho más popular que la justicia absoluta que representa Neuvillette.\n" +
                    "\n" +
                    "Alzar la mirada para contemplar a un ser que está en lo alto no es la única forma que hay de mostrar respeto. De hecho, en Fontaine, ese respeto se puede ver en el “amor y la dicha” mutuos que hay entre Furina y su pueblo.\n" +
                    "\n" +
                    "Puede que ella no sea ni perfecta ni extremadamente poderosa, pero una vez que sube al escenario, es la persona en quien más puedes confiar, pues nunca ha decepcionado a nadie.\n" +
                    "\n" +
                    "En los círculos literarios de Fontaine, existe una metáfora muy popular que dice así: “Para la gente, Furina es como un recuerdo eterno e inolvidable de la juventud, ¿y quién no quiere ser joven para siempre?”.\n" +
                    "\n" +
                    "La juventud se marcha demasiado pronto, pero ella siempre está ahí. Solo tienes que seguir la luz de los focos para verla."),
        PersonajeInfo("Jean",
            R.drawable.jean_003_gacha_splash,"Los Caballeros de Favonius son los protectores de Mondstadt, la espada y el escudo de esta ciudad.\n" +
                    "\n" +
                    "Además de mantener la paz y la seguridad en ella y proteger las rutas de viaje de la amenaza de los monstruos de la naturaleza, la responsabilidad más importante de los Caballeros es mantener el orden en toda Mondstadt.\n" +
                    "\n" +
                    "Aunque sea la Capital de la Libertad, una libertad sin un control de las reglas solo invita al caos y el desorden.\n" +
                    "\n" +
                    "Jean entiende esto perfectamente, por lo que es diligente y asidua en todo momento, manteniendo siempre los más altos estándares.\n" +
                    "\n" +
                    "Y... como resultado, a menudo agota su cuota mensual de café nada más comienza el mes sin darse cuenta."),
        PersonajeInfo("Kaedehara Kazuha",
            R.drawable.kazuha_047_gacha_splash,"Un samurái errante de Inazuma que actualmente se hospeda en la Flota Crux Meridianam de Liyue. El corazón de este amable y libre joven encierra sus muchas cargas del pasado.\n" +
                    "\n" +
                    "Al ver por primera vez a Kaedehara Kazuha, la mayoría de la gente piensa que no es más que un marinero en prácticas de la Flota Crux Meridianam.\n" +
                    "\n" +
                    "Al fin y al cabo, es una persona realmente serena, le encanta recitar poesía cuando tiene tiempo libre y, al hablar con otros, siempre mantiene una expresión sosegada. Nadie pensaría que, en realidad, es un peligroso prófugo en la lista de los más buscados por el shogunato de Inazuma.\n" +
                    "\n" +
                    "Ni siquiera la Capitana Beidou, a quien tan bien se le da leer a las personas, fue capaz antes de acogerlo de prever las increíbles habilidades con la espada de este joven tan tranquilo y veterano de tantas peleas.\n" +
                    "\n" +
                    "Es más, nadie sabe si su carácter real es el resultado de todas las cosas por las que ha pasado o si solo esconde su filo debido a su reservada personalidad."),
        PersonajeInfo("Wriothesley",
            R.drawable.wriothesley_086_gacha_splash,"\"Alcaide del Fuerte Merópide\".\n" +
                    "\n" +
                    "Si Wriothesley tuviera una tarjeta de presentación, eso sería lo único que pondría en ella.\n" +
                    "\n" +
                    "Sin palabrería innecesaria, supervisa ese taciturno lugar del fondo marino al que van los criminales desterrados.\n" +
                    "\n" +
                    "Como lugar de residencia de malhechores, y a pesar de la discreción del lugar, los conflictos de intereses del Fuerte Merópide son motivo de corrupción para muchos.\n" +
                    "\n" +
                    "En realidad, aunque alguien tramara entrar a escondidas en este sitio, desaparecería tan rápidamente como un trocito de pan en una sopa.\n" +
                    "\n" +
                    "Cuando alguien adula al Ilustrísimo Sr. duque por ser tan bueno resolviendo problemas, él deja en la mesa su taza de té y... agarra un periódico.\n" +
                    "\n" +
                    "\"No lo estás entendiendo bien. Esa gente solo quiere un lugar con normas en el que vivir. Yo simplemente les doy la tranquilidad que necesitan\"."),
        PersonajeInfo("Neuvillete",
            R.drawable.neuvillette_087_gacha_splash,"Neuvillette es una persona solitaria.\n" +
                    "\n" +
                    "Ha rechazado educadamente a todas y cada una de las personas de Fontaine que han intentando acercarse a él. Es más, a día de hoy, nadie sabe cuál es el nombre de pila del juez supremo porque él siempre le dice a los demás que lo llamen por su apellido, Neuvillette.\n" +
                    "\n" +
                    "Él considera que tener una relación cercana o un trato frecuente con alguien podría suscitar sospechas sobre su imparcialidad, y su deber es precisamente ser el símbolo de la imparcialidad absoluta.\n" +
                    "\n" +
                    "Sin embargo, siempre hay gente muy insistente que le dice: “Su señoría Neuvillette, si no todo el mundo tiene que comparecer ante un tribunal a lo largo de su vida, usted tampoco tiene por qué estar todos los días sentado en el asiento del juez”.\n" +
                    "\n" +
                    "Nunca sabremos si las cosas de verdad son así, ya que Neuvillette nunca se ha pronunciado con respecto a esa pregunta.\n" +
                    "\n" +
                    "Pero la realidad es que, aunque le lleve una enorme cantidad de tiempo, un río siempre acabará desbordándose, igual que el día del juicio final acabará cayendo algún día sobre la gente de Fontaine, ya que —y esto no es ni una metáfora ni una figura retórica— todas y cada una de las personas de Fontaine son culpables.\n" +
                    "\n" +
                    "Sin embargo, Neuvillette no le puede contar esto a nadie.\n" +
                    "\n" +
                    "Y por eso es una persona tan solitaria."),
        PersonajeInfo("Zhongli",
            R.drawable.zhongli_030_gacha_splash,"En las costumbres tradicionales de Liyue, \"recibir a los Adeptus\" y \"despedir a los Adeptus\" son igual de importantes.\n" +
                    "\n" +
                    "La familia Hu, encargada de dirigir la Funeraria El Camino durante 77 generaciones, es la mejor haciendo funerales. Sin embargo, Hu Tao, la actual directora de la Funeraria El Camino, se especializa en el arte de despedir a los mortales.\n" +
                    "\n" +
                    "Por eso, para las diversas ceremonias de despedida de los Adeptus, Hu Tao suele emplear la ayuda de un amigo que se dedica al mismo negocio que ella: Zhongli. Los Adeptus han convivido con la gente de Liyue durante milenios, pero solo un puñado ha ascendido en los últimos tres mil años, lo que significa que, actualmente, todo lo relacionado con estas tradiciones solamente existe en los textos. No es algo que se pueda ver dos veces en la vida.\n" +
                    "\n" +
                    "Ni siquiera el más exigente de los investigadores o académicos podría encontrar un error en los Ritos del Ascenso de la Funeraria El Camino.\n" +
                    "\n" +
                    "Todo debe ser perfecto: el vestuario, el tiempo, el lugar, los objetos, el clima, la duración, el tamaño de la audiencia permitida, la estatura, la profesión y la edad de dicha audiencia... Nada puede pasarse por alto.\n" +
                    "\n" +
                    "Cuando la gente común describe a Zhongli como una \"enciclopedia con patas\", él sonríe, suspira y dice:\n" +
                    "\n" +
                    "\"Yo... solo tengo buena memoria\"."),
        PersonajeInfo("Shogun Raiden",
            R.drawable.shougun_052_gacha_splash,"Desde los albores de la humanidad, la gente siempre ha sentido un gran anhelo y curiosidad por las cosas del mundo. Esta es la base de la cognición y el fundamento sobre el que se sustenta la razón.\n" +
                    "\n" +
                    "Para la gente de Inazuma, el mundo es así también. En esta nación, las tormentas y los rayos existen desde hace mucho tiempo, igual que existe la luz, el océano y... la Shogun Raiden.\n" +
                    "\n" +
                    "Cuando los niños se van a dormir, las madres suelen contarles la leyenda de cómo la Shogun derrotó a otros dioses y conquistó otros pueblos.\n" +
                    "\n" +
                    "Cuando los niños de Inazuma visitan otras islas, ven cañones creados mediante el corte de los rayos y los pálidos e imponentes huesos de una serpiente.\n" +
                    "\n" +
                    "Cuando los soldados van a la guerra, todos gritan \"¡gloria a la Shogun y a su imperio eterno!\"\n" +
                    "\n" +
                    "La gente con trabajos y vidas tranquilos se siente agradecida con la Shogun y los servicios del fiel Triunvirato.\n" +
                    "\n" +
                    "La reputación de la Shogun Raiden trascendió los límites de lo mortal hace mucho tiempo, y se convirtió en una eterna fe que se ha transmitido en Inazuma durante generaciones.\n" +
                    "\n" +
                    "Debido al prestigio y el poder de su Arconte, el pueblo de Inazuma tiene razones para creer que sus descendientes también verán los mismos paisajes y construirán su mundo en torno a esta fe, la cual seguirán transmitiendo a las siguientes generaciones.\n" +
                    "\n" +
                    "En esto precisamente consiste la utopía eterna que la Shogun le prometió a su pueblo."),
        PersonajeInfo("Arataki Itto",
            R.drawable.itto_057_gacha_splash,"Si paseas por las calles de Hanamizaka, seguramente te encontrarás con un joven oni llamado Arataki Itto.\n" +
                    "\n" +
                    "Dejando de lado sus llamativos cuernos de oni o su penetrante voz, el hecho de verlo jugando con gran entusiasmo con los más pequeños es suficiente para hacer que sobresalga entre la multitud.\n" +
                    "\n" +
                    "En cualquier caso, su absoluta ociosidad contrasta con los numerosos artesanos y las ajetreadas calles de Hanamizaka.\n" +
                    "\n" +
                    "Itto se llama a sí mismo el \"primer gran líder de la Banda de Arataki\". Tras una de sus muchas inofensivas peleas callejeras, le explicó en qué consistía su trabajo a unos funcionarios de la Comisión Tenryou que fueron a ver qué pasaba. Dos minutos después, los funcionarios le interrumpieron y lo registraron como \"desempleado\".\n" +
                    "\n" +
                    "Sin embargo, esta calificación no es del todo exacta, ya que, además del trabajo en su banda, el cual no es reconocido por el Shogunato, Itto también trabaja a tiempo parcial para subsistir. Además, trabaja con una frecuencia muy estable: ¡un día de trabajo y tres de descanso!\n" +
                    "\n" +
                    "Por eso, una calificación más precisa sería \"75% desempleado\"."),
        PersonajeInfo("Noelle",
            R.drawable.noel_034_gacha_splash,"A diferencia del resto de criadas normales y corrientes de los Caballeros, Noelle guarda en su corazón un sueño mucho más ambicioso.\n" +
                    "\n" +
                    "Al vivir en la ciudad que durante miles de años ha sido protegida por los Caballeros de Favonius, tanto ella como todos los jóvenes de Mondstadt ansían el día en que puedan ponerse esa honorable armadura.\n" +
                    "\n" +
                    "Aunque no tenga las aptitudes suficientes para pasar el exigente proceso de selección, lo único que busca Noelle es acercarse cada vez más a los Caballeros para aprender de ellos.\n" +
                    "\n" +
                    "Aparte de entrenar y estudiar, también disfruta de su vida en Mondstadt, yendo siempre de un sitio a otro para ayudar a todo aquel que necesite que le echen una mano.\n" +
                    "\n" +
                    "Su frase estrella es: \"¡Déjamelo a mí! ¡Yo me encargo!\". Por eso, si alguien se encuentra en apuros, basta con gritar su nombre y ella acudirá a ayudar gustosamente."),
    )}

    var personajeSeleccionado = PersonajeInfo("",1,"")

    listaPersonajes.forEach { item -> (
            if(nombre == item.nombre)
                personajeSeleccionado = item
            )}

    var mediaEstrellas = 0
    var totalEstrellas = 0
    var numeroEquipos = 0
    listaEquipos.forEach{ item -> (
            if(personajeSeleccionado.nombre == item.nombre) {
                totalEstrellas = totalEstrellas + item.estrellas
                mediaEstrellas = mediaEstrellas + item.estrellas
                numeroEquipos++
            }
            )}

    mediaEstrellas /= numeroEquipos

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp,20.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(personajeSeleccionado.nombre,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Image(painter = painterResource(id = personajeSeleccionado.imagen),
            contentDescription = "",
            modifier = Modifier.padding(0.dp,10.dp)
                )
        Text("Numero de estrellas conseguidas en total: "+totalEstrellas,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(0.dp,10.dp)
        )
        Text("Media de estrellas conseguidas: "+mediaEstrellas,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier.padding(0.dp,10.dp)
        )
        Text(personajeSeleccionado.descripcion,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        ExtendedFloatingActionButton(
            onClick = { navController.navigate(Rutas.JuegoGenshin.ruta)},
            icon = { Icon(Icons.Filled.ArrowBack, "Boton flotante de volver atras") },
            text = { Text(text = "Volver") },
            modifier = Modifier.padding(0.dp,10.dp)
        )
    }
}