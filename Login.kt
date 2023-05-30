// Sample code for sign-up with phone number
val phoneNumber = "+91XXXXXXXXXX" // Replace with the user-provided phone number

val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
    .setPhoneNumber(phoneNumber)
    .setTimeout(60L, TimeUnit.SECONDS)
    .setActivity(this)
    .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // Automatically handle verification on trusted devices
            val auth = FirebaseAuth.getInstance()
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // User successfully signed up and logged in
                        val user = auth.currentUser
                        // Save relevant user details to your app's backend
                    } else {
                        // Handle sign-up failure
                    }
                }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // Handle verification failure
        }

        // Additional callbacks for handling other verification states
    })
    .build()

PhoneAuthProvider.verifyPhoneNumber(options)
