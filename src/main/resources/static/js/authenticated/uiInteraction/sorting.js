import { PageRequests } from "../index.js";

export const sortProcess = async () =>{

    const sort = document.querySelector('#sort-container > select');
    
    switch(sort.value){

        case 'd-m':
            sessionStorage.setItem('sortBy','d-m');
            break;
        case 'title':
            sessionStorage.setItem('sortBy','sort-by-title');
            break;
        case 't-d':
            sessionStorage.setItem('sortBy','sort-by-target-date');
            break;

    }
    
    await PageRequests.sortByRequest();

}
