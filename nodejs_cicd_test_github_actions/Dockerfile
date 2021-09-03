FROM node:13
WORKDIR /apiluis
COPY package.json /apiluis
RUN npm install
COPY . /apiluis

EXPOSE 3000

CMD ["node", "index.js"]