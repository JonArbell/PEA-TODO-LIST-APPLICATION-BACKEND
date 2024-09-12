export const home = () => {
    fetch('http://localhost:8080/home', {
        method: 'GET',  
        credentials: 'include'  
    })
    .then(async response => {

        if(!response.ok){
            const error = await response.json();
            throw new Error(error.message);
        }
        return response.json();  
    })
    .then(data => {
        console.log(data);  
    })
    .catch(error => {
        console.error(error);
        // window.location.href = '../../../index.html';
    });
}

