package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  /*
  La variable cantidadPlantas se opone a la cualidad de Mutaciones Controladas y resulta Redundante
  ya que añade un cambio de estado interno innecesario que puede ser reemplazado calculando el tamaño
  de la lista plantas.
  */
  var cantidadPlantas = 0

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
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
