package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()

  fun cantidadPlantas() = plantas.size

  fun parcelaTieneComplicaciones() =
    this.plantas.any { it.horasDeSolQueTolera() < this.horasSolPorDia }

  fun superficie() = ancho * largo

  fun cantidadMaximaPlantas() =
    if (ancho > largo) this.superficie() / 5 else this.superficie() / 3 + largo

  fun plantar(planta: Planta) {
    when {
        this.cantidadPlantas() == this.cantidadMaximaPlantas() -> {
          error("Ya no hay lugar en esta parcela")
        }
        horasSolPorDia > planta.horasDeSolQueTolera() + 2 -> {
          error("No se puede plantar esto acá, se va a quemar")
        }
        else -> {
          plantas.add(planta)
        }
    }
  }
  fun soySemillera() = this.plantas.all { it.daSemillas() }

}

class Agricultora(val parcelas: MutableList<Parcela>) {

  fun parcelasSemilleras() = parcelas.filter { it.soySemillera() }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas() }!!
    laElegida.plantar(planta)
  }
}
