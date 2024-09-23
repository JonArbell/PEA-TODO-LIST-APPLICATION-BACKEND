/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/authenticated/main/home.html",
  "./src/main/resources/templates/index.html"],
  theme: {
    extend: {
      height : {
        "10vh" : "10vh",
        "20vh" : "20vh",
        "30vh" : "30vh",
        "40vh" : "40vh",
        "50vh" : "50vh",
        "60vh" : "60vh",
        "70vh" : "70vh",
        "80vh" : "80vh",
        "90vh" : "90vh"
      },
      width : {
        "10vw" : "10vw",
        "20vw" : "20vw",
        "30vw" : "30vw",
        "40vw" : "40vw",
        "50vw" : "50vw",
        "60vw" : "60vw",
        "70vw" : "70vw",
        "80vw" : "80vw",
        "90vw" : "90vw"
      },
      backgroundColor :{
        "green-bg" : "#254336",
        "brown-bg" : "#2B2B2B",
        "white-bg" : "#d0cab5"
      },
      colors :{
        "green-color" : "#182B22",
        "white-color" : "#DDD9CA"
      },
      fontFamily: {
              montserrat: ['Montserrat', 'sans-serif'],
      },
      boxShadow: {
          'top-nav': '0 2px 3px black',
      }
    },
  },
  plugins: [

  ],
}

