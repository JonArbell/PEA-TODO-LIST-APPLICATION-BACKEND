export const sortByChangePage = async () =>{

    const sort = document.querySelector('#sort-container > select');
    
    let currentPath = null;

    const dev = 'http://localhost:8080';
    const prod = 'https://pea-todo-list-application.onrender.com';

    if(window.location.pathname.includes('/home')){
        currentPath = '/home';
    }else if(window.location.pathname.includes('/todays-tasks')){
        currentPath = '/todays-tasks';
    }else if(window.location.pathname.includes('/completed-tasks')){
        currentPath = '/completed-tasks';
    }else if(window.location.pathname.includes('/all-tasks')){
        currentPath = '/all-tasks';
    }else if(window.location.pathname.includes('/overdue-tasks')){
        currentPath = '/overdue-tasks';
    }else{
        return;
    }

    let url = null;
    switch(sort.value){

        case 'd-m':
            url = `${prod}${currentPath}`;
            break;
        case 'title':
            url = `${prod}${currentPath}/sort-by-title`;
            break;
        case 't-d':
            url = `${prod}${currentPath}/sort-by-target-date`;
            break;

    }

    window.location.href = url;

}

const changeSortValue = () =>{

    const sort = document.querySelector('#sort-container > select');

    const path = window.location.pathname;
    if(path.includes('/sort-by-title')){
        sort.value = 'title';
    }else if(path.includes('/sort-by-target-date')){
        sort.value = 't-d';
    }else{
        sort.value = 'd-m';
    }

}
document.addEventListener('DOMContentLoaded',changeSortValue);