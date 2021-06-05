package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Double) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10
  abstract fun horasDeSolQueTolera(): Int
  open fun daSemillas() = this.esFuerte()
}

class Menta(anioObtencionSemilla: Int, altura: Double) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = super.daSemillas() || altura > 0.4
}

class Soja(anioObtencionSemilla: Int, altura: Double, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    return if (esTransgenica) this.horasBase() * 2
            else this.horasBase()
  }

  fun horasBase() = when {
    altura < 0.5 -> 6
    altura < 1 -> 7
    else -> 9
  }

  override fun daSemillas() =
    if (this.esTransgenica) {
      false
    }
    else this.daSemillasoNo()

  fun daSemillasoNo() = super.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
}
