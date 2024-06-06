package com.password.manager.data.model

import android.util.Base64
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.password.manager.core.encryption.EncryptDecrypt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "Password")
@Serializable
data class Password(
    @PrimaryKey(autoGenerate = true) @SerialName("id") val id: Long?,
    @SerialName("accountName") var accountName: String?,
    @SerialName("password") var password: String?,
    @SerialName("secretKey") var secretKey: String?,
    @SerialName("userNameOrEmail") var userNameOrEmail: String?
) {
    fun getDecryptPassword() = EncryptDecrypt.decryptAES(
        password ?: "",
        Base64.decode(secretKey, Base64.DEFAULT)
    )
}