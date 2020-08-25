package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)

  val construccion = Certificacion(true,15)
  val mecanica = Certificacion(true,15)
  val indumentaria = Certificacion(true,15)
  val plomeria = Certificacion(false,10)
  val electricista = Certificacion(false,10)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)

    // agregando certificacion
    vendedorFijo.agregarCertificacion(construccion)
    vendedorFijo.agregarCertificacion(plomeria)

    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esVersatil") {
      it("NO es versatil como vendedor") {
        vendedorFijo.esVersatil().shouldBeFalse()
      }
      it("SI es versatil como vendedor") {
        vendedorFijo.agregarCertificacion(indumentaria)
        vendedorFijo.agregarCertificacion(mecanica)
        vendedorFijo.esVersatil().shouldBeTrue()
      }
    }

    describe("esInfluyente") {
      it("NO es influyente como vendedor") {
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(mutableListOf(misiones))

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }

    describe("esInfluyente") {
      it("NO es influyente como vendedor") {
        viajante.esInfluyente().shouldBeFalse()
      }
      it("SI es influyente como vendedor") {
        val buenosAires = Provincia(11000000)
        val viajante2 = Viajante(mutableListOf(buenosAires))
        viajante2.esInfluyente().shouldBeTrue()
      }
    }
  }

  describe("ComercioCorresponsal") {
    val cordoba = Provincia(4000000)
    val villaDolores = Ciudad(cordoba)
    val rioCuarto = Ciudad(cordoba)
    val casaGrande = Ciudad(cordoba)
    val cosquin = Ciudad(cordoba)
    val sanFrancisco = Ciudad(cordoba)
    val corresponsal = ComercioCorresponsal(listOf(villaDolores,rioCuarto,casaGrande,cosquin))

    // Ciudad: Chivilcoy, Bragado, Lobos, Pergamino y Zárate
    val buenosAires = Provincia(20000000)
    val chivilcoy = Ciudad(buenosAires)
    val bragado = Ciudad(buenosAires)
    val lobos = Ciudad(buenosAires)
    val pergamino = Ciudad(buenosAires)
    val zarate = Ciudad(buenosAires)

    // Rosario (Santa Fe), Rafaela (Santa Fe), San Francisco (Córdoba), y Diamante (Entre Ríos)
    val santaFe = Provincia(5000000)
    val rosario = Ciudad(santaFe)
    val rafael = Ciudad(santaFe)
    val entreRios = Provincia(4000000)
    val diamante = Ciudad(entreRios)

    // Rosario, Rafaela, Amstrong (Santa Fe) y Diamante
    val amstrong = Ciudad(santaFe)

    // agregando certificacion
    corresponsal.agregarCertificacion(construccion)
    corresponsal.agregarCertificacion(plomeria)

    describe("puedeTrabajarEn") {
      it("una ciudad donde tiene sucursal") {
        corresponsal.puedeTrabajarEn(rioCuarto).shouldBeTrue()
      }
      it("una ciudad donde NO tiene sucursal") {
        corresponsal.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esFirme") {
      it("NO es Firme como vendedor") {
        corresponsal.esFirme().shouldBeFalse()
      }
      it("SI es Firme como vendedor") {
        corresponsal.agregarCertificacion(electricista)
        corresponsal.esFirme().shouldBeTrue()
      }
    }

    describe("comercio corresponsal esInfluyente()") {
      it("Es esInfluyente como vendedor corresponsal1") {
        // Chivilcoy, Bragado, Lobos, Pergamino y Zárate: es influyente, se cumple la condición de 5 ciudades.
        val corresponsal1 = ComercioCorresponsal(listOf(chivilcoy,bragado,lobos,pergamino,zarate))
        corresponsal1.esInfluyente().shouldBeTrue()
      }
      it("Es esInfluyente como vendedor corresponsal2") {
        // Rosario (Santa Fe), Rafaela (Santa Fe), San Francisco (Córdoba), y Diamante (Entre Ríos): es infuyente, se cumple la condición de 3 provincias
        val corresponsal2 = ComercioCorresponsal(listOf(rosario,rafael,sanFrancisco,diamante))
        corresponsal2.esInfluyente().shouldBeTrue()
      }
      it("NO es esInfluyente como vendedor corresponsal3") {
        // Rosario, Rafaela, Amstrong (Santa Fe) y Diamante: no es influyente, son 4 ciudades y 2 provincias, no cumple ninguna de las condiciones.
        val corresponsal3 = ComercioCorresponsal(listOf(rosario,rafael,amstrong,diamante))
        corresponsal3.esInfluyente().shouldBeFalse()
      }
    }
  }

  // Centro de distribucion
  describe("centroDistribucion") {
    val obera = Ciudad(misiones)
    val cordoba = Provincia(4000000)
    val villaDolores = Ciudad(cordoba)
    val rioCuarto = Ciudad(cordoba)
    val casaGrande = Ciudad(cordoba)
    val cosquin = Ciudad(cordoba)
    val sanFrancisco = Ciudad(cordoba)

    // Ciudad: Chivilcoy, Bragado, Lobos, Pergamino y Zárate
    val buenosAires = Provincia(20000000)
    val chivilcoy = Ciudad(buenosAires)
    val bragado = Ciudad(buenosAires)
    val lobos = Ciudad(buenosAires)
    val pergamino = Ciudad(buenosAires)
    val zarate = Ciudad(buenosAires)

    // Rosario (Santa Fe), Rafaela (Santa Fe), San Francisco (Córdoba), y Diamante (Entre Ríos)
    val santaFe = Provincia(5000000)
    val rosario = Ciudad(santaFe)
    val rafael = Ciudad(santaFe)
    val entreRios = Provincia(4000000)
    val diamante = Ciudad(entreRios)

    // Rosario, Rafaela, Amstrong (Santa Fe) y Diamante
    val amstrong = Ciudad(santaFe)

    val vendedorFijo1 = VendedorFijo(obera)
    val viajante1 = Viajante(mutableListOf(misiones))
    val corresponsal1 = ComercioCorresponsal(listOf(villaDolores,rioCuarto,casaGrande,cosquin))
    val corresponsal2 = ComercioCorresponsal(listOf(rosario,rafael,sanFrancisco,diamante))
    val corresponsal3 = ComercioCorresponsal(listOf(rosario,rafael,amstrong,diamante))

    // agregando certificacion
    corresponsal1.agregarCertificacion(construccion)
    corresponsal1.agregarCertificacion(plomeria)

    corresponsal2.agregarCertificacion(construccion)
    corresponsal2.agregarCertificacion(plomeria)

    vendedorFijo1.agregarCertificacion(electricista)
    vendedorFijo1.agregarCertificacion(plomeria)
    vendedorFijo1.agregarCertificacion(mecanica)

    viajante1.agregarCertificacion(indumentaria)
    viajante1.agregarCertificacion(mecanica)

    /*
    val construccion = Certificacion(true,15)
    val mecanica = Certificacion(true,15)
    val indumentaria = Certificacion(true,15)
    val plomeria = Certificacion(false,10)
    val electricista = Certificacion(false,10)
    */

    val centro = CentroDistribucion(rosario)
    centro.agregar(vendedorFijo1) //45
    centro.agregar(viajante1)  //30
    centro.agregar(corresponsal2)  //25

    // el vendedor estrella, que es el que tiene mayor puntaje total por certificaciones.
    describe("CentroDistribucion vendedorEstrella()") {
      it("vendedor estrella") {
        centro.vendedorEstrella().shouldBe(vendedorFijo1)
      }
    }
    // si puede cubrir, o no, una ciudad dada. La condición es que al menos uno de los vendedores registrados pueda trabajar en esa ciudad.
    describe("CentroDistribucion puedeCubrir()") {
      it("NO puede cubrir la ciudad dada") {
        centro.puedeCubrir(lobos).shouldBeFalse()
      }
      it("SI puede cubrir la ciudad dada") {
        centro.puedeCubrir(rosario).shouldBeTrue()
      }
    }
    // la colección de vendedores genéricos registrados. Un vendedor se considera genérico si tiene al menos una certificación que no es de productos.
    describe("CentroDistribucion coleccionVendedoresGenericos()") {
      it("CentroDistribucion devuelve un vendedor generico") {
        centro.coleccionVendedoresGenericos().shouldBe(listOf(vendedorFijo1,corresponsal2))
      }
    }
    // si es robusto, la condición es que al menos 3 de sus vendedores registrados sea firme.
    describe("CentroDistribucion esRobusto()") {
      it("CentroDistribucion NO es robusto") {
        centro.esRobusto().shouldBeFalse()
      }
      it("CentroDistribucion SI es robusto") {
        val vendedorFijo2 = VendedorFijo(casaGrande)
        val vendedorFijo3 = VendedorFijo(diamante)
        centro.agregar(vendedorFijo2)
        centro.agregar(vendedorFijo3)
        vendedorFijo2.agregarCertificacion(indumentaria)
        vendedorFijo2.agregarCertificacion(mecanica)
        vendedorFijo2.agregarCertificacion(electricista)
        vendedorFijo3.agregarCertificacion(indumentaria)
        vendedorFijo3.agregarCertificacion(mecanica)
        vendedorFijo3.agregarCertificacion(construccion)
        centro.esRobusto().shouldBeTrue()
      }
    }
  }
})
