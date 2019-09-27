package tech.sealsoft.crypto.model

data class MarketEntity(
    val exchangeId: String,
    val baseId: String,
    val quoteId: String,
    val baseSymbol: String,
    val quoteSymbol: String,
    val volumeUsd24Hr: Double,
    val priceUsd: Double,
    val volumePercent: Double
)