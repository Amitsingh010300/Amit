const express = require('express');
const mysql = require('mysql');
const app = express();

// Create a MySQL connection pool
const pool = mysql.createPool({
  host: 'YOUR_MYSQL_HOST',
  user: 'YOUR_MYSQL_USER',
  password: 'YOUR_MYSQL_PASSWORD',
  database: 'YOUR_MYSQL_DATABASE'
});

// API endpoint for storing user information
app.post('/user', (req, res) => {
  const { username, email, password } = req.body;

  // Insert user information into the database
  pool.query('INSERT INTO users (username, email, password) VALUES (?, ?, ?)', [username, email, password], (error, results) => {
    if (error) {
      console.error('Error inserting user:', error);
      return res.status(500).json({ error: 'Failed to store user information' });
    }

    res.json({ message: 'User information stored successfully' });
  });
});

// API endpoint for storing video metadata
app.post('/video', (req, res) => {
  const { title, description, videoUrl } = req.body;

  // Insert video metadata into the database
  pool.query('INSERT INTO videos (title, description, video_url) VALUES (?, ?, ?)', [title, description, videoUrl], (error, results) => {
    if (error) {
      console.error('Error inserting video metadata:', error);
      return res.status(500).json({ error: 'Failed to store video metadata' });
    }

    res.json({ message: 'Video metadata stored successfully' });
  });
});

// Start the server
app.listen(3000, () => {
  console.log('Backend server for video player data storage is running on port 3000');
});
