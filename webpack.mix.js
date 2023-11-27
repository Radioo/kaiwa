let mix = require("laravel-mix");

mix.disableSuccessNotifications();

mix.setPublicPath("src/main/webapp/static");

// mix.sass("src/main/resources/web-assets/main.scss", "css/app.css");

mix.js("src/main/webapp/src/js/main.js", "js/app.js")
    .vue({version: 3});
