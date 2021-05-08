package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  /*
  desacoplamiento
  Deberia estar en la clase parcela ya que añade otra responsabilidad a la clase planta
  */
  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

/*
Abstraccion
Para evitar pasar por parametro la condicion de transgenica a la soja
Se podria  implementar una clase SojaTransgenica que herede de Soja y use super en horasDeSolQueTolera
*/
class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {
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

  /*
  la funcion daSemillas es Redundante al utilizar un condicional para devolver un valor booleano, conviene retornar
  directamente el resultado de la operacion logica.
  Se reduce la Abstraccion ya que ademas de resolver la logica para determinar si da semillas tambien resuelve la condicion
  alternativa para que esto ocurra, que bien podria separarse en otra funcion y ganar reusabilidad.
  */
  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) {
      return false
    }

    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
