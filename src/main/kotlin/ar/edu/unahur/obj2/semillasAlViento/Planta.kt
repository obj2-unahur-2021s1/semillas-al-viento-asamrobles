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

/*
Abstraccion
Para evitar pasar por parametro la condicion de transgenica a la soja
Se podria  implementar una clase SojaTransgenica que herede de Soja y use super en horasDeSolQueTolera
*/
class Soja(anioObtencionSemilla: Int, altura: Double, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return if (esTransgenica) horasBase * 2 else horasBase
  }

  override fun daSemillas() =
    if (this.esTransgenica) {
      false
    }
    else this.daSemillasoNo()

  fun daSemillasoNo() = super.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
}
