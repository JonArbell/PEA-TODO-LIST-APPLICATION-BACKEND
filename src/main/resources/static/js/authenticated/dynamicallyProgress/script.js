document.addEventListener("DOMContentLoaded", function() {
    const average = document.querySelector('#donut').dataset.average;

    const donut = document.querySelector('#donut');
    donut.style.background = `conic-gradient(
        #293b49 ${average}%,
        #808080 0
    )`;
    donut.querySelector('p').textContent = `${average}%`;
});