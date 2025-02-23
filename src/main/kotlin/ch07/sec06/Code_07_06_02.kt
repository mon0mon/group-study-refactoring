package ch07.sec06

data class TrackingInformation (
    val shippingCompany: String,
    val trackingNumber: String
) {
    fun display() = "$shippingCompany: $trackingNumber"
}

data class Shipment01(
    var trackingInformation: TrackingInformation
) {
    fun trackingInfo() = trackingInformation.display()
}

data class Shipment02(
    val shippingCompany: String,
    val trackingNumber: String
) {
    fun trackingInfo() = "$shippingCompany: $trackingNumber"
}
