package tech.sealsoft.crypto.model

data class CoinEntity(val id: String,
                      val name: String,
                      val symbol: String,
                      val rank: Int,
                      val supply: Double,
                      val maxSupply: Double,
                      val priceUsd: Double
                      )
