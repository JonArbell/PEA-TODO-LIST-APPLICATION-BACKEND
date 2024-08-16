function sortBy(){

    const select = document.querySelector('#sort');

    switch(select.value){
        case '1':
            window.location.href =`/pea/all-tasks`;
            break;
        case '2':
            window.location.href =`/pea/all-tasks/sort-by-title`;
            
            break;
        case '3':
            window.location.href =`/pea/all-tasks/sort-by-target-date`;
            break;

        default:

    }

}


document.addEventListener('DOMContentLoaded',()=>{

    const select = document.querySelector('#sort');

    switch(window.location.href){
        case 'http://localhost:8080/pea/all-tasks':
            select.value = '1';
            break;
        case 'http://localhost:8080/pea/all-tasks/sort-by-title':
            select.value = '2';
            break;
        case 'http://localhost:8080/pea/all-tasks/sort-by-target-date':
            select.value = '3';
            break;
        default:
    }

});



