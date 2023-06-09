class DigitalSignature {

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    /**
     * set key generation with specifications (specific algorithms, purposes and digest)
     * setting up specifications like authorizes uses of the key, what operations are authorized, with what parameters and to what date.
     * the purpose of the keys is to be used in digital signature (sign, verify)
     */
    private fun generateKeyPair(): KeyPair {
        // get generator for keys
        val keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_EC, "AndroidKeyStore")
        // set specifications
        val keyPairSpecifications = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            KeyGenParameterSpec.Builder(
                Constants.alias,
                KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY or KeyProperties.PURPOSE_WRAP_KEY
            )
        } else {
            KeyGenParameterSpec.Builder(
                Constants.alias,
                KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
            )
        }.run {
            build()
        }
        // apply specifications
        keyPairGenerator.initialize(keyPairSpecifications)

        // generate key pair
        val keyPair = keyPairGenerator.genKeyPair()

        return keyPair
    }

    private fun generateDigitalSignature(): ByteArray {

        // get key entry
        val keyEntry = keyStore.getEntry(Constants.alias, null) as? KeyStore.PrivateKeyEntry

        // retrieve private key
        val privateKey = keyEntry?.privateKey ?: generateKeyPair().private

        // digest a data to a fixed-sized hash value
        val testFile = File("C:/Users/ikupc/dev/store/glep.txt")
        val fileByteArray = testFile.readBytes()

        val messageDigestAlgorithm = MessageDigest.getInstance("SHA-256")
        val digest = messageDigestAlgorithm.digest(fileByteArray)

        // sign digest with a private key
        val signatureAlgorithm = Signature.getInstance("SHA256withECDSA").apply {
            initSign(privateKey)
            update(digest)
        }
        val digitalSignature = signatureAlgorithm.sign()

        return digitalSignature
    }
}
