# Use a lightweight web server image
FROM nginx:alpine

# Copy your project files into the web server's directory
COPY *lesson_01/joneemckellar/ /usr/share/nginx/html