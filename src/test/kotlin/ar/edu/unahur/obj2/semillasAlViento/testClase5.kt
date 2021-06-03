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

        //parcelas ancho > largo
        val parcela1 = Parcela(2, 5, 7)
        val parcela2 = Parcela(3, 8, 4)
        val parcela3 = Parcela(3, 5, 9)

        // parcelas largo > ancho
        val parcela4 = Parcela(8, 5, 6)
        val parcela5 = Parcela(8, 7, 10)
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
                plantaPruevaSojaSi.daSemillas() shouldBe true
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
            it("superficie de parcela2 debe ser 40") {
                parcela2.superficie() shouldBe 24
            }
            it("superficie de parcela3 debe ser 15") {
                parcela3.superficie() shouldBe 15
            }
        }
        describe("cantidad maxima de plantas que tolera una parcela") {

            it("la cantidad maxima de plantas para la parcela1 de ancho 2 y largo 5 debe ser 8") {
                parcela1.cantidadMaximaPlantas() shouldBe 8
            }
            it("la cantidad maxima de plantas para la parcela2 de ancho 3 y largo 4 debe ser 4") {
                parcela2.cantidadMaximaPlantas() shouldBe 16
            }
            it("la cantidad maxima de plantas para la parcela4 ancho 8 y largo 5 debe ser ") {
                parcela4.cantidadMaximaPlantas() shouldBe 8
            }
            it("la cantidad maxima de plantas para la parcela5 de ancho 8 y largo 7 debe ser ") {
                parcela5.cantidadMaximaPlantas() shouldBe 11
            }
        }
    }
})
