# Use a small official Node image
FROM node:20-alpine
 
# Create app directory
WORKDIR /usr/src/app
 
# Copy source
COPY app.js ./
 
# Expose port and run
EXPOSE 3000
CMD ["node", "app.js"]