package com.password.manager.core.encryption

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


object EncryptDecrypt {

    fun generateKey(): ByteArray {
        val random = SecureRandom()
        val tempKey = ByteArray(16)
        random.nextBytes(tempKey)
        return tempKey
    }

    fun encryptAES(plainText: String): Pair<String, String> {
        val key = generateKey()
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(key, "AES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        val encryptedBytes = cipher.doFinal(plainText.toByteArray())
        return Pair(
            Base64.encodeToString(encryptedBytes, Base64.DEFAULT),
            Base64.encodeToString(key, Base64.DEFAULT)
        )
    }

    fun decryptAES(encryptedText: String, key: ByteArray): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(key, "AES")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
        val encryptedBytes = Base64.decode(encryptedText, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }

}