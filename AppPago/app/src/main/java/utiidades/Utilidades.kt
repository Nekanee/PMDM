package utiidades

fun calcularPorcentajeTotal(totalCuenta: Double, valorPorcentaje:Int): Double{
    return if(totalCuenta>1 && totalCuenta.toString().isNotEmpty())
        (totalCuenta*valorPorcentaje)/100
    else 0.0
}
fun calularTotalPorPersona(
    totalCuenta: Double,
    personas: Int,
    valorPorcentaje: Int):Double{
    val factura = calcularPorcentajeTotal(totalCuenta = totalCuenta,
        valorPorcentaje= valorPorcentaje)+ totalCuenta
    return (factura / personas)
}