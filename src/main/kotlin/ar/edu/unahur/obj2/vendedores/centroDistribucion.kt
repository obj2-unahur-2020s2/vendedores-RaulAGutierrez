package ar.edu.unahur.obj2.vendedores

class CentroDistribucion(var ciudad: Ciudad) {

    val vendedores = mutableListOf<Vendedor>() // lista de vendedores

    fun agregar(vendedor: Vendedor) {  // agrega un tipo Vendedor
        if (vendedores.contains(vendedor)) {
            println("El vendedor ya esta registrado")
        } else {
            vendedores.add(vendedor)
        }
    }

    fun vendedorEstrella(): Vendedor? { // devuelve un tipo Vendedor
        return vendedores.maxBy{ it.puntajeCertificaciones() }
    }

    fun puedeCubrir(unaCiudad: Ciudad): Boolean {  // devuelve un tipo Booleano
        return vendedores.any { vendedor -> vendedor.puedeTrabajarEn(unaCiudad) }
    }

    fun coleccionVendedoresGenericos(): List<Vendedor> { // devuelve una lista tipo Vendedor
        return (vendedores.filter{ vendedor -> vendedor.otrasCertificaciones() >= 1 }).toSet().toList()
    }

    fun vendedoresFirmes(): List<Vendedor> { // devuelve una lista tipo Vendedor
        return (vendedores.filter{ vendedor -> vendedor.esFirme() }).toSet().toList()
    }

    fun esRobusto(): Boolean { // devuelve un tipo Boolaano
        return this.vendedoresFirmes().size >= 3
    }

}