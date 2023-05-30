interface VideoUploadService {
    @Multipart
    @POST("/upload")
    fun uploadVideo(
        @Part("title") title: RequestBody,
        @Part videoFile: MultipartBody.Part
    ): Call<ResponseBody>
}

// Usage
val file = File("path_to_video_file")
val requestFile = RequestBody.create(MediaType.parse("video/mp4"), file)
val body = MultipartBody.Part.createFormData("video", file.name, requestFile)

val title = RequestBody.create(MediaType.parse("text/plain"), "Video Title")

val service = retrofit.create(VideoUploadService::class.java)
val call = service.uploadVideo(title, body)
call.enqueue(object : Callback<ResponseBody> {
    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        // Handle upload success
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        // Handle upload failure
    }
})
