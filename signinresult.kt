// Sample code for handling the sign-in result
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if (requestCode == RC_SIGN_IN) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            // Authenticate the user with Firebase using the Google sign-in account
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener { authTask ->
                    if (authTask.isSuccessful) {
                        // User successfully logged in
                        val user = FirebaseAuth.getInstance().currentUser
                        // Save relevant user details to your app's backend
                    } else {
                        // Handle login failure
                    }
                }
        } catch (e: ApiException) {
            // Handle sign-in failure
        }
    }
}
