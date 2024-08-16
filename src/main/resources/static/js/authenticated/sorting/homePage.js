function sortBy(){

    const select = document.querySelector('#sort');

    switch(select.value){
        case '1':
            window.location.href =`/pea/home`;
            break;
        case '2':
            window.location.href =`/pea/home/sort-by-title`;
            
            break;
        case '3':
            window.location.href =`/pea/home/sort-by-target-date`;
            break;

        default:

    }

   
}

document.addEventListener('DOMContentLoaded',()=>{

    const select = document.querySelector('#sort');

    switch(window.location.href){
        case 'http://localhost:8080/pea/home':
            select.value = '1';
            break;
        case 'http://localhost:8080/pea/home/sort-by-title':
            select.value = '2';
            break;
        case 'http://localhost:8080/pea/home/sort-by-target-date':
            select.value = '3';
            break;
        default:
            
    }
});



