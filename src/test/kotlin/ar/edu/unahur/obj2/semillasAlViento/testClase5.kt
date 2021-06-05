package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class testClase5 : DescribeSpec ({

    describe("Refactorizando Semillas") {
        //Variables
        //Plantas
        val plantaPruevaSojaSi = Soja(2017,10.0,true) //tolera 9 horas de sol
        val plantaPruevaSojaSiPetisa = Soja(1990,0.3,true) //tolera 6 horas de sol
        val plantaPruevaSojaNo = Soja(2010,0.5,false) //tolera 7 horas de sol
        val plantaPruevaSojaNoPetisa = Soja(2011,0.2,false) //tolera 6 horas, no es fuerte, no da semillas
        val plantaPruevaMenta = Menta(2000,10.0) //tolera 6 horas de sol
        val plantaPruevaMentaPetisa = Menta(1920,0.2) //tolera 6 horas de sol

        val plantaPruevaSojaDaSemillas = Soja(2018,20.0,false)
        val plantaPruevaSojaDaSemillas01 = Soja(2008,18.0,false)
        val plantaPruevaSojaDaSemillas02 = Soja(2009,16.0,false)
        val plantaPruevaSojaDaSemillas03 = Soja(2010,14.0,false)

        //Parcelas
        //parcelas ancho > largo
        val parcela1 = Parcela(2, 5, 7)
        val parcela2 = Parcela(2, 3, 4)
        val parcela3 = Parcela(3, 5, 9)
        // parcelas largo > ancho
        val parcela4 = Parcela(8, 5, 6)
        val parcela5 = Parcela(8, 2, 10)
        val parcela6 = Parcela(4, 3, 3)

        //Agricultoras
        val agricultora01 = Agricultora(mutableListOf(parcela1,parcela2))

        describe("Provando plantas Sojas: año, altura , horas de sol, es fuerte y si da o no nuevas semillas")  {
            it ("Provando soja que da Semillas"){
                plantaPruevaSojaDaSemillas.daSemillas() shouldBe true
                plantaPruevaSojaDaSemillas.esTransgenica shouldBe false
            }
            it("Probando soja transgenica") {
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

            it("una parcela con lugar que recibe 9 horas de sol permite plantar una planta que tolera 8 horas o mas"){
                parcela3.plantar(plantaPruevaSojaSi)
                parcela3.plantas.shouldContainExactlyInAnyOrder(plantaPruevaSojaSi)
            }
            it("una parcela con lugar que recibe 9 horas de sol no permite plantar una planta que tolera menos de 8 horas"){
                shouldThrowAny { parcela3.plantar(plantaPruevaMenta) }

            }
            it("parcela2 tiene el maximo de plantas, si se intenta agregar plantas debe arrojar un error") {
                shouldThrowAny { parcela2.plantar(plantaPruevaSojaNoPetisa) }
            }

        }
        describe("saber si una parcela tiene complicaciones") {
            parcela1.plantar(plantaPruevaSojaSi)
            parcela1.plantar(plantaPruevaSojaNo)

            it("una parcela que recibe 7 horas de sol no tiene complicaciones si sus plantas toleran al menos esa cantidad") {
                parcela1.parcelaTieneComplicaciones().shouldBeFalse()
            }
            it("una parcela que recibe 7 horas de sol tiene complicaciones si agrego una planta que tolera 6 horas de sol") {
                parcela1.plantar(plantaPruevaMenta)
                parcela1.parcelaTieneComplicaciones().shouldBeTrue()
            }
        }

        describe ("Agricultoras ") {
            it ("Provando metodo parcelas semilleras") {
                parcela1.plantar(plantaPruevaSojaDaSemillas)
                parcela1.plantar(plantaPruevaSojaDaSemillas01)
                parcela1.plantar(plantaPruevaSojaDaSemillas02)
                parcela1.plantar(plantaPruevaSojaDaSemillas03)
                agricultora01.parcelasSemilleras() shouldBe mutableListOf(parcela1,parcela2)

            }
            it ("Provando metodo plantar estrategicamente, Tiene que plantar en la parcela con mas lugar, El cual funciona mal porque nunca cambia de parcela "){
                parcela1.cantidadMaximaPlantas() shouldBe 8
                parcela2.cantidadMaximaPlantas() shouldBe 5

                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)
                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)
                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)

                parcela1.plantas.size shouldBe 3
                parcela2.plantas.size shouldBe 0

                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)
                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)

                parcela1.plantas.size shouldBe 4
                parcela2.plantas.size shouldBe 1

                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)
                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)

                parcela1.plantas.size shouldBe 5
                parcela2.plantas.size shouldBe 2

                agricultora01.plantarEstrategicamente(plantaPruevaSojaDaSemillas)

                parcela1.plantas.size shouldBe 6
                parcela2.plantas.size shouldBe 2

            }
        }

    }
})
