package com.example.bankbranches.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class BankBranchesResponse(
    val bankBranchList: List<BankBranch>? = listOf()
): Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class BankBranch(
    @Json(name = "ID")
    val id: Int?,
    @Json(name = "dc_SEHIR")
    val sehir: String? = null,
    @Json(name = "dc_ILCE")
    val ilce: String? = null,
    @Json(name = "dc_BANKA_SUBE")
    val bankaSube: String? = null,
    @Json(name = "dc_BANKA_TIPI")
    val bankaTipi: String? = null,
    @Json(name = "dc_BANK_KODU")
    val bankKodu: String? = null,
    @Json(name = "dc_ADRES_ADI")
    val adresAdi: String? = null,
    @Json(name = "dc_ADRES")
    val adres: String? = null,
    @Json(name = "dc_POSTA_KODU")
    val postaKodu: String? = null,
    @Json(name = "dc_BOLGE_KOORDINATORLUGU")
    val bolgeKoordinatorlugu: String? = null,
    @Json(name = "dc_EN_YAKIM_ATM")
    val enYakinAtm: String? = null,
): Parcelable
