function home(){
    fetch('http://localhost:8080/home', {
        method: 'GET',  
        credentials: 'include'  
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();  
    })
    .then(data => {
        console.log(data);  
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
        // window.location.href = '../../../index.html';
    });
}


document.addEventListener('DOMContentLoaded',home);