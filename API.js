const express = require('express');
const { google } = require('googleapis');
const app = express();

// Set up YouTube Data API client
const youtubeClient = google.youtube({
  version: 'v3',
  auth: 'YOUR_YOUTUBE_API_KEY' // Replace with your YouTube Data API key
});

// API endpoint for retrieving video metadata
app.get('/video/:videoId', async (req, res) => {
  const videoId = req.params.videoId;

  try {
    // Retrieve video metadata using YouTube Data API
    const response = await youtubeClient.videos.list({
      part: 'snippet',
      id: videoId
    });

    // Extract relevant video details from the API response
    const videoDetails = response.data.items[0].snippet;
    
    // Return the video details to the client
    res.json({ videoDetails });
  } catch (error) {
    console.error('Error retrieving video metadata:', error);
    res.status(500).json({ error: 'Failed to retrieve video metadata' });
  }
});

// API endpoint for user authentication using Google Sign-In
app.post('/auth/google', async (req, res) => {
  const idToken = req.body.idToken;

  try {
    // Verify the Google Sign-In token using Google Sign-In API
    const ticket = await google.auth().verifyIdToken({
      idToken,
      audience: 'YOUR_GOOGLE_SIGN_IN_CLIENT_ID' // Replace with your Google Sign-In client ID
    });

    // Extract relevant user details from the token
    const user = ticket.getPayload();

    // Perform additional authentication logic or create a user account if necessary

    // Return the user details to the client
    res.json({ user });
  } catch (error) {
    console.error('Error authenticating user:', error);
    res.status(401).json({ error: 'Failed to authenticate user' });
  }
});

// Start the server
app.listen(3000, () => {
  console.log('Backend server for APIs and integrations is running on port 3000');
});
