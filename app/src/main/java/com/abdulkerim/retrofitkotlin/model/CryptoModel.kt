package com.abdulkerim.retrofitkotlin.model




data class CryptoModel(
  // @SerializedName("currency") tırnağın içindeki yazı ile jsondaki değişken ismi aynı olmalı.
    val currency: String,
   // @SerializedName("price") Ama tırnakdaki isim ile altındaki mor isim aynı ise Kotlinde annotion kullanmaya gerek yok
    val price : String
    )
