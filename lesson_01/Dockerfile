# Simple Dockerfile for lesson_01
FROM node:20-alpine
WORKDIR /app
COPY . .
CMD ["echo", "Lesson 01 container ready!"]

# Use a lightweight web server image
FROM nginx:alpine

# Copy your project files into the web server's directory
COPY *lesson_01 /usr/share/nginx/html