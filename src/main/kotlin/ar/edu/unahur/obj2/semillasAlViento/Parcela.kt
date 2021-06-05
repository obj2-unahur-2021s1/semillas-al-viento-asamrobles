package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  /*
  La variable cantidadPlantas se opone a la cualidad de Mutaciones Controladas y resulta Redundante
  ya que añade un cambio de estado interno innecesario que puede ser reemplazado calculando el tamaño
  de la lista plantas.
  */
  var cantidadPlantas = 0

  // Refactorizando Inicio

  fun parcelaTieneComplicaciones() =
    this.plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }


  //

  fun superficie() = ancho * largo

  /*
  La funcion cantidadMaximaPlantas afecta a la cualidad de Redundancia Minima por la repeticion
  de variables que se usan para calcular un valor ya resuelto en la funcion superficie.
  */
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  /*
  La funcion plantar disminuye la Abstraccion del diseño ya que las multiples condiciones pueden
  minimizarse implementando distintas funciones que resuelvan la logica y retornen la menor
  cantidad de valores booleanos posible.
  */
  fun plantar(planta: Planta) {
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  /*
  La variable ahorroEnPesos se opone a la cualidad de Mutaciones Controladas ya que añade un cambio de estado interno
  innecesario para el sistema y la resolucion de los requerimientos.
  */
  var ahorrosEnPesos = 20000

  /*
  La funcion comprarParcela rompe con la cualidad de Simplicidad ya que no esta permitida
  por una de las consignas del punto 4 y ademas añade complejidad innecesaria al diseño
  que no aporta a la solucion del problema.
  A su vez la funcion afecta la Redundancia Minima al introducir el valor
  de una parcela que va a repetirse en cada instancia de la clase ademas de dificultar
  su actualizacion, aumentando la posibilidad de cometer errores.
  */
  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }
  /*
  La funcion parcelasSemilleras disminuye la Abstraccion del diseño al resolver de manera compleja y poco legible
  uno de los requerimientos del punto 4 que podria dividirse en varias funciones y resolverlo por partes ganando
  reusabilidad y genericidad.
  */
  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }
  /*
  La funcion plantarEstrategicamente pierde Abstraccion al tener asignadas varias tareas a
  resolver que podrian dividirse en varias funciones.
  Disminuye el Desacoplamiento al depender de varios componentes de la clase Parcela que podrian resolver parte del
  problema dentro de ésta, por ej. con una funcion que calcule el lugar que le queda para plantar.
  Pierde Robustez al añadir una planta a una parcela sin verificar las condiciones necesarias para ello generando
  datos y comportamiento inconsistentes.
  */
  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
