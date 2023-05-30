// Send a notification to a specific user
val message = RemoteMessage.Builder(token)
    .setMessageId(messageId)
    .addData("title", "New video uploaded")
    .addData("body", "Check out the latest video on our app")
    .build()
FirebaseMessaging.getInstance().send(message)
