val player = ExoPlayerFactory.newSimpleInstance(context)
val mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
    .createMediaSource(Uri.parse("https://example.com/video.mp4"))
player.prepare(mediaSource)
player.playWhenReady = true
