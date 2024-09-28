/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/authenticated/main/home.html",
  "./src/main/resources/templates/authenticated/main/test.html",
  "./src/main/resources/templates/index.html"],
  theme: {
    extend: {
       keyframes: {
            fadeOut: {
              '0%': { transform: 'translateX(0%)', opacity: '1' },
              '100%': { transform: 'translateX(37%)', opacity: '0' },
            },
       },
      animation: {
        fadeOut: 'fadeOut 7s ease-out forwards',
      },
      height: {
              "10vh": "10vh",
              "20vh": "20vh",
              "25vh": "25vh",
              "30vh": "30vh",
              "35vh": "35vh",
              "40vh": "40vh",
              "45vh": "45vh",
              "50vh": "50vh",
              "55vh": "55vh",
              "60vh": "60vh",
              "65vh": "65vh",
              "70vh": "70vh",
              "75vh": "75vh",
              "80vh": "80vh",
              "85vh": "85vh",
              "90vh": "90vh",
              "95vh": "95vh"
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
      backgroundColor : {
        "green-bg" : "#254336",
        "brown-bg" : "#2B2B2B",
        "white-bg" : "#d0cab5"
      },
      colors : {
        "white-color" : "#DDD9CA",
        "green-color" : "#182B22"
      },
      fontFamily : {
              montserrat: ['Montserrat', 'sans-serif'],
      },
      boxShadow : {
          'top-nav': '0 2px 3px black',
      }
    },
  },
  plugins: [

  ],
}

