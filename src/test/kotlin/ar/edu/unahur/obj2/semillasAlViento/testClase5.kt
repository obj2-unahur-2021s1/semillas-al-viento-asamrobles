package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class testClase5 : DescribeSpec ({

    describe("Refactorizando Semillas") {
        val plantaPruevaSojaSi = Soja(2017,10.0F,true) //tolera 9 horas de sol
        val plantaPruevaSojaSiPetisa = Soja(1990,0.3F,true) //tolera 6 horas de sol
        val plantaPruevaSojaNo = Soja(2010,0.5F,false) //tolera 7 horas de sol
        val plantaPruevaSojaNoPetisa = Soja(2011,0.2F,false) //tolera 6 horas, no es fuerte, no da semillas
        val plantaPruevaMenta = Menta(2000,10.0F) //tolera 9 horas de sol
        val plantaPruevaMentaPetisa = Menta(1920,0.2F) //tolera 6 horas de sol

        //parcelas ancho > largo
        val parcela1 = Parcela(2, 5, 7)
        val parcela2 = Parcela(2, 3, 4)
        val parcela3 = Parcela(3, 5, 9)

        // parcelas largo > ancho
        val parcela4 = Parcela(8, 5, 6)
        val parcela5 = Parcela(8, 2, 10)
        val parcela6 = Parcela(4, 3, 3)

        /*val plantaMadre = Planta(1910,35.0F)*/

        describe("Provando plantas Sojas: año, altura , horas de sol, es fuerte y si da o no nuevas semillas")  {

            it("probando soja transgenica") {
                plantaPruevaSojaSi.esTransgenica shouldBe true
                plantaPruevaSojaSi.altura shouldBe 10.0
                plantaPruevaSojaSi.horasDeSolQueTolera() shouldBe 18
                plantaPruevaSojaSiPetisa.horasDeSolQueTolera() shouldBe 12
                plantaPruevaSojaSi.esFuerte() shouldBe true
                plantaPruevaSojaSiPetisa.esFuerte() shouldBe true
                plantaPruevaSojaSiPetisa.daSemillas() shouldBe false
                plantaPruevaSojaSi.daSemillas() shouldBe false  //lo cambie x q la transgenica nunca da nuevas semillas
            }
            it("probando soja comun") {
                plantaPruevaSojaNo.esTransgenica shouldBe false
                plantaPruevaSojaNo.anioObtencionSemilla shouldBe 2010
                plantaPruevaSojaNo.horasDeSolQueTolera() shouldBe 7
                plantaPruevaSojaNo.esFuerte() shouldBe false
                plantaPruevaSojaNo.daSemillas() shouldBe false
            }

        }

        describe("Provando plantas Menta: año, altura , horas de sol, es fuerte y si da o no nuevas semillas") {
            plantaPruevaMenta.anioObtencionSemilla shouldBe 2000
            plantaPruevaMenta.altura shouldBe 10.0
            plantaPruevaMenta.horasDeSolQueTolera() shouldBe 6
            plantaPruevaMenta.esFuerte() shouldBe false
            plantaPruevaMenta.daSemillas() shouldBe true
            plantaPruevaMentaPetisa.daSemillas() shouldBe false
        }
        describe("Probando parcelas") {

            it("superficie de parcela1 debe ser 10") {
                parcela1.superficie() shouldBe 10
            }
            it("superficie de parcela2 debe ser 6") {
                parcela2.superficie() shouldBe 6
            }
            it("superficie de parcela3 debe ser 15") {
                parcela3.superficie() shouldBe 15
            }
        }
        describe("cantidad maxima de plantas que tolera una parcela") {

            it("la cantidad maxima de plantas para la parcela1 de ancho 2 y largo 5 debe ser 8") {
                parcela1.cantidadMaximaPlantas() shouldBe 8
            }
            it("la cantidad maxima de plantas para la parcela2 de ancho 2 y largo 3 debe ser 5") {
                parcela2.cantidadMaximaPlantas() shouldBe 5
            }
            it("la cantidad maxima de plantas para la parcela4 ancho 8 y largo 5 debe ser 8") {
                parcela4.cantidadMaximaPlantas() shouldBe 8
            }
            it("la cantidad maxima de plantas para la parcela5 de ancho 8 y largo 2 debe ser 3") {
                parcela5.cantidadMaximaPlantas() shouldBe 3
            }
        }
        describe("plantar en la parcela") {
            parcela2.plantar(plantaPruevaSojaSi)
            parcela2.plantar(plantaPruevaSojaNo)
            parcela2.plantar(plantaPruevaSojaSiPetisa)
            parcela2.plantar(plantaPruevaMenta)
            parcela2.plantar(plantaPruevaMentaPetisa)

            it("una parcela con lugar que recibe 9 horas de sol permite plantar una planta que tolera 8 o mas horas de sol"){
                parcela3.plantar(plantaPruevaSojaSi)
                parcela3.plantas.shouldContainExactlyInAnyOrder(plantaPruevaSojaSi)
            }
            it("una parcela con lugar que recibe 9 horas de sol no permite plantar una planta que tolera menos de 8 horas de sol"){
                parcela3.plantar(plantaPruevaMenta)
                parcela3.plantas.shouldNotContain(plantaPruevaMenta)
            }
            it("parcela2 no permite plantar mas de 5 plantas") {
                parcela2.plantar(plantaPruevaSojaNoPetisa)
                parcela2.cantidadPlantas shouldBe 5
            }

            /*
            No se puede realizar el test sobre el error que arrojaria la funcion plantar ya que imprime por pantalla un mensaje
            y no lanza un error que capturar.

            it("parcela2 tiene el maximo de plantas, si se intenta agregar plantas debe arrojar un error") {

                shouldThrowAny { parcela2.plantar(plantaPruevaSojaNoPetisa) }
            }
            */
        }
    }
})
