package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe

class testClase5 : DescribeSpec ({

    describe("Refactorizando Semillas") {
        val plantaPruevaSojaSi = Soja(2017,10.0F,true)
        val plantaPruevaSojaSiPetisa = Soja(1990,0.3F,true)
        val plantaPruevaSojaNo = Soja(2010,0.5F,false)
        val plantaPruevaMenta = Menta(2000,10.0F)
        val plantaPruevaMentaPetisa = Menta(1920,0.2F)

        /*val plantaMadre = Planta(1910,35.0F)*/

        describe("Provando plantas Sojas: año, altura , horas de sol, es fuerte y si da o no nuevas semillas")  {
            plantaPruevaSojaNo.anioObtencionSemilla shouldBe 2010
            plantaPruevaSojaSi.altura shouldBe 10.0
            plantaPruevaSojaSi.horasDeSolQueTolera() shouldBe 18
            plantaPruevaSojaSiPetisa.horasDeSolQueTolera() shouldBe 12
            plantaPruevaSojaNo.horasDeSolQueTolera() shouldBe 7
            plantaPruevaSojaSi.esFuerte() shouldBe true
            plantaPruevaSojaSiPetisa.esFuerte() shouldBe true
            plantaPruevaSojaNo.esFuerte() shouldBe false
            plantaPruevaSojaNo.daSemillas() shouldBe false
            plantaPruevaSojaSiPetisa.daSemillas() shouldBe false
            plantaPruevaSojaSi.daSemillas() shouldBe true

        }

        describe("Provando plantas Menta: año, altura , horas de sol, es fuerte y si da o no nuevas semillas") {
            plantaPruevaMenta.anioObtencionSemilla shouldBe 2000
            plantaPruevaMenta.altura shouldBe 10.0
            plantaPruevaMenta.horasDeSolQueTolera() shouldBe 6
            plantaPruevaMenta.esFuerte() shouldBe false
            plantaPruevaMenta.daSemillas() shouldBe true
            plantaPruevaMentaPetisa.daSemillas() shouldBe false
        }
    }
})