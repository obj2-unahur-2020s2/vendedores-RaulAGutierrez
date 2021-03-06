package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
  // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
  // Además, a una MutableList se le pueden agregar elementos
  val certificaciones = mutableListOf<Certificacion>()


  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve.
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean // devuelve un tipo booleano

  // En las funciones declaradas con = no es necesario explicitar el tipo
  fun esVersatil() =
    certificaciones.size >= 3
      && this.certificacionesDeProducto() >= 1
      && this.otrasCertificaciones() >= 1

  // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
  fun agregarCertificacion(certificacion: Certificacion) { // agrega tipo tipo Certificacion
    certificaciones.add(certificacion)
  }

  fun esFirme() = this.puntajeCertificaciones() >= 30 // devuelve un tipo booleano

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto } // devuelve un tipo Integer
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto } // devuelve un tipo Integer

  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje } // devuelve un tipo Integer

  // vendedor influyente
  abstract fun esInfluyente(): Boolean  // devuelve un tipo Booleano

}

// En los parámetros, es obligatorio poner el tipo
class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {  // devuelve un tipo Booleano
    return ciudad == ciudadOrigen
  }
  // vendedor influyente
  override fun esInfluyente() = false  // devuelve un tipo Booleano
}

// A este tipo de List no se le pueden agregar elementos una vez definida
class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {  // devuelve un tipo Booleano
    return provinciasHabilitadas.contains(ciudad.provincia)
  }
  // vendedor influyente
  override fun esInfluyente(): Boolean {  // devuelve un tipo Booleano
    return provinciasHabilitadas.sumBy { provincia -> provincia.poblacion } >= 10000000
  }
}

class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {
  override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {  // devuelve un tipo Booleano
    return ciudades.contains(ciudad)
  }

  // vendedor influyente
  fun cantSucursalesEnCiudades() = ciudades.size  // devuelve un tipo Integer

  fun listaProvincias(): List<Provincia> {  // devuelve una lista tipo Provincia
    return (ciudades.map { ciudad -> ciudad.provincia }).toSet().toList()
  }

  fun cantSucursalesEnProvincias() = this.listaProvincias().size  // devuelve un tipo Integer

  override fun esInfluyente(): Boolean {  // devuelve un tipo booleano
    return this.cantSucursalesEnCiudades() >= 5 ||
            this.cantSucursalesEnProvincias() >= 3
  }

}
