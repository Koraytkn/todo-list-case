FROM node:18-alpine AS build

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install

COPY . .

RUN npm run build

FROM nginx:stable-alpine

ENV NGINX_PORT=80

COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE ${NGINX_PORT}

CMD ["nginx", "-g", "daemon off;"]
